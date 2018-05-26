/*
 * @author: Thorben Schomacker
 * @author: Ferdinand Trendelenburg
 *
 */
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <pwd.h>

/*
  Function Declarations for builtin shell commands:
 */
int hawsh_cd();
int hawsh_version();
int hawsh_help();
int hawsh_quit();


char *builtin_str_help[] = {
  "/:       zum aendern des verzeichnisses",
	"help:    gibt dir alle commands aus",
	"version: gibt die aktuelle versionsnummer und autoren zurueck",
  "quit:    beendet die HAW-SHELL",
  '\0'
};

int hawsh_cd(char *args)
{
  printf("%c", args[1]);
  if (&args[1] == NULL) {
    fprintf(stderr, "hawsh: expected argument to \"cd\"\n");
  } else {
    if (chdir(&args[1]) != 0) {
      perror("hawsh");
    }
  }
  return 1;
}

int hawsh_version()
{
  int i;
  printf("Schomacker & Trendelenburg\n");
  printf("HAW Shell 1.0\n");
}

int hawsh_help()
{
  int i;
  printf("HAW Shell\n");
  printf("Type program names and arguments, and hit enter.\n");
  printf("The following are built in:\n");

  for (i = 0; i < sizeof(builtin_str_help); i++) {
    if(builtin_str_help[i]==NULL) return 1;
    printf("  %s\n", builtin_str_help[i]);
  }

  printf("Use the man command for information on other programs.\n");
  return 1;
}

int hawsh_quit()
{
  exit(EXIT_SUCCESS);
}

int hawsh_split_line(char *line)
{
  char newline[sizeof(line)];
  char newlinecpy[sizeof(line)];
  char newlinecpy2[sizeof(line)];
  char delimiter[] = " ";
  char *wort;
  char *token;
  char *args[10];
  int i=0;
  int position = 0;
	char last_letter;
	int command_size; 

  strncpy(newline, line, sizeof(line));
  strncpy(newlinecpy, line, sizeof(line));
  strncpy(newlinecpy2, line, sizeof(line));

  wort = strtok(newline, delimiter);

  token = strtok(newlinecpy2, delimiter);
   while (token != NULL) {
    args[position] = token;
    position++;
    token = strtok(NULL, delimiter);

  }
  args[position+1] = "\0";

  if(strcmp(newlinecpy, "help")==0){
    hawsh_help();
  }else if(strcmp(newlinecpy, "version")==0){
    hawsh_version();
  }else if(strcmp(wort, "/")==0){
    hawsh_cd(strpbrk(newlinecpy, delimiter));
  }else if(strcmp(newlinecpy, "quit")==0){
    hawsh_quit();
  }else{
	//Externes Programm straten funktioniert nicht!
	printf("starte externes program ");
	last_letter = args[0][7];
	command_size = sizeof(args[0]);
	printf("last letter %c \n", last_letter);
	printf("size %i \n", command_size);
	if(*args[(sizeof(args)-1)]=='&'){
	  *args[(sizeof(args)-1)]=0;
	  printf("im hintergrund %s &\n", args[0]);
	  system(("%s &", args[0]));
	  	
	}else{
	  printf("%s\n", args[0]);
	  system(("%s", args[0])); 	
	}
  }
  
  return 1;
}

#define hawsh_RL_BUFSIZE 1024
/**
   @brief Read a line of input from stdin.
   @return The line from stdin.
 */
char *hawsh_read_line(void)
{
  int bufsize = hawsh_RL_BUFSIZE;
  int position = 0;
  char *buffer = malloc(sizeof(char) * bufsize);
  int c;

  if (!buffer) {
    fprintf(stderr, "hawsh: allocation error\n");
    exit(EXIT_FAILURE);
  }

  while (1) {
    // Read a character
    c = getchar();

    if (c == EOF) {
      exit(EXIT_SUCCESS);
    } else if (c == '\n') {
      buffer[position] = '\0';
      return buffer;
    } else {      buffer[position] = c;
    }
    position++;

    // If we have exceeded the buffer, reallocate.
    if (position >= bufsize) {
      bufsize += hawsh_RL_BUFSIZE;
      buffer = realloc(buffer, bufsize);
      if (!buffer) {
        fprintf(stderr, "hawsh: allocation error\n");
        exit(EXIT_FAILURE);
      }
    }
  }
}
void hawsh_loop()
{
  char *line;
  //char **args;
  //int status;
  char cwd[1024];
  char user[1024];

  do {
		if (getcwd(cwd, sizeof(cwd)) != NULL) 
    	fprintf(stdout, "%s sitz in %s >", getenv("USER"), cwd);
		else
			perror("getcwd() error");
	  
    line = hawsh_read_line();
    hawsh_split_line(line);
//    status = hawsh_execute(args);
  } while (1);
}


int main(int argc, char **argv)
{
  hawsh_loop();

  return EXIT_SUCCESS;
}
