/******************************************************************************

** Student Name: Harrison Smith

** Student Number: s3658817

** Date of submission: Fri, 13 Oct 2017

** Course: COSC1076 / COSC2207, Semester 2, 2017

******************************************************************************/
#include "vm_options.h"

/**
* vm_options.c this is where you need to implement the system handling
* functions (e.g., init, free, load, save) and the main options for
* your program. You may however have some of the actual work done
* in functions defined elsewhere.
**/

/**
* Initialise the system to a known safe state. Look at the structure
* defined in vm_system.h.
**/
Boolean systemInit(VmSystem * system)
{
	/*initilises the system to predefined safe values.*/
	int i;
	List * list;
	
	list = initialiseLinkedList();
	
	
	for(i = 0; i < NUM_DENOMS;i++){
		system->cashRegister[i].denom = i;
		system->cashRegister[i].count = 0;
	}
	
	/*Sets the input files to default names, which are changed when loaded.*/
	/* item list set to linked list.*/
	system->itemList = list;
	system->stockFileName = "stock.dat";
	system->coinFileName = "coins.dat";

	return FALSE;
}

/**
* Free all memory that has been allocated. If you are struggling to
* find all your memory leaks, compile your program with the -g flag
* and run it through valgrind.
**/
void systemFree(VmSystem * system)
{ 
	/* frees the systems dynamically allocated memory. */
	freeList(system);
	free(system);
}

/**
* Loads the stock and coin data into the system. You will also need to assign
* the char pointers to the stock and coin file names in the system here so
* that the same files will be used for saving. A key part of this function is
* validation. A substantial number of marks are allocated to this function.
**/
Boolean loadData(VmSystem * system, const char * stockFileName, const char * coinsFileName)
{
	return FALSE;
}

/**
* Loads the stock file data into the system.
**/
Boolean loadStock(VmSystem * system, const char * fileName)
{
	FILE *fp;
	char line[MAX_LINE + EXTRACHARS];
	int i = 0;
	int onHand;
	char * endPtr;
	/* Changes the stockFileName to the correct file, rather than the value
	* that it was initilised with. 
	*/
	system->stockFileName = fileName;
	/* The file is then attempts to open, if it exsists, then it will continue oherwise it will
	* return false.
	*/
	if ((fp = fopen(fileName, "r")) != NULL){
		/* It goes through the whole file untill it doesnt have another line */
		while(fgets(line, MAX_LINE+EXTRACHARS, fp) != NULL ){	
			char *token;
			Stock stock;
			i = 0;
			
			token = strtok(line, STOCK_DELIM);
			while(token != NULL) 
			{
				if (i == 0){
					strcpy(stock.id,token);
				}else if( i == 1){
					strcpy(stock.name,token);
				}else if( i == 2){
					strcpy(stock.desc,token);
				}else if( i == 3){
					stock.price = setPrice(token);
				}else if( i == 4){
					onHand = strtol(token, &endPtr, 10);
					stock.onHand = onHand;
				}
				
				token = strtok(NULL, STOCK_DELIM);
				i++;

			}
			/* From the values it read it then adds this to the itemList. */
			addToList(system,system->itemList,&stock);
		}
		
		fclose(fp);
		return TRUE;
	}else{
		return FALSE;
	}
	
}

/**
* Loads the coin file data into the system.
**/
Boolean loadCoins(VmSystem * system, const char * fileName)
{
	return FALSE;
}

/**
* Saves all the stock back to the stock file.
**/
Boolean saveStock(VmSystem * system)
{
	FILE *fp;
	Node * node = system->itemList->head;
	/* opens the file */
	if ((fp = fopen("stock.dat", "w")) != NULL){
		
		/*Save each node to file */
		while(node != NULL)
		{
			
			fprintf(fp, "%s|%s|%s|%d.%02d|%d\n",node->data->id,node->data->name,node->data->desc,node->data->price.dollars,node->data->price.cents,node->data->onHand);
			node = node->next;
		}

		fclose(fp);
		return TRUE;
	}

	
	return FALSE;
}

/**
* Saves all the coins back to the coins file.
**/
Boolean saveCoins(VmSystem * system)
{
	return FALSE;
}

/**
* This option allows the user to display the items in the system.
* This is the data loaded into the linked list in the requirement 2.
**/
void displayItems(VmSystem * system)
{
	
	Node * node = system->itemList->head;
	/* seperate node to get the longest node of the itemList*/
	Node * longestname = system->itemList->head;
	
	int lengthOfString;
	int longestString = 0;
	int i;
	int editLength = 0;
	
	while(longestname != NULL)
	{
		lengthOfString = strlen(longestname->data->name);
		if (lengthOfString > longestString){
			/* node is set to longest out of list*/
			longestString = lengthOfString;
			
		}
		longestname = longestname->next;
	}
	
	/* this is to make sure the formatting is correct based of the longest name */
	longestString += 1;
	printf("Items Menu\n\n");
	editLength = longestString - 4;
	
	printf("ID    %s Name",STOCK_DELIM);
	/* This is to input the correct number of spaces based off the longest input*/
	for (i = 0; i < editLength;i++){
		printf(" ");
	}
	
	printf("| Available %s Price\n",STOCK_DELIM);
	printf("---------------------------------------------\n");
	
	/* This goes through the nodes */
	while(node != NULL)
	{
		/* prints the id and name and spaces for formatting. */
		printf("%s %s ",node->data->id,STOCK_DELIM);
		printf("%s", node->data->name);
		editLength = longestString - strlen(node->data->name);
		
		for (i = 0; i < editLength;i++){
			printf(" ");
		}
		
		printf("| %d ",node->data->onHand);
		
		
		/* Checks how many didgits the onHand number is, and then adds the required spaces. */
		if (node->data->onHand >= 10){
			for (i = 0; i < 7;i++){
				printf(" ");
			}
		}else{
			for (i = 0; i < 8;i++){
				printf(" ");
			}
		}
		/*Prints the price. */
		printf("| %d.%02d \n", node->data->price.dollars,node->data->price.cents);

		node = node->next;
	}
	printf("\n");

}
/**
* This option allows the user to purchase an item.
* This function implements requirement 5 of the assignment specification.
**/
void purchaseItem(VmSystem * system)
{ 
	printf("Purchase Item\n");
	printf("--------------\n");

	while(TRUE){
		
		char input[MAX_LINE + EXTRACHARS];
		Node * node = system->itemList->head;
		int total;
		int value;
		int change = 0;
		int transaction = 0;	
		char * endPtr;
		
		
		printf("Please enter the id of the item you wish to purchase: ");
		/* gets the user input to the question above.*/
		if (fgets(input,sizeof(input),stdin) == NULL){
			break;
			/*This catches when ctrl-d is entered into the console.*/
		}
		else{
			if(input[strlen(input) - 1] != '\n')
			{
				printf("Error: line entered was too long. Please try again.\n\n");
				readRestOfLine();
				
			}else{
				input[strlen(input) - 1] = '\0';/*removes \n at end of string */
			}
		}
		/* Checks if the input is empty*/
		if (input[0] == '\0') {
			break;
		}
		
		while(node != NULL)
		{
			if (strcmp(node->data->id,input)==0){
				/* Checks how many are avaiable to buy.*/
				if (node->data->onHand == 0){
					printf("Error: Insufficient items onhand.\n");
					total = -1;
					transaction = 1;
					break;	
				}
				
				/* Tells the user the price of the item.*/
				printf("You have selected \"%s %s\". This will cost you $%d.%02d.\n", node->data->name,node->data->desc,node->data->price.dollars,node->data->price.cents);
				
				printf("Please hand over the money - type in the value of each note/coin in cents.\n");
				printf("Press enter on a new and empty line to cancel this purchase:\n");
				total = (node->data->price.dollars * 100) + node->data->price.cents;
				
				
				while (TRUE){
					/* Prompts the user each time how much they owe the vending mahcine.*/
					printf("You still need to give us $%d.%02d: ",total/100,total%100);
					
					if (fgets(input,sizeof(input),stdin) == NULL){
						/* When the user doesnt want to buy the item.*/
						total -= ((node->data->price.dollars * 100) + node->data->price.cents);
						total = total * -1;
						
						printf("Change of mind - here is your change: $%d.%02d.\n",total/100, total%100);
						
						total = -1;
						break;
						/*This catches when ctrl-d is entered into the console.*/
					}else{
						if(input[strlen(input) - 1] != '\n')
						{
							printf("Error: line entered was too long. Please try again.\n\n");
							readRestOfLine();
							
						}else{
							input[strlen(input) - 1] = '\0';/*removes \n at end of string */
						}
					}
					
					if (input[0] == '\0') {
						/* When the user doesnt want to buy the item.*/
						total -= ((node->data->price.dollars * 100) + node->data->price.cents);
						total = total * -1;
						
						printf("Change of mind - here is your change: $%d.%02d.\n",total/100, total%100);
						
						total = -1;
						break;
					}
					
					value = strtol(input, &endPtr, 10); /*returns number*/
					
	
					
					/* Checks number is a valid input*/
					if (value != 5 && value != 10 && value != 20 && value != 50 &&
							value != 100 && value != 200 && value != 500 && value != 1000){
								/* Tells the user if the value wasnt a number or an incorrect denomination*/
								if (value == 0){
									printf("Error: input was not a number. Please try again.\n");
									printf("Error: you did not enter a valid integer. Please try again.\n");

								}else{
									printf("Error: $%d.%02d is not a valid denomination of money. \n",value/100, value%100);
								}
						
						continue;
					}
					
						/* Checks if there is a character after*/
					if (strlen(endPtr) != 0){
						/* Tells the user if the value wasnt a number or an incorrect denomination*/
									
									printf("Error: you did not enter a valid integer. Please try again.\n");
				
						continue;
					}
					
					if (value != 0){
						/* Checks if total = 0, if so it then exits loop*/
						if (total == 0){
							break;
						}
						/* total is updated with the coins inputted.*/
						total -= value;
						
						if (total < 0){
							change = total * -1;
							break;
						}
						/* double checks that the total is not 0, after money is input */
						if (total == 0){
							break;
						}
						
					}
					
					
				}
				/* Checks how much change is due.*/
				if (change != 0){
					printf("Thank you. Here is your %s and your change of $%d.%02d.\n",node->data->name, change/100, change%100);
				}
				printf("Please come again soon.\n");
				transaction = 1;
				
				break;
			}
			
			node = node->next;
		}
		/* That a transaction has tried to take place*/
		if (transaction == 1 ){
			/*This makes sure that infact the user has paid all the money, then the item is removed from the list.*/
			if (total != -1){
				node->data->onHand -= 1;
			}
			
			break;
		}
		printf("Error: you did not enter a valid id. Please try again.\n");
	}
	
}



/**
* You must save all data to the data files that were provided on the command
* line when the program loaded up, display goodbye and free the system.
* This function implements requirement 6 of the assignment specification.
**/
void saveAndExit(VmSystem * system)
{ 
	/* attempts to save the stock, if it fails a message is returned.*/
	if (saveStock(system) == FALSE){
		printf("Unable to save information to stock.dat\n");
	}
	
	/* free the systems malloc'd memory*/
	systemFree(system);
	exit(EXIT_SUCCESS);
}

/**
* This option adds an item to the system. This function implements
* requirement 7 of of assignment specification.
**/
void addItem(VmSystem * system)
{ 

	Node * node = system->itemList->head;
	Stock stock;
	
	char id[5] = " ",newID[5],input[MAX_LINE + EXTRACHARS],*tempID,*next,*token;
	
	int j,i,executionNumber = 0,k = 0,outputNum,largestID = 0,errorFound = 0,exit = 0,tempDollar,tempCents;
	long val;
	
	char tempName[NAME_LEN + NULL_SPACE],tempDesc[DESC_LEN + NULL_SPACE],tempPrice[10];

	
	/* This while loop goes through the nodes and gets the largest id so that it is able */
	/* to be used, in order to determined the next open ID for the item which is being added */
	while(node != NULL)
	{
		i = 0;
		for (j=1;j<5;j++){
			id[i] = node->data->id[j];
			i++;
		}
		
		id[5] = '\0';
		tempID = id;
		outputNum = strtol (tempID, &next, 10);
		
		
		if (outputNum > largestID){
			largestID = outputNum;
		}
		
		node = node->next;
	}
	
	/* makes newID the same as printf output */
	sprintf (newID, "I%04d", largestID + 1);

	printf("This new meal item will have the Item id of %s\n", newID);
	
	printf("Enter the item name: ");
	while (TRUE){ 
		/* This is shown on the first time the while loop or untill the user inputs a item description which is valid.*/
		if (executionNumber == 1){
			printf("Enter the item description: ");
			/* This is shown until the user inputs a price which is valid.*/
		}else if (executionNumber == 2){
			printf("Enter the price for this item: ");
		}
		
		
		if (fgets(input,sizeof(input),stdin) == NULL){	
			exit = 1;		
			break;
			/*This catches when ctrl-d is entered into the console.*/
		}else{
			if(input[strlen(input) - 1] != '\n')
			{
				printf("Error: line entered was too long. Please try again.\n\n");
				readRestOfLine();
				
			}else{
				input[strlen(input) - 1] = '\0';/*removes \n at end of string */
			}
		}
		/* Checks if input is empty*/
		if (input[0] == '\0') {
			exit = 1;
			break;
		}
		/* Checks where program is up to and runs the code based off it., when executionNumber = 1*/
		/* The program is waiting for a name to be inputted.*/
		if (executionNumber == 0){
			if (strlen(input) > NAME_LEN + NULL_SPACE){
				/* Makes sure the name isnt too big for the input*/
				printf("Error: line entered was too long. Please try again.\n");
				printf("Error inputting name of the product. Please try again.\n");
				printf("Enter the item name: ");
				continue;
				
			}else{
				/* else the input string is copied.*/
				strcpy(tempName,input);
			}
		/* when executionNumber = 1, the program is wating for the user to input a description for the item.*/	
		}else if ( executionNumber == 1){
			if (strlen(input) > DESC_LEN + NULL_SPACE){
				/* makes sure the desc isnt too large.*/
				printf("Error: line entered was too long. Please try again.\n");
				printf("Error inputting name of the product. Please try again.\n");
				continue;
				
			}else{
				/* else it copies the string*/
				strcpy(tempDesc,input);
			}
			
			/* when executionNumber = 2, the program is wating for the user to input a price for the item.*/	
		}else if (executionNumber == 2){
			k = 0;
			errorFound = 0;
			
			token = strtok(input, ".");
			while(token != NULL) 
			{
				/* checks that the token entered didgit before the '.' is valid*/
				if (k == 0){
					val = strtol (token, &next, 10);
					
					if (*next){
						printf("Error: The price is not valid.\n");
						errorFound = 1;
						break;
					}else{
						tempDollar = val;	
					}	
				} 
				
				/* checks that the token entered didgit after the '.' is valid*/
				if ( k == 1){
					val = strtol (token, &next, 10);

					if (*next){
						printf("Error: the cents are not numeric.\n");
						errorFound = 1;
						break;
					}
					/* checks that the cents portion of the token is a multiple of 5*/
					if (strlen(token) == 2){
						if (val % 5 != 0){
							printf("Error: the cents need to be a multiple of 5.\n");
							errorFound = 1;
							break;
						}
						
						tempCents = val;
						break;
					}else{
						printf("Error: cents value is not valid.\n");
						errorFound = 1;
						break;
					}
				}
				token = strtok(NULL, ".");
				k++;
			}
			
			if (errorFound == 1){
				continue;
			}
			
			break;
		}	
		executionNumber++;
	}
	
	/* checks if ctrl d or new line was pressed, if it hasnt it creates a new item in the list with the input values. */
	if (exit != 1){
		sprintf (tempPrice, "%d.%02d",tempDollar,tempCents);
		
		strcpy(stock.id,newID);
		strcpy(stock.name,tempName);
		strcpy(stock.desc,tempDesc);
		stock.price = setPrice(tempPrice);
		stock.onHand = DEFAULT_STOCK_LEVEL;
		
		addToList(system,system->itemList,&stock);
		
		printf("This item \"%s – %s\" has now been added to the menu.",stock.name,stock.desc);
	}else{
		/* otherwise it tells the user that it wasnt made*/
		printf("Cancelling \"add item\" at the user's request.\n");
		printf("Error: The task Add Item failed to run successfully.");

	}
}

/**
* Remove an item from the system, including free'ing its memory.
* This function implements requirement 8 of the assignment specification.
**/
void removeItem(VmSystem * system)
{ 
	char input[ID_LEN + NULL_SPACE + 1];
	Node * deletedNode;
	char output[MAX_LINE + EXTRACHARS];

	while (TRUE){
		printf("Enter the item id of the item to remove from the menu: ");	
		if (fgets(input,sizeof(input),stdin) == NULL){	
			
			break;
			/*This catches when ctrl-d is entered into the console.*/
		}else{
			if(input[strlen(input) - 1] != '\n')
			{
				printf("Error: line entered was too long. Please try again.\n\n");
				readRestOfLine();
				continue;
			}else{
				input[strlen(input) - 1] = '\0';/*removes \n at end of string */
			}
		}
		
		if (input[0] == '\0') {
			break;
		}
		
		break;
	}
	/* Serachs the list for node with corresponding input ID */
	deletedNode = searchNode(system,input);
	/* Formats output to item id, name and description of it. */
	sprintf(output,"\"%s - %s %s\"", deletedNode->data->id,deletedNode->data->name,deletedNode->data->desc);
	
	/* Attempts to free node, if it returns false meaning it couldnt do it, it promots the user*/
	/* that it failed. */
	if (freeNode(system,input) == FALSE){
		
		printf("Error: desired id was not found.\n");
		printf("Error: %s was not an id in the stock list.\n",input);
		printf("Error: The task Remove Item failed to run successfully.\n");
		
	}else{
		printf("%s has been removed from the system.\n",output);
	}
}

/**
* This option will require you to display the coins from lowest to highest
* value and the counts of coins should be correctly aligned.
* This function implements part 4 of requirement 18 in the assignment
* specifications.
**/
void displayCoins(VmSystem * system)
{ 
	/* Didnt do this extra mark, so there is no code.*/
}

/**
* This option will require you to iterate over every stock in the
* list and set its onHand count to the default value specified in
* the startup code.
* This function implements requirement 9 of the assignment specification.
**/
void resetStock(VmSystem * system)
{ 
	/* Sets the node to the head */
	Node * node = system->itemList->head;
	while(node != NULL)
	{
		/* Then it goes through every node until null and resets the stock level,
		* which is 20.
		*/
		
		node->data->onHand = DEFAULT_STOCK_LEVEL;
		node = node->next;
	}
	printf("All stock has been reset to the default level of %d",DEFAULT_STOCK_LEVEL);
}

/**
* This option will require you to iterate over every coin in the coin
* list and set its 'count' to the default value specified in the
* startup code.
* This requirement implements part 3 of requirement 18 in the
* assignment specifications.
**/
void resetCoins(VmSystem * system)
{ 
	/* Didnt do this extra mark, so there is no code.*/
}

/**
* This option will require you to display goodbye and free the system.
* This function implements requirement 10 of the assignment specification.
**/
void abortProgram(VmSystem * system)
{ 
	/* Free the systems nodes and exits without saving*/
	systemFree(system);
	exit(EXIT_SUCCESS);
}