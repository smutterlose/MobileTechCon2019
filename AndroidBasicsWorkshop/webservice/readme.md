# Android Workshop 

## ToDo Web Service  

Der "ToDo Web Service" bietet eine API zur Verwaltung von Todo Items via RESTful Calls. 

### Web Service starten  

Zum Starten des Web Services einfach folgenden Befehl auf der Kommandozeile ausführen: 

```
java -jar todo-webservice.jar
```

Nach erfolgreichem Start des Web Services sollte folgende Ausgabe erscheinen: 

```
 ... 
 ... [org.jboss.as.server] (main) WFLYSRV0010: Deployed "api.war" (runtime-name : "api.war")
 ... [org.wildfly.swarm] (main) THORN99999: Thorntail is Ready
```

### Test Call 

Ob der Web Service auch tatsächlich das tut, was er tun soll, lässt sich mittels einfachem HTTP GET Call prüfen:

```
curl http://localhost:8080/api/todos
```

Das Ergebnis sollte ein http Response mit dem Status 200 (ok) sowie einigen Todo Einträgen innerhalb der http Payload sein: 

```json
[
    {
        "id": "c20a375e-7d54-4978-b5a2-02cc0968ab5a",
        "title": "Mother",
        "shortDescription": "Call my mother",
        "description": "It' s mothers birthday. So better do not forget to call her!",
        "todoType": "CALL"
     }, 
     ...
]
```

### API Dokumentation 

Dank OpenAPI Dokumentation ist unser ToDo Service introspektive. Ein Aufruf der API Doku URL verrät so einiges über den Service ...

```
http://localhost:8080/api/openapi-ui   // ToDo Service OpenAPI Web UI 
http://localhost:8080/openapi.         // ToDo Service OpenAPI YAML

```

