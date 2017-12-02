#ifndef GAME_H
#define GAME_H

#include "gameboard.h"

/**
* All directions pieces can be captured in.
* 
* Note you are NOT required to use this enum if you don't want to.
*This enum was NOT used in my code.
*/
typedef enum direction
{
	NORTH, SOUTH, EAST, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
} Direction;

Player * playGame(Player * first, Player * second);
Boolean makeMove(Player * player, Cell board[BOARD_HEIGHT][BOARD_WIDTH]);
Boolean applyMove(Cell board[BOARD_HEIGHT][BOARD_WIDTH], int y, int x, Cell token);
unsigned gameScore(Cell board[BOARD_HEIGHT][BOARD_WIDTH], Cell token);
void swapPlayers(Player ** first, Player ** second);
char * displayTokenScoreboard(Cell board[BOARD_HEIGHT][BOARD_WIDTH], Player * player);
void displayScoreBoard(Cell board[BOARD_HEIGHT][BOARD_WIDTH], Player * first, Player * second);
int checkValidMoveDirection(Cell board[BOARD_HEIGHT][BOARD_WIDTH], int y, int x, Cell token);
#endif
