package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * @author Harrison Smith - S3658817
 */
public class GameEngineImpl implements GameEngine {

	Collection<Player> gamePlayers = new ArrayList<Player>();
	ArrayList<GameEngineCallback> gameEngineCallbackList = new ArrayList<>();
	
	//Places a bet for a player.
	@Override
	public boolean placeBet(Player player, int bet) {
		if (player.placeBet(bet) == true) {
			return true;
		}

		return false;
	}

	//Rolls the player.
	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
		DicePair rollresult = rollingDice(player, "player", initialDelay, finalDelay, delayIncrement);
		player.setRollResult(rollresult);

		// Goes through the array and updates the game engine call back.
		for (GameEngineCallback gameEngineCallback : gameEngineCallbackList) {
			gameEngineCallback.result(player, rollresult, this);
		}
	}

	//Rolls the house.
	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		DicePair rollresult = rollingDice(null, "house", initialDelay, finalDelay, delayIncrement);
		
		// Goes through the array and updates the game engine call back.
		for (GameEngineCallback gameEngineCallback : gameEngineCallbackList) {
			gameEngineCallback.houseResult(rollresult, this);
		}

		// Display result, of bets.
		compareResults(rollresult);

	}
	
	//General rolling of the dice method used by the roll methods.
	private DicePair rollingDice(Player player, String entity, int initialDelay, int finalDelay, int delayIncrement) {
		int dice1roll;
		int dice2roll;

		int diceMinVal = 1;

		Random seed = new Random();
		DicePair rollresult;
		
		//Rolls dice until the time is up.
		while (initialDelay < finalDelay) {
			dice1roll = seed.nextInt(NUM_FACES) + diceMinVal;
			dice2roll = seed.nextInt(NUM_FACES) + diceMinVal;

			rollresult = new DicePairImpl(dice1roll, dice2roll, NUM_FACES);

			for (GameEngineCallback gameEngineCallback : gameEngineCallbackList) {
				if (entity.equals("house")) {
					gameEngineCallback.intermediateHouseResult(rollresult, this);
				} else {
					gameEngineCallback.intermediateResult(player, rollresult, this);
				}
			}

			try {
				Thread.sleep(delayIncrement);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			initialDelay += delayIncrement;
		}
		
		dice1roll = seed.nextInt(NUM_FACES) + diceMinVal;
		dice2roll = seed.nextInt(NUM_FACES) + diceMinVal;
		
		//returns with the proper roll of the player/house.
		return rollresult = new DicePairImpl(dice1roll, dice2roll, NUM_FACES);
	}
	
	//Compares the results of the players versus the house and hands outs money.
	private void compareResults(DicePair rollresult) {
		for (Player player : this.gamePlayers) {
			if (player.getBet() == 0) {
				continue;
			}
			// if the player wins
			if (playerResultDiceValue(player) > houseResultDiceValue(rollresult)) {
				player.setPoints(player.getPoints() + player.getBet());
				player.placeBet(0);
				player.setRollResult(null);
			} // if the player draws
			else if (playerResultDiceValue(player) == houseResultDiceValue(rollresult)) {
				player.placeBet(0);
				player.setRollResult(null);
			}// If the players loses
			else if (playerResultDiceValue(player) < houseResultDiceValue(rollresult)){
				player.setPoints(player.getPoints() - player.getBet());
				player.placeBet(0);
				player.setRollResult(null);
			}
		}

	}
	
	//returns the total of the dice roll for the house.
	private int houseResultDiceValue(DicePair rollresult) {
		int total = rollresult.getDice1() + rollresult.getDice2();
		return total;
	}
	
	//returns the total of the dice roll for the player.
	private int playerResultDiceValue(Player player) {
		int total = player.getRollResult().getDice1() + player.getRollResult().getDice2();
		return total;
	}
	
	//Adds a player to the game.
	@Override
	public void addPlayer(Player player) {
		this.gamePlayers.add(player);
	}
	
	//Returns a player for a given id.
	@Override
	public Player getPlayer(String id) {
		for (Player players : this.gamePlayers) {
			if (players.getPlayerId().equals(id)) {
				return players;
			}
		}
		return null;
	}
	
	//Removes a player from the game.
	@Override
	public boolean removePlayer(Player player) {
		for (Player players : this.gamePlayers) {
			if (players == player) {
				this.gamePlayers.remove(players);
				return true;
			}
		}
		return false;
	}

	//Adds another gamecallback.
	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		gameEngineCallbackList.add(gameEngineCallback);
	}

	//removes game engine callback if it exsits. 
	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {

		if (gameEngineCallbackList.contains(gameEngineCallback)) {
			gameEngineCallbackList.remove(gameEngineCallback);
			return true;
		}

		return false;
	}
	
	//Returns the gameplayers of the current game.
	@Override
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(gamePlayers);
	}

}
