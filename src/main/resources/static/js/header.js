document.addEventListener("DOMContentLoaded", function () {
    const header = document.querySelector('header');
    const logo = document.getElementById('logo');
    const welcomeImage = document.querySelector('.welcome-image');
    const scrollToTopButton = document.querySelector(".scroll-to-top");

    toggleHeaderShadow();

    window.addEventListener('scroll', toggleHeaderShadow);

    if (scrollToTopButton) {
        scrollToTopButton.addEventListener("click", scrollToTop);
    }

    function scrollToTop() {
        window.scrollTo({top: 0, behavior: "smooth"});
    }

    function toggleHeaderShadow() {
        const isScrolled = window.scrollY > 0;
        const force = !welcomeImage;
        const isPageScrollable = document.documentElement.scrollHeight > window.innerHeight;

        header.classList.toggle('shadow', force || isScrolled);
        header.classList.toggle('scrolled', force || isScrolled);
        logo.classList.toggle('scrolled', force || isScrolled);

        scrollToTopButton.style.visibility = isPageScrollable && isScrolled ? 'visible' : 'hidden';
    }
});
