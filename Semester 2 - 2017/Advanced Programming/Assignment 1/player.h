#ifndef PLAYER_H
#define PLAYER_H

#include "utility.h"
/**
 * enum for the different types of tokens that are placed on the board.
 **/
typedef enum cell
{
    BLANK, RED, CYAN
} Cell;

/* Color codes required to display the tokens on the board. */
#define COLOR_RED   "\33[31m"
#define COLOR_CYAN  "\33[36m"
#define COLOR_RESET "\33[0m"

/* The maximum length of a player name. */
#define NAMELEN 20

/**
 * This strcture enables the players to be distinguished from each other.
 **/
typedef struct player
{
    char name[NAMELEN + NULL_SPACE];
    Cell token;
    unsigned score;
} Player;

void initFirstPlayer(Player * player);
void initSecondPlayer(Player * player, Cell token);

#endif
