package br.gov.sp.santos.semam.saneamento.service;

import br.gov.sp.santos.semam.saneamento.dto.NovoChamadoForm;
import br.gov.sp.santos.semam.saneamento.model.Chamado;
import br.gov.sp.santos.semam.saneamento.model.ChamadoComentario;
import br.gov.sp.santos.semam.saneamento.model.ChamadoFoto;
import br.gov.sp.santos.semam.saneamento.model.StatusChamado;
import br.gov.sp.santos.semam.saneamento.model.ZonaSantos;
import br.gov.sp.santos.semam.saneamento.repository.ChamadoRepository;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChamadoService {

    private static final int MAX_FOTOS = 5;
    private static final long MAX_TAMANHO_BYTES = 10L * 1024L * 1024L;
    private static final Set<String> CONTENT_TYPES_PERMITIDOS = Set.of("image/jpeg", "image/png");

    private final ChamadoRepository chamadoRepository;
    private final FotoStorageService fotoStorageService;

    public ChamadoService(ChamadoRepository chamadoRepository, FotoStorageService fotoStorageService) {
        this.chamadoRepository = chamadoRepository;
        this.fotoStorageService = fotoStorageService;
    }

    @Transactional
    public Chamado registrarChamado(NovoChamadoForm form) {
        String protocolo = gerarProtocolo();

        Chamado chamado = new Chamado();
        chamado.setProtocolo(protocolo);
        chamado.setEndereco(form.getEndereco().trim());
        chamado.setZona(inferirZonaPorEndereco(form.getEndereco()));
        chamado.setLatitude(form.getLatitude());
        chamado.setLongitude(form.getLongitude());
        chamado.setTipoProblema(form.getTipoProblema());
        chamado.setDescricao(form.getDescricao().trim());
        chamado.setStatus(StatusChamado.PENDENTE);

        List<MultipartFile> fotosValidas = filtrarFotosNaoVazias(form.getFotos());
        validarFotos(fotosValidas);

        for (MultipartFile arquivo : fotosValidas) {
            String urlStorage = fotoStorageService.salvarFoto(protocolo, arquivo);
            ChamadoFoto foto = new ChamadoFoto();
            foto.setNomeArquivo(arquivo.getOriginalFilename() != null ? arquivo.getOriginalFilename() : "foto.jpg");
            foto.setUrlStorage(urlStorage);
            foto.setContentType(arquivo.getContentType());
            foto.setTamanhoBytes(arquivo.getSize());
            chamado.adicionarFoto(foto);
        }

        return chamadoRepository.save(chamado);
    }

    @Transactional(readOnly = true)
    public List<Chamado> listarComFiltros(
            ZonaSantos zona,
            StatusChamado status,
            LocalDate dataInicio,
            LocalDate dataFim,
            String busca) {
        Specification<Chamado> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (zona != null) {
                predicates.add(cb.equal(root.get("zona"), zona));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if (dataInicio != null) {
                OffsetDateTime inicio = dataInicio.atStartOfDay().atOffset(ZoneOffset.ofHours(-3));
                predicates.add(cb.greaterThanOrEqualTo(root.get("criadoEm"), inicio));
            }
            if (dataFim != null) {
                OffsetDateTime fim = dataFim.plusDays(1).atStartOfDay().atOffset(ZoneOffset.ofHours(-3));
                predicates.add(cb.lessThan(root.get("criadoEm"), fim));
            }
            if (busca != null && !busca.isBlank()) {
                String termo = "%" + busca.trim().toLowerCase(Locale.ROOT) + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("protocolo")), termo),
                        cb.like(cb.lower(root.get("endereco")), termo),
                        cb.like(cb.lower(root.get("descricao")), termo)
                ));
            }

            return cb.and(predicates.toArray(Predicate[]::new));
        };

        return chamadoRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "criadoEm"));
    }

    @Transactional(readOnly = true)
    public Chamado buscarPorId(Long id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chamado não encontrado."));
    }

    @Transactional
    public void atualizarStatusEComentar(
            Long id,
            StatusChamado novoStatus,
            String textoComentario,
            String autorPerfil,
            String autorNome) {
        Chamado chamado = buscarPorId(id);
        StatusChamado statusAnterior = chamado.getStatus();

        boolean statusAlterado = novoStatus != null && novoStatus != statusAnterior;
        boolean temComentario = textoComentario != null && !textoComentario.isBlank();

        if (!statusAlterado && !temComentario) {
            throw new IllegalArgumentException("Altere o status ou escreva um comentário antes de salvar.");
        }

        if (statusAlterado) {
            chamado.setStatus(novoStatus);
        }

        ChamadoComentario registro = new ChamadoComentario();
        registro.setAutorPerfil(autorPerfil);
        registro.setAutorNome(autorNome);
        registro.setStatusAnterior(statusAnterior);
        registro.setStatusNovo(statusAlterado ? novoStatus : statusAnterior);
        registro.setComentario(temComentario
                ? textoComentario.trim()
                : "Status atualizado para " + novoStatus.getDescricao() + ".");

        chamado.adicionarComentario(registro);
        chamadoRepository.save(chamado);
    }

    @Transactional(readOnly = true)
    public long contarTodos() {
        return chamadoRepository.count();
    }

    @Transactional(readOnly = true)
    public long contarPorStatus(StatusChamado status) {
        return chamadoRepository.countByStatus(status);
    }

    private ZonaSantos inferirZonaPorEndereco(String endereco) {
        if (endereco == null) {
            return ZonaSantos.ORLA_INTERMEDIARIA;
        }
        String lower = endereco.toLowerCase(Locale.ROOT);
        if (lower.contains("centro") || lower.contains("valongo") || lower.contains("paquetá") || lower.contains("paqueta") || lower.contains("vila nova")) {
            return ZonaSantos.CENTRO_HISTORICO;
        }
        if (lower.contains("noroeste") || lower.contains("rádio clube") || lower.contains("radio clube") || lower.contains("chico de paula") || lower.contains("castelo") || lower.contains("areia branca") || lower.contains("bom retiro")) {
            return ZonaSantos.ZONA_NOROESTE;
        }
        if (lower.contains("morro") || lower.contains("nova cintra") || lower.contains("marapé") || lower.contains("marape") || lower.contains("são bento") || lower.contains("jabaquara")) {
            return ZonaSantos.MORROS;
        }
        if (lower.contains("caruara") || lower.contains("monte cabrão") || lower.contains("monte cabrao") || lower.contains("quilombo") || lower.contains("continental")) {
            return ZonaSantos.CONTINENTAL;
        }
        return ZonaSantos.ORLA_INTERMEDIARIA;
    }

    private List<MultipartFile> filtrarFotosNaoVazias(List<MultipartFile> fotos) {
        if (fotos == null) {
            return List.of();
        }
        return fotos.stream()
                .filter(f -> f != null && !f.isEmpty())
                .toList();
    }

    private void validarFotos(List<MultipartFile> fotos) {
        if (fotos.size() > MAX_FOTOS) {
            throw new IllegalArgumentException("Envie no máximo " + MAX_FOTOS + " fotos por solicitação.");
        }
        for (MultipartFile arquivo : fotos) {
            if (arquivo.getSize() > MAX_TAMANHO_BYTES) {
                throw new IllegalArgumentException("Cada foto deve ter no máximo 10 MB.");
            }
            String contentType = arquivo.getContentType();
            if (contentType == null || !CONTENT_TYPES_PERMITIDOS.contains(contentType.toLowerCase())) {
                throw new IllegalArgumentException("Formato inválido. Envie apenas imagens JPG ou PNG.");
            }
        }
    }

    private String gerarProtocolo() {
        String sufixo = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return "SAN-" + Year.now().getValue() + "-" + sufixo;
    }
}
