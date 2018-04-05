/*
 * @author: Thorben Schomacker
 * @author: Ferdinand Trendelenburg
 *
 */
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
int main(void) {
	//Konstanten 
	const int size = 30;
	const int mode = 0700;
	char path[] = "/home/students/acf200/git/haw.bai3.bs/";
	//Variablen
	int fd;
	char name[size];


	
	//eigabe des Namens der neuen textdatei
	fgets(name, size, stdin); 
	//zeilenumbruch wegschneiden
	strtok(name, "\n");
	//'./' und '.txt' anfügen und datei erstellen mit dem in mode gespeicherten berechtigungen
	strcat(strcat(path, name), ".txt");
	fd = creat(path, mode);
	//datei schließen
	close(fd);
	//ausgaben
	printf("Name der neuen Datei: %s\n", name);
	printf("Die Datei bsp1 wurde erfolgreich angelegt!\n");
	return 0;
}
