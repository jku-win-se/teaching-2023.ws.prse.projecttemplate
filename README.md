# Praktikum Software Engineering WS 2023

## GitHub Actions
### Was ist GitHub Actions?
GitHub Actions ist eine Funktion von GitHub, die es ermöglicht, automatisierte Workflows in den Software-Entwicklungsprozess zu integrieren.
Man kann viele Aufgaben automatisieren, die im Repository ausgeführt werden müssen. Diese Aufgaben gehen von Tests bis hin zu ständigen Bereitstellung der Anwendung.

Hauptmerkmale sind:
- Automatisierung: automatisiert wiederkehrende Aufgaben und Prozesse (spart Zeit und Arbeitsaufwand)
- CI/CD: Continuous Integration und Continuous Deployment, um sicherzustellen, dass der Code zuverlässig gebaut und verfügbar ist.
- Vielseitigkeit: Man kann nahezu jede Aufgabe automatisieren. Von Tests über Dokumentation bis hin zur Veröffentlichung.
- Ereignisgesteuert: GitHub Actions basiert auf Ereignissen, die im Repository auftreten. Sie können auftreten, wenn beispielsweise ein Issue erstellt wird.

Es ist eine effiziente Möglichkeit, hochwertigen Code zu erstellen, zu testen und bereitzustellen, ohne weitere CI/CD-Plattformen verwenden zu müssen. 
Erleichtert die Zusammenarbeit und den Entwicklungsprozess erheblich und man hat eine bessere Kontrolle über den Lebenszyklus eines Projekts. 

## Vorteile von GitHub Actions
- Automatisierung
- Kontinuierliche Integration und Bereitstellung
- Integration in GitHub-Workflow: da GitHub Actions in GitHub integriert ist, passt es nahtlos in einen vorhandenen Workflow
- Skalierbarkeit und Anpassbarkeit: GitHub Actions ist sehr flexibel und anpassbar, bedeutet man kann Arbeitsabläufe erstellen, die genau auf des jeweilige Projekt zugeschnitten sind.
- Effektivität: GitHub Actions fördert die Zusammenarbeit, da jeder auf den Status der Arbeitsabläufe zugreifen kann. Dies führt zu einer effizienten Zusammenarbeit.
- Zeitersparnis: Durch die Automatisierung kann man sehr viel Zeit sparen. Außerdem werden Fehler sehr bald erkannt und können so frühzeitig ausgebessert werden.

## Grundlegende Konzepte 
- Workflow: ist eine automatisierte Abfolge von Aktionen, die als Reaktion auf ein bestimmtes Ereignis ausgeführt wird.
- Actions: sind Bestandteile eines Workflows. Sie sind eigenständige Befehle, die in einem Workflow verwendet werden, um spezielle Aufgaben auszuführen. Es gibt einige vordefinierte Aktionen, man kann aber auch eigene erstellen.
- Events: sind die Auslöser, die zum starten eines Workflows führen. Beispiel hierfür ist das Erstellen eines Issues.
- Runner: ist die Umgebung, in der Aktionen eines Workflows ausgeführt werden. GitHub hat bereits virtuelle Runner, aber auch hier kann man wieder eigene erstellen.

## Apache Maven
### Overwiev
Apache Maven ist ein leistungsstarkes und weit verbreitetes Build-Automatisierungs-Tool (von den Entwicklern wird es als und Projektmanagement-Tool bezeichnet), das in erster Linie für Java-Projekte entwickelt wurde. Es läuft auf einer JVM und damit plattformunabhängig, kann aber auch Projekte in anderen Programmiersprachen verarbeiten.
Es hilft Entwicklern bei der Erstellung, Verwaltung und Bereitstellung von Softwareprojekten, indem es wiederholte Aufgaben wie das Kompilieren von Quellcode, das Verwalten von Abhängigkeiten und das Verpacken von Anwendungen in deploybare Artefakte (z.B. JAR, WAR) automatisiert.
### Wichtige Funktionen:
- Project Object Model (POM): Maven verwendet eine deklarative XML-Datei namens Project Object Model (POM), um die Struktur des Projekts, Abhängigkeiten und Konfigurationen zu definieren. Das POM dient als Blaupause des Projekts und erleichtert das Teilen und die Zusammenarbeit an Projekten. Darüber hinaus bietet das POM viele konfigurationsmöglichkeiten zu den einzelnen Abhängigkeiten (beispielsweise Scopes).
- Abhängigkeitsverwaltung: Maven vereinfacht die Verwaltung von Projektabhängigkeiten. Es kann Bibliotheken, Plugins und Frameworks automatisch herunterladen und verwalten, die für ein Projekt benötigt werden, wodurch der Build-Prozess vereinfacht wird. Diese Abhängigkeitsverwaltung hilft beispielsweise Kompatibilitätsprobleme verschiedener Versionen zu verhindern oder Abhängigkeiten einer genutzten Bibliothek zu vergessen.
- Build-Lebenszyklus: Maven definiert einen standardisierten Build-Lebenszyklus mit einer Reihe vordefinierter Phasen, wie z. B. Kompilieren, Testen, Verpacken und Bereitstellen. Dies ermöglicht Entwicklern die Ausführung von Aufgaben in einer konsistenten und organisierten Weise.
- Plugins: Mavens umfangreiches Ökosystem von Plugins bietet zusätzliche Funktionen und erweitert seine Fähigkeiten. Über die offiziellen und inoffiziellen plugins hinaus können Entwickler ebenso eigene Plugins für diverse Zwecke schreiben und aufgrund des modularen Aufbaus von Maven direkt einbinden.
- Remote Repositories: Das zentrale Repository (Central Repository) von Apache Maven ist ein umfangreiches Online-Repository von Open-Source-Bibliotheken und Artefakten. Entwickler können problemlos auf diese Abhängigkeiten zugreifen und sie in ihren Projekten verwenden, was den Bedarf an lokaler Speicherung von Bibliotheken reduziert. Es gibt zahlreiche weitere Repositories und ebenso die Möglichkeit eigene Repositories (beispielsweise mit firmeninternem Code) zu hosten und im Firmennetzwerk zur Verfügung zu stellen (oder aber auch online.
- Konvention über Konfiguration: Maven folgt dem Prinzip "Konvention über Konfiguration", was bedeutet, dass es auf sinnvollen Standardeinstellungen und Standardprojektstrukturen basiert. Dies verringert die Notwendigkeit für umfangreiche Konfiguration und fördert bewährte Praktiken.
- Integration: Maven kann nahtlos in verschiedene integrierte Entwicklungsumgebungen (IDEs) wie Eclipse, IntelliJ IDEA und NetBeans integriert werden. Dies ermöglicht eine reibungslose Entwicklungserfahrung.
- Konsistenz: Maven erzwingt eine konsistente Projektstruktur und einen einheitlichen Build-Prozess in verschiedenen Projekten, was es Entwicklern erleichtert, an Projekten zu arbeiten, die von anderen gepflegt werden.

### Häufige Anwendungsfälle:
- Build-Automatisierung: Maven automatisiert die Kompilierung, Tests und das Verpacken von Java-Projekten und erleichtert die Verwaltung und Bereitstellung von Software.
- Abhängigkeitsverwaltung: Es vereinfacht die Verwaltung von Projektabhängigkeiten und stellt sicher, dass die erforderlichen Bibliotheken ordnungsgemäß in das Projekt aufgenommen werden.
- Projektberichterstattung: Maven kann Berichte über den Projektstatus, die Code-Qualität und die Testabdeckung generieren, um Teams bei der Wahrung der Code-Qualität und der Überwachung des Projektfortschritts zu unterstützen.
- Releasemanagement: Maven hilft bei der Erstellung von Release-Builds, der Generierung von Dokumentation und der Bereitstellung von Software in Repositories zur Verteilung.
- Kontinuierliche Integration (CI): Es wird häufig in CI/CD-Pipelines eingesetzt, um den Build- und Testprozess zu automatisieren und sicherzustellen, dass Code-Änderungen keine Fehler einführen.

Zusammenfassend ist Apache Maven ein vielseitiges und weit verbreitetes Build-Tool, das den Entwicklungsprozess für Java- und andere Softwareprojekte vereinfacht. Sein Schwerpunkt auf Konventionen, Abhängigkeitsverwaltung und Automatisierung hilft Entwicklern, Konsistenz und Effizienz während des gesamten Projektlebenszyklus aufrechtzuerhalten.
