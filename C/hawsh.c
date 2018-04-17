/*
 * @author: Thorben Schomacker
 * @author: Ferdinand Trendelenburg
 *
 */
#include <unistd.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
int main(void) {
   char cwd[1024];
   if (getcwd(cwd, sizeof(cwd)) != NULL)
       fprintf(stdout, "Current working dir: %s\n", cwd);
   else
       perror("getcwd() error");
   return 0;
}
