import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 * Random guessing player.
 * This player is for task B.
 *
 * You may implement/extend other interfaces or classes, but ensure ultimately
 * that this class implements the Player interface (directly or indirectly).
 */
public class RandomGuessPlayer extends AbstractPlayer implements Player
{

    /**
     * Loads the game configuration from gameFilename, and also store the chosen
     * person.
     *
     * @param gameFilename Filename of game configuration.
     * @param chosenName Name of the chosen person for this player.
     * @throws IOException If there are IO issues with loading of gameFilename.
     *    Note you can handle IOException within the constructor and remove
     *    the "throws IOException" method specification, but make sure your
     *    implementation exits gracefully if an IOException is thrown.
     */


    public RandomGuessPlayer(String gameFilename, String chosenName)
        throws IOException
    {
	super(gameFilename,chosenName);
    } // end of RandomGuessPlayer()


    public Guess guess() {
	//if only one person left guess that person
	if (possiblePeople.size() == 1)
	{
	    return new Guess(Guess.GuessType.Person, "", possiblePeople.get(0).name);
	}
	//otherwise guess a random value of a random attribute of a random person left,
	//if there is at least 1 person with a different value for that attribute.
	String attributeChosen = "";
	String attributeValue = "";
	while (true)
	{
	    int count = 0;
	    int randPerson = (int) (Math.random() * 100) % possiblePeople.size();
	    Person tempPerson = possiblePeople.get(randPerson);
	    int randAttribute = (int) (Math.random() * 100) % attributes.size();
	    attributeChosen = attributes.get(randAttribute);
	    attributeValue = tempPerson.attributeList.get(randAttribute);
	    for (Person p : possiblePeople)
	    {
		if (!p.attributeList.get(randAttribute).equals(attributeValue))
		{
		    count++;
		}
	    }
	    if (count > 0)
	    {
		break;
	    }
	}
        return new Guess(Guess.GuessType.Attribute, attributeChosen, attributeValue);
    } // end of guess()


    public boolean answer(Guess currGuess) {

	//checks if the Guess is that of a player.
	if (currGuess.getType().equals(Guess.GuessType.Person)) 
	{
	    //Checks if the guess of a persons name is the same of the initial chosen person.
	    return currGuess.getValue().equals(chosenPerson.name);
	}
	
	//checks if the Guess is that of a attribute.
	if (currGuess.getType().equals(Guess.GuessType.Attribute)) 
	{
	    //Checks that the guessed attribute is one of the initial chosen person's attributes value.
	    String chosenAttribute = chosenPerson.attributeList.get(attributes.indexOf(currGuess.getAttribute()));
	    if (chosenAttribute.equals(currGuess.getValue())) 
	    {
		return true;
	    }
	    return false;// if chosen person doesnt have the attribute.
    	}
        return false;
    } // end of answer()


    public boolean receiveAnswer(Guess currGuess, boolean answer) {
	// Player Guess
	if (currGuess.getType().equals(Guess.GuessType.Person)) 
	{
	    // returns the answer
	    return answer;
	}
	//temporary list to store players that satisfy the guess and answer
	List<Person> possiblePeopleTempAnswer = new ArrayList<>();
	//checks which players satisfy the conditions, then adds them to temp list	
	for (Person p : possiblePeople) 
	{
	    if (p.attributeList.get(attributes.indexOf(currGuess.getAttribute())).equals(currGuess.getValue()) == answer) 
	    {
	        possiblePeopleTempAnswer.add(p);
	    }
	}	
	// Assign the temp list with the new playerlist to possiblePlayers
	possiblePeople = possiblePeopleTempAnswer;
        return false;
    } // end of receiveAnswer()

} // end of class RandomGuessPlayer
