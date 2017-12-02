import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Transaction {
	static ArrayList<String> Transaction = new ArrayList<String>();

	public void saveTransaction(String username, String Date, String numberBoughtArray, double money)
			throws IOException {
		PrintWriter SaveOnExit = new PrintWriter(
				new FileOutputStream(new File("src\\TransactionList\\" + username + ".txt"), true));
		SaveOnExit.println(username + "|" + Date + "|" + numberBoughtArray + "|" + money);
		SaveOnExit.close();

		for (int i = 0; i < ShareMarketMain.users.size(); i++) {
			if (ShareMarketMain.users.get(i).getUsername().equals(username)) {
				numberBoughtArray = numberBoughtArray.replaceAll(" ", "");
				String[] stringInts = numberBoughtArray.split(",");

				((TradingAccount) ShareMarketMain.users.get(i)).setData(Integer.parseInt(stringInts[0]),
						Integer.parseInt(stringInts[1]), Integer.parseInt(stringInts[2]),
						Integer.parseInt(stringInts[3]), Integer.parseInt(stringInts[4]),
						Integer.parseInt(stringInts[5]));
				((TradingAccount) ShareMarketMain.users.get(i)).setMoney(money);

			}
		}

	}

	public static void readTransactions() throws ParseException {

		for (int i = 0; i < ShareMarketMain.users.size(); i++) {
			double total = 0;
			String arrayListUsername = null;
			Scanner input = new Scanner("src\\TransactionList\\" + ShareMarketMain.users.get(i).getUsername() + ".txt");
			File file = new File(input.nextLine());

			try {
				input = new Scanner(file);
			} catch (FileNotFoundException e) {
				// This means that if the user is only a customer it will skip
				// them instead of creating an error message.yu
				continue;
			}

			while (input.hasNextLine()) {

				String line = input.nextLine();
				line = line.replaceAll("\\[", "").replaceAll("\\]", "");

				String[] SalesInformation = line.split("\\|");

				String Username = SalesInformation[0];
				String dateString = SalesInformation[1];
				String amountSpent = SalesInformation[3];
				Calendar StockCalender = Calendar.getInstance();
				DateFormat StockDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

				Date todayDate = new Date();

				StockCalender.setTime(todayDate);
				// goes back 1 week on the stock exchange.
				StockCalender.add(Calendar.HOUR, -120);
				todayDate = StockCalender.getTime();

				Date transactionDate = StockDateFormat.parse(dateString);

				if (ShareMarketMain.users.get(i).getUsername().equals(Username) && transactionDate.after(todayDate)) {
					arrayListUsername = Username;
					total += Double.parseDouble(amountSpent);

				}
			}
			Transaction.add(arrayListUsername + " - " + total);

		}
	}

	public ArrayList<String> readPerson(String username, LocalDate date, LocalDate date2) throws ParseException {
		ArrayList<String> UserTransaction = new ArrayList<String>();

		Scanner input = new Scanner("src\\TransactionList\\" + username + ".txt");
		File file = new File(input.nextLine());

		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			// This means that if the user is only a customer it will skip
			// them instead of creating an error message.
			return UserTransaction;
		}

		Date normalDate = java.sql.Date.valueOf(date);
		Date normalDate2 = java.sql.Date.valueOf(date2);

		while (input.hasNextLine()) {
			String line = input.nextLine();

			line = line.replaceAll("\\[", "").replaceAll("\\]", "");

			String[] SalesInformation = line.split("\\|");

			String dateString = SalesInformation[1];

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			Date transactiondate = formatter.parse(dateString);

			if (transactiondate.after(normalDate) && transactiondate.before(normalDate2)) {
				UserTransaction.add(line);
			}
		}

		return UserTransaction;
	}
}
