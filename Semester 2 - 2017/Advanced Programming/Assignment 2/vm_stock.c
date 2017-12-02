/******************************************************************************

** Student Name: Harrison Smith

** Student Number: s3658817

** Date of submission: Fri, 13 Oct 2017

** Course: COSC1076 / COSC2207, Semester 2, 2017

******************************************************************************/

#include "vm_stock.h"
/**
* vm_stock.c this is the file where you will implement the
* interface functions for managing the stock list.
**/

/* this code is from week 8's tutorial work */
/* This code allows for new items to be added to exsisting list. */
/* It adds the new entry in alphabetical order.*/
void addToList(VmSystem * system,List * list, Stock * data)
{

	Node * previousNode,* currentNode;

	Node * newNode = malloc(sizeof(*newNode));
	newNode->data = malloc(sizeof(*newNode->data));
	
	*newNode->data = *data;
	
	newNode->next = NULL;
	previousNode = NULL;
	
	currentNode = system->itemList->head;
	
	while(currentNode != NULL)
	{/* sorts the list by order, change to id if you want it to sort in id order rather than name. */ 
		if(strcmp(data->name, currentNode->data->name) < 0)
		{		
			system->itemList->size++;	
			break;
		}

		previousNode = currentNode;
		currentNode = currentNode->next;
	}
				/* If list is empty list. */
			if(system->itemList->head == NULL)
			{
				/* Empty list. */
				system->itemList->head = newNode;
			}else if(previousNode == NULL)
			{
				/* Inserting at the head. */
				newNode->next = system->itemList->head;
				system->itemList->head = newNode;
			}
			else
			{
				previousNode->next = newNode;
				newNode->next = currentNode;
			}
}

/*From week 9's notes */
/* This is called to free a specific node when deleting an item */
Boolean freeNode(VmSystem * system, char * ID){
	Node * previousNode, * currentNode;

	currentNode = system->itemList->head;
	previousNode = NULL;
	/* currentNode and previous node are used to easily be able to cycle through*/
	/* nodes until the correct node with an id which matches the input ID*/
	while(currentNode != NULL)
	{
		if(strcmp(ID, currentNode->data->id) == 0)
		{
			/* Checks if the node has nothing in it. */
			if(currentNode == NULL)
			{
				return FALSE;
			}
			
			
			/* Checks if the node is at the head. */
			if(previousNode == NULL)
			{
				system->itemList->head = currentNode->next;
			}
			else
			{/* otherwise it carries on normally and shifts the list.*/
				previousNode->next = currentNode->next;
			}
			
			free(currentNode->data);
			free(currentNode);
			
			system->itemList->size--;

			return TRUE;
		}

		previousNode = currentNode;
		currentNode = currentNode->next;
	}
	
	return FALSE;
}
/* This code is used when deleting an item from the list, this will the node to be used*/
/* to display what was item was deleted to the user*/
Node * searchNode(VmSystem * system, char * ID){
	Node * previousNode, * currentNode;

	previousNode = NULL;
	currentNode = system->itemList->head;
	
	while(currentNode != NULL)
	{
		if (strcmp(ID, currentNode->data->id) == 0){
			return currentNode;
		}

		previousNode = currentNode;
		currentNode = currentNode->next;
	}

	return previousNode;
}

/* this code is from week 8's tutorial work */
/* this code is used to free the list when the program is shitting down **/
void freeList(VmSystem * system)
{
	Node * node = system->itemList->head;
	/* As you can see above, it starts at the head and gradually makes it way through 
	the list by freeing the data from the notes, and then the list itself.**/
	while(node != NULL)
	{
		Node * temp = node;
		node = node->next;

		free(temp->data);
		free(temp);

	}
	
	free(system->itemList);
}

/* this code is from week 8's tutorial work */
/* This code is to initialise a linkedlist when the program first launches.*/
List * initialiseLinkedList()
{
	List * linkedListForItems = malloc(sizeof(*linkedListForItems));
	
	linkedListForItems->head = NULL;
	linkedListForItems->size = 0;
	
	return linkedListForItems;
}

/* Inputs a char pointer and returns a price struct.**/
Price setPrice(char * inputPrice){
	int dollars, cents;
	char endChar;
	Price price;
	/* Splits the price into the seperate parts, the seperator being the '.'**/
	sscanf(inputPrice, "%d . %d%c", &dollars, &cents, &endChar);

	price.dollars = dollars;
	price.cents = cents;
	return price;
}
