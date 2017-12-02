import java.util.Arrays;

public class User {
	String Username;
	String Password;
	double Money;
	int[] data = new int[6];
	double sale;
	double purchase;
	double cutLossOption;

	public User(String Username, String Password, double Money, int[] data, double sale, double purchase,
			double cutLossOption) {
		this.Username = Username;
		this.Password = Password;
		this.Money = Money;
		this.data = data;
		this.sale = sale;
		this.purchase = purchase;
		this.cutLossOption = cutLossOption;
	}

	public String getUsername() {
		return Username;
	}

	public String getPassword() {
		return Password;
	}

	public String saveUserDetails() {

		String username = Username;
		String password = Password;
		double money = Money;
		int[] shares = data;
		double salePercentage = sale;
		double purchasePercentage = purchase;
		double cutLossPercentage = cutLossOption;

		return ("USER" + ":" + username + ":" + password + ":" + money + ":" + Arrays.toString(shares) + ":"
				+ salePercentage + ":" + purchasePercentage + ":" + cutLossPercentage);
	}
}
