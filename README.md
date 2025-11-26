# Instrucciones básicas
Sitúese en /out/


Comando:
javac --module-path C:\Java\javafx-sdk-25.0.1\lib --add-modules javafx.controls,javafx.fxml -d out .\src\*.java

java --module-path ..\Java\javafx-sdk-25.0.1\lib  --add-modules javafx.controls,javafx.fxml App

## Código fuente en src
### Backend
- Script.py
### Frontend
- App.java
- Mainscene.fxml
- MainsceneController.java


# REQUIREMENTS

REQUIERE javaFX
REQUIERE networkx

# Arquitectura:
Está compilado para windows, pero se puede compilar para linux siempre que se actualice el path (/ vs \\)