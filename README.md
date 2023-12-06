# Praktikum Software Engineering WS2023 - “Digital Driver’s Logbook”

Entwicklung eines Fahrtenbuches als Desktop-Applikation.

## UML

UML Diagramm SQL-Tabellen

![alt text](https://github.com/jku-win-se/teaching-2023.ws.prse.braeuer.team2/blob/main/docs/UML_Tabellen.jpg?raw=true)



UML Diagramm Klassen

![alt text](https://github.com/jku-win-se/teaching-2023.ws.prse.braeuer.team2/blob/main/docs/UML_Java_Klassen.jpg?raw=true)

## Prototype 
https://www.figma.com/file/uS6ZQE29Ug4cUVFpfFBYfK/Prototyp-Fahrtenbuch?type=design&node-id=118%3A644&mode=design&t=eRd3X0ueWxJAVFvE-1

## Projektstruktur
### MYSQL Installation
#### Windows
https://dev.mysql.com/doc/refman/8.2/en/windows-installation.html
#### Mac
https://dev.mysql.com/doc/refman/8.0/en/macos-installation.html
### Download JDBC Connector
https://www.youtube.com/watch?v=whhSR0wlWQY
### Download JAVAFX SDK
https://gluonhq.com/products/javafx/
### Voraussetzung
JavaFX SDK; JDBC Connector; lokaler MySQL Server
### Installation
Unter Project Structure -> Libraries JAVAFX SDK hinzufügen und unter Project Structure -> Modules -> Dependencies -> Add jar den Mysql JDBC Connector hinzufügen. Anschließend kann das Programm mit der Main Klasse "LogbookApplication" im "fahrtenbuch" package mit Klick auf die Schaltfläche "Run" ausgeführt werden.


### Testdokumentation
#### Übersicht

Die Testklasse `TestBackup` enthält Tests für die Methoden zum Import und Export von Daten in der Datenbank durch die `DatabaseConnection`-Klasse. Diese Tests prüfen die Funktionalität der Methoden `exportDataToCSV()` und `importDataFromCSV()`.

#### Umgebung

- Sprache: Java
- Framework: JUnit
- Datenbank: MySQL

#### Testfälle

##### testExportData()

**Beschreibung:**
Dieser Test prüft, ob die Methode `exportDataToCSV()` Daten aus der Datenbank in CSV-Dateien exportiert.

**Schritte:**
1. Ausführung der Methode `exportDataToCSV()`.
2. Überprüfung, ob die erzeugten CSV-Dateien für Fahrzeuge (`vehicle.csv`), Kategorien (`category.csv`), Fahrten (`drive.csv`) und Kategorie-Fahrten-Beziehungen (`category_drive.csv`) existieren.

**Erwartetes Ergebnis:**
Alle CSV-Dateien werden erfolgreich erstellt.

##### testImportData()

**Beschreibung:**
Dieser Test prüft, ob die Methode `importDataFromCSV()` Daten aus CSV-Dateien in die Datenbank importiert.

**Schritte:**
1. Erzeugung von Beispiel-CSV-Dateien (`vehicle.csv`, `category.csv`, `drive.csv`, `category_drive.csv`).
2. Löschen bereits vorhandener Backup-Dateien, falls vorhanden.
3. Import der Daten aus den CSV-Dateien in die Datenbank.
4. Zählen der Zeilen in den entsprechenden Datenbanktabellen (`vehicle`, `drive`, `category`, `category_drive`).

**Erwartetes Ergebnis:**
- Die Anzahl der Zeilen in den Tabellen `vehicle`, `drive`, `category` und `category_drive` entspricht den erwarteten Werten nach dem Import.

#### Ausführung

Die Tests wurden mittels JUnit auf einer lokal eingerichteten Entwicklungsumgebung ausgeführt und erfolgreich validiert.

### Code Quality
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=prse-team2_fahrtenbuecher)
Sonarcube Testergebnis 6.12:
<img width="1100" alt="image" src="https://github.com/jku-win-se/teaching-2023.ws.prse.braeuer.team2/assets/32127275/2fc6adc5-0ad7-417e-a57b-b80307a2bc24">


