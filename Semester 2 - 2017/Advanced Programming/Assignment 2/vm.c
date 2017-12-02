#include "vm.h"
/******************************************************************************

** Student Name: Harrison Smith

** Student Number: s3658817

** Date of submission: Fri, 13 Oct 2017

** Course: COSC1076 / COSC2207, Semester 2, 2017

******************************************************************************/
int main(int argc, char ** argv)
{
	/* Dynmaccialy assigns memory to VmSystem*/
	VmSystem * system = malloc(sizeof * system);
	/*calls a method Intitilises the system*/
	systemInit(system);

	/* if  input doesnt equal two inputs it prompts the user and exits.*/
	if (argc != 2){
		fprintf(stderr, "Usage: %s <file.dat>\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	/* if it does pick up two inputs to the console then it loads the file to the system.*/
	if (argc == 2){
		if (loadStock(system,argv[1]) == FALSE){
			perror("Unable to open file");
			return EXIT_SUCCESS;
		}
	}
	/*Calls the mainMenu method*/
	mainMenu(system);
	
	return EXIT_SUCCESS;
}

void mainMenu(VmSystem * system){
	/* Declares an array menu of size 9 */ 
	MenuItem menu[9];
	MenuFunction function;
	int j;
	
	/* Intitilises the menu with the array defined above.*/ 
	initMenu(menu);
	
	printf("\n");
	/* Prints the main menu and its options. **/ 
	printf("Main Menu:\n");
	for(j = 0; j < 3;j++){
		printf("        %s\n",menu[j].text);
	}
	printf("Administrator-Only Menu:\n");
	for (j = 3; j < 9;j++){
		printf("        %s\n",menu[j].text);
	}
	
	/* Waits for input and then will execute the correct function based off of input */ 
	while(TRUE){
		printf("\nSelect your option (1-9): ");
		function = getMenuChoice(menu);
		if (function == NULL){
			continue;
		}else{
			function(system);
			mainMenu(system);
		}
	}
	
}
