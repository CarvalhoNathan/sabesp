document.addEventListener('DOMContentLoaded', () => {
    initCustomSelect();
    initCepLookup();
    initAddressAutocomplete();
    initGpsDetection();
    initPhotoUpload();
});

function initCepLookup() {
    const cepInput = document.getElementById('cep');
    const cepStatus = document.getElementById('cepStatus');
    const enderecoInput = document.getElementById('endereco');
    if (!cepInput || !enderecoInput) return;

    let ultimoCepConsultado = '';

    cepInput.addEventListener('input', async () => {
        const digits = cepInput.value.replace(/\D/g, '').slice(0, 8);
        cepInput.value = digits.length > 5 ? `${digits.slice(0, 5)}-${digits.slice(5)}` : digits;

        if (digits.length < 8) {
            cepStatus.textContent = '';
            return;
        }

        if (digits === ultimoCepConsultado) return;
        ultimoCepConsultado = digits;
        cepStatus.textContent = 'Buscando CEP...';

        try {
            const response = await fetch(`/api/cep/${digits}`);
            if (!response.ok) throw new Error('Falha na consulta');
            const data = await response.json();

            if (data.erro) {
                cepStatus.textContent = 'CEP não encontrado.';
                return;
            }

            const partes = [
                data.logradouro ? `${data.logradouro}, ` : '',
                data.bairro,
                data.localidade ? `${data.localidade}${data.uf ? '/' + data.uf : ''}` : ''
            ].filter(Boolean);

            enderecoInput.value = partes.join(' - ').replace(',  - ', ',  - ');
            cepStatus.textContent = 'Endereço preenchido! Digite o número após a vírgula.';
            enderecoInput.focus();
            if (data.logradouro) {
                const posNumero = data.logradouro.length + 2;
                enderecoInput.setSelectionRange(posNumero, posNumero);
            }
        } catch {
            cepStatus.textContent = 'Não foi possível consultar o CEP agora.';
        }
    });
}

function initAddressAutocomplete() {
    const enderecoInput = document.getElementById('endereco');
    const list = document.getElementById('enderecoSugestoes');
    const latInput = document.getElementById('latitude');
    const lngInput = document.getElementById('longitude');
    if (!enderecoInput || !list) return;

    let debounceTimer = null;
    let abortController = null;

    const hideList = () => {
        list.hidden = true;
        list.innerHTML = '';
    };

    const extrairNumeroDigitado = (texto) => {
        const match = texto.match(/\b(\d{1,5}[a-zA-Z]?)\b/);
        return match ? match[1] : '';
    };

    const formatarEndereco = (item, termoDigitado) => {
        const addr = item.address || {};
        const rua = addr.road || addr.pedestrian || addr.footway || '';
        const numero = addr.house_number || extrairNumeroDigitado(termoDigitado);
        const ruaComNumero = [rua, numero].filter(Boolean).join(', ');
        const bairro = addr.suburb || addr.neighbourhood || addr.city_district || '';
        const cidade = addr.city || addr.town || 'Santos';
        const principal = [ruaComNumero, bairro, cidade].filter(Boolean).join(' - ');
        return {
            principal: principal || item.display_name,
            completo: item.display_name
        };
    };

    enderecoInput.addEventListener('input', () => {
        const termo = enderecoInput.value.trim();
        clearTimeout(debounceTimer);

        if (termo.length < 4) {
            hideList();
            return;
        }

        debounceTimer = setTimeout(async () => {
            if (abortController) abortController.abort();
            abortController = new AbortController();

            try {
                const query = encodeURIComponent(termo);
                const url = `/api/enderecos?q=${query}`;
                const response = await fetch(url, { signal: abortController.signal });
                if (!response.ok) return;

                const results = await response.json();
                if (!Array.isArray(results) || results.length === 0) {
                    hideList();
                    return;
                }

                list.innerHTML = '';
                results.forEach((item) => {
                    const { principal, completo } = formatarEndereco(item, termo);
                    const li = document.createElement('li');
                    li.className = 'address-autocomplete-item';
                    li.setAttribute('role', 'option');
                    li.innerHTML = `<span>${escapeHtml(principal)}</span><small>${escapeHtml(completo)}</small>`;

                    li.addEventListener('click', () => {
                        enderecoInput.value = principal;
                        if (latInput && item.lat) latInput.value = Number(item.lat).toFixed(6);
                        if (lngInput && item.lon) lngInput.value = Number(item.lon).toFixed(6);
                        hideList();
                    });

                    list.appendChild(li);
                });

                list.hidden = false;
            } catch {
                // Ignora abortos de digitação rápida ou falha temporária de rede
            }
        }, 350);
    });

    document.addEventListener('click', (event) => {
        if (!enderecoInput.contains(event.target) && !list.contains(event.target)) {
            hideList();
        }
    });
}

function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

function initCustomSelect() {
    const wrapper = document.getElementById('customTipoSelect');
    const nativeSelect = document.getElementById('tipoProblemaSelect');
    if (!wrapper || !nativeSelect) return;

    const trigger = wrapper.querySelector('.custom-select-trigger');
    const label = document.getElementById('customSelectLabel');
    const options = wrapper.querySelectorAll('.custom-select-option');

    const syncSelected = (value) => {
        options.forEach((opt) => {
            const isSelected = opt.dataset.value === value;
            opt.classList.toggle('selected', isSelected);
            if (isSelected) {
                label.textContent = opt.dataset.label;
                label.classList.remove('placeholder');
            }
        });
    };

    if (nativeSelect.value) {
        syncSelected(nativeSelect.value);
    }

    trigger.addEventListener('click', () => {
        const isOpen = wrapper.classList.toggle('open');
        trigger.setAttribute('aria-expanded', String(isOpen));
    });

    options.forEach((opt) => {
        opt.addEventListener('click', () => {
            nativeSelect.value = opt.dataset.value;
            syncSelected(opt.dataset.value);
            wrapper.classList.remove('open');
            trigger.setAttribute('aria-expanded', 'false');
        });
    });

    document.addEventListener('click', (event) => {
        if (!wrapper.contains(event.target)) {
            wrapper.classList.remove('open');
            trigger.setAttribute('aria-expanded', 'false');
        }
    });
}

function initGpsDetection() {
    const btn = document.getElementById('btnDetectarGps');
    const status = document.getElementById('gpsStatus');
    const enderecoInput = document.getElementById('endereco');
    const latInput = document.getElementById('latitude');
    const lngInput = document.getElementById('longitude');

    if (!btn) return;

    btn.addEventListener('click', () => {
        if (!navigator.geolocation) {
            status.textContent = 'Geolocalização não suportada neste navegador.';
            return;
        }

        status.textContent = 'Obtendo localização...';
        btn.disabled = true;

        navigator.geolocation.getCurrentPosition(
            async (position) => {
                const { latitude, longitude } = position.coords;
                latInput.value = latitude.toFixed(6);
                lngInput.value = longitude.toFixed(6);

                try {
                    const url = `https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${latitude}&lon=${longitude}&accept-language=pt-BR`;
                    const response = await fetch(url);
                    if (response.ok) {
                        const data = await response.json();
                        const addr = data.address || {};
                        const rua = addr.road || addr.pedestrian || '';
                        const numero = addr.house_number || '';
                        const bairro = addr.suburb || addr.neighbourhood || addr.city_district || '';
                        const cidade = addr.city || addr.town || 'Santos';

                        const partes = [
                            [rua, numero].filter(Boolean).join(', '),
                            bairro,
                            cidade
                        ].filter(Boolean);

                        if (partes.length > 0) {
                            enderecoInput.value = partes.join(' - ');
                            status.textContent = 'Localização preenchida via GPS.';
                        } else {
                            status.textContent = `Coordenadas capturadas (${latInput.value}, ${lngInput.value}). Complete o endereço.`;
                        }
                    } else {
                        status.textContent = `Coordenadas capturadas (${latInput.value}, ${lngInput.value}).`;
                    }
                } catch {
                    status.textContent = `Coordenadas capturadas (${latInput.value}, ${lngInput.value}). Digite o endereço.`;
                } finally {
                    btn.disabled = false;
                }
            },
            () => {
                status.textContent = 'Não foi possível acessar o GPS. Verifique a permissão do navegador.';
                btn.disabled = false;
            },
            { enableHighAccuracy: true, timeout: 10000 }
        );
    });
}

function initPhotoUpload() {
    const dropzone = document.getElementById('uploadDropzone');
    const input = document.getElementById('fotosInput');
    const previews = document.getElementById('uploadPreviews');

    if (!dropzone || !input || !previews) return;

    const MAX_FILES = 5;
    const MAX_BYTES = 10 * 1024 * 1024;
    let selectedFiles = [];

    const syncInputFiles = () => {
        const dt = new DataTransfer();
        selectedFiles.forEach((file) => dt.items.add(file));
        input.files = dt.files;
        renderPreviews();
    };

    const addFiles = (files) => {
        for (const file of files) {
            if (selectedFiles.length >= MAX_FILES) {
                alert('Limite máximo de 5 fotos por solicitação.');
                break;
            }
            if (!['image/jpeg', 'image/png'].includes(file.type)) {
                alert(`Arquivo "${file.name}" ignorado: envie apenas JPG ou PNG.`);
                continue;
            }
            if (file.size > MAX_BYTES) {
                alert(`Arquivo "${file.name}" excede 10 MB.`);
                continue;
            }
            selectedFiles.push(file);
        }
        syncInputFiles();
    };

    const renderPreviews = () => {
        previews.innerHTML = '';
        selectedFiles.forEach((file, index) => {
            const item = document.createElement('div');
            item.className = 'preview-item';

            const img = document.createElement('img');
            img.alt = file.name;
            img.src = URL.createObjectURL(file);

            const removeBtn = document.createElement('button');
            removeBtn.type = 'button';
            removeBtn.className = 'preview-remove';
            removeBtn.title = 'Remover foto';
            removeBtn.textContent = '×';
            removeBtn.addEventListener('click', (e) => {
                e.stopPropagation();
                selectedFiles.splice(index, 1);
                syncInputFiles();
            });

            item.appendChild(img);
            item.appendChild(removeBtn);
            previews.appendChild(item);
        });
    };

    dropzone.addEventListener('click', () => input.click());

    input.addEventListener('change', () => {
        addFiles(Array.from(input.files));
    });

    dropzone.addEventListener('dragover', (e) => {
        e.preventDefault();
        dropzone.classList.add('dragover');
    });

    dropzone.addEventListener('dragleave', () => {
        dropzone.classList.remove('dragover');
    });

    dropzone.addEventListener('drop', (e) => {
        e.preventDefault();
        dropzone.classList.remove('dragover');
        addFiles(Array.from(e.dataTransfer.files));
    });
}
