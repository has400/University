/******************************************************************************

** Student Name: Harrison Smith

** Student Number: s3658817

** Date of submission: Fri, 13 Oct 2017

** Course: COSC1076 / COSC2207, Semester 2, 2017

******************************************************************************/
#include "vm_menu.h"

/**
* vm_menu.c handles the initialisation and management of the menu array.
**/

/**
* In this function you need to initialise the array of menu items
* according to the text to be displayed for the menu. This array is
* an array of MenuItem with text and a pointer to the function
* that will be called.
**/
void initMenu(MenuItem * menu)
{ 
	/* This function changes the name of the text in the menu to the menu items*/
	strcpy(menu[0].text,"1.Display Items");	
	/* This is assigning a pointer to the function, so that when someone enters
	what they want to do they will be taken to the correct screen.
	*/
	menu[0].function = &displayItems;
	
	strcpy(menu[1].text,"2.Purchase Items");
	menu[1].function = &purchaseItem;
	
	strcpy(menu[2].text,"3.Save and Exit");
	menu[2].function = &saveAndExit;
	
	strcpy(menu[3].text,"4.Add Item");
	menu[3].function = &addItem;
	
	strcpy(menu[4].text,"5.Remove Item");
	menu[4].function = &removeItem;
	
	strcpy(menu[5].text,"6.Display Coins");
	menu[5].function = &displayCoins;
	
	strcpy(menu[6].text,"7.Reset Stock");
	menu[6].function = &resetStock;
	
	strcpy(menu[7].text,"8.Reset Coins");
	menu[7].function = &resetCoins;
	
	strcpy(menu[8].text,"9.Abort Program");
	menu[8].function = &abortProgram;
}

/**
* Gets input from the user and returns a pointer to the MenuFunction
* that defines how to perform the user's selection. NULL is returned
* if an invalid option is entered.
**/
MenuFunction getMenuChoice(MenuItem * menu)
{
	
	int intValue;
	char input[MAX_LINE + EXTRACHARS];
	char * endPtr;
	
	/** This waits for the input of the user **/
	if (fgets(input,sizeof(input),stdin) == NULL){
		return NULL;
		/*This catches when ctrl-d is entered into the console.*/
	}
	else{
		if(input[strlen(input) - 1] != '\n')
		{
			printf("Error: line entered was too long. Please try again.\n\n");
			readRestOfLine();
			return NULL;
		}else{
			input[strlen(input) - 1] = '\0';/*removes \n at end of string */
			
			intValue = strtol(input, &endPtr, 10); /*returns number*/
			
			/* Checks if the intValue has failed or is 0 */
			if (intValue != 0){
				/* Further checks if it within the specificied range of menu items */
				if (intValue >= 0 && intValue <= 9){
					intValue -= 1;
					/*returns the pointer to the function */
					return menu[intValue].function;
				}else{
					printf("Error: %s is not a valid choice.\n",input);
					return NULL;
				}
			}
		}
	}
	
	/* if nothing is entered null is returned*/
	if (input[0] == '\0') {
	return NULL;
	}

	
	printf("Error: %s is not a valid number. Please try again.\n",input);
	return NULL;
	
}
