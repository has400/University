#include "reversi.h"
#include <stdlib.h>
#include <stdio.h>


/*
* This displays the main menu text.
*/
int main()
{		
	printf("Welcome to Reversi!\n");
	printf("===================\n");
	printf("Select an option:\n");
	printf("1. Play a game\n");
	printf("2. Quit the program");
	enterChoice();

	return EXIT_SUCCESS;
}

/*
* This is function allows the user to input what they want to do,
* either play or exit the game.
*/
void enterChoice(){
	char userChoice[3];
	int input;
	/*
	* This is prompts for the user to input their choice,
	*/
	printf("\nPlease enter your choice: ");
	if (fgets(userChoice, 10, stdin) == NULL){
		enterChoice();
	}
	
	if(userChoice[strlen(userChoice)-1]!='\n')
	{
		printf("Input was too long, try again. \n\n");
		readRestOfLine();
	}
	
	/*
	* This is allows for the entered number to be passed into 
	* the next function in order to see if the user wants to play or not.
	*/
	userChoice[strlen(userChoice)-1]=0;
	sscanf(userChoice, "%d", &input);

	playYesOrNo(input);

}

/*
* This function verifies if the user wants to play, quit and the outcome 
* of the game when it is finished.
*/
int playYesOrNo(int i) {
	if (i == 1){

		Player first,second, * winner;
		
		/*
		* This triggers the game to start and when finished winner will be returned a 
		* value, either a single winner or if it was a draw.
		*/
		winner =  playGame(&first, &second);
		
		/*
		* If it was a draw winner would be null otherwise the details are passed through
		* and a winner is announced.
		*/
		if (winner == NULL){
			printf("\nThe game was a draw\n\n");
			main();
		}else{
			printf("\n%s won with a score of %d\n\n", winner->name, winner->score);
			main();
		}
		
		
	}
	else if (i == 2){
		printf("\nGoodbye.\n\n");
	}
	else
	{
		enterChoice();
	}

	return EXIT_SUCCESS;

}
