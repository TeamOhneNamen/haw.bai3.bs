/*
 * @author: Thorben Schomacker
 * @author: Ferdinand Trendelenburg
 *
 */

#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <unistd.h>
#include <stdlib.h>
#include <wait.h>

/**
 * Prints the Prompt String
 */
void type_prompt() {
    char cwd[256];
    getcwd(cwd, sizeof(cwd));
    printf("%s@%s > ", getenv("USER"), cwd);
}


/**
 * Reads the keyboard input Stream and saves the input into param command
 *
 * @param command: Holds the user input
 * @return 0 = a command without &, 1 = a command with &
 */
int read_command(char *command) {
    fgets(command, 128, stdin);
    command[strlen(command) - 1] = '\0';
    if (command[strlen(command) - 1] == '&') {
        command[strlen(command) - 1] = '\0';
        return 1;
    } else {
        return 0;
    }
}

/**
 * Prints help text
 */
void help() {
    printf("hawsh is a shell, which can execute the following built-in commands:\n");
    printf("help - shows this help message\n");
    printf("version - shows the current version of hawsh\n");
    printf("/[pathname] - change the current working directory to pathname\n");
    printf("quit - closes hawsh\n");
}

/**
 * Prints version info
 */
void version() {
		printf("Schomacker & Trendelenburg\n");
    printf("1.0\n");
}


int main(int argc, char *argv[]) {

    int command_in_background = 0;
    int status = 0;
    char command[128];

    while (true) {
        type_prompt();
        command_in_background=read_command(command);

        if (strcmp(command, "help") == 0) {
            help();
        } else if (strcmp(command, "version") == 0) {
            version();
        } else if (strcmp(command, "quit") == 0) {
            exit(EXIT_SUCCESS);
        } else if (strncmp(command, "/", 1) == 0) {
            if(chdir(command) == -1)
            {
                printf("failed to change directory");
            }
        } else {
            int PIDstatus = fork();
            if (PIDstatus < 0){
                printf("Unable to fork\n");
                continue;
            }
            if(PIDstatus > 0){
                if(command_in_background==0){
                    waitpid(PIDstatus, &status, 0);
                }
            } else {
                int returnVal = 0;
                returnVal = execlp(command, command, NULL);

                if(returnVal == -1)
                {
                    printf("unknown command\n");
                    exit(0);
                }

            }
        }
    }
}
