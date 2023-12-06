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


[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=prse-team2_fahrtenbuecher)
