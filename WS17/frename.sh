#!/bin/bash 
# Hängt für alle Dateien im angegebenen Verzeichnis dir 
#die Zeichenkette string an den aktuellen Dateinamen an 
#(Umbenennung aller Dateien im Verzeichnis, Unterverzeichnisse bleiben unberührt). 
# Thorben Schomacker
# 19.02.2018

echo Umbenennung im Verzeichnis: $1
echo Durch anhaengen von $2 an den Dateinamen

# Inhalt des aktuellen Arbeitsverzeichnisses als 
#Liste von Dateinamen ausgeben. Übergebene 
#Infos SPEC (Dateiname oder Verzeichnis) werden 
#verwendet

ls [-la] [SPEC]