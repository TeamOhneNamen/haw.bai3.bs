/*
 * @author: Thorben Schomacker
 * @author: Ferdinand Trendelenburg
 *
 */
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
int main(void) {
	//Variablen initialisieren und einige deklarieren
	FILE *fp;
	int size = 30;
	char filename[size];
	int mode = 0700;
	
	//eigabe des Namens der neuen textdatei
	fgets(filename, size, fp); 
	//zeilenumbruch wegschneiden
	strtok(filename, "\n");
	//'./' und '.txt' anfügen und datei erstellen mit dem in mode gespeicherten berechtigungen
	creat(strcat(strcat("./", filename), ".txt"), mode);
	//datei schließen
	fclose(fp);
	//ausgaben
	printf("Name der neuen Datei: %s\n", filename);
	printf("Die Datei bsp1 wurde erfolgreich angelegt!");
	return(0);
}
