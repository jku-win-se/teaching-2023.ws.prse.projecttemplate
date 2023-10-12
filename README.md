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
