#!/bin/bash 
# Hängt für alle Dateien im angegebenen Verzeichnis dir 
#die Zeichenkette string an den aktuellen Dateinamen an 
#(Umbenennung aller Dateien im Verzeichnis, Unterverzeichnisse bleiben unberührt). 
# Thorben Schomacker
# Ferdinand Trendelenburg
# 19.02.2018

echo "Umbenennung im Verzeichnis: $1 durch anhaengen von $2 an den Dateinamen"

for entry in "$1"/*
do
	#umbenennen von "alerName" in "alerName" + das was angefügt werden soll
	mv "./$entry" "$entry$2"
  # Terminalausgabe
  echo "$entry wurde umbenannt in $entry$2"
done
