/*
 * @author: Thorben Schomacker
 *
 */
#include <stdio.h>
void main(void) {
	char* filename = fgets(); 
	#https://stackoverflow.com/questions/2693776/removing-trailing-newline-character-from-fgets-input
	strtok(filename, "\n");
	file = creat(*/filename.txt);
	file.close();
	printf("Name der neuen Datei: %s\n", filename);
	printf("Die Datei bsp1 wurde erfolgreich angelegt!");
}
