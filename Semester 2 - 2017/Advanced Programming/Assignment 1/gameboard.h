#ifndef GAMEBOARD_H
#define GAMEBOARD_H

#include "player.h"

/**
 * Decleration of thw size of board. 
 **/
#define BOARD_HEIGHT 8
#define BOARD_WIDTH BOARD_HEIGHT

void initBoard(Cell board[BOARD_HEIGHT][BOARD_WIDTH]);
void displayBoard(
Cell board[BOARD_HEIGHT][BOARD_WIDTH], Player * first, Player * second);
char * displayBoardColourTokens(Cell board[BOARD_HEIGHT][BOARD_WIDTH],int i, int j);
#endif
