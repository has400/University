#include "game.h"

/*
* This function runs most of the game. It initilises the first and second player
* setting either one at random to red. It also holds a game loop which exits when 
* certin actions are performed. Each loop it displays the game board and scoreboard. 
* 
* If someone enters a new line, the score is returned, if null is 
* returned then it is a draw.
*/
Player * playGame(Player * first, Player * second)
{	
	Cell board[BOARD_HEIGHT][BOARD_WIDTH];
	Boolean gameLoop = TRUE;
	int redTokenFirstTurn, ScoreboardCounter = 0;
	
	initFirstPlayer(first);
	
	/* This makes the game return to the main menu if the first player doesnt enter in 
	*  a name at all.
	*/
	if(first->name[0] == '\0'){
		return NULL;
	}
	
	/* The second player is assigned the opposite of what was given to the first player in the random number generator.
	* 
	*/
	if (first->token == RED){
		initSecondPlayer(second,CYAN);
	}else{
		initSecondPlayer(second,RED);
	}

	
	/* This makes the game return to the main menu if the second player doesnt enter in 
	*  a name at all.
	*/
	if(second->name[0] == '\0'){
		return NULL;
	}
	
	/* initilises the game board */
	initBoard(board);
	
	/* Displays the game board and scoreboard for the first time in the game. */
	displayScoreBoard(board,first,second);
	displayBoard(board,first,second);
	
	/* This checks which player has the red token so they will be the first player
	* in the game.
	*/
	if (first->token == RED){
		redTokenFirstTurn = 0;
	}else{
		redTokenFirstTurn = 1;
	}
	
	/* Start of the game loop */
	while (gameLoop != FALSE){	
		
		
		/* As stated above the first makeMove is executed by the red token player */
		if (redTokenFirstTurn == 0){
			gameLoop = makeMove(first,board); 
		}else{
			gameLoop = makeMove(second,board); 
		}
		
		/* this if statements checks that the user actually enetered something into the console
		* if false it will return to the main menu. WHen returning to the main menu it checks who won
		* based off who won a message is printed, however if a draw null is returned. 
		*/
		if(gameLoop == TRUE){
			/* swaps the players*/
			swapPlayers(&first,&second);
			
			/* Shows the scoreboard as the first player who entered their name on top and the second player
			* to the bottom each time the loop goes through. 
			*/
			if (ScoreboardCounter % 2 == 0){
				displayScoreBoard(board,second,first);
			}else{
				displayScoreBoard(board,first,second);
			}
			
			/* Prints the display board. */
			displayBoard(board,first,second);
			
			/* setting the score of first player */
			if (first->token == RED){
			first->score = gameScore(board, RED);
			}else{
			first->score = gameScore(board, CYAN);
			} 
			
			/* setting the score of the second player */
			if (second->token == RED){
			second->score = gameScore(board, RED);
			}else{
			second->score = gameScore(board, CYAN);
			} 
		}else{
			printf("Returning to the main menu ...\n");
			
			/* Checks the score of the game, based off which token has more points, it will print a message 
			*  that will tell the player if they lost or won.
			*/
			if (gameScore(board, RED) > gameScore(board, CYAN)){
				
				if (first->token == RED){
					
					return first;
				}else{
					
					return second;
				}
				
				
			}else if (gameScore(board, RED) < gameScore(board, CYAN)){
				
				if (first->token == CYAN){
					
					return first;
				}else{
					
					return second;
				}
				
				
			}else{
				return NULL;
			}
			
			
			
			
		}
		/* Counts the loop so the scoreboard will be accurate.*/
		ScoreboardCounter++;
	}
	
	return NULL;
}

/*
* This asks the user for pair of coordinates, eg (1,1), if a valid pair of 
* coordinates then calls the applyMove function to capture a piece
* IF an empty line is entered false is returned, otherwise it will prompt for valid 
*	coordinates to be in and then true is returned.
*/
Boolean makeMove(Player * player, Cell board[BOARD_HEIGHT][BOARD_WIDTH])
{
	printf("It is %s's turn\n",player->name);
	
	while (TRUE) {
		char inputCoordinates[5];
		
		int firstCordinate, secondCordinate, returnedInteger;
		char endChar;
		
		printf("Please enter x and y coordinates separated by a comma for the piece you wish to place: ");
		/* 
		* This prompts the user to enter a pair of coordinates into the console, 
		* if the coordinates are too long then it will call the readRestOfLine function 
		* and then go to the start of the while loop and ask for a valid set of coordinates
		* if nothing is empty, FALSE is returned.
		*/
		if (fgets(inputCoordinates, sizeof(inputCoordinates), stdin) == NULL){
			return FALSE;
			/* This catches when a user inputs ctrl-d to the console */
		}else{
			
			if(inputCoordinates[strlen(inputCoordinates) - 1] != '\n')
			{
				printf("\nError: buffer overflow. Please try again, entering less data.\n");
				readRestOfLine();
				continue;
				
			}else if (inputCoordinates[0] == '\n') {
				return FALSE;
			}else{
				
				returnedInteger = sscanf(inputCoordinates, "%d , %d%c", &firstCordinate, 
				&secondCordinate, &endChar);
				
				/*
			* If the coordinates arent empty or too long then they will be read by the sscanf to 
			* make sure they are in the correct format, being an ' int , int ' if it isnt then the 
			* user will be prompted to enter valid coordinates, and if the cordinates are already the 
			* coordinates of a previous piece the user will be prompted to enter valid coordinates.
			*/
				if (returnedInteger != 3) {  
					printf("Error: invalid coordinates.\n");
					printf("Error: you did not enter valid coordinates. Please try again.\n");
					continue;
				}else if (board[secondCordinate - 1][firstCordinate - 1] == RED 
						|| board[secondCordinate - 1][firstCordinate - 1] == CYAN ){
					printf("Error: the move you attempted was not valid.\n");
					continue;
					
				}else{
					/* 
				* Once confirmed the piece will be passed into the applyMove function to capture a piece 
				* if the applyMove is false then it will tell the coordinates are invalid and prompt for them
				* to re-enter them. Otherwise it breaks the while loop and the function returns true.
				*/
					
					/* [y][x]*/
					if ( applyMove( board, (secondCordinate - 1), (firstCordinate - 1),
								player->token) == FALSE){
						printf("Error: the move you attempted was not valid.\n");
						continue;
					}else{
						break;
					}
				}
			}
		}
		
	}

	return TRUE;
	
}

/*
* Applys the move which was passed through, it calls a function to
* see if it is valid in all directions and able to capture a piece. 
* If the move was able to be executed it would capture the piece and 
* change the board and returns true, otherwise it returns false.
*/
Boolean applyMove(Cell board[BOARD_HEIGHT][BOARD_WIDTH], int y, int x, Cell token)
{
	
	if (checkValidMoveDirection(board,y,x, token) > 0){
		board[y][x] = token;
		return TRUE;
	}else{
		return FALSE;
	}
}

/*
* This counts how many tokens of a specific colour are on the gameboard
* based off this a number is returned.
*/
unsigned gameScore(Cell board[BOARD_HEIGHT][BOARD_WIDTH], Cell token)
{
	int score = 0;
	int i, j;
	/* Identifies if token is red and then counts and adds one to score*/
	if (token == RED){
		for (i = 0; i < BOARD_HEIGHT; i++)
		{
			for (j = 0; j < BOARD_WIDTH; j++)
			{
				if(board[i][j] == RED){
					score++;
				}
			}
			
		}
	} 
	/* Identifies if token is cyan and then counts and adds one to score*/
	if (token == CYAN){
		for (i = 0; i < BOARD_HEIGHT; i++)
		{
			for (j = 0; j < BOARD_WIDTH; j++)
			{
				if(board[i][j] == CYAN){
					score++;
				}
			}
		}
		
	}
	/* Then it is returned to where it is called to be used.*/
	return score;
}

/*
* Swaps the players back and forth.
*/
void swapPlayers(Player ** first, Player ** second)
{ 
	Player * temp = *first;
	*first  = *second;
	*second = temp;
}

/* 
* This function displays the scoreboard with current information, 
* such as player names, scores and token colour.
*/
void displayScoreBoard(Cell board[BOARD_HEIGHT][BOARD_WIDTH], Player * first, Player * second)
{
	/* Simple declartions in order to print the various lines to complete the scoreboard */
	char *OutputLine = NULL;
	OutputLine = malloc (100 * sizeof *OutputLine);
	
	printf("================================================================================\n");
	
	/*
	* The sprintf calls functions to show the correct score and token colour for player 1.
	*/
	printf("Player One's Details\n");
	printf("--------------------\n");
	sprintf (OutputLine, "Name: %s         Score: %u          Token Colour: %s ", 
	first->name, gameScore(board,first->token),displayTokenScoreboard(board,first));
	printf("%s\n", OutputLine);
	
	/*
	* The sprintf calls functions to show the correct score and token colour for player 2.
	*/
	printf("--------------------------------------------------------------------------------\n");
	printf("Player Two's Details\n");
	printf("--------------------\n");
	sprintf (OutputLine, "Name: %s         Score: %u          Token Colour: %s ", 
	second->name, gameScore(board,second->token),displayTokenScoreboard(board,second));
	printf("%s\n", OutputLine);
	printf("--------------------------------------------------------------------------------\n");
}

/* 
* This function just displays the colour of the token for each player on the scoreboard.
*/
char * displayTokenScoreboard(Cell board[BOARD_HEIGHT][BOARD_WIDTH], Player * player)
{
	if (player->token == 2){
		return COLOR_CYAN "O" COLOR_RESET;		
	}
	if (player->token == 1){
		return COLOR_RED "O" COLOR_RESET;
	}
	return EXIT_SUCCESS;
}

/* 
* This function checks that there is a valid move in every direction 
* of the selected position of the 2d array. If no moves are made 
* that means a 0 is returned.
*/
int checkValidMoveDirection(Cell board[BOARD_HEIGHT][BOARD_WIDTH], int y, int x, Cell token)
{
	int validMove = 0;
	
	if (token == RED){
		/* South */
		if (board[y+1][x] == CYAN){
			if (board[y+2][x] == RED){
				board[y+1][x] = token;
				validMove++;
			}
		}
		/* North */
		if(board[y-1][x] == CYAN){
			if (board[y-2][x] == RED){
				board[y-1][x] = token;
				validMove++;
			}
		}
		/* West */
		if(board[y][x+1] == CYAN){
			if (board[y][x+2] == RED){
				board[y][x+1] = token;
				validMove++;
			}
		}
		/* East */	
		if(board[y][x-1] == CYAN){
			if (board[y][x-2] == RED){
				board[y][x-1] = token;
				validMove++;
			}
		}
		
		/* South West */	
		if(board[y+1][x+1] == CYAN){
			if (board[y+2][x+2] == RED){
				board[y+1][x+1] = token;
				validMove++;
			}
		}
		/* South East */		
		if(board[y+1][x-1] == CYAN){
			if (board[y+2][x-2] == RED){
				board[y+1][x-1] = token;
				validMove++;
			}
		}
		/* North West */	
		if(board[y-1][x+1] == CYAN){
			if (board[y-2][x+2] == RED){
				board[y-1][x+1] = token;
				validMove++;
			}
		}
		/* North East */		
		if(board[y-1][x-1] == CYAN){
			if (board[y-2][x-2] == RED){
				board[y-1][x-1] = token;
				validMove++;
			}
		}
	}
	

	if (token == CYAN){
		/* South */
		if (board[y+1][x] == RED){
			if (board[y+2][x] == CYAN){
				board[y+1][x] = token;
				validMove++;
			}
		}
		/* North */
		if(board[y-1][x] == RED){
			if (board[y-2][x] == CYAN){
				board[y-1][x] = token;
				validMove++;
			}
		}
		/* West */
		if(board[y][x+1] == RED){
			if (board[y][x+2] == CYAN){
				board[y][x+1] = token;
				validMove++;
			}
		}
		/* East */	
		if(board[y][x-1] == RED){
			if (board[y][x-2] == CYAN){
				board[y][x-1] = token;
				validMove++;
			}
		}
		/* South West */			
		if(board[y+1][x+1] == RED){
			if (board[y+2][x+2] == CYAN){
				board[y+1][x+1] = token;
				validMove++;
			}
		}
		/* South East */		
		if(board[y+1][x-1] == RED){
			if (board[y+2][x-2] == CYAN){
				board[y+1][x-1] = token;
				validMove++;
			}
		}
		/* North West */
		if(board[y-1][x+1] == RED){
			if (board[y-2][x+2] == CYAN){
				board[y-1][x+1] = token;
				validMove++;
			}
		}
		/* North East */		
		if(board[y-1][x-1] == RED){
			if (board[y-2][x-2] == CYAN){
				board[y-1][x-1] = token;
				validMove++;
			}
		}
	}
	return validMove;
}
