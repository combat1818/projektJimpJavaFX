# Wireworld - JIMP2 2020

Projekt ten powstał na potrzeby zajęć JIMP2. Został w nim zaimplementowany Wireworld (https://en.wikipedia.org/wiki/Wireworld).

## Jak korzystać z programu? 

### Ograniczenia: 

- Rozmiar planszy powinien być conajmniej wielkości 5x5 i conajwyżej wielkości 70x70
- Liczba generacji powinna być liczbą naturalną
- Powinniśmy podawać pliki tekstowe do utworzenia animacji.
- Plik powinien być utworzony ściśle z <b>zasadami tworzenia takiego pliku (patrz sekcje poniżej)</b> 

### Jak powinien wyglądać plik wejściowy ? 

<img width="419" alt="Zrzut ekranu 2020-06-1 o 22 28 31" src="https://user-images.githubusercontent.com/48855027/83451506-5e559b00-a457-11ea-9a66-038341bf4e59.png">

#### 1 linia:

- Znajdują się tam 2 liczby odpowiadające kolejno za <b>szerokość</b> i <b>wysokość</b> planszy.
- Kolejne linie to interpretacja naszej planszy za pomocą cyfr z czego każda cyfra odpowiada za jeden "kwadrat" na naszej planszy.
- Przypomnijmy zasady dopierania tych liczb aby utworzyć obraz:

<b>0 (komórka czarna)</b> - pusta komórka </br>
<b>1 (komórka niebieska</b> - głowa  </br>
<b>2 (komórka czerwona)</b> - ogon  </br>
<b>3 (komórka żółta)</b> - przewodnik  </br>

#### Generalne zasady tworzenia danych komórek podczas animacji można znaleźć w linku na początku.


### Działanie programu

- Uruchomienie programu przebiega poprzez plik .jar, który znajduje się pod ścieżką:./out/artifacts/FirstJavaFXApp_jar/FirstJavaFXApp.jar
- Po uruchomieniu pokaże nam się nastepujące okno: 

<img width="798" alt="Zrzut ekranu 2020-06-1 o 22 14 48" src="https://user-images.githubusercontent.com/48855027/83450346-5dbc0500-a455-11ea-955f-db8a65148ea2.png">

#### Mamy 2 inputy:

- Po lewej: do wpisania relatywnej ścieżki do pliku:
np. ./gates.txt
- Po prawej: do wpisania liczby generacji które mają się wykonać

Po wpisaniu wartości powinniśmy je zatwierdzić odpowiednimi przyciskami.

Jeżeli wszystko poszło dobrze przycisk do animacji powinien zostać odblokowany: 

<img width="799" alt="Zrzut ekranu 2020-06-1 o 22 20 20" src="https://user-images.githubusercontent.com/48855027/83450732-1e41e880-a456-11ea-818f-30ea4acbf1de.png">

Po kliknięciu  <b>Start Animation</b> przechodzimy do 2 ekranu aplikacji: 

<img width="801" alt="Zrzut ekranu 2020-06-1 o 22 21 31" src="https://user-images.githubusercontent.com/48855027/83450828-47627900-a456-11ea-8ea2-a78e0d7e1891.png">

Jak widać na tym ekranie mamy <b>3 przyciski</b>:

- <b>Go back</b> - wraca do ekranu 1 gdzie możemy wpisać nowy plik. <b>Ważne!</b> Po powrocie jeśli chcemy zmienić plik powinniśmy też podać liczbę generacji.
- <b>Start</b> - rozpoczyna animacje
- <b>Stop</b> - zatrzymuje animacje na danej generacji
- <b>Next Generation</b> - pozwala przejść do kolejnej animacji( przechodzenie działa automatycznie jednak ta opcja jest po to gdy użytkownik naprzykład chce dokładniej obejrzeć generacje lub przeskoczyć kilka z nich)


