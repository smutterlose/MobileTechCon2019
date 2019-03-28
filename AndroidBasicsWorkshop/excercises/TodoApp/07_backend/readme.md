# Android Workshop 

## Backend vs. Frontend

Die folgend Übung zeigt die Anbindung Web Service basierten Backends an unsere ToDo App. Für den Aufruf des Web Services verwenden wir das Retrofit Framework. 

In dem Verzeichnis 

```
/webservice 
```

liegt ein passender Web Service, inkl. **readme** mit einer Anleitung zum Starten und Testen, bereit. 

### Repository Pattern 

Um innerhalb der UI Controller mit einer konsistenten API arbeiten zu können, haben wir ein Repository eingeführt, welches je nach Aufruf den Call gegen die lokale Datenbank oder gegen den entfernten Web Service delegiert. 

> Ein Blick in das Repository hilft dem Verständnis. 
> 
> * de.openknowledge.todo.domain.TodoRepository.java

### Web Service Client 

Mit Hilfe des Web Service Clients werden REST Calls gegen den ToDo Web Service abgesetzt. Hier gilt es die passenden Retrofit Annotationen zu ergänzen. 

Tip: ein Blick hinter die Kulissen von Retrofit kann sicherlich nicht schaden (https://square.github.io/retrofit/)

> Beschreibung der Todos siehe: 
> 
> * de.openknowledge.todo.domain.TodoWebServiceClient.java
> * de.openknowledge.todo.domain.TodoRepository.java
 
### Web Servcie Aufruf

Nun fehlt nur noch der Aufruf des Web Services via Refresh Button der Toolbar. 

> Beschreibung der Todos siehe: 
> 
> * de.openknowledge.todo.MainActivity.java



