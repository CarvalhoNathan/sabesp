document.addEventListener('DOMContentLoaded', () => {
    const gallery = document.getElementById('galeriaFotos');
    const overlay = document.getElementById('lightboxOverlay');
    if (!gallery || !overlay) return;

    const thumbs = Array.from(gallery.querySelectorAll('.photo-thumb'));
    if (thumbs.length === 0) return;

    const imgEl = document.getElementById('lightboxImg');
    const nameEl = document.getElementById('lightboxName');
    const counterEl = document.getElementById('lightboxCounter');
    const closeBtn = document.getElementById('lightboxClose');
    const prevBtn = document.getElementById('lightboxPrev');
    const nextBtn = document.getElementById('lightboxNext');

    let currentIndex = 0;

    const render = () => {
        const current = thumbs[currentIndex];
        imgEl.src = current.dataset.src;
        imgEl.alt = current.dataset.name || 'Foto do chamado';
        nameEl.textContent = current.dataset.name || 'Foto';
        counterEl.textContent = `${currentIndex + 1} / ${thumbs.length}`;

        const showNav = thumbs.length > 1;
        prevBtn.style.display = showNav ? 'inline-flex' : 'none';
        nextBtn.style.display = showNav ? 'inline-flex' : 'none';
    };

    const openAt = (index) => {
        currentIndex = (index + thumbs.length) % thumbs.length;
        render();
        overlay.hidden = false;
        document.body.style.overflow = 'hidden';
    };

    const close = () => {
        overlay.hidden = true;
        imgEl.src = '';
        document.body.style.overflow = '';
    };

    const step = (delta) => {
        if (thumbs.length <= 1) return;
        currentIndex = (currentIndex + delta + thumbs.length) % thumbs.length;
        render();
    };

    thumbs.forEach((btn, idx) => {
        btn.addEventListener('click', () => openAt(idx));
    });

    closeBtn.addEventListener('click', close);
    prevBtn.addEventListener('click', () => step(-1));
    nextBtn.addEventListener('click', () => step(1));

    overlay.addEventListener('click', (e) => {
        if (e.target === overlay) {
            close();
        }
    });

    document.addEventListener('keydown', (e) => {
        if (overlay.hidden) return;
        if (e.key === 'Escape') close();
        if (e.key === 'ArrowLeft') step(-1);
        if (e.key === 'ArrowRight') step(1);
    });
});
