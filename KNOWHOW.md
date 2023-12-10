# KNOW HOW - Progetto CampFinder

## Introduzione

Con questo docuemento voglio spiegare come ho configurato il progetto CampFinder, analizzando le tecnologie usate e la
struttura scelta per il progetto.

## Tecnologie

Le teconologie utilizzate sono tutte incluse nel progetto con Maven tramite il file `pom.xml`. Facciamo una rapida
panoramica:

- ##### Spring Boot Starter Parent (3.2.0):

    È il genitore del progetto Spring Boot. Fornisce configurazioni di default e dipendenze comuni, semplificando il
processo di build e gestione del progetto.

- ##### Thymeleaf:

    Thymeleaf è un motore di template per la creazione di pagine web

- ##### Spring Boot Starter Web:

    Fornisce le dipendenze e le configurazioni di base per sviluppare applicazioni web con Spring Boot. Include un server
incorporato, semplificando la creazione di applicazioni web stand-alone.

- ##### Spring Boot DevTools:

    Semplifica il ciclo di sviluppo fornendo funzionalità come il riavvio automatico dell'applicazione durante lo sviluppo
senza la necessità di ricompilarla manualmente.

- ##### Lombok:

    Riduce la verbosity del codice Java, generando automaticamente metodi come getter, setter e costruttori. Migliora la
leggibilità del codice e riduce la quantità di codice boilerplate.

- ##### Spring Boot Starter Tomcat:

    Fornisce dipendenze e configurazioni necessarie per integrare il server Tomcat con Spring Boot. In questo caso, è
configurato con lo scope "provided", indicando che Tomcat sarà fornito dall'ambiente di esecuzione e non verrà incluso
nel pacchetto del tuo progetto.

- ##### Spring Boot Starter Test:

    Include dipendenze per i test unitari con Spring Boot.

- ##### Spring Boot Starter Data JPA:

    Semplifica l'utilizzo di JPA ([Java Persistence API](https://spring.io/projects/spring-data-jpa)) per la gestione dei dati nel tuo progetto Spring Boot.
Fornisce le dipendenze e le configurazioni necessarie.

- ##### H2 Database:
 
    È un database embedded che può essere configurato per l'uso in file o in memoria. Semplifica lo sviluppo e i test poichè permette di simulare un database senza la necessità di un'installazione separata. (In produzioene verra sostituito con un dtabase relaizonale).

- ##### Spring Boot Maven Plugin:

    Consente di confezionare il progetto Spring Boot come un JAR eseguibile.

## Struttura del progetto

Il progetto è organizzato secondo uno schema standard, seguendo le convenzioni di Spring Boot per garantire chiarezza e facilità di manutenzione.

```
src
│
└── main
├── java
│   └── com
│       └── tatonimatteo
│           └── campfinder
│               ├── controller
│               ├── entity
│               ├── repository
│               └── service
│
└── resources
├── static
│   ├── css
│   ├── font
│   └── image
│
├── templates
│   ├── error
│   └── fragments
│
└── application.yaml
└── data.sql
```

- ### Directory Java
  - ##### controller
    La directory controller contiene le classi responsabili di gestire le richieste HTTP e di orchestrare le chiamate ai servizi sottostanti.

  - ##### entity
    La directory entity ospita le classi Java che rappresentano gli oggetti principali del dominio dell'applicazione. Queste classi sono utilizzate per mappare i dati dal database e definire la struttura delle entità.

  - ##### repository
    La directory repository contiene le interfacce che definiscono le operazioni di accesso ai dati per le entità.

  - ##### service
    La directory service contiene le classi che implementano la logica di business dell'applicazione. Qui vengono effettuate elaborazioni sui dati e interazioni con i repository.

- ### Directory Resources
  - ##### static
    La directory static contiene risorse statiche come file CSS, font e immagini utilizzate nell'interfaccia utente dell'applicazione.

  - ##### templates
    La directory templates contiene template di visualizzazione, tra cui gestione degli errori e frammenti riutilizzabili.

  - ##### application.yaml
    Il file `application.yaml` è il file di configurazione principale dell'applicazione, dove puoi impostare proprietà globali e configurazioni specifiche.