
//S3658817
//This class handles the input of information and dictates
//what the user can do, eg create a new auction or sale.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RealEstateSystem {
	// Initializing collection used for storing Sale and Auction objects.
	static ArrayList<Sale> sales = new ArrayList<Sale>();

	public static void main(String[] args) {
		// On startup a method is triggered to read from file.
		readFromFileOnStartUp();
	}

	// This method is home of all of the main menu items which a user can input
	// a letter (non case sesnsitve) and it will take them to the correct menu.
	private static void mainMenu() {
		// Menu is printed out as a formmated string.
		System.out.println("*** Real Estate System Menu ***\n\n");
		System.out.print(String.format("%-25s %s\n", "Add New Sale", "A"));
		System.out.print(String.format("%-25s %s\n", "Submit Offer", "B"));
		System.out.print(String.format("%-25s %s\n", "Display Sales Summary", "C"));
		System.out.print(String.format("%-25s %s\n", "Add New Auction", "D"));
		System.out.print(String.format("%-25s %s\n", "Close Auction", "E"));
		System.out.print(String.format("%-25s %s\n", "Exit Program", "X"));

		// User enters a letter then will take them to a menu that they want
		Scanner userInputToProgram = new Scanner(System.in);
		if (userInputToProgram.hasNext()) {
			String userInput = userInputToProgram.nextLine();

			if (userInput.equalsIgnoreCase("A")) {
				addNewSale();
			}
			if (userInput.equalsIgnoreCase("B")) {
				addNewOffer();
			}
			if (userInput.equalsIgnoreCase("C")) {
				SalesSummary();
			}
			if (userInput.equalsIgnoreCase("D")) {
				Auction();
			}
			if (userInput.equalsIgnoreCase("E")) {
				closeAuction();
			}
			if (userInput.equalsIgnoreCase("X")) {
				// This saves the objects to a text file, and then exits.
				saveObjectsOnExit();
				System.exit(0);
			}

			System.out.println("Invalid input");
			mainMenu();
		}

	}

	// Method to add a new Sale object, users input a saleID, it then checks if
	// it is valid or not, if it valid then it will prompt the user to enter the
	// address and reserver price of the house.
	private static void addNewSale() {

		Scanner userInputAddNewSale = new Scanner(System.in);
		System.out.print(String.format("%-50s", "Enter Sale ID for new PropertySale:"));
		String saleID = userInputAddNewSale.nextLine();

		// This handles validation of saleID, whether or not it already exsits.
		for (int i = 0; i < sales.size(); i++) {
			if (sales.get(i).getsaleID().equals(saleID)) {
				System.out.println("Error - Sale ID " + saleID + " already exists in the system!");
				mainMenu();
				System.exit(0);
			}
		}

		System.out.print(String.format("%-50s", "Enter Property Adddress for new PropertySale:"));
		String propertyAddress = userInputAddNewSale.nextLine();
		System.out.print(String.format("%-50s", "Enter Reserve Price for new PropertySale:"));
		int reservePrice = userInputAddNewSale.nextInt();

		// A new sale Object is created from the variables that the user
		// entered and then added to the sales arraylist
		sales.add(new Sale(saleID, propertyAddress, reservePrice));

		// Confirms the property is added and outputs the details of the
		// property.
		System.out.println("\n" + "New Property Sale added successfully for property at " + propertyAddress);
		System.out.print((sales.get(sales.size() - 1)).getSaleDetails());
		mainMenu();
		userInputAddNewSale.close();

	}

	// This method is used to add a new offer, this function works on both Sale
	// and Auction objects.
	private static void addNewOffer() {

		Scanner userInputAddNewOffer = new Scanner(System.in);
		System.out.print(String.format("%-55s", "Enter Sale ID:"));
		String saleID = userInputAddNewOffer.nextLine();

		// this for loop checks if the sale ID entered is valid or not, if it
		// isn't it will say that it has not been found.
		for (int i = 0; i < sales.size(); i++) {

			// It checks if exists in the array list and if it is an instance of
			// Auction or Sale, it will then attempt to make a new offer.
			if (sales.get(i).getsaleID().equals(saleID) && sales.get(i) instanceof Auction) {
				System.out.println(String.format("%-55s %s", "Current offer:", sales.get(i).getcurrentOffer()));
				System.out.print(String.format("%-55s", "Enter new offer:"));
				int newOffer = userInputAddNewOffer.nextInt();

				// This makes use of the custom exception class so that if
				// makeOffer fails it will throw the necessary exception and it
				// will be output to console.
				try {
					((Auction) sales.get(i)).makeOffer(newOffer);
				} catch (OfferException e) {
					System.out.println(e);
				}

				mainMenu();
				System.exit(0);
				userInputAddNewOffer.close();

			} else if (sales.get(i).getsaleID().equals(saleID) && sales.get(i) instanceof Sale) {
				System.out.println(String.format("%-55s %s", "Current offer:", sales.get(i).getcurrentOffer()));
				System.out.print(String.format("%-55s", "Enter new offer:"));
				int newOffer = userInputAddNewOffer.nextInt();

				// Exactly like above the custom exception class
				// 'offerException'
				// is used here to see why the offer isn't accepted.
				try {
					sales.get(i).makeOffer(newOffer);
				} catch (OfferException e) {
					System.out.println(e);
				}

				mainMenu();
				System.exit(0);
				userInputAddNewOffer.close();

			}
		}

		System.out.println("Error - property sale ID " + saleID + " not found!");
		addNewOffer();
		System.exit(0);
	}

	// Used to print a summary of all of the objects
	private static void SalesSummary() {

		for (int j = 0; j < sales.size(); j++) {
			System.out.println(sales.get(j).getSaleDetails());
		}
		mainMenu();

	}

	// This handles adding a new auction.
	private static void Auction() {

		Scanner userInputAddNewSale = new Scanner(System.in);
		System.out.print(String.format("%-50s", "Enter Sale ID for new AuctionSale:"));
		String saleID = userInputAddNewSale.nextLine();

		// Checks if the saleID is already in the system.
		for (int i = 0; i < sales.size(); i++) {
			if (sales.get(i).getsaleID().equals(saleID)) {
				System.out.println("Error - Sale ID " + saleID + " already exists in the system!");
				mainMenu();
				System.exit(0);
			}
		}

		System.out.print(String.format("%-50s", "Enter Property Adddress for new AuctionSale:"));
		String propertyAddress = userInputAddNewSale.nextLine();

		System.out.print(String.format("%-50s", "Enter Reserve Price for new AuctionSale:"));
		int reservePrice = userInputAddNewSale.nextInt();

		// a new Auction object is created and added to the sales arraylist.
		sales.add(new Auction(saleID, propertyAddress, reservePrice));

		// The information of the new auction is printed.
		System.out.println("\n" + "New Auction Sale added successfully for property at " + propertyAddress);
		System.out.print((sales.get(sales.size() - 1)).getSaleDetails());
		mainMenu();
		userInputAddNewSale.close();

	}

	// This method is how a user closes auctions.
	private static void closeAuction() {
		Scanner userInputAddNewSale = new Scanner(System.in);
		System.out.print(String.format("%-50s", "Enter ID of Auction to be closed:"));
		String saleID = userInputAddNewSale.nextLine();

		// For loop to see if sale id that user inputs exists in the system, if
		// it doesn't it exists it starts this method again.
		for (int i = 0; i < sales.size(); i++) {

			if (sales.get(i).getsaleID().equals(saleID) && sales.get(i) instanceof Auction) {

				// Checks if the auction has already been closed.
				if (((Auction) sales.get(i)).getPropertyStatus().equals("PASSED IN")
						|| ((Auction) sales.get(i)).getPropertyStatus().equals("SOLD")) {
					System.out.println("Auction " + saleID + " has already been closed.");
					mainMenu();
					System.exit(0);
				}

				// If the auction hasn't already been canceled, it is cancelled.
				((Auction) sales.get(i)).closeAuction();

				// The correct message on the situation of the closing of the
				// house is then displayed.
				if (((Auction) sales.get(i)).getPropertyStatus().equals("PASSED IN")) {
					System.out.println("Auction " + saleID + " has ended - property has been: PASSED IN");
				} else {
					System.out.println("Auction " + saleID + " has ended - property has been: SOLD");
				}
				mainMenu();
				System.exit(0);

				// Outputs when the sale id is a sale rather than an auction
			} else if (sales.get(i).getsaleID().equals(saleID) && sales.get(i) instanceof Sale) {
				System.out.println("Error - property sale ID " + saleID + " is not an auction!");
				mainMenu();
				System.exit(0);
			}

		}

		System.out.println("Error - property sale ID " + saleID + " not found!");
		closeAuction();
		userInputAddNewSale.close();
		System.exit(0);
	}

	// This method saves the objects when the user wants to exit the application
	private static void saveObjectsOnExit() {
		try {
			// Calls a two printwriters, going to two files saleidfile.txt and
			// saleidfilebackup.txt, they also don't appended meaning it is
			// rewritten each time the user leaves the application.
			PrintWriter outputStream = new PrintWriter(new FileOutputStream(new File("SaleIDFile.txt"), false));
			PrintWriter outputStreamBACKUP = new PrintWriter(
					new FileOutputStream(new File("SaleIDFileBackup.txt"), false));

			// this forloop prints each object in both files, in the special
			// format so it can be read when application opens again
			for (int j = 0; j < sales.size(); j++) {
				outputStream.println(sales.get(j).saveSaleDetails());
				outputStreamBACKUP.println(sales.get(j).saveSaleDetails());
			}
			outputStream.flush();
			outputStreamBACKUP.flush();
			outputStream.close();
			outputStreamBACKUP.close();

		} catch (IOException e) {
			System.out.println(e);
		}

	}

	// This method reads from the file and then recreates the objects
	private static void readFromFileOnStartUp() {

		try {
			// when the program launches, it tries to load
			// SaleIDFile.txt however if that fails it will catch the error and
			// try with the backup file, if that fails it will prompt the user
			// to say yes or no to use sample data.
			Scanner input = new Scanner("SaleIDFile.txt");
			File file = new File(input.nextLine());
			input = new Scanner(file);

			int i = 0;

			while (input.hasNextLine()) {
				// Takes the string of each line and then puts it into a string
				// array It does this putting each element which is separated by
				// a ':'into the array. A ':' was used because commas are used
				// in addresses of houses and it would then cause problems with
				// the loading the file.
				String line = input.nextLine();
				String[] SalesInformation = line.split(":");

				// The first word in the line of the text file denotes if the
				// object is a sale or an auction then base off this it will
				// either create a new sale object or a new auction object
				if (SalesInformation[0].equals("SALE")) {

					// the relevant information is entered to the object
					// recreate the object exactly the same as the last time as
					// the program is exited.
					String saleID = SalesInformation[1];
					String propertyAddress = SalesInformation[2];
					String currentOffer = SalesInformation[3];
					String ReservePrice = SalesInformation[4];
					String acceptingOffers = SalesInformation[5];

					sales.add(new Sale(saleID, propertyAddress, Integer.parseInt(ReservePrice)));
					sales.get(i).setAcceptingOffers(Boolean.parseBoolean(acceptingOffers));
					sales.get(i).setcurrentOffer(Integer.parseInt(currentOffer));

				} else if (SalesInformation[0].equals("AUCTION")) {

					// This is the same as above however, 6 and 7 are added.
					String saleID = SalesInformation[1];
					String propertyAddress = SalesInformation[2];
					String currentOffer = SalesInformation[3];
					String ReservePrice = SalesInformation[4];
					String acceptingOffers = SalesInformation[5];
					String PropertyStatus = SalesInformation[6];
					String highestBidder = SalesInformation[7];

					sales.add(new Auction(saleID, propertyAddress, Integer.parseInt(ReservePrice)));
					sales.get(i).setAcceptingOffers(Boolean.parseBoolean(acceptingOffers));
					sales.get(i).setcurrentOffer(Integer.parseInt(currentOffer));

					((Auction) sales.get(i)).setPropertyStatus(PropertyStatus);
					((Auction) sales.get(i)).setHighestBidder(highestBidder);
				}
				// Increments each loop
				i++;

			}
			mainMenu();
			System.exit(0);
			input.close();

			// As explained above if file not found then it will try to read
			// from the backup file.
		} catch (FileNotFoundException ExceptionNoFile) {

			// Prints the file not found exception and a backup message.
			// other than that nothing has changed. since this is the case the
			// any explanation about the code can found using the comments in
			// the block of code above
			System.out.println(ExceptionNoFile);
			System.out.println("The backup file will now attempt to be loaded");
			try {
				Scanner input = new Scanner("SaleIDFileBackup.txt");
				File file = new File(input.nextLine());
				input = new Scanner(file);

				int i = 0;
				while (input.hasNextLine()) {
					String line = input.nextLine();
					String[] SalesInformation = line.split(":");
					if (SalesInformation[0].equals("SALE")) {

						String saleID = SalesInformation[1];
						String propertyAddress = SalesInformation[2];
						String currentOffer = SalesInformation[3];
						String ReservePrice = SalesInformation[4];
						String acceptingOffers = SalesInformation[5];

						sales.add(new Sale(saleID, propertyAddress, Integer.parseInt(ReservePrice)));
						sales.get(i).setAcceptingOffers(Boolean.parseBoolean(acceptingOffers));
						sales.get(i).setcurrentOffer(Integer.parseInt(currentOffer));

					} else if (SalesInformation[0].equals("AUCTION")) {

						String saleID = SalesInformation[1];
						String propertyAddress = SalesInformation[2];
						String currentOffer = SalesInformation[3];
						String ReservePrice = SalesInformation[4];
						String acceptingOffers = SalesInformation[5];
						String PropertyStatus = SalesInformation[6];
						String highestBidder = SalesInformation[7];

						sales.add(new Auction(saleID, propertyAddress, Integer.parseInt(ReservePrice)));
						sales.get(i).setAcceptingOffers(Boolean.parseBoolean(acceptingOffers));
						sales.get(i).setcurrentOffer(Integer.parseInt(currentOffer));

						((Auction) sales.get(i)).setPropertyStatus(PropertyStatus);
						((Auction) sales.get(i)).setHighestBidder(highestBidder);
					}
					i++;

				}

				mainMenu();
				input.close();

				// If the backup file isnt found, it will prompt the user to
				// either select yes or no on to use sample data.
			} catch (FileNotFoundException ExceptionNoBackupFile) {
				Scanner userInputNoBackup = new Scanner(System.in);
				System.out.println(ExceptionNoBackupFile);
				System.out.println("Do you want to load the program with sample data?");
				System.out.println("y/n");
				String userYesOrNo = userInputNoBackup.nextLine();

				// Depending on if the user wants to use sample data or not,
				// they will select what they want and the application will
				// launch with these settings.
				if (userYesOrNo.equalsIgnoreCase("y") || userYesOrNo.equalsIgnoreCase("yes")) {
					sales.add(new Sale("LT2311SAMPLE", "23 Hooli street", 340));
					sales.add(new Sale("RT3900SAMPLE", "2 Mills street", 3890));
					sales.add(new Sale("AZ310SAMPLE", "100 O'Neil road", 9000));
					sales.add(new Auction("PP103SAMPLE", "1 Piper road", 4520));
					sales.add(new Auction("PAQQE1SAMPLE", "2 Frog street", 1000));

					mainMenu();

				} else if (userYesOrNo.equalsIgnoreCase("n") || userYesOrNo.equalsIgnoreCase("no")) {
					mainMenu();
				}

			}
		}
	}
}
