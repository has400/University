import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SharePriceInformation {
	String LastRefreshTime;

	double AAPLSharePrice;
	double TSLASharePrice;
	double MSFTSharePrice;
	double AMZNSharePrice;
	double NFLXSharePrice;
	double AMDSharePrice;

	public void saveToFileShareMarketInformation(String StockSymbol) throws IOException {
		String interval = "1min";
		URL website = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + StockSymbol
				+ "&interval=" + interval + "&apikey=MGG7HPG0H2MKVNO1");
		File sharePrice = new File("src\\ShareMarketPrices\\" + StockSymbol + ".txt");
		FileUtils.copyURLToFile(website, sharePrice);

		Scanner input = new Scanner("src\\ShareMarketPrices\\" + StockSymbol + ".txt");
		File file = new File(input.nextLine());

		input = new Scanner(file);
		FileReader sharePriceReader = new FileReader("src\\ShareMarketPrices\\" + StockSymbol + ".txt");

		JSONTokener StockJSONTokener = new JSONTokener(sharePriceReader);
		JSONObject StockBaseJSONObject = new JSONObject(StockJSONTokener);

		try {
			JSONObject stockMetaDataObject = (JSONObject) StockBaseJSONObject.get("Meta Data");

			String time = (String) stockMetaDataObject.get("3. Last Refreshed");
			String intervalString = (String) stockMetaDataObject.get("4. Interval");
			setLastRefreshTime(time);

			JSONObject stockTimeIntervalObject = (JSONObject) StockBaseJSONObject
					.get("Time Series (" + intervalString + ")");
			JSONObject LatestStockInformation = (JSONObject) stockTimeIntervalObject.get(time);
			String closeSharePrice = (String) LatestStockInformation.get("4. close");

			if (StockSymbol == "AAPL") {
				setAAPLSharePrice(Double.parseDouble(closeSharePrice));
			} else if (StockSymbol == "TSLA") {
				setTSLASharePrice(Double.parseDouble(closeSharePrice));
			} else if (StockSymbol == "MSFT") {
				setMSFTSharePrice(Double.parseDouble(closeSharePrice));
			} else if (StockSymbol == "AMZN") {
				setAMZNSharePrice(Double.parseDouble(closeSharePrice));
			} else if (StockSymbol == "NFLX") {
				setNFLXSharePrice(Double.parseDouble(closeSharePrice));
			} else if (StockSymbol == "AMD") {
				setAMDSharePrice(Double.parseDouble(closeSharePrice));
			}

			File backupsharePrice = new File("src\\ShareMarketPrices\\backup" + StockSymbol + ".txt");
			FileUtils.copyURLToFile(website, backupsharePrice);
		} catch (JSONException e) {
			sharePriceReader = new FileReader("src\\ShareMarketPrices\\backup" + StockSymbol + ".txt");

			JSONTokener StockJSONTokener2 = new JSONTokener(sharePriceReader);
			JSONObject StockBaseJSONObject2 = new JSONObject(StockJSONTokener2);

			JSONObject stockMetaDataObject2 = (JSONObject) StockBaseJSONObject2.get("Meta Data");

			String time2 = (String) stockMetaDataObject2.get("3. Last Refreshed");
			String intervalString2 = (String) stockMetaDataObject2.get("4. Interval");
			setLastRefreshTime(time2);

			JSONObject stockTimeIntervalObject2 = (JSONObject) StockBaseJSONObject2
					.get("Time Series (" + intervalString2 + ")");
			JSONObject LatestStockInformation = (JSONObject) stockTimeIntervalObject2.get(time2);
			String closeSharePrice = (String) LatestStockInformation.get("4. close");

			if (StockSymbol == "AAPL") {
				setAAPLSharePrice(Double.parseDouble(closeSharePrice));
			} else if (StockSymbol == "TSLA") {
				setTSLASharePrice(Double.parseDouble(closeSharePrice));
			} else if (StockSymbol == "MSFT") {
				setMSFTSharePrice(Double.parseDouble(closeSharePrice));
			} else if (StockSymbol == "AMZN") {
				setAMZNSharePrice(Double.parseDouble(closeSharePrice));
			} else if (StockSymbol == "NFLX") {
				setNFLXSharePrice(Double.parseDouble(closeSharePrice));
			} else if (StockSymbol == "AMD") {
				setAMDSharePrice(Double.parseDouble(closeSharePrice));
			}
		}

	}

	void readAndSave() throws FileNotFoundException {
		String[] stockSymbols = { "AAPL", "TSLA", "MSFT", "AMZN", "NFLX", "AMD" };

		for (int i = 0; i < stockSymbols.length; i++) {
			Scanner input = new Scanner("src\\ShareMarketPrices\\" + stockSymbols[i] + ".txt");
			File file = new File(input.nextLine());

			input = new Scanner(file);
			FileReader sharePriceReader = new FileReader("src\\ShareMarketPrices\\" + stockSymbols[i] + ".txt");
			try {
				JSONTokener StockJSONTokener = new JSONTokener(sharePriceReader);
				JSONObject StockBaseJSONObject = new JSONObject(StockJSONTokener);

				JSONObject stockMetaDataObject = (JSONObject) StockBaseJSONObject.get("Meta Data");
				String time = (String) stockMetaDataObject.get("3. Last Refreshed");
				String intervalString = (String) stockMetaDataObject.get("4. Interval");
				setLastRefreshTime(time);

				JSONObject stockTimeIntervalObject = (JSONObject) StockBaseJSONObject
						.get("Time Series (" + intervalString + ")");
				JSONObject LatestStockInformation = (JSONObject) stockTimeIntervalObject.get(time);
				String closeSharePrice = (String) LatestStockInformation.get("4. close");

				if (stockSymbols[i] == "AAPL") {
					setAAPLSharePrice(Double.parseDouble(closeSharePrice));
				} else if (stockSymbols[i] == "TSLA") {
					setTSLASharePrice(Double.parseDouble(closeSharePrice));
				} else if (stockSymbols[i] == "MSFT") {
					setMSFTSharePrice(Double.parseDouble(closeSharePrice));
				} else if (stockSymbols[i] == "AMZN") {
					setAMZNSharePrice(Double.parseDouble(closeSharePrice));
				} else if (stockSymbols[i] == "NFLX") {
					setNFLXSharePrice(Double.parseDouble(closeSharePrice));
				} else if (stockSymbols[i] == "AMD") {
					setAMDSharePrice(Double.parseDouble(closeSharePrice));
				}
			} catch (JSONException e) {

				FileReader sharePriceReader2 = new FileReader(
						"src\\ShareMarketPrices\\backup" + stockSymbols[i] + ".txt");

				JSONTokener StockJSONTokener2 = new JSONTokener(sharePriceReader2);
				JSONObject StockBaseJSONObject2 = new JSONObject(StockJSONTokener2);

				JSONObject stockMetaDataObject = (JSONObject) StockBaseJSONObject2.get("Meta Data");
				String time = (String) stockMetaDataObject.get("3. Last Refreshed");
				String intervalString = (String) stockMetaDataObject.get("4. Interval");
				setLastRefreshTime(time);

				JSONObject stockTimeIntervalObject = (JSONObject) StockBaseJSONObject2
						.get("Time Series (" + intervalString + ")");
				JSONObject LatestStockInformation = (JSONObject) stockTimeIntervalObject.get(time);
				String closeSharePrice = (String) LatestStockInformation.get("4. close");

				if (stockSymbols[i] == "AAPL") {
					setAAPLSharePrice(Double.parseDouble(closeSharePrice));
				} else if (stockSymbols[i] == "TSLA") {
					setTSLASharePrice(Double.parseDouble(closeSharePrice));
				} else if (stockSymbols[i] == "MSFT") {
					setMSFTSharePrice(Double.parseDouble(closeSharePrice));
				} else if (stockSymbols[i] == "AMZN") {
					setAMZNSharePrice(Double.parseDouble(closeSharePrice));
				} else if (stockSymbols[i] == "NFLX") {
					setNFLXSharePrice(Double.parseDouble(closeSharePrice));
				} else if (stockSymbols[i] == "AMD") {
					setAMDSharePrice(Double.parseDouble(closeSharePrice));
				}
			}

		}
	}

	public double getSharePrice(String StockSymbol) {

		if (StockSymbol == "AAPL") {
			return getAAPLSharePrice();
		} else if (StockSymbol == "TSLA") {
			return getTSLASharePrice();
		} else if (StockSymbol == "MSFT") {
			return getMSFTSharePrice();
		} else if (StockSymbol == "AMZN") {
			return getAMZNSharePrice();
		} else if (StockSymbol == "NFLX") {
			return getNFLXSharePrice();
		} else if (StockSymbol == "AMD") {
			return getAMDSharePrice();
		}
		return 0;
	}

	private void setLastRefreshTime(String time) {
		this.LastRefreshTime = time;
	}

	public String getLastRefreshTime() {
		return LastRefreshTime;
	}

	private void setAAPLSharePrice(double shareprice) {
		this.AAPLSharePrice = shareprice;
	}

	public double getAAPLSharePrice() {
		return AAPLSharePrice;
	}

	private void setTSLASharePrice(double shareprice) {
		this.TSLASharePrice = shareprice;
	}

	public double getTSLASharePrice() {
		return TSLASharePrice;
	}

	private void setMSFTSharePrice(double shareprice) {
		this.MSFTSharePrice = shareprice;
	}

	public double getMSFTSharePrice() {
		return MSFTSharePrice;
	}

	private void setAMZNSharePrice(double shareprice) {
		this.AMZNSharePrice = shareprice;
	}

	public double getAMZNSharePrice() {
		return AMZNSharePrice;
	}

	private void setNFLXSharePrice(double shareprice) {
		this.NFLXSharePrice = shareprice;
	}

	public double getNFLXSharePrice() {
		return NFLXSharePrice;
	}

	private void setAMDSharePrice(double shareprice) {
		this.AMDSharePrice = shareprice;
	}

	public double getAMDSharePrice() {
		return AMDSharePrice;
	}

	// DateFormat StockDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

}
