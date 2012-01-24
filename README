# Clear My Fridge

Sample Project for BFH Module SWOS


## Entwicklungsumgebung

### Tomcat

Nach dem Importieren der Projekte muss ein Tomcat für lokale Tests konfiguriert werden. In der SpringSource Toolsuite geht das wie folgt:

* Im "Servers" View: Rechtsklick -> New -> Server
* Tomcat 7 Server auswählen, hostname: localhost, Server name: egal, -> Next
* Im nächsten Dialog auf "Download and Install..." klicken, Installationsverzeichnis auswählen. Es kann eine Weile dauern, bis die Fehlermeldung ("invalid installation directory" oder so ähnlich) verschwindet, weil Eclipse den Tomcat erst installieren muss. -> Next
* Add and Remove: ch.clearmyfridge.web auswählen und "Add>" klicken -> Finish

### MySQL

Die Entwicklungsumgebung braucht eine MySQL Datenbank, die ihr wie gewohnt installieren könnt. 
Konfiguriert wird die Datenbank über das File ch.clearmyfridge.service/src/main/resources/jdbc.properties. Im Repository ist eine jdbc.properties.sample Datei, die ihr einfach kopieren und ggf. anpassen könnt. Wenn eure Datenbank-Installation den Sample Properties entspricht, braucht ihr natürlich nichts anzupassen.

### Test
Die Applikation sollte jetzt über http://localhost:8080/clearmyfridge/ erreichbar sein.

### User
Login funktioniert aktuell wie folgt: mit "admin" als Benutzername und Passwort loggt man sich als Admin ein, mit einem anderen Benutzernamen als normaler User. User müssen in der DB erfasst sein (ausgenommen der Admin), Passwort ist immer gleich dem Benutzernamen. Welche URL welche Rechte braucht, ist im web-application-context.xml festgelegt.
