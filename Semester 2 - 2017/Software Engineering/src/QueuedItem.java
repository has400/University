import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class QueuedItem {

	String username;
	String date;
	int[] shareArray;
	double Money;

	public QueuedItem(String username, String date, int[] shareArray, double money) {
		this.username = username;
		this.date = date;
		this.shareArray = shareArray;
		this.Money = money;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	// Broker Fee methods.
	static int fixedCharge = 50;

	public static double BrokeragPriceSale(String username, double InputMoney) {
		double salePercentage = 0;
		for (int i = 0; i < ShareMarketMain.users.size(); i++) {
			if (ShareMarketMain.users.get(i).getUsername().equals(username)) {
				salePercentage = ((TradingAccount) ShareMarketMain.users.get(i)).getSale();
			}
		}

		double PercentageFee = InputMoney * (salePercentage / 100);
		return PercentageFee + fixedCharge;
	}

	public static double BrokeragPricePurchase(String username, double InputMoney) {
		double purchasePercentage = 0;
		for (int i = 0; i < ShareMarketMain.users.size(); i++) {
			if (ShareMarketMain.users.get(i).getUsername().equals(username)) {
				purchasePercentage = ((TradingAccount) ShareMarketMain.users.get(i)).getPurchase();
			}
		}

		double PercentageFee = InputMoney * (purchasePercentage / 100);
		return PercentageFee + fixedCharge;
	}

	public static Boolean QueueStockPurchase(String Username, int[] shareArray, double money)
			throws FileNotFoundException {

		if (money > ShareMarketMain.getTradelimit()) {
			ShareMarketMain.showMessage("You're trying to queue a trade for over the trade limit", "Invalid");
			return false;
		} else {

			Calendar StockCalender = Calendar.getInstance();
			DateFormat StockDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date todayDate = new Date();

			StockCalender.setTime(todayDate);
			StockCalender.add(Calendar.HOUR, +1);
			todayDate = StockCalender.getTime();

			PrintWriter SaveOnExit = new PrintWriter(
					new FileOutputStream(new File("src\\QueuedItem\\" + Username + ".txt"), true));

			SaveOnExit.println(Username + "|" + StockDateFormat.format(todayDate) + "|" + Arrays.toString(shareArray)
					+ "|" + money);

			SaveOnExit.close();

			QueuedItem.add(new QueuedItem(Username, StockDateFormat.format(todayDate), shareArray, money));
			saveQueue(Username);

			try {
				ShareMarketMain.model2.addElement(QueuedItem.get(QueuedItem.size() - 1).getUsername() + " - "
						+ QueuedItem.get(QueuedItem.size() - 1).getDate());
			} catch (NullPointerException e) {

			}
			return true;
		}

	}

	static ArrayList<QueuedItem> QueuedItem = new ArrayList<QueuedItem>();

	public static Boolean cancelQueue(String username, int position) throws FileNotFoundException {
		readQueuedItem(username);

		try {
			QueuedItem.remove(position);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

		try {
			ShareMarketMain.model2.remove(position);
		} catch (NullPointerException e) {

		}

		saveQueue(username);
		return true;
	}

	public static void saveQueue(String username) throws FileNotFoundException {
		PrintWriter SaveOnExit = new PrintWriter(
				new FileOutputStream(new File("src\\QueuedItem\\" + username + ".txt"), false));

		for (int j = 0; j < QueuedItem.size(); j++) {
			SaveOnExit.println(QueuedItem.get(j).saveUserDetails());
		}
		SaveOnExit.close();
	}

	public String saveUserDetails() {

		String Username = username;
		String Date = date;
		int[] shares = shareArray;
		double money = Money;

		return (Username + "|" + Date + "|" + Arrays.toString(shares) + "|" + money);
	}

	public static void readQueuedItem(String Username) throws FileNotFoundException {

		QueuedItem.clear();

		Scanner input = new Scanner("src\\QueuedItem\\" + Username + ".txt");
		File file = new File(input.nextLine());

		input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] transaction = line.split("\\|");

			String username = transaction[0];
			String date = transaction[1];
			String shareArrayString = transaction[2].replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
			double money = Double.parseDouble(transaction[3]);

			String[] SharesInformation = shareArrayString.split(",");

			int[] shareArray = new int[6];

			for (int i1 = 0; i1 < 5; i1++) {
				shareArray[i1] = Integer.parseInt(SharesInformation[i1]);
			}
			QueuedItem.add(new QueuedItem(username, date, shareArray, money));
		}
	}
}
