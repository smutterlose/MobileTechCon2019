# Android Workshop 

## UX & UI

Die folgenden Übungen zeigen den Umgang mit verschiedensten UI Elementen. 

### UI Constraints 

In der ersten Übung aus dem Bereich **UX & UI** wollen wir uns mit den Möglichkeiten der UI Constraints auseinander setzen und zu diesem Zweck die Darstellung der einzelnen ToDo-Items innerhalb der ToDo-Liste anpassen. 

> Beschreibung der Todos siehe: 
> 
> * res/layout/listitem_todo.xml 

### RecyclerView & Adapter 

In der zweiten Übung schauen wir uns den Umgang mit Listen in Kombination mit der **RecyclerView** und dem zugehörigen **Adapter** genauer an. 

Ziel ist es, einen eigenen **RecyclerView.Adapter** zu implementieren, welcher mit einer Liste von ToDo-Items verbunden ist und die Daten der einzelnen Items in seine **ViewHolder** Objekte überführt. Damit wir am Ende auch Daten sehen, muss der Adapter natürlich auch noch mit der RecyclerView verbunden werden. 

> Beschreibung der Todos siehe: 
> 
> * de.openknowledge.todo.TodoAdapter.java 
> * de.openknowledge.todo.MainActivity.java 


### Preferences (optional)

In der dritten (optionalen) Übung soll das Toolbar-Menu der App um zwei Einträge erweitert werden. Zum einen soll ein Eintrag zum Durchsuchen/Filtern der Liste eingefügt werden, welcher bei ausreichend Platz als Icon (Lupe) sichtbar ist. Zum anderen soll ein zweiter Eintrag - Help - im Overflow Bereich ergänzt werden. 

Der Klicklistener für die Menüeinträge soll in der Art ergänzt werden, dass er einen Toast mit Hinweis auf das eben angeklickte Menü-Item ausgibt. 

> Beschreibung der Todos siehe: 
> 
> * res/menu/menu_main.xml  
> * de.openknowledge.todo.MainActivity.java 
