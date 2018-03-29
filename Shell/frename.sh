#!/bin/bash 
# Hängt für alle Dateien im angegebenen Verzeichnis dir 
#die Zeichenkette string an den aktuellen Dateinamen an 
#(Umbenennung aller Dateien im Verzeichnis, Unterverzeichnisse bleiben unberührt). 
# Thorben Schomacker
# 19.02.2018

echo "Umbenennung im Verzeichnis: $1 durch anhaengen von $2 an den Dateinamen"

for entry in "$1"/*
do
	mv "./$entry" "$entry$2"
  echo "$entry wurde umbenannt in $entry$2"
done
