package model;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see model.interfaces.GameEngineCallback
 * @author Harrison Smith - S3658817
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
	private Logger logger = Logger.getLogger("assignment1");
	
	
	public GameEngineCallbackImpl()
	{
		logger.setLevel(Level.FINE);
		logger.getGlobal().getParent().getHandlers()[0].setLevel(Level.FINE);
	}
	
	//Prints results when dice is rolling for a player.
	@Override
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
	{
		String output = String.format("%s: ROLLING %s", player.getPlayerName(), dicePair.toString());
		logger.log(Level.FINE, output);
	}
	
	//Prints final result of player.
	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine)
	{
		String output = String.format("%s: *RESULT* %s", player.getPlayerName(), result.toString());
		logger.log(Level.INFO, output);
	}
	
	//Prints results when dice is rolling for the house.
	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
		String output = String.format("%s: ROLLING %s", "House",dicePair.toString());
		logger.log(Level.FINE, output);
		
	}
	//Prints final result of the house.
	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		String output = String.format("%s: *RESULT* %s",  "House", result.toString());
		logger.log(Level.INFO, output);
	}

}
