//S3658817
//This class is a custom exception class which when triggered by a throw
//will output the message for the user to know exactly what went wrong. 
public class OfferException extends Exception {
	
	public OfferException(String Error){
		super(Error);
	}
	
}
