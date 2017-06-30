//S3658817
//This is for Sale type of property  in the application
//it is different from the Auction type of sale.

public class Sale {

	// Initializing the instance variables
	String saleID;
	String propertyAddress;
	int currentOffer;
	int reservePrice;
	boolean acceptingOffers = true;

	// Constructor to hold information, used also to create objects in the
	// RealEstateSystem class.
	public Sale(String saleID, String propertyAddress, int reservePrice) {
		this.saleID = saleID;
		this.propertyAddress = propertyAddress;
		this.reservePrice = reservePrice;
	}

	// Getters (Accessors)
	// The way these work are, they're called in the RealEstateSystem class
	// in order to find information about the current object.
	public String getsaleID() {
		return saleID;
	}

	public String getpropertyAddress() {
		return propertyAddress;
	}

	public int getreservePrice() {
		return reservePrice;
	}

	public int getcurrentOffer() {
		return currentOffer;
	}

	public boolean getacceptingOffers() {
		return acceptingOffers;
	}

	// Setters (Mutators)
	// These work in the opposite way of the getters, when called in the
	// RealEstateSystem class they SET information of the current object.
	public void setcurrentOffer(int currentOffer) {
		this.currentOffer = currentOffer;

	}

	public void setAcceptingOffers(boolean acceptingOffers) {
		this.acceptingOffers = acceptingOffers;
	}

	// This method is for the user to input an offer, it is called from the
	// RealEstateSystem class by inputting the offerPrice (INT). It uses the
	// custom exception class named OfferException. It will go through the IF
	// statements and perform the correct action to notify the user if their
	// offer is accepted or denied.
	public void makeOffer(int offerPrice) throws OfferException {
		if (getacceptingOffers() == false) {

			throw new OfferException("Not accepting offers");
		} else if (offerPrice <= currentOffer) {
			
			throw new OfferException("Error - New offer must be higher than current offer!\n" + "Offer rejected!");
		} else {
			if (offerPrice <= reservePrice) {
				System.out.println("Offer accepted! (Offer is below reserve price)");
			}
			if (offerPrice >= reservePrice && getacceptingOffers() == true) {
				System.out.println("Offer accepted! (reserve price has been met/exceeded)");
				setAcceptingOffers(false);
			}
			currentOffer = offerPrice;
		}
	}

	// THis method checks the status of the property, it checks if accepting
	// offers is true or false and then outputs the correct message for the
	// specific object.
	public String getPropertyStatus() {
		String propertyStatusOfOffer = null;

		if (acceptingOffers == true) {
			propertyStatusOfOffer = "ON SALE";
		} else if (acceptingOffers == false) {
			propertyStatusOfOffer = "SOLD";
		}
		return propertyStatusOfOffer;
	}

	// This formats and prints strings which contain all of the information of
	// the object. it it used for the summary method in RealEstateSystem.
	public String getSaleDetails() {
		
		String firstLine = String.format("%-20s %s\n", "Sale ID:", saleID);
		String secondLine = String.format("%-20s %s\n", "Property Address:", propertyAddress);
		String thirdLine = String.format("%-20s %s\n", "Current Offer:", currentOffer);
		String forthLine = String.format("%-20s %s\n", "Reserve Price:", reservePrice);
		String fifthLine = String.format("%-20s %s\n", "Accepting offers:", acceptingOffers);
		String sixthLine = String.format("%-20s %s\n", "Sale Status:", this.getPropertyStatus());

		return (firstLine + secondLine + thirdLine + forthLine + fifthLine + sixthLine);
	}

	// This method is used for saving the details of the Sale object to a text
	// file, the variables are separated by a ':' so when the file is read it
	// knows where to split the information.
	public String saveSaleDetails() {
		
		String firstLine = saleID;
		String secondLine = propertyAddress;
		int thirdLine = currentOffer;
		int forthLine = reservePrice;
		boolean fifthLine = acceptingOffers;

		return ("SALE" + ":" + firstLine + ":" + secondLine + ":" + thirdLine + ":" + forthLine + ":" + fifthLine);
	}

}