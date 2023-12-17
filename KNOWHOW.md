# KNOW HOW - Progetto CampFinder

## Introduzione

Con questo documento voglio spiegare come ho configurato il progetto CampFinder, analizzando le tecnologie usate e la
struttura scelta per il progetto.

## Tecnologie

Le tecnologie utilizzate sono tutte incluse nel progetto con Maven tramite il file `pom.xml`. Facciamo una rapida
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

  Semplifica l'utilizzo di JPA ([Java Persistence API](https://spring.io/projects/spring-data-jpa)) per la gestione dei
  dati nel tuo progetto Spring Boot.
  Fornisce le dipendenze e le configurazioni necessarie.

- ##### H2 Database:

  È un database embedded che può essere configurato per l'uso in file o in memoria. Semplifica lo sviluppo e i test
  poiché permette di simulare un database senza la necessità di un'installazione separata. (In produzione verrà
  sostituito con un database relazionale).

- ##### Spring Boot Maven Plugin:

  Consente di confezionare il progetto Spring Boot come un JAR eseguibile.

## Struttura del progetto

Il progetto è organizzato secondo uno schema standard, seguendo le convenzioni di Spring Boot per garantire chiarezza e
facilità di manutenzione.

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

### 1. Directory Java

- ##### controller
  La directory controller contiene le classi responsabili di gestire le richieste HTTP e di orchestrare le chiamate ai
  servizi sottostanti.

- ##### entity
  La directory entity ospita le classi Java che rappresentano gli oggetti principali del dominio dell'applicazione.
  Queste classi sono utilizzate per mappare i dati dal database e definire la struttura delle entità.

- ##### repository
  La directory repository contiene le interfacce che definiscono le operazioni di accesso ai dati per le entità.

- ##### service
  La directory service contiene le classi che implementano la logica di business dell'applicazione. Qui vengono
  effettuate elaborazioni sui dati e interazioni con i repository.

- ### Directory Resources
    - ##### static
      La directory static contiene risorse statiche come file CSS, font e immagini utilizzate nell'interfaccia utente
      dell'applicazione.

    - ##### templates
      La directory templates contiene template di visualizzazione, tra cui gestione degli errori e frammenti
      riutilizzabili.

    - ##### application.yaml
      Il file `application.yaml` è il file di configurazione principale dell'applicazione, dove puoi impostare proprietà
      globali e configurazioni specifiche.

### 2. File di configurazione

- #### `application.yaml` (o in alternativa `application.properties`)
  É un file che contiene le di configurazioni principali dell'applicazione. Nel nostro caso viene usato per impostare il
  collegamento al database con JPA, le impostazioni della console di H2 e le configurazioni di JPA.

- #### `data.sql`
  É un file che viene eseguito dopo la creazione della base di dati. Serve per inizializzare il database H2 a scopo di
  test.
  Tramite l'impostazione `spring.jpa.defer-datasource-initialization: true` indichiamo a JPA di inizializzare il
  datasource automaticamente e successivamente verrà eseguito lo script SQL presente nel file.
  Nel caso in cui volessimo definire manualmente la struttura del datasource possiamo
  impostare `spring.jpa.defer-datasource-initialization: false` e definire, in aggiunta, il file `schema.sql`.

## Spring Boot e i Bean

In Spring Boot, un Bean è un oggetto gestito dal framework di Spring. È un componente software che viene stanziato,
assemblato e gestito da Spring IoC (Inversion of Control) container. Ecco una spiegazione di come funziona all'interno
di un progetto Spring Boot:

### Cos'è un Bean?

Un Bean in Spring Boot è una classe che viene gestita dal container IoC di Spring. Può rappresentare qualsiasi oggetto,
da un semplice oggetto di dati a un componente complesso. I Bean vengono dichiarati e configurati in modo da essere
gestiti da Spring.

### Creazione e Gestione di un Bean

#### Dichiarazione del Bean:

I Bean possono essere dichiarati in diverse parti del tuo progetto, ma comunemente vengono dichiarati attraverso
annotazioni come `@Component`, `@Service`, `@Repository`, o `@Controller`. Ad esempio:

```
@Component
public class MiaClasse {
// Implementazione della classe
}
```

#### Scansione dei Componenti:

Spring Boot, durante l'avvio dell'applicazione, esegue una scansione delle classi all'interno del tuo progetto per
individuare automaticamente i Bean dichiarati. Questo processo è noto come "component scanning".

#### Creazione del Bean:

Quando Spring rileva una classe con un'annotazione come `@Component`, crea un'istanza di quella classe e la gestisce
come
un Bean.

### Iniezione delle Dipendenze:

Un aspetto chiave di Spring è la gestione delle dipendenze. Se una classe A dipende da una classe B, Spring si occupa di
iniettare un'istanza di B nella classe A. Questo processo è noto come "dependency injection".

### Gestione del Ciclo di Vita del Bean

Spring Boot gestisce automaticamente il ciclo di vita dei Bean. Quando l'applicazione viene avviata, i Bean vengono
creati. Durante il ciclo di vita dell'applicazione, possono essere richiamati metodi specifici, come @PostConstruct per
l'inizializzazione e `@PreDestroy` prima della distruzione del Bean.

### Come Spring Boot Gestisce i Bean

Spring Boot utilizza un IoC container per gestire i Bean. Questo container è responsabile della creazione,
dell'inizializzazione, dell'assemblaggio e della gestione dei cicli di vita dei Bean. La configurazione dei Bean può
essere fatta attraverso annotazioni o tramite file di configurazione, come `application.yaml`
o `application.properties`.

Inoltre, Spring Boot semplifica la configurazione e l'uso di molti Bean comuni attraverso le "auto-configurazioni". Ad
esempio, se aggiungi `spring-boot-starter-data-jpa` alle tue dipendenze, Spring Boot fornirà automaticamente
configurazioni predefinite per il persistence context, l'EntityManager, e altre componenti JPA.

## Collegamento al Repository: JPA

Java Persistence API (JPA) è uno standard di programmazione Java che fornisce un framework per la gestione dei dati. La
sua utilità si manifesta in modo particolare in contesti come Spring Boot, dove diviene spesso parte integrante insieme
a Hibernate, un noto provider di persistenza. JPA, fondamentalmente, riduce notevolmente la complessità dello sviluppo
di applicazioni che coinvolgono database relazionali. Lo fa offrendo agli sviluppatori un modello di programmazione
orientato agli oggetti per manipolare i dati del database, eliminando la necessità di scrivere manualmente codice SQL
complesso. Questo approccio riduce drasticamente la quantità di codice che uno sviluppatore deve produrre, contribuendo
a una maggiore efficienza e facilitando la manutenzione del codice nel tempo.

- #### ORM (Object-Relational Mapping):

  JPA si basa sul concetto di ORM, che consente di mappare le classi Java direttamente alle
  tabelle del database. In pratica, le entità Java rappresentano le righe delle tabelle del database, semplificando il
  modo in cui il codice interagisce con i dati persistenti.

- #### Entity:

  Un'entità in JPA è una classe Java annotata con `@Entity` che rappresenta un oggetto persistente nel database. Le
  entità
  contengono campi che mappano le colonne della tabella e vengono utilizzate per eseguire operazioni CRUD sul database.

- #### EntityManager:

  L'EntityManager è l'interfaccia principale per l'interazione con JPA. Gestisce il ciclo di vita delle entità e
  fornisce metodi per eseguire operazioni sul database. L'EntityManager è responsabile di persistere le modifiche alle
  entità nel database, recuperare entità dal database e gestire le transazioni.

- #### Derived Query Methods:

  I Derived Query Methods sono una funzionalità che consente agli sviluppatori di definire metodi nei repository JPA
  seguendo una convenzione di denominazione specifica, grazie alla quale JPA genera dinamicamente le query SQL
  corrispondenti. Ad esempio, un metodo chiamato `findById` genererà automaticamente una query SQL per cercare
  un'entità in base al campo Id. Con questa tecnica si possono definire anche metodi che eseguono query SQL piuttosto
  complesse.

### Configurazione

In combinazione con Spring Boot, JPA rappresenta un modo semplice e veloce per configurare l'interazione con il
database.

#### 1. Configurazione del DataSource

Il primo passo è configurare il DataSource nel file `application.yaml`, definendo gli
attributi `spring.datasource.url`,`spring.datasource.username`, `spring.datasource.password`
e `spring.datasource.driverClassName`.

#### 2. Creazione di un'Entità

Successivamente, bisogna definire un'entità che rappresenti la tabella del database. L'entità è una classe in cui ogni
variabile rappresenta una colonna del database. Questa classe deve essere annotata con l'annotazione @Entity e può
essere migliorata con altre annotazioni come `@Id`, `@GeneratedValue`, `@Table`, `@Column` e altre che permettono
all'entità di riflettere nel migliori dei modi i dati rappresentati nel DB.

#### 3. Creazione di un Repository

Infine bisogna definire un'interfaccia annotata con l'annotazione `@Repository` che estenda `JpaRepository` per ogni
entità. Questo fornisce operazioni di base
come `save`, `findById` e `findAll`. Inoltre si può sfruttare la tecnica del Derived Query Methods per definire altre
funzioni.

## `@Service`: Logica di Business

La classe annotata con `@Service` rappresenta uno strato intermedio tra il controller e il repository. Qui, implementi
la logica di business dell'applicazione. Può coinvolgere la manipolazione dei dati, la logica aziendale e la
coordinazione delle operazioni tra i repository. L'annotazione `@Service` dichiara questa classe come un componente
gestito da Spring.

## `@Controller`: Gestione delle Richieste HTTP

La classe annotata con `@Controller` gestisce le richieste HTTP e orchestra le chiamate ai servizi sottostanti. I metodi
in questa classe possono essere annotati con `@GetMapping`, `@PostMapping`, ecc., per definire le operazioni associate
a ogni chiamata HTTP

## Thymeleaf: Template per le Pagine Web

Thymeleaf è un motore di template che semplifica la creazione di pagine web dinamiche. Utilizza la sintassi incorporata
nei file HTML per inserire dinamicamente dati provenienti dal backend. I template Thymeleaf possono accedere
direttamente agli oggetti gestiti da Spring, rendendo la visualizzazione dei dati più efficiente.

### Principali Keyword di Thymeleaf:

- `th:each`: Utilizzata per iterare su una collezione, ad esempio, una lista. Consente di ripetere un blocco di HTML per
  ogni elemento della collezione.

```
<ul>
    <li th:each="item : ${items}">${item}</li>
</ul>
```

- `th:if`, Th:unless, Th:else: Usate per condizionare l'inclusione di elementi HTML in base a una condizione.

```
<p th:if="${isAdmin}">Benvenuto, amministratore!</p>
<p th:unless="${loggedIn}">Effettua l'accesso per vedere il contenuto</p>
<p th:else>Benvenuto!</p>
```

- `th:text`: Sostituisce il contenuto di un tag con il valore della variabile o dell'espressione fornita.

```
<p>Il tuo saldo è: <span th:text="${balance}"></span> euro</p>
```

- `th:attr`: Aggiunge o modifica gli attributi di un tag HTML.

```
<a th:attr="href=${url}, title=${linkTitle}">Clicca qui</a>
```

- `th:src`, `th:href`: Gestiscono in modo dinamico gli attributi src e href.

```
<img th:src="@{/images/logo.png}" alt="Logo">
<a th:href="@{/home}">Torna alla home</a>
```

- `th:class`, `th:style`: Aggiungono o modificano dinamicamente classi CSS o stili.

```
<div th:class="${condition} ? 'active' : 'inactive'">Contenuto</div>
<span th:style="'color:' + ${textColor}">Testo colorato</span>
```

- `th:switch`, `th:case`, `th:default`: Permette di implementare uno switch-case per la selezione di blocchi di codice
  in base a un valore.

```
<div th:switch="${status}">
    <p th:case="'success'">Operazione completata con successo.</p>
    <p th:case="'error'">Si è verificato un errore.</p>
    <p th:default>Stato sconosciuto.</p>
</div>
```

- `th:inline`: Consente di incorporare espressioni Thymeleaf direttamente nel testo.

```
<p>L'utente attuale è <span th:inline="text">${user.name}</span></p>
```

- `th:include`: Include dinamicamente il contenuto di un altro file o frammento di template.

```
<div th:include="fragments/header :: header"></div>
```

- `th:replace`: Sostituisce l'intero contenuto di un elemento con il contenuto di un altro file o frammento di template.

```
<div th:replace="fragments/footer :: footer"></div>
```

- `th:fragment`: Definisce un frammento di template riutilizzabile all'interno di altri template.

```
<!-- Nel file fragments/header.html -->
<div th:fragment="header">
    <!-- Contenuto dell'header -->
</div>
```

- `th:block`: Un blocco di Thymeleaf che può essere utilizzato per raggruppare elementi HTML senza generare un elemento
HTML aggiuntivo.

```
<th:block th:each="item : ${items}">
<p th:text="${item}"></p>
</th:block>
```

- `th:with`: Introduce una variabile locale all'interno di un blocco specifico.

```
<div th:with="myVar=${someObject.property}">
    <p th:text="${myVar}"></p>
</div>
```

- `th:href` con `@{...}`: Utilizzata per costruire dinamicamente URL. È particolarmente utile quando si lavora con
  Spring Boot.

```
<a th:href="@{/path/{id}(id=${entityId})}">Dettagli</a>
```

## Riepilogo: le annotazioni principali

### Annotazioni di JPA (Java Persistence API):

- `@Entity`: Annota una classe come entità, rappresentando un oggetto persistente nel database.
- `@Table`: Specifica il nome della tabella del database associata a un'entità JPA.
- `@Id`: Indica il campo che funge da chiave primaria nella tabella del database.
- `@GeneratedValue`: Configura la generazione automatica dei valori per un campo, ad esempio, un ID auto-incrementato.
- `@Column`: Configura le proprietà di una colonna del database in un'entità JPA.

### Annotazioni di Spring Boot:

- `@SpringBootApplication`: Contrassegna una classe di configurazione principale di Spring Boot.
- `@Autowired`: Indica a Spring di iniettare automaticamente una dipendenza.
- `@Value`: Inietta un valore da un file di configurazione o da un'altra sorgente esterna.
- `@ConfigurationProperties`: Collega le proprietà dell'applicazione a una classe Java.
- `@ComponentScan`: Specifica i pacchetti da esaminare per i componenti Spring.
- `@EnableAutoConfiguration`: Abilita la configurazione automatica di Spring Boot.

### Annotazioni di Lombok:

- `@Data`: Genera automaticamente i metodi toString, equals, hashCode, i getter e i setter.
- `@NoArgsConstructor`, `@AllArgsConstructor`: Genera costruttori senza argomenti o con tutti gli argomenti.

### Annotazioni di Spring Web:

- `@Controller`: Indica che una classe è un controller Spring responsabile di gestire le richieste HTTP.
- `@RestController`: Combina `@Controller` e `@ResponseBody`, utile per restituire direttamente dati JSON.
- `@RequestMapping`: Specifica il percorso di base per le richieste gestite da un controller.
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: Mappano i metodi ai diversi tipi di richieste HTTP.
- `@PathVariable`: Estrae il valore da una variabile di percorso nel URL.