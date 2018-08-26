import java.io.*;
import java.util.*;

/**
 * Binary-search based guessing player. This player is for task C.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class BinaryGuessPlayer extends AbstractPlayer implements Player {

	/**
	 * Loads the game configuration from gameFilename, and also store the chosen
	 * person.
	 *
	 * @param gameFilename
	 *            Filename of game configuration.
	 * @param chosenName
	 *            Name of the chosen person for this player.
	 * @throws IOException
	 *             If there are IO issues with loading of gameFilename. Note you can
	 *             handle IOException within the constructor and remove the "throws
	 *             IOException" method specification, but make sure your
	 *             implementation exits gracefully if an IOException is thrown.
	 */
	public BinaryGuessPlayer(String gameFilename, String chosenName) throws IOException {
		super(gameFilename, chosenName);
	} // end of BinaryGuessPlayer()

	public Guess guess() {
		// Guesses a person if there is only one left.
		if (possiblePeople.size() == 1) {
			return new Guess(Guess.GuessType.Person, "", possiblePeople.get(0).name);
		}
		
		String AttributeName = "";
		String AttributeVal = "";
		int currentDifference = Integer.MAX_VALUE;

		// Gets an attribute name and value so it can guess it.
		for (int i = 0; i < attributes.size(); i++) {
			for (int j = 0; j < attributeValues.get(i).size(); j++) {
				int count = 0;

				// Sees how many possible people out of the remaining persons have the attribute value.
				for (int x = 0; x < possiblePeople.size(); x++) {
					List<String> AttributeList = possiblePeople.get(x).attributeList;
					if (AttributeList.get(i).equals(attributeValues.get(i).get(j))) {
						count++;
					}
				}

				// Try to eliminate 50% of the population of people.
				int playersHalf = (possiblePeople.size() / 2);
				//Sees the difference between half the people and guessed attribute.
				int difference = (playersHalf - count);

				// Attempts to get a value which splits the people in 50/50.
				if (difference <= currentDifference && difference >= 0) {
					// Assigns the variables outside the forloop to the attribute name, value and difference.
					// So they can be used to either find a better attribute or to guess a player.
					AttributeName = attributes.get(i);
					AttributeVal = attributeValues.get(i).get(j);
					currentDifference = difference;
				}
			}
		}
		return new Guess(Guess.GuessType.Attribute, AttributeName, AttributeVal);
	} // end of guess()

	public boolean answer(Guess currGuess) {
		//checks if the Guess is that of a player.
		if (currGuess.getType().equals(Guess.GuessType.Person)) {
			//Checks if the guess of a persons name is the same of the initial chosen person.
			return currGuess.getValue().equals(chosenPerson.name);
		}
		
		//checks if the Guess is that of a attribute.
		if (currGuess.getType().equals(Guess.GuessType.Attribute)) {
			//Checks that the guessed attribute is one of the initial chosen person's attributes value.
			if (chosenPerson.attributeList.get(attributes.indexOf(currGuess.getAttribute())).equals(currGuess.getValue())) {
				return true;
			}
			return false;// if chosen person doesnt have the attribute.
		}

		return false;
	} // end of answer()

	public boolean receiveAnswer(Guess currGuess, boolean answer) {
		// Player Guess
		if (currGuess.getType().equals(Guess.GuessType.Person)) {
			// When it guesses the correct player, it will 
			// return who guessed the player either won or drew the game.
			return true;
		}

		// Attributes Guess, if the answer was true.
		if (answer == true) {
			// Temp Arraylist which gets all the players with the correct guess that the user entered.
			List<Person> possiblePeopleTempAnswer = new ArrayList<>();
			
			// Goes through the possible people and checks that it satisfy what is said above. (only playuers which match the correct guess)
			for (Person p : possiblePeople) {
				if (p.attributeList.get(attributes.indexOf(currGuess.getAttribute())).equals(currGuess.getValue())) {
					possiblePeopleTempAnswer.add(p);
				}
			}

			// Assign the temp arraylist with the new playerlist to 
			possiblePeople = possiblePeopleTempAnswer;
		}
		
		// Attributes Guess, if the answer was false.
		if (answer == false) {
			//Temp Arraylist which gets all the players which dont have the value of the guess that the user entered.
			List<Person> possiblePeopleTempAnswer = new ArrayList<>();
			
			// Goes through the possible people and checks that it satisfy what is said above. (only players which dont match the guess)
			for (Person p : possiblePeople) {
				if (!p.attributeList.get(attributes.indexOf(currGuess.getAttribute())).equals(currGuess.getValue())) {
					possiblePeopleTempAnswer.add(p);
				}
			}
			
			// Assign the temp arraylist with the new playerlist to 
			possiblePeople = possiblePeopleTempAnswer;

		}
		
		
		return false;
	} // end of receiveAnswer()

} // end of class BinaryGuessPlayer
