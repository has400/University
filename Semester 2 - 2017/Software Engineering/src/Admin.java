import java.util.Arrays;

public class Admin extends User {

	public Admin(String Username, String Password, double Money, int[] data, double sale, double purchase, double cutLossOption) {
		super(Username, Password, Money, data, sale, purchase, cutLossOption);
	}

	public String saveUserDetails() {

		String username = Username;
		String password = Password;
		double money = Money;
		int[] shares = data;
		double salePercentage = sale;
		double purchasePercentage = purchase;
		double cutLossPercentage = cutLossOption;
		
		return ("ADMIN" + ":" + username + ":" + password + ":" + money + ":" + Arrays.toString(shares) + ":"
				+ salePercentage + ":" + purchasePercentage + ":" + cutLossPercentage);
	}

}
