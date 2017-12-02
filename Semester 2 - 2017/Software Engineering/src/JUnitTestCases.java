import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Arrays;
import org.junit.Test;

public class JUnitTestCases {

	// Users are unable to login with an incorrect username and password.
	@Test
	public void test() throws NumberFormatException, NoSuchAlgorithmException, IOException {
		ShareMarketMain main = new ShareMarketMain();
		ShareMarketMain.readAtStartUp();

		assertEquals("Failure", main.Login("aa", "AA"));
		assertEquals("Failure", main.Login("main", "MAIN23"));
		assertEquals("Failure", main.Login("Jeff", "JEFFFFFFFF"));

	}

	// users is able to login with a correct username and password.
	@Test
	public void test2() throws NumberFormatException, NoSuchAlgorithmException, IOException {
		ShareMarketMain main = new ShareMarketMain();

		assertEquals("Success", main.Login("aa", "aa"));
		assertEquals("Success", main.Login("admin", "admin"));
		assertEquals("Success", main.Login("Jeff", "Jeff"));
	}

	// Able to see if number of shares is increased or decreased based on input.
	@Test
	public void test3() throws FileNotFoundException {
		// users 3,4,5 are all trading accounts
		((TradingAccount) ShareMarketMain.users.get(3)).setData(+0, +3, -2, +3, +7, +5);
		((TradingAccount) ShareMarketMain.users.get(4)).setData(+0, +0, +0, +0, +0, +0);
		((TradingAccount) ShareMarketMain.users.get(5)).setData(+100, +3, -50, +6, +9, +1);

		assertEquals("[0, 3, -2, 3, 7, 5]",
				(Arrays.toString(((TradingAccount) ShareMarketMain.users.get(3)).getData())));
		assertEquals("[0, 0, 0, 0, 0, 0]",
				(Arrays.toString(((TradingAccount) ShareMarketMain.users.get(4)).getData())));
		assertEquals("[100, 3, -50, 6, 9, 1]",
				(Arrays.toString(((TradingAccount) ShareMarketMain.users.get(5)).getData())));
	}

	// Checks if brokerage price is computed correctly for a Sale.
	@Test
	public void test4() throws FileNotFoundException {

		ShareMarketMain.readAtStartUp();

		assertEquals(75.0, QueuedItem.BrokeragPriceSale("a2", 10000), 0.0);
		assertEquals(50.0, QueuedItem.BrokeragPriceSale("a2", 0), 0.0);
		assertEquals(300, QueuedItem.BrokeragPriceSale("a2", 100000), 0.0);
	}

	// Checks if brokerage price is computed correctly for a Purchase.
	@Test
	public void test5() {
		assertEquals(150, QueuedItem.BrokeragPricePurchase("a2", 10000), 0.0);
		assertEquals(50.0, QueuedItem.BrokeragPricePurchase("a2", 0), 0.0);
		assertEquals(1050, QueuedItem.BrokeragPricePurchase("a2", 100000), 0.0);
	}

	// Test whether average share price for existing share held by the customer
	// is computed correctly based on three or more purchases.
	@Test
	public void test6() {

	}

	// Test whether an item queued can be removed successfully before
	// transaction is done
	@Test
	public void test7() throws FileNotFoundException {
		ShareMarketMain.readSettingsOnStartUP();

		Boolean output = true;
		int[] test = { 1, 2, 3, 4, 5, 6 };

		QueuedItem.QueueStockPurchase("Jeff", test, 10000);
		QueuedItem.QueueStockPurchase("Jeff", test, 12000);

		assertEquals(output, QueuedItem.cancelQueue("Jeff", 1));
		assertEquals(output, QueuedItem.cancelQueue("Jeff", 0));

	}

	// Test that an item cannot be removed from the queue after the transaction
	// is done
	@Test
	public void test8() throws FileNotFoundException {
		Boolean output = true;

		assertNotEquals(output, QueuedItem.cancelQueue("Jeff", 1));
		assertNotEquals(output, QueuedItem.cancelQueue("Jeff", 0));
	}

	// Test attempts to queue a purchase or sale for amounts exceeding $600,000
	// are automatically rejected
	@Test
	public void test9() throws FileNotFoundException {
		int[] test = { 1, 2, 3, 4, 5, 6 };
		Boolean output = true;
		assertNotEquals(output, QueuedItem.QueueStockPurchase("admin", test, 600001));
		assertNotEquals(output, QueuedItem.QueueStockPurchase("admin", test, 750000));
		assertNotEquals(output, QueuedItem.QueueStockPurchase("admin", test, 900000));

	}

	//share market price test
	@Test
	public void test10() throws IOException {
		SharePriceInformation aa = new SharePriceInformation();
		aa.saveToFileShareMarketInformation("AAPL");
		aa.saveToFileShareMarketInformation("TSLA");
		aa.saveToFileShareMarketInformation("MSFT");
		aa.saveToFileShareMarketInformation("AMZN");
		aa.saveToFileShareMarketInformation("NFLX");
		aa.saveToFileShareMarketInformation("AMD");
		
		System.out.println(aa.getAAPLSharePrice());
		System.out.println(aa.getAMDSharePrice());
		System.out.println(aa.getAMZNSharePrice());
		System.out.println(aa.getMSFTSharePrice());
		System.out.println(aa.getNFLXSharePrice());
		System.out.println(aa.getTSLASharePrice());
	}
	
	//share market price test
		@Test
		public void test11() throws IOException, ParseException {
			ShareMarketMain.readSettingsOnStartUP();

		
			int[] test = { 1, 2, 3, 4, 5, 6 };

			QueuedItem.QueueStockPurchase("Jeff", test, 10000);
			
			
		}
}
