package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

/**
 * @author Harrison Smith - S3658817
 */
public class SimplePlayer implements Player {

	String playerName;
	String playerID;
	int gamePoints;
	int bet = 0;
	DicePair rollResult;
	
	//Player constructor. 
	public SimplePlayer(String playerID, String playerName, int gamePoints) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.gamePoints = gamePoints;
	}
	
	//gets the player name
	@Override
	public String getPlayerName() {
		return this.playerName;
	}
	
	//sets the player name
	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	//gets game points of a player.
	@Override
	public int getPoints() {
		return this.gamePoints;
	}
	
	//Sets gamepoints of a player.
	@Override
	public void setPoints(int points) {
		this.gamePoints = points;
	}
	
	//Returns the player id
	@Override
	public String getPlayerId() {
		return this.playerID;
	}
	
	//Assigns a value to the bet variable.
	@Override
	public boolean placeBet(int bet) {
		
		if (this.gamePoints >= bet && bet >= 0) {
			this.bet = bet;
			return true;
		}
		
		return false;
	}
	
	//gets what the bet is.
	@Override
	public int getBet() {
		return this.bet;
	}
	
	//Gets the roll result.
	@Override
	public DicePair getRollResult() {
		return this.rollResult;
	}
	
	//sets roll result.
	@Override
	public void setRollResult(DicePair rollResult) {
		this.rollResult = rollResult;	
	}

	//Outputs information about the player.
	@Override
	public String toString() {
		String output = "Player: id="+this.playerID+", name="+this.playerName+", points="+this.gamePoints;
		return output;
	}
	
}
