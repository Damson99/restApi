# REST API - praktyczny projekt

Witaj! Niniejszy projekt zawiera materiały do kursu "REST API - praktyczny 
projekt".

Ten dokument zawiera wszystkie informacje niezbędne, aby zaznajomić się z 
materiałami i rozpocząć pracę nad projektem.

## Technologie

Projekt z materiałami napisany jest w języku Java w wersji **11**.

Projekt korzysta z Mavena, nie powinno być więc żadnych problemów z 
zaimportowaniem go do dowolnego IDE.

Przykłady korzystają ze Spring Boot **2.1.5**.

Testy napisane zostały w JUnit 5.

## Gałęzie Git

Każda lekcja ma odpowiadające jej gałęzie w systemie Git: stan na 
początek lekcji i na koniec. Każda taka gałąź ma nazwę w formacie 
**rozdział_lekcja_begin|end**, np. początek lekcji 4-tej rozdziału 6-tego 
znajduje się w gałęzi **6_4_begin** a jej koniec w **6_4_end**. Dzięki temu 
możesz pracować razem z kursem, eksperymentując ze swoim kodem a jednocześnie
 mieć możliwość, aby w każdej chwili przełączyć się na gałąź zawierającą 
 efekt finalny.
 
 ## Pozostałe pliki
 
 Wszystkie pozostałe pliki (np. materiały tekstowe) zawarte są w katalogu 
 **files** w głównym katalogu projektu.
 
 ## Wymagania
 JDK **11+**. Zmienna środowiskowa **PATH** powinna wskazywać na katalog z 
 instalacją JDK. W przeciwnym wypadku mogą być problemy z wykonaniem komend 
 programu Maven.
 
 ## Uruchamianie i testowanie projektu
 
 Aby uruchomić projekt wystarczy wykonać komendę programu Maven będąc w katalogu projektu:
 
 Gdy używasz systemu Linux `./mvnw spring-boot:run`.
 
 Gdy używasz systemu Windows `mvnw.cmd spring-boot:run`.
 
 Zauważ, że nie musisz nawet instalować programu Maven, bo znajduje się on w 
 wersji embedded wewnątrz projektu (wspomniane pliki `mvnw` i `mvnw.cmd`).
 
 Aby uruchomić wszystkie testy w projekcie, wystarczy wydać komendę z 
 katalogu projektu:
 
 Gdy używasz systemu Linux `./mvnw test`.
  
 Gdy używasz systemu Windows `mvnw.cmd test`.
 
 ## Jak mogę zweryfikować, czy projekt został poprawnie zaimportowany?
 
 Wystarczy, że uruchomisz testy w sposób opisany w sekcji dotyczącej 
 uruchamiania i testowania projektu. Jeśli wszystkie testy zakończyły się 
 sukcesem, oznacza to, że projekt został zaimportowany poprawnie. Możesz 
 także uruchomić testy ze swojego ulubionego IDE.
 
 Najczęstsze problemy dotyczą niepoprawnego zaimportowania projektu w swoim 
 IDE. W przypadku IntelliJ wybierz zakładkę **File->Open** i wskaż 
 **scieżkę do pliku pom.xml** wewnątrz katalogu projektu. Następnie wybierz 
 opcję "Open as Project". Warto także abyś miał włączoną opcję "Enable 
 auto-import" w ustawieniach Mavena, dzięki czemu projekt zostanie 
 automatycznie przebudowany gdy np. dodasz jakąś nową zależność.
 
 ## Zaczynamy
 
 Zacznij od pierwszej lekcji trzeciego rozdziału, tj. od gałęzi **3_1_begin**. 
 Życzę owocnej nauki!