#include "utility.h"

/*
* This function allows for the buffer to be cleared, it reads 
*each character from the buffer and waits until it is empty
*/
void readRestOfLine()
{
	int ch;
	while(ch = getc(stdin), ch != EOF && ch != '\n')
	{ } /* Gobble each character. */

	/* Reset the error status of the stream. */
	clearerr(stdin);
}
