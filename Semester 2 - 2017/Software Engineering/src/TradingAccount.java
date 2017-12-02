import java.util.Arrays;

public class TradingAccount extends User {

	// Since we can only have 6 shares each position in the array our shares
	// will have an id so eg ANZ bank and 5 other shares
	// would be position 0 and so on array and the user would have {2

	public TradingAccount(String Username, String Password, double Money, int[] data, double sale, double purchase, double cutLossOption) {
		super(Username, Password, Money, data, sale, purchase, cutLossOption);
	}

	public double getMoney() {
		return Money;
	}
	public void setMoney(double money) {
		this.Money -= money;
	}
	public int[] getData() {
		return data;
	}
	
	public void setData(int one, int two, int three, int four, int five, int six) {
		if (one != 0) {
			this.data[0] += one;
		}
		if (two != 0) {
			this.data[1] += two;
		}
		if (three != 0) {
			this.data[2] += three;
		}
		if (four != 0) {
			this.data[3] += four;
		}
		if (five != 0) {
			this.data[4] += five;
		}
		if (six != 0) {
			this.data[5] += six;
		}

	}

	public double getSale() {
		return sale;
	}

	public double getPurchase() {
		return purchase;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public void setPurchase(double purchase) {
		this.purchase = purchase;
	}
	
	public void setCutLossOption(double cutLossOption) {
		this.cutLossOption = cutLossOption;
	}
	
	public double getCutLossOption() {
		return cutLossOption;
	}

	public String saveUserDetails() {
		String username = Username;
		String password = Password;
		double money = Money;
		int[] shares = data;
		double salePercentage = sale;
		double purchasePercentage = purchase;
		double cutLossPercentage = cutLossOption;

		return ("TRADING" + ":" + username + ":" + password + ":" + money + ":" + Arrays.toString(shares) + ":"
				+ salePercentage + ":" + purchasePercentage + ":" + cutLossPercentage);
	}
}
