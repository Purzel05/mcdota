Folgende Grundlagen müsst Ihr mindestens verstehen:

* Dateien: Git ist ein Tool zur Verwaltung von unterschiedlichen Versionen von Dateien
* Commits: Geänderte Dateien (neue Dateiversionen) werden in Commits zusammengefasst
* Repositories: Commits werden in Repositories gespeichert, in einer festen Reihenfolge
* Branch: In Repositories kann es auch Branches geben, für uns aber erst mal nicht relevant, wir arbeiten nur auf dem "Master" Branch

Lokale Repositories und Remote Repository auf github:
* Repositories können gecloned werden und dann als Kopien an verschiedenen Orten liegen
* Wir haben für unser Projekt nur ein Repository "mcdota", dieses haben wir aber mehrfach ge-cloned
* Einmal liegt das Repository zentral auf github
* Ausserdem hat es jeder von uns lokal gecloned im Verzeichnis "mcdota"

Lokal gibt es 3 drei Bereiche:
* Das Working Directory: Wenn Ihr Dateien verändert liegen sie erst mal nur im Working Directory, sind noch nicht in einem Commit und noch nicht im Repository selbst!
* Die Stage: Die Stage ist ein Bereich in dem man veränderte Dateien sammelt um sie zu einem Commit zusammenzufügen
* Das lokale Repository und das Repository auf GitHub enthält Commits: eine Liste der gleichen Commits, aber eventuell zusätzlich Commits die das andere noch nicht hat

Wenn Ihr Dateien verändert habt müsst Ihr folgendes tun:
* Mit "git status" könnt Ihr immer sehen was Ihr grad im Working Directory habt, was auf der Stage, und welche Commits im Repository
* Wenn Ihr Dateien geändert habt sehr Ihr sie im "git status" rot dargestellt
* Wenn Ihr Dateien zu einem Commit zusammenfassen wollt, müsst Ihr diese Dateien zunächst auf die Stage packen mit "git add"
* zum Beispiel: 
"git add *" : alle veränderten Dateien
"git add /ein-directory/Ein-file.java" : nur diese Datei

* Dateien auf der Stage werden bei "git status" grün dargestellt
* Mit "git commit -m 'ein kommentar'" fasst Ihr alle Dateien auf der Stage zu einem neuen Commit zusammen
* Dieser Commit ist erst mal nur im lokalen Klon des Repository
* Mit "git push" schickt Ihr alle Commits, die es nur im Lokalen Repo aber noch nicht im zentralen Repo auf GitHub gibt, hoch zu GitHub
* Mit "git pull" holt Ihr alle Commits in's lokale Repo, die es nur auf GitHub gibt
* Wenn es im zentralen Repo neue Commits gibt, die Ihr lokale noch nicht habt, erlaubt Euch git keinen Push, bevor Ihr nicht einen Pull gemacht habt   
 
