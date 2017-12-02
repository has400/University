#include "gameboard.h"
#include <stdio.h>
/*
* This initialises the game board, it makes all of the pieces blank excluding the 4 pieces in the middle. 
*/
void initBoard(Cell board[BOARD_HEIGHT][BOARD_WIDTH])
{ 

/*
* For loop which makes populates the 2d array as a blank state
*/
	int i, j;
	for ( i = 0; i < BOARD_HEIGHT ; i++)
	{
		for ( j = 0; j < BOARD_WIDTH ; j++)
		{
			board[i][j] = BLANK;
		}
	}
	
	/*
	* Manually setting the 4 center pieces to red and cyan.
	*/
	board[3][3] = RED;
	board[4][4] = RED;
	
	board[3][4] = CYAN;
	board[4][3] = CYAN;
	

}

/*
* This initialises the game board, it makes all of the pieces blank excluding the 4 pieces in the middle. 
*/
void displayBoard(
Cell board[BOARD_HEIGHT][BOARD_WIDTH], Player * first, Player * second)
{ 
	char *OutputLine = NULL;
	int rowNumber = 1;
	int i, j;
	OutputLine = malloc (100 * sizeof *OutputLine);

	printf("     1   2   3   4   5   6   7   8  \n");
	printf("====================================\n");
	
/*
* This loop allows for a game board to be printed which denotes a grid. 
*/
	for ( i = 0; i < 8;i++){
		
		/*
		* This prints the row number on th right hand side
		*/
		sprintf (OutputLine, " %d |", rowNumber);
		printf("%s", OutputLine);
		
		for (j = 0; j < 8;j++){
			
			/*
			* This prints the indiviual grid squares of the rows. 
			*/
			sprintf (OutputLine, " %s |", displayBoardColourTokens(board,(rowNumber-1),j));
			printf("%s", OutputLine);
			
		}
		if (i <7){
			/*
			* This prints a divider after each row.
			*/
			printf("\n------------------------------------\n");
		}
		rowNumber++;
	}
	printf("\n====================================\n");
}

/*
* This allows for the coloured tokens to show up on the game board.  
*/
char * displayBoardColourTokens(Cell board[BOARD_HEIGHT][BOARD_WIDTH],int i, int j){	   
/*
* This if statement checks if a postion in the 2d array is supposed to be denoted 
* by either a red or cyan piece. Otherwise a blank spot is returned.
*/
	if( board[i][j] == RED ){
		return COLOR_RED "O" COLOR_RESET;
	} 
	else if (board[i][j] == CYAN){
		return COLOR_CYAN "O" COLOR_RESET;
	}
	else{
		return " ";
	}
	
	
}
