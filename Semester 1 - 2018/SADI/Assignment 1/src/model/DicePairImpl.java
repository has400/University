package model;

import model.interfaces.DicePair;
/**
 * @author Harrison Smith - S3658817
 */
public class DicePairImpl implements DicePair{
	
	int dice1;
	int dice2;
	int numFaces;
	
	//Dice pair constructor.
	public DicePairImpl(int diceroll1, int diceroll2,int numFaces) {
		this.dice1 = diceroll1;
		this.dice2 = diceroll2;
		this.numFaces = numFaces;
		
	}
	
	//returns dice 1 value.
	@Override
	public int getDice1() {
		return this.dice1;
	}
	
	//returns dice 2 value.
	@Override
	public int getDice2() {
		return this.dice2;
	}
	
	//returns how many sides on a dice.
	@Override
	public int getNumFaces() {
		return this.numFaces;
	}
	
	//returns dice information.
	@Override
	public String toString() {
		String output = "Dice 1: " + this.dice1 + ",  Dice 2: "+ this.dice2 + " .. Total: " + (this.dice1+this.dice2);
		return output;
	}
	
}
