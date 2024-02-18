document.addEventListener("DOMContentLoaded", () => {
    const welcomeImage = document.querySelector('.welcome-image')
    const firstSection = document.getElementById('section1')
    const secondSection = document.getElementById('section2')
    const thirdSection = document.getElementById('section3')

    initialActions()

    window.addEventListener('scroll', () => {
        const scroll = window.scrollY
        const windowHeight = window.innerHeight

        // Calcolo dei punti di rottura
        const firstSectionTop = firstSection.getBoundingClientRect().top
        const secondSectionTop = secondSection.getBoundingClientRect().top
        const thirdSectionTop = thirdSection.getBoundingClientRect().top

        // Aggiornamento dei punti di rottura in base allo scroll
        const breakpoint1 = firstSectionTop - windowHeight * 0.2
        const breakpoint2 = secondSectionTop
        const breakpoint3 = thirdSectionTop + windowHeight * 0.5

        // Mostra le sezioni quando lo scroll supera i punti di rottura
        if (scroll > breakpoint1) {
            showElement(firstSection)
        }
        if (scroll > breakpoint2) {
            showElement(secondSection)
        }
        if (scroll > breakpoint3) {
            showElement(thirdSection)
        }
    })

    function showElement(element) {
        element.classList.add("visited")
    }

    function initialActions() {
        showElement(welcomeImage)
    }
})
