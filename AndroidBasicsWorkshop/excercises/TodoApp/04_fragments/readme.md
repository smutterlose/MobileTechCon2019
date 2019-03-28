# Android Workshop 

## Fragments

Die folgend Übung zeigt den Umgang mit Fragments, sowie deren Verwendung zum adaptiven UI Design für Devices mit unterschiedlicehn Screen Capabilities, wie Smartphone und Tablet. 

### Mulit-Device UI

In der Übung **Mulit-Device UI** wollen wir den Unterschied zwischen **Activities** und **Fragments** verdeutlichen. Zu diesem Zweck stellen wir unsere App so um, dass sie sowohl auf dem Smartphone als auch auf einem Tablet eine optimale User Experience bietet. 

Während auf dem Smartphone entweder die Liste der ToDos oder alternativ Details eines einzelnen ToDo-Items angezeigt werden, wird auf dem Tablet beides parallel (nebeneinander) dargestellt. 

Innerhalb der Sourcen gilt es in einem ersten Schritt festzustellen, auf welcher Art von Device die App gerade läuft und bei einem Klick auf ein ToDo-Item innerhalb der Liste, entweder die gesamte UI auszutauschen (Smartphone) oder lediglich einen Teil davon (Tablet). 

> Beschreibung der Todos siehe: 
> 
> * de.openknowledge.todo.view.overview.TodoOverviewFragment.java

In einem zweiten Schritt muss nun noch die typensichere Kommunikation zwischen den einzelnen UIs  implementiert werden, d.h. die Übergabe eines ToDo-Items aus der Liste heraus an die Detailansicht. 

> Beschreibung der Todos siehe: 
> 
> * de.openknowledge.todo.view.detail.TodoDetailFragment.java
> * de.openknowledge.todo.view.overview.TodoOverviewFragment.java

