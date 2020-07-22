### Budowanie aplikacji:
Aby skompilować aplikację do pliku JAR należy:
0. Upewnić się że program gradle został zainstalowany lub zainstalować go na komputerze. https://gradle.org/install/
1. Uruchomić terminal i przejść do głównego katalogu `rekrutacja`.
2. Uruchomić polecenie `gradlew build`.
3. Upewnić się że plik o nazwie `demo-0.0.1-SNAPSHOT.jar` został wygenerowany w ścieżce `/build/libs/`.

### Uruchomienie aplikacji:
Aby uruchomić aplikację należy wygenerowany plik uruchomić za pomocą komendy `java -jar demo-0.0.1-SNAPSHOT.jar`.


###Obsługa aplikacji:
Aplikacja przyjmuje dane od użytkownika przez zapytanie HTTP.
Aby pobrać dane od użytkownika należy wywołać zapytanie z metodą GET, wyglądające następująco:
`http://{{host}}/numbers/{{metoda}}/{{number}}`, gdzie:
- **host** oznacza adres ip maszyny na której uruchomiono aplikację.
- **number** oznacza wartość pierwszej z liczb jaką chcemy użyć w operacji łączenia.
- **metoda** oznacza sposób uzyskania drugiej z liczb. Na ten moment przyjmuje wartość `csv` lub `random`, gdzie:
    - random oznacza pobranie z generatora liczb pseudolosowych.
    - csv oznacza pobranie z pliku umieszczonego w folderze `resources`.

Można to zrobić za pomocą dowolnej przeglądarki WWW. W przypadku używania IntellIJ można równierz wykorzystać przygotowany plik `generated-requests.http`. Należy jednak dostosować do własnego środowiska uruchomieniowego, JSONa z pliku `http-client.env.json`.
 
Drugim źródłem danych jest generator liczb pseudolosowych. W przypadku dodania kolejnych źródeł danych należy dodać wpis do klasy wyliczeniowej `DataSource`, a następnie dodać metodę generowania do `NumberProvider.getSuppilers()`.

Dodano również ładowanie wartości z pliku CSV. Plik ten należy umieścić w folderze `resources`, a następnie zmodyfikować dwa parametry w `application.properties:
-  **numbers.filename** -> nazwa pliku (rozszerzenie `.csv` jest opcjonalne w tym miejscu)
-  **numbers.valueseparator** -> separator użyty w pliku

