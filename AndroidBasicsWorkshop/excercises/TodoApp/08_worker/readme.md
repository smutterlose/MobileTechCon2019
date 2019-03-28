# Android Workshop 

## Worker 

Die folgende Übung zeigt die Verwendung des Android Worker Frameworks für zeitgesteuerte bzw. periodische asynchrone Kommunikation. 

### Synchronization Worker 

Ein Synchronization Worker soll regelmäßig, d.h. alle 15 Minuten, den aktuellen Stand der Todo Items via Web Service Call abfragen. 

Die Abfrage der Todo Items wird innerhalb des Workers selbst angestoßen. 

> Beschreibung der Todos siehe: 
> 
> * de.openknowledge.todo.SyncWorker.java

### Set Periodic Work Request on App Creation 

Die Konfiguration und Aktivierung des Synchronization Worker als periodischer Worker erfolgt innerhalb der Android Application Klasse App. 

Damit sichergestellt ist, dass das für den Call benötigte Netzwerk zur Verfügung steht, müssen zusätzlich die notwendigen Constraints gesetzt werden. 

> Beschreibung der Todos siehe: 
> 
> * de.openknowledge.todo.App.java
