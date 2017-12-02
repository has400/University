/******************************************************************************

** Student Name: Harrison Smith

** Student Number: s3658817

** Date of submission: Fri, 13 Oct 2017

** Course: COSC1076 / COSC2207, Semester 2, 2017

******************************************************************************/
#ifndef VM_STOCK_H
#define VM_STOCK_H

#include "vm_coin.h"

/**
 * The default stock level that all new stock should start with and the value
 * to use when restting stock.
 **/
#define DEFAULT_STOCK_LEVEL 20

#define STOCK_DELIM "|"

Price setPrice(char * inputPrice);
void addToList(VmSystem * system,List * list, Stock * data);
void freeList(VmSystem * system);
List * initialiseLinkedList();
Boolean freeNode(VmSystem * system, char * ID);
Node * searchNode(VmSystem * system, char * ID);
#endif
