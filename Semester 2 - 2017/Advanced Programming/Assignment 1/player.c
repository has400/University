#include "player.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
* This initialises the first player who enters their name into the console.
*/
void initFirstPlayer(Player * player)
{ 
	char player1Name[NAMELEN + NULL_SPACE];

	printf("\nPlease enter the first player's name: ");
	
	while (TRUE) {
		/*
		* This handles retreiving what the user enters for their name, if it is too large it executes
		* readRestOfLine and then starts this function over again.
		*/
		if (fgets(player1Name, sizeof(player1Name), stdin) == NULL){
			player->name[0] = '\0';
			break;
			/*This catches when ctrl-d is entered into the console.*/
		}
		else{
			if(player1Name[strlen(player1Name) - 1] != '\n')
			{
				printf("\nError: buffer overflow. Please try again, entering less data.\n");
				readRestOfLine();
				
				initFirstPlayer(player);
				
				break;
			}else{
				
				/*
			* This is a random number generator, it produces either a 1 or 0, based off
			* the output the first player will either get assigned red or cyan.
			*/
				srand(time(NULL));
				
				if (rand() % 2 == 0){
					player->token = RED;
				}else{
					player->token = CYAN;
				}
				
				
				
				player1Name[strlen(player1Name) - 1] = '\0';
				
				/*
			* This sets the score of the first player to 0 and their name to what they entered. 
			*/
				strcpy(player->name,player1Name);
				player->score = 0;
				
				break;
			}
		}
	}
	
	
}

/*
* This initialises the second player whose colour is set to the opposite of the first player. 
*/
void initSecondPlayer(Player * player, Cell token)
{ 
	char player2Name[NAMELEN + NULL_SPACE];
	
	/*
	* This handles retreiving what the user enters for their name, if it is too large it executes
	* readRestOfLine and then starts this function over again.
	*/
	printf("Please enter the second player's name: ");
	while (TRUE) {
		if (fgets(player2Name, sizeof(player2Name), stdin) == NULL){
			player->name[0] = '\0';
			break;
			/*This catches when ctrl-d is entered into the console.*/
		}else{
			if(player2Name[strlen(player2Name) - 1] != '\n')
			{
				printf("\nError: buffer overflow. Please try again, entering less data.\n");
				readRestOfLine();
				
				initSecondPlayer(player,token);
				break;
			}
			else
			{
				player2Name[strlen(player2Name) - 1] = '\0';
				
				/*
			* Like above this sets the score of the second player to 0 and their name to 
			* what they entered, and the token that is passed through the function.
			*/
				strcpy(player->name,player2Name);
				player->token = token;
				player->score = 0;	
				break;
			}
		}
	}

	
}
