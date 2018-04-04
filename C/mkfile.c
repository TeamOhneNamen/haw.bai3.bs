/*
 * @author: Thorben Schomacker
 *
 */
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
int main(void) {
	FILE *fp;
	int size = 30;
	char filename[size];
	int mode = 0700;

	fgets(filename, size, fp); 
	//https://stackoverflow.com/questions/2693776/removing-trailing-newline-character-from-fgets-input
	strtok(filename, "\n");
	creat(strcat(strcat("./", filename), ".txt"), mode);
	fclose(fp);
	printf("Name der neuen Datei: %s\n", filename);
	printf("Die Datei bsp1 wurde erfolgreich angelegt!");
	return(0);
}
