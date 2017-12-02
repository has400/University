/******************************************************************************

** Student Name: Harrison Smith

** Student Number: s3658817

** Date of submission: Fri, 13 Oct 2017

** Course: COSC1076 / COSC2207, Semester 2, 2017

******************************************************************************/
#ifndef VM_OPTIONS_H
#define VM_OPTIONS_H

#define MAX_LINE 512
#define EXTRACHARS 2

#include "vm_stock.h"
/**
 * These functions are called when a specific menu option is selected by the user
 * and also a a place for vital functions which is used by the system to initilise.
 **/
Boolean systemInit(VmSystem * system);
void systemFree(VmSystem * system);

Boolean loadData(VmSystem * system, const char * stockFileName, const char * coinsFileName);
Boolean loadStock(VmSystem * system, const char * fileName);
Boolean loadCoins(VmSystem * system, const char * fileName);
Boolean saveStock(VmSystem * system);
Boolean saveCoins(VmSystem * system);

void displayItems(VmSystem * system);
void purchaseItem(VmSystem * system);
void saveAndExit(VmSystem * system);
void addItem(VmSystem * system);
void removeItem(VmSystem * system);
void displayCoins(VmSystem * system);
void resetStock(VmSystem * system);
void resetCoins(VmSystem * system);
void abortProgram(VmSystem * system);
#endif
