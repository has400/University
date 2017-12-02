
//S3658817
//This class is the specific Auction sales in the program
//it is different from Sale because there are 
//bidders and doesn't automatically close.
import java.util.Scanner;

public class Auction extends Sale {
	// Initializing the variable
	String highestBidder;

	// This is the constructor for the Auction object, it also makes the value
	// of highestbidder to the default message
	public Auction(String saleID, String propertyAddress, int reservePrice) {
		super(saleID, propertyAddress, reservePrice);
		highestBidder = "NO BIDS PLACED";
	}

	// Overrides the default Sale getPropertyStatus to the Auction object
	// this property status returns different values from the same Sale class
	// method, it checks if accepting offers is true or false and then will
	// supply the correct message based off this condition.
	@Override
	public String getPropertyStatus() {
		String propertyStatusOfOffer = null;

		if (getacceptingOffers() == true) {
			propertyStatusOfOffer = "ACCEPTING BIDS";
		} else if (getacceptingOffers() == false) {
			if (getreservePrice() < getcurrentOffer()) {
				propertyStatusOfOffer = "SOLD";
			} else if (getreservePrice() > getcurrentOffer()) {

				propertyStatusOfOffer = "PASSED IN";
			}
		}
		return propertyStatusOfOffer;
	}

	// Setter method that is used when a file is read, to recreate an object
	// this was needed for it to have ALL of its information
	public String setPropertyStatus(String propertyStatusOfOffer) {
		return propertyStatusOfOffer;
	}

	// Setter method that is used once again when a file is read, it needs this
	// to recreate objects.
	public String setHighestBidder(String highestBidder) {
		this.highestBidder = highestBidder;
		return highestBidder;
	}

	// This method is to close an auction, it checks accepting offers and
	// depending on the condition will return the boolean value
	public boolean closeAuction() {
		boolean outcome = false;
		if (getacceptingOffers() == false) {
			outcome = false;
		}if (getacceptingOffers() == true) {
			setAcceptingOffers(false);
			outcome = true;
		}
		return outcome;
	}

	// When a user creates an offer that is an Auction object they need to input
	// the offer price and then depending if accepting offers is true or false
	// it will take them to more if statements, depending on what they entered
	// if accepting offers is false a new OfferException will be thrown and the
	// user will be notified, however if it true the user will need to input the
	// name of the highest bidder
	@Override
	public void makeOffer(int offerPrice) throws OfferException {

		if (getacceptingOffers() == false) {
			throw new OfferException("Not accepting offers");
		} else if (offerPrice <= getcurrentOffer()) {
			throw new OfferException("Error - New offer must be higher than current offer!\n" + "Offer rejected!");
		} else {
			
			setcurrentOffer(offerPrice);
			if (offerPrice <= getreservePrice()) {
				System.out.print(String.format("%-55s", "Enter name of the highest bidder:"));
				
				Scanner highestBidderScanner = new Scanner(System.in);
				highestBidder = highestBidderScanner.next();
				
				System.out.println("Offer accepted! (offer is below reserve price)");

			}
			if (offerPrice >= getreservePrice() && getacceptingOffers() == true) {
				System.out.print(String.format("%-55s", "Enter name of the highest bidder:"));
				
				Scanner highestBidderScanner = new Scanner(System.in);
				highestBidder = highestBidderScanner.next();
				
				System.out.println("Offer accepted! (reserve price has been met/exceeded)");

			}

		}

	}

	// This formats and prints strings which contain all of the information of
	// the Auction object. It it used for the summary method in
	// RealEstateSystem. since the Sale getSaleDetails is overridden by this one
	// it also prints out the highestbidder information.
	@Override
	public String getSaleDetails() {
		
		String firstLine = String.format("%-20s %s\n", "Sale ID:", getsaleID());
		String secondLine = String.format("%-20s %s\n", "Property Address:", getpropertyAddress());
		String thirdLine = String.format("%-20s %s\n", "Current Offer:", getcurrentOffer());
		String forthLine = String.format("%-20s %s\n", "Reserve Price:", getreservePrice());
		String fifthLine = String.format("%-20s %s\n", "Accepting offers:", getacceptingOffers());
		String sixthLine = String.format("%-20s %s\n", "Sale Status:", getPropertyStatus());
		String seventhLine = String.format("%-20s %s\n", "Highest Bidder:", highestBidder);

		return (firstLine + secondLine + thirdLine + forthLine + fifthLine + sixthLine + seventhLine);
	}

	// This method is used for saving the details of the Auction object to a text
	// file, the variables are separated by a ':' so when the file is read it
	// knows where to split the information.
	@Override
	public String saveSaleDetails() {
		
		String firstLine = getsaleID();
		String secondLine = getpropertyAddress();
		int thirdLine = getcurrentOffer();
		int forthLine = getreservePrice();
		boolean fifthLine = getacceptingOffers();
		String sixthLine = getPropertyStatus();
		String seventhLine = highestBidder;

		return ("AUCTION" + ":" + firstLine + ":" + secondLine + ":" + thirdLine + ":" + forthLine + ":" + fifthLine + ":" + sixthLine + ":" + seventhLine);
	}
}