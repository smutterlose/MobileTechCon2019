# Android Workshop 

## Model & View

Die folgende Übung zeigt den Umgang mit Model, View und Controller sowie deren Zusammenspiel.  

### Database

In einem ersten Schritt wollen wir eine ToDo-Item Datenbank aufbauen und den Zugriff auf diese via DAO (Data Access Object) realisieren. Zu diesem Zweck müssen lediglich ein paar Annotationen an den dafür bereits implementierten Klassen ergänzt werden. 

> Beschreibung der Todos siehe: 
> 
> * de.openknowledge.todo.AppDatabase.java
> * de.openknowledge.todo.domain.Todo.java
> * de.openknowledge.todo.domain.TodoDao.java

### ViewModel

Im zweiten Schritt gilt es, das ViewModell mit der DB bzw. dem DAO zu verbinden, um so innerhalb der App Aufrufe aus der UI heraus an die Datenbank delegieren zu können. Innerhalb des ViewModels **TodoOverviewViewModel** muss dazu auf die Instanz der Datanbank zugegriffen und dort die Liste der gespeicherten ToDo-Items abgefragt werden. 

Damit aus der UI heraus später ein Zugriff auf diese Daten erfolgen kann, muss innerhalb des **TodoOverviewViewModel** zusätzlich eine Methode implementiert werden, welche die Liste der abgefragten ToDo-Items als LiveData bereitstellt. 

> Beschreibung der Todos siehe: 
> 
> * de.openknowledge.todo.view.overview.TodoOverviewViewModel.java

### Controller (Fragment) 

Im dritten und letzten Schritt der Übung soll nun die Verbindung zwischen View und ViewModel hergestellt werden. Zu diesem Zweck muss innerhalb des  **TodoOverviewFragment** auf das **TodoOverviewViewModel** zugegriffen und die dort verwaltete ToDo-Liste observed werden. 

> Beschreibung der Todos siehe: 
> 
> * de.openknowledge.todo.view.overview.TodoOverviewFragment.java


