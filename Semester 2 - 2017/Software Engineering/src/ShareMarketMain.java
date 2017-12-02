import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.json.JSONException;

import com.github.lgooddatepicker.components.DatePicker;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JList;

public class ShareMarketMain {
	static ArrayList<User> users = new ArrayList<User>();

	private JFrame frame;

	static double Tradelimit;
	static double intervalPeriod;
	static double cutLoss;
	protected JTextField UsernameTextfield;
	protected JPasswordField PasswordTextfield;
	protected JTextField UsernameRegister;
	protected JPasswordField PasswordRegister;
	protected JTextField inputSale;
	protected JTextField inputPurchase;
	protected JTextField inputParameter;

	protected JLabel AdminName;
	protected JLabel CustomerName;
	protected JLabel TradingName;
	protected JLabel lblMoney;

	protected JPanel MainMenu;
	protected JPanel Login;
	protected JPanel Register;

	protected JPanel User;
	protected JPanel Admin;
	protected JPanel TradingAccount;

	protected JPanel DelistMember;
	protected JPanel TradersList;
	protected JPanel LowerBrokerage;
	protected JPanel Parameter;

	protected JPanel BuyOrSell;
	protected JPanel BUY;
	protected JPanel SELL;
	protected JPanel QueuedItempanel;
	protected JPanel TransactionSummary;
	protected JLabel lblShareNums[];
	protected JLabel lblAvgPricep[];
	protected JLabel lblAvgPrice[];
	protected DefaultListModel model;
	protected JList list;
	protected JScrollPane pane;

	protected DatePicker datePicker1;
	protected DatePicker datePicker2;

	protected static DefaultListModel model2;
	protected static JList list2;
	protected static JScrollPane pane2;

	protected DefaultListModel model3;
	protected JList list3;
	protected JScrollPane pane3;

	public static void main(String[] args) throws ParseException, IOException {
		readAtStartUp();
		readSettingsOnStartUP();
		getStockInfo();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ShareMarketMain window = new ShareMarketMain();
				window.frame.setVisible(true);
			}
		});

		displayQueuedItem("admin");

		Boolean transactionLoop = true;

		while (transactionLoop == true) {

			for (int i = 0; i < users.size(); i++) {

				try {
					Scanner input = new Scanner("src\\QueuedItem\\" + users.get(i).getUsername() + ".txt");
					File file = new File(input.nextLine());

					input = new Scanner(file);
					String line = null;

					try {
						line = input.nextLine();
					} catch (NoSuchElementException e) {
						continue;
					}

					String[] test = line.split("\\|");

					DateFormat StockDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

					Date todayDate = StockDateFormat.parse(test[1]);
					Date currenttime = new Date();

					if (currenttime.after(todayDate)) {

						Transaction newTransaction = new Transaction();

						newTransaction.saveTransaction(test[0], test[1],
								test[2].replaceAll("\\[", "").replaceAll("\\]", ""), Double.parseDouble(test[3]));

						ArrayList<String> QueuedList = new ArrayList<String>();

						Scanner input2 = new Scanner("src\\QueuedItem\\" + users.get(i).getUsername() + ".txt");
						File file2 = new File(input2.nextLine());

						input2 = new Scanner(file2);
						int counter = 0;

						while (input2.hasNextLine()) {
							String line2 = input2.nextLine();
							if (counter == 0) {
								counter++;
								continue;
							} else {
								QueuedList.add(line2);
							}
							counter++;
						}

						PrintWriter Save = new PrintWriter(new FileOutputStream(
								new File("src\\QueuedItem\\" + users.get(i).getUsername() + ".txt"), false));

						for (int i1 = 0; i1 < QueuedList.size(); i1++) {
							Save.println(QueuedList.get(i1));
						}

						Save.close();
						input2.close();
						try {
							model2.remove(0);
						} catch (ArrayIndexOutOfBoundsException e) {
						}
					}
					input.close();
				} catch (FileNotFoundException e) {
					continue;
				}
			}

		}
	}

	private static void getStockInfo() throws IOException {
		String[] stockSymbols = { "AAPL", "TSLA", "MSFT", "AMZN", "NFLX", "AMD" };

		new Thread() {
			public void run() {
				SharePriceInformation stf = new SharePriceInformation();
				try {

					stf.saveToFileShareMarketInformation(stockSymbols[0]);
				} catch (IOException e) {

				}

			}

		}.start();
		new Thread() {
			public void run() {
				SharePriceInformation stf = new SharePriceInformation();
				try {

					stf.saveToFileShareMarketInformation(stockSymbols[1]);
				} catch (IOException e) {
				}

			}

		}.start();

		new Thread() {
			public void run() {
				SharePriceInformation stf = new SharePriceInformation();
				try {
					stf.saveToFileShareMarketInformation(stockSymbols[2]);
				} catch (IOException e) {

				}

			}

		}.start();

		new Thread() {
			public void run() {
				SharePriceInformation stf = new SharePriceInformation();
				try {

					stf.saveToFileShareMarketInformation(stockSymbols[3]);
				} catch (IOException e) {

				}

			}

		}.start();

		new Thread() {
			public void run() {
				SharePriceInformation stf = new SharePriceInformation();
				try {

					stf.saveToFileShareMarketInformation(stockSymbols[4]);
				} catch (IOException e) {

				}

			}

		}.start();

		new Thread() {
			public void run() {
				SharePriceInformation stf = new SharePriceInformation();
				try {

					stf.saveToFileShareMarketInformation(stockSymbols[5]);
				} catch (IOException e) {
				}

			}

		}.start();
	}

	/**
	 * Create the application.
	 */
	public ShareMarketMain() {
		initialize();
		// The following code is from;
		// https://stackoverflow.com/questions/9093448/do-something-when-the-close-button-is-clicked-on-a-jframe.
		// It is used in order to always save files needed for the application
		// to run on exit.
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(frame, "Are you sure to close this window?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					try {
						SaveOnExit();
					} catch (FileNotFoundException e) {

						e.printStackTrace();
					}
					System.exit(0);
				}
			}
		});
		// End of citation.
	}

	private static Component displayQueuedItem(String username) throws ParseException, FileNotFoundException {

		model2 = new DefaultListModel();

		list2 = new JList(model2);
		pane2 = new JScrollPane(list2);

		QueuedItem.readQueuedItem(username);

		for (int i = 0; i < QueuedItem.QueuedItem.size(); i++) {
			model2.addElement(
					QueuedItem.QueuedItem.get(i).getUsername() + " - " + QueuedItem.QueuedItem.get(i).getDate());
		}

		pane2.setVisible(true);
		pane2.setBounds(20, 70, 230, 168);

		return pane2;
	}

	private Component delistMemberList() {

		model3 = new DefaultListModel();

		list3 = new JList(model3);
		pane3 = new JScrollPane(list3);

		for (int i = 0; i < users.size(); i++) {
			model3.addElement(users.get(i).getUsername());
		}

		pane3.setVisible(true);
		pane3.setBounds(20, 70, 230, 168);

		return pane3;
	}

	public Component ChangeParameters() throws FileNotFoundException {

		model = new DefaultListModel();

		list = new JList(model);
		pane = new JScrollPane(list);

		model.addElement("Trade Limit: " + getTradelimit());
		model.addElement("Stock Update Interval: " + getPriceInterval());
		pane.setVisible(true);
		pane.setBounds(20, 85, 230, 110);

		return pane;
	}

	boolean isNumber(String input) {
		try {
			Float.parseFloat(input);
			return true;
		} catch (NumberFormatException error) {
			return false;
		}
	}

	public static void readSettingsOnStartUP() throws FileNotFoundException {

		Scanner input = new Scanner("Settings.txt");
		File file = new File(input.nextLine());

		input = new Scanner(file);
		int i = 0;
		while (input.hasNextLine()) {
			String line = input.nextLine();

			String[] settings = line.split(":");
			if (i == 0) {
				setTradelimit(Double.parseDouble(settings[1]));
			} else if (i == 1) {
				setPriceInterval(Double.parseDouble(settings[1]));
			}
			i++;
		}
	}

	public static void saveSettings() throws FileNotFoundException {
		PrintWriter SaveOnExit = new PrintWriter(new FileOutputStream(new File("settings.txt"), false));

		SaveOnExit.println("trade-limit:" + getTradelimit());
		SaveOnExit.println("interval-period:" + getPriceInterval());

		SaveOnExit.close();

	}

	private Component tradersgreaterthan100k() throws ParseException {

		model = new DefaultListModel();

		list = new JList(model);
		pane = new JScrollPane(list);
		Transaction.readTransactions();
		
		for (int i = 0; i < Transaction.Transaction.size(); i++) {
			String[] totalMoney = Transaction.Transaction.get(i).split(" ");
			if (Double.parseDouble(totalMoney[2]) > 100000) {
				model.addElement(Transaction.Transaction.get(i));
			}
		}

		pane.setVisible(true);
		pane.setBounds(146, 70, 144, 180);

		return pane;
	}

	private Component transactionOfuser() throws ParseException {

		DefaultListModel newModel = new DefaultListModel();
		newModel.clear();

		Transaction aa = new Transaction();
		try {
			aa.readPerson(getCurrentUsername(), datePicker1.getDate(), datePicker2.getDate());
			for (int i = 0; i < aa.readPerson(getCurrentUsername(), datePicker1.getDate(), datePicker2.getDate())
					.size(); i++) {
				String[] transactions = aa
						.readPerson(getCurrentUsername(), datePicker1.getDate(), datePicker2.getDate()).get(i)
						.split("\\|");
				newModel.addElement(transactions[1] + " - " + transactions[2] + " - " + transactions[3]);
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		list = new JList(newModel);

		pane = new JScrollPane(list);

		pane.setVisible(true);
		pane.setBounds(20, 79, 219, 171);

		return pane;
	}

	private Component LowerBrokerageForPlayer() throws ParseException {

		model = new DefaultListModel();

		list = new JList(model);
		pane = new JScrollPane(list);

		for (int i = 0; i < users.size(); i++) {
			model.addElement(users.get(i).getUsername());
		}

		pane.setVisible(true);
		pane.setBounds(20, 70, 230, 168);

		return pane;
	}

	protected void SaveOnExit() throws FileNotFoundException {
		PrintWriter SaveOnExit = new PrintWriter(new FileOutputStream(new File("UsernameAndPassword.txt"), false));

		for (int j = 0; j < users.size(); j++) {
			SaveOnExit.println(users.get(j).saveUserDetails());
		}
		SaveOnExit.close();
	}

	protected static void readAtStartUp() throws FileNotFoundException {

		Scanner input = new Scanner("UsernameAndPassword.txt");
		File file = new File(input.nextLine());

		input = new Scanner(file);

		while (input.hasNextLine()) {

			String line = input.nextLine();
			line = line.replaceAll("\\[", "").replaceAll(" ", "").replaceAll("\\]", "");

			String[] SalesInformation = line.split(":");

			if (SalesInformation[0].equals("USER")) {

				String Username = SalesInformation[1];
				String Password = SalesInformation[2];
				String Money = SalesInformation[3];
				String Shares = SalesInformation[4];
				String sale = SalesInformation[5];
				String purchase = SalesInformation[6];
				String cutLossOption = SalesInformation[7];
				String[] SharesInformation = Shares.split(",");

				int[] shareArray = new int[6];

				for (int i = 0; i < SharesInformation.length; i++) {
					shareArray[i] = Integer.parseInt(SharesInformation[i]);
				}

				users.add(new User(Username, Password, Double.parseDouble(Money), shareArray, Double.parseDouble(sale),
						Double.parseDouble(purchase), Double.parseDouble(cutLossOption)));

			} else if (SalesInformation[0].equals("ADMIN")) {

				String Username = SalesInformation[1];
				String Password = SalesInformation[2];
				String Money = SalesInformation[3];
				String Shares = SalesInformation[4];
				String sale = SalesInformation[5];
				String purchase = SalesInformation[6];
				String cutLossOption = SalesInformation[7];
				String[] SharesInformation = Shares.split(",");

				int[] shareArray = new int[6];

				for (int i = 0; i < SharesInformation.length; i++) {
					shareArray[i] = Integer.parseInt(SharesInformation[i]);
				}

				users.add(new Admin(Username, Password, Double.parseDouble(Money), shareArray, Double.parseDouble(sale),
						Double.parseDouble(purchase), Double.parseDouble(cutLossOption)));

			} else if (SalesInformation[0].equals("TRADING")) {

				String Username = SalesInformation[1];
				String Password = SalesInformation[2];
				String Money = SalesInformation[3];
				String Shares = SalesInformation[4];
				String sale = SalesInformation[5];
				String purchase = SalesInformation[6];
				String cutLossOption = SalesInformation[7];
				String[] SharesInformation = Shares.split(",");

				int[] shareArray = new int[6];

				for (int i = 0; i < SharesInformation.length; i++) {
					shareArray[i] = Integer.parseInt(SharesInformation[i]);
				}

				users.add(new TradingAccount(Username, Password, Double.parseDouble(Money), shareArray,
						Double.parseDouble(sale), Double.parseDouble(purchase), Double.parseDouble(cutLossOption)));

			}

		}
		input.close();

	}

	protected void Register(String Username, String Password) {

		int SameUserNameCounter = 0;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equalsIgnoreCase(Username)) {
				showMessage("Invalid Username or password", "Invalid");
				UsernameRegister.setText("");
				PasswordRegister.setText("");
				SameUserNameCounter++;
			}
		}
		if (SameUserNameCounter == 0) {
			int[] intArray = new int[6];
			double Money = 0;
			users.add(new User(Username, Password, Money, intArray, 0, 0, 0));

			try {
				model3.addElement(Username);
			} catch (NullPointerException e) {
			}

			// go back to main menu
			Register.setVisible(false);
			MainMenu.setVisible(true);
		}
	}

	String CurrentUsername;

	public String getCurrentUsername() {
		return CurrentUsername;
	}

	public void setCurrentUsername(String username) {
		this.CurrentUsername = username;
	}

	double CurrentCutLossOption;

	public double getCurrentCutLossOption() {
		return CurrentCutLossOption;
	}

	public void setCurrentCutLossOption(double cutLossOption) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equalsIgnoreCase(CurrentUsername)
					&& users.get(i) instanceof TradingAccount) {
				((TradingAccount) users.get(i)).setCutLossOption(cutLossOption);
			}
		}
		CurrentCutLossOption = cutLossOption;
	}

	protected String Login(String Username, String Password) throws FileNotFoundException {

		int LoginSuccess = 0;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equalsIgnoreCase(Username)) {
				if (users.get(i).getPassword().equals(Password)) {
					LoginSuccess = 1;
					if ((users.get(i) instanceof Admin)) {
						Login.setVisible(false);
						Admin.setVisible(true);
						AdminName.setText(users.get(i).getUsername());
					} else if (users.get(i) instanceof TradingAccount) {
						SharePriceInformation spi = new SharePriceInformation();
						try {
							readStockInformation(spi);
						} catch (JSONException e) {
							readStockInformation(spi);
						}
						Login.setVisible(false);
						TradingAccount.setVisible(true);

						TradingName.setText(users.get(i).getUsername());

						lblMoney.setText(String.format("%.2f", ((TradingAccount) users.get(i)).getMoney()));
						String[] stockSymbols = { "AAPL", "TSLA", "MSFT", "AMZN", "NFLX", "AMD" };

						for (int j = 0; j < 6; j++) {
							lblShareNums[j]
									.setText(stockSymbols[j] + ": " + ((TradingAccount) users.get(i)).getData()[j]);
						}

						for (int j = 0; j < 6; j++) {
							lblAvgPrice[j].setText(stockSymbols[j] + ": " + spi.getSharePrice(stockSymbols[j]));
						}
						setCurrentCutLossOption(((TradingAccount) users.get(i)).getCutLossOption());
						SharePriceGraphs chart = new SharePriceGraphs();
						chart.newGraph();
					} else if (users.get(i) instanceof User) {
						Login.setVisible(false);
						User.setVisible(true);

						CustomerName.setText(users.get(i).getUsername());
					}

					setCurrentUsername(users.get(i).getUsername());
				} else {
					if (LoginSuccess == 0) {
						showMessage("Username or password is incorrect!", "Invalid");
						UsernameTextfield.setText("");
						PasswordTextfield.setText("");
						return "Failure";
					}
				}

			}

		}
		if (LoginSuccess == 0) {
			showMessage("Username or password is incorrect!", "Invalid");
			UsernameTextfield.setText("");
			PasswordTextfield.setText("");
			return "Failure";
		}

		return "Success";

	}

	private void readStockInformation(SharePriceInformation spi) throws FileNotFoundException {

		spi.readAndSave();
	}

	protected static void showMessage(String Message, String Title) {
		JOptionPane.showMessageDialog(null, Message, Title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static double getTradelimit() {
		return Tradelimit;
	}

	public static double getPriceInterval() {
		return intervalPeriod;
	}

	static void setTradelimit(double priceInterval) {
		Tradelimit = priceInterval;
	}

	protected static void setPriceInterval(double priceInterval) {
		intervalPeriod = priceInterval;
	}

	protected static double getCutLoss() {
		return cutLoss;
	}

	static void setCutLoss(double cutLoss) {
		ShareMarketMain.cutLoss = cutLoss;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 449, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainMenu = new JPanel();
		Login = new JPanel();
		Register = new JPanel();
		User = new JPanel();
		Admin = new JPanel();
		TradingAccount = new JPanel();
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		frame.getContentPane().add(MainMenu, "name_36783138964300");
		frame.getContentPane().add(Login, "name_36783144488544");
		frame.getContentPane().add(Register, "name_36783149400093");
		frame.getContentPane().add(User, "name_36783154646394");
		frame.getContentPane().add(Admin, "name_36783160121631");
		frame.getContentPane().add(TradingAccount, "name_36783164844228");

		Login.setLayout(null);
		MainMenu.setLayout(null);
		Register.setLayout(null);
		User.setLayout(null);
		Admin.setLayout(null);
		TradingAccount.setLayout(null);

		// MainMenu
		JLabel lblShareMarketInvestment = new JLabel("Share Market Investment Application");
		lblShareMarketInvestment.setBounds(10, 8, 414, 26);
		MainMenu.add(lblShareMarketInvestment);
		lblShareMarketInvestment.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblShareMarketInvestment.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setBounds(10, 45, 414, 14);
		MainMenu.add(lblMainMenu);
		lblMainMenu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnbtnNewButton = new JButton("Quit");
		btnbtnNewButton.setBounds(170, 158, 100, 50);
		MainMenu.add(btnbtnNewButton);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(256, 82, 100, 50);
		MainMenu.add(btnRegister);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(80, 82, 100, 50);
		MainMenu.add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenu.setVisible(false);
				Login.setVisible(true);
			}
		});

		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.setVisible(false);
				Register.setVisible(true);
			}
		});

		btnbtnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SaveOnExit();
					saveSettings();
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				}
				System.exit(0);
			}
		});

		// Login
		JLabel label = new JLabel("Share Market Investment Application");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 21));
		label.setBounds(10, 8, 414, 26);
		Login.add(label);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBounds(10, 45, 414, 26);
		Login.add(lblLogin);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.setVisible(false);
				MainMenu.setVisible(true);
			}
		});
		btnBack.setBounds(335, 227, 89, 23);
		Login.add(btnBack);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(109, 104, 70, 14);
		Login.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(109, 129, 70, 14);
		Login.add(lblPassword);

		UsernameTextfield = new JTextField();
		UsernameTextfield.setBounds(189, 101, 138, 20);
		Login.add(UsernameTextfield);
		UsernameTextfield.setColumns(10);

		PasswordTextfield = new JPasswordField();
		PasswordTextfield.setBounds(189, 126, 138, 20);
		Login.add(PasswordTextfield);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String Username = UsernameTextfield.getText();
				String Password = PasswordTextfield.getText();
				try {
					Login(Username, Password);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnSubmit.setBounds(236, 227, 89, 23);
		Login.add(btnSubmit);

		// Register
		JLabel label_1 = new JLabel("Share Market Investment Application");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_1.setBounds(10, 8, 414, 26);
		Register.add(label_1);

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegister.setBounds(10, 45, 414, 26);
		Register.add(lblRegister);

		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register.setVisible(false);
				MainMenu.setVisible(true);
			}
		});
		btnBack_1.setBounds(335, 227, 89, 23);
		Register.add(btnBack_1);

		JLabel label_3 = new JLabel("Username:");
		label_3.setBounds(109, 104, 70, 14);
		Register.add(label_3);

		UsernameRegister = new JTextField();
		UsernameRegister.setColumns(10);
		UsernameRegister.setBounds(189, 101, 138, 20);
		Register.add(UsernameRegister);

		JLabel label_5 = new JLabel("Password:");
		label_5.setBounds(109, 129, 70, 14);
		Register.add(label_5);

		PasswordRegister = new JPasswordField();
		PasswordRegister.setBounds(189, 126, 138, 20);
		Register.add(PasswordRegister);

		JButton SumbitRegister = new JButton("Submit");
		SumbitRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				if (UsernameRegister.getText().isEmpty() || PasswordRegister.getText().isEmpty()
						|| UsernameRegister.getText().length() > 15 || UsernameRegister.getText().contains(":")
						|| PasswordRegister.getText().contains(":")) {

					if (UsernameRegister.getText().isEmpty() || PasswordRegister.getText().isEmpty()) {
						showMessage("Username or password text field is empty!", "Invalid");

					} else if (UsernameRegister.getText().length() > 15) {
						showMessage("Username is too long!", "Invalid");
					} else if (UsernameRegister.getText().contains(":")) {
						showMessage("Invalid Username contains ':'", "Invalid");
					} else if (PasswordRegister.getText().contains(":")) {
						showMessage("Invalid Password contains ':'", "Invalid");
					}

				} else {

					Register(UsernameRegister.getText(), UsernameRegister.getText());

					try {
						SaveOnExit();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		SumbitRegister.setBounds(236, 227, 89, 23);
		Register.add(SumbitRegister);

		Register.setVisible(false);

		// Customer
		JLabel label_2 = new JLabel("Share Market Investment Application");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_2.setBounds(10, 8, 414, 26);
		User.add(label_2);

		JLabel lblCustomerPortal = new JLabel("User Portal");
		lblCustomerPortal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerPortal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustomerPortal.setBounds(10, 45, 414, 26);
		User.add(lblCustomerPortal);

		JButton btnBack_4 = new JButton("Back");
		btnBack_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User.setVisible(false);
				MainMenu.setVisible(true);
			}
		});

		btnBack_4.setBounds(335, 227, 89, 23);
		User.add(btnBack_4);

		CustomerName = new JLabel("Name");
		CustomerName.setHorizontalAlignment(SwingConstants.CENTER);
		CustomerName.setBounds(275, 50, 149, 14);
		User.add(CustomerName);

		JLabel lblLoggedInAs = new JLabel("Logged in as:");
		lblLoggedInAs.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoggedInAs.setBounds(275, 38, 149, 14);
		User.add(lblLoggedInAs);

		JButton btnOpenTradingAccount = new JButton("<html><center>Open Trading<br />Account</center></html>");
		btnOpenTradingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getUsername().equals(CustomerName.getText())) {

						int[] shareArray = new int[6];
						users.add(new TradingAccount(users.get(i).getUsername(), users.get(i).getPassword(), 1000000,
								shareArray, 0.25, 1.0, 0.01));

						users.remove(i);

						try {
							SaveOnExit();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						SharePriceInformation spi = new SharePriceInformation();
						try {
							readStockInformation(spi);
						} catch (JSONException | FileNotFoundException e) {
							try {
								readStockInformation(spi);
							} catch (FileNotFoundException e1) {

							}
						}
						User.setVisible(false);
						TradingAccount.setVisible(true);

						TradingName.setText(users.get(i).getUsername());

						lblMoney.setText(String.format("%.2f", ((TradingAccount) users.get(i)).getMoney()));
						String[] stockSymbols = { "AAPL", "TSLA", "MSFT", "AMZN", "NFLX", "AMD" };

						for (int j = 0; j < 6; j++) {
							lblShareNums[j]
									.setText(stockSymbols[j] + ": " + ((TradingAccount) users.get(i)).getData()[j]);
						}

						for (int j = 0; j < 6; j++) {
							lblAvgPrice[j].setText(stockSymbols[j] + ": " + spi.getSharePrice(stockSymbols[j]));
						}
						setCurrentCutLossOption(((TradingAccount) users.get(i)).getCutLossOption());
						break;
					}
				}
			}
		});
		btnOpenTradingAccount.setBounds(149, 96, 139, 41);
		User.add(btnOpenTradingAccount);

		JButton btnViewStockGraph = new JButton("View Stock Graph");
		btnViewStockGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SharePriceGraphs chart = new SharePriceGraphs();
				chart.newGraph();
			}
		});
		btnViewStockGraph.setBounds(149, 148, 139, 41);
		User.add(btnViewStockGraph);

		// Admin
		JLabel label_4 = new JLabel("Share Market Investment Application");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_4.setBounds(10, 8, 414, 26);
		Admin.add(label_4);

		JLabel lblAdminPortal = new JLabel("Admin Portal");
		lblAdminPortal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminPortal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAdminPortal.setBounds(10, 45, 414, 26);
		Admin.add(lblAdminPortal);

		JButton btnBack_3 = new JButton("Back");
		btnBack_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin.setVisible(false);
				MainMenu.setVisible(true);
			}
		});
		btnBack_3.setBounds(335, 227, 89, 23);
		Admin.add(btnBack_3);

		AdminName = new JLabel("Name");
		AdminName.setHorizontalAlignment(SwingConstants.CENTER);
		AdminName.setBounds(275, 50, 149, 14);
		Admin.add(AdminName);

		JLabel label_7 = new JLabel("Logged in as:");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(275, 38, 149, 14);
		Admin.add(label_7);

		JButton btndelistmember = new JButton("<html><center>Delist<br/>Member</center></html>");
		btndelistmember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin.setVisible(false);
				DelistMember.setVisible(true);

				DelistMember.add(delistMemberList());

				list3.revalidate();
				list3.repaint();
				pane3.revalidate();
				pane3.repaint();
			}
		});
		btndelistmember.setBounds(147, 80, 141, 35);
		Admin.add(btndelistmember);

		JButton btnplayersTradingMoremember = new JButton(
				"<html><center>Players Trading <br />More Than 100K </center></html>");
		btnplayersTradingMoremember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin.setVisible(false);
				TradersList.setVisible(true);
				try {
					TradersList.add(tradersgreaterthan100k());
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
			}
		});
		btnplayersTradingMoremember.setBounds(147, 122, 141, 35);
		Admin.add(btnplayersTradingMoremember);

		JButton btnlowerBrokerageCostmember = new JButton(
				"<html><center>Lower Brokerage<br />Cost For Players</center></html>");
		btnlowerBrokerageCostmember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin.setVisible(false);
				LowerBrokerage.setVisible(true);
				try {
					LowerBrokerage.add(LowerBrokerageForPlayer());
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
			}
		});
		btnlowerBrokerageCostmember.setBounds(147, 164, 141, 35);
		Admin.add(btnlowerBrokerageCostmember);

		JButton btnmodifyApplicationparameters = new JButton("<html><center>Modify Application<br />Parameters</html>");
		btnmodifyApplicationparameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin.setVisible(false);
				Parameter.setVisible(true);

				new Thread() {
					public void run() {
						try {
							Parameter.add(ChangeParameters());
						} catch (FileNotFoundException e) {

							e.printStackTrace();
						}

						Parameter.revalidate();
						Parameter.repaint();

					}
				}.start();
			}
		});
		btnmodifyApplicationparameters.setBounds(147, 206, 141, 35);
		Admin.add(btnmodifyApplicationparameters);

		// TradingAccount
		JLabel label_6 = new JLabel("Share Market Investment Application");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_6.setBounds(10, 8, 414, 26);
		TradingAccount.add(label_6);

		JLabel lblTradingPortal = new JLabel("Trading Portal");
		lblTradingPortal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTradingPortal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTradingPortal.setBounds(10, 45, 414, 26);
		TradingAccount.add(lblTradingPortal);

		JButton btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TradingAccount.setVisible(false);
				MainMenu.setVisible(true);
			}
		});
		btnBack_2.setBounds(335, 227, 89, 23);
		TradingAccount.add(btnBack_2);

		TradingName = new JLabel("Name");
		TradingName.setHorizontalAlignment(SwingConstants.CENTER);
		TradingName.setBounds(275, 53, 149, 14);
		TradingAccount.add(TradingName);

		JLabel label_9 = new JLabel("Logged in as:");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(275, 38, 149, 14);
		TradingAccount.add(label_9);

		JLabel lblAccountBalance = new JLabel("Account Balance:");
		lblAccountBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountBalance.setBounds(10, 38, 149, 14);
		TradingAccount.add(lblAccountBalance);

		lblMoney = new JLabel("Money");
		lblMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoney.setBounds(10, 53, 149, 14);
		TradingAccount.add(lblMoney);

		JLabel lblNumberOfShares = new JLabel("Number Of Shares:");
		lblNumberOfShares.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfShares.setBounds(10, 68, 150, 14);
		TradingAccount.add(lblNumberOfShares);

		lblShareNums = new JLabel[6];
		for (int i = 0; i < 6; i++) {
			lblShareNums[i] = new JLabel();
			lblShareNums[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblShareNums[i].setBounds(10, 68 + (i + 1) * 15, 150, 14);
			TradingAccount.add(lblShareNums[i]);
		}

		JLabel lblAvgSharePrice = new JLabel("Average Price:");
		lblAvgSharePrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvgSharePrice.setBounds(275, 68, 149, 14);
		TradingAccount.add(lblAvgSharePrice);

		lblAvgPrice = new JLabel[6];
		for (int i = 0; i < 6; i++) {
			lblAvgPrice[i] = new JLabel();
			lblAvgPrice[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblAvgPrice[i].setBounds(275, 68 + (i + 1) * 15, 150, 14);
			TradingAccount.add(lblAvgPrice[i]);
		}

		JButton btndelistsell = new JButton("<html><center>Buy Or<br/>Sell Stocks</center></html>");
		btndelistsell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TradingAccount.setVisible(false);
				BuyOrSell.setVisible(true);

			}
		});
		btndelistsell.setBounds(152, 82, 141, 35);
		TradingAccount.add(btndelistsell);

		JButton btntrans = new JButton("<html><center>Transaction<br />Summary</center></html>");
		btntrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TradingAccount.setVisible(false);
				TransactionSummary.setVisible(true);
			}
		});
		btntrans.setBounds(152, 144, 141, 35);
		TradingAccount.add(btntrans);

		JButton btncutLossoptions = new JButton("<html><center>Cut Loss<br />Options</html>");
		btncutLossoptions.setBounds(152, 208, 141, 35);
		TradingAccount.add(btncutLossoptions);
		btncutLossoptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SpinnerNumberModel sModel = new SpinnerNumberModel(getCurrentCutLossOption() * 100, 1, 9, 1);
				JSpinner spinner = new JSpinner(sModel);

				int option = JOptionPane.showOptionDialog(frame, spinner, "Enter 1%-9% for cut loss option",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (option == JOptionPane.OK_OPTION) {
					setCurrentCutLossOption(Double.parseDouble(spinner.getModel().getValue().toString()) * 0.01);
				}

			}
		});

		TradersList = new JPanel();
		TradersList.setLayout(null);
		frame.getContentPane().add(TradersList, "name_4160104364797");

		JLabel label_10 = new JLabel("Share Market Investment Application");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_10.setBounds(10, 8, 414, 26);
		TradersList.add(label_10);

		JLabel lblAdminWeekly = new JLabel("Admin - Weekly Traders Who Exceed 100K");
		lblAdminWeekly.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminWeekly.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAdminWeekly.setBounds(10, 45, 414, 23);
		TradersList.add(lblAdminWeekly);

		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TradersList.setVisible(false);
				Admin.setVisible(true);
			}
		});
		button_1.setBounds(334, 227, 89, 23);
		TradersList.add(button_1);

		LowerBrokerage = new JPanel();
		LowerBrokerage.setLayout(null);
		frame.getContentPane().add(LowerBrokerage, "name_249050064091612");

		JLabel label_11 = new JLabel("Share Market Investment Application");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_11.setBounds(10, 8, 414, 26);
		LowerBrokerage.add(label_11);

		JLabel lblAdminLower = new JLabel("Admin - Lower Brokerage For Players");
		lblAdminLower.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLower.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAdminLower.setBounds(10, 45, 414, 23);
		LowerBrokerage.add(lblAdminLower);

		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LowerBrokerage.setVisible(false);
				Admin.setVisible(true);
			}
		});
		button.setBounds(334, 227, 89, 23);
		LowerBrokerage.add(button);

		inputSale = new JTextField();
		inputSale.setBounds(264, 82, 61, 20);
		LowerBrokerage.add(inputSale);
		inputSale.setColumns(10);

		inputPurchase = new JTextField();
		inputPurchase.setColumns(10);
		inputPurchase.setBounds(339, 82, 61, 20);
		LowerBrokerage.add(inputPurchase);

		JLabel lblSale = new JLabel("SALE");
		lblSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblSale.setBounds(264, 102, 61, 14);
		LowerBrokerage.add(lblSale);

		JLabel lblPurchase = new JLabel("PURCHASE");
		lblPurchase.setHorizontalAlignment(SwingConstants.CENTER);
		lblPurchase.setBounds(336, 102, 66, 14);
		LowerBrokerage.add(lblPurchase);

		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Selected = list.getSelectedIndex();

				if (Selected != -1) {
					if (users.get(Selected) instanceof User && !(users.get(Selected) instanceof TradingAccount)) {
						showMessage("You're Only Able to Edit Trading Accounts", "Error");
					} else {

						if (inputSale.getText().isEmpty() || inputPurchase.getText().isEmpty()
								|| isNumber(inputPurchase.getText()) == false
								|| isNumber(inputSale.getText()) == false) {
							showMessage("You Must Input A Valid Input", "Invalid Input");
						} else {

							((TradingAccount) users.get(Selected)).setSale(Double.parseDouble(inputSale.getText()));
							((TradingAccount) users.get(Selected))
									.setPurchase(Double.parseDouble(inputPurchase.getText()));
							try {
								SaveOnExit();
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							showMessage("You've successfully edited the Brokerage Cost For "
									+ users.get(Selected).getUsername(), "Success");
						}
					}
				}
			}
		});
		btnSave.setBounds(284, 155, 89, 23);
		LowerBrokerage.add(btnSave);

		JLabel label_12 = new JLabel("%");
		label_12.setBounds(327, 85, 46, 14);
		LowerBrokerage.add(label_12);

		JLabel label_13 = new JLabel("%");
		label_13.setBounds(402, 85, 46, 14);
		LowerBrokerage.add(label_13);

		BuyOrSell = new JPanel();
		BuyOrSell.setLayout(null);
		frame.getContentPane().add(BuyOrSell, "name_25678217884902");

		JLabel label_15 = new JLabel("Share Market Investment Application");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_15.setBounds(10, 8, 414, 26);
		BuyOrSell.add(label_15);

		JLabel lblTradingBuy = new JLabel("Trading - Buy Or Sell Stocks");
		lblTradingBuy.setHorizontalAlignment(SwingConstants.CENTER);
		lblTradingBuy.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTradingBuy.setBounds(10, 45, 414, 23);
		BuyOrSell.add(lblTradingBuy);

		JButton button_4 = new JButton("Back");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyOrSell.setVisible(false);
				TradingAccount.setVisible(true);
			}
		});
		button_4.setBounds(334, 227, 89, 23);
		BuyOrSell.add(button_4);

		JButton btnNewButton = new JButton("BUY");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuyOrSell.setVisible(false);
				BUY.setVisible(true);
			}
		});
		btnNewButton.setBounds(175, 101, 89, 51);
		BuyOrSell.add(btnNewButton);

		JButton btnSell = new JButton("SELL");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyOrSell.setVisible(false);
				SELL.setVisible(true);
			}
		});
		btnSell.setBounds(175, 163, 89, 51);
		BuyOrSell.add(btnSell);

		JButton button_7 = new JButton("<html><center>Queued<br /> Item</center></html>");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyOrSell.setVisible(false);
				QueuedItempanel.setVisible(true);
			}
		});
		button_7.setBounds(335, 180, 89, 37);
		BuyOrSell.add(button_7);

		BUY = new JPanel();
		BUY.setLayout(null);
		frame.getContentPane().add(BUY, "name_18651111084319");

		JLabel label_16 = new JLabel("Share Market Investment Application");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_16.setBounds(10, 8, 414, 26);
		BUY.add(label_16);

		JLabel lblTradingBuy_1 = new JLabel("Trading - Buy Stocks");
		lblTradingBuy_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTradingBuy_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTradingBuy_1.setBounds(10, 45, 414, 23);
		BUY.add(lblTradingBuy_1);

		JButton button_5 = new JButton("Back");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BUY.setVisible(false);
				BuyOrSell.setVisible(true);
			}
		});
		button_5.setBounds(334, 227, 89, 23);
		BUY.add(button_5);

		SELL = new JPanel();
		SELL.setLayout(null);
		frame.getContentPane().add(SELL, "name_18746085667948");

		JLabel label_18 = new JLabel("Share Market Investment Application");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_18.setBounds(10, 8, 414, 26);
		SELL.add(label_18);

		JLabel lblTradingSell = new JLabel("Trading - Sell Stocks");
		lblTradingSell.setHorizontalAlignment(SwingConstants.CENTER);
		lblTradingSell.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTradingSell.setBounds(10, 45, 414, 23);
		SELL.add(lblTradingSell);

		JButton button_6 = new JButton("Back");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SELL.setVisible(false);
				BuyOrSell.setVisible(true);
			}
		});
		button_6.setBounds(334, 227, 89, 23);
		SELL.add(button_6);

		QueuedItempanel = new JPanel();
		QueuedItempanel.setLayout(null);
		frame.getContentPane().add(QueuedItempanel, "name_21092994179544");

		JLabel label_17 = new JLabel("Share Market Investment Application");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_17.setBounds(10, 8, 414, 26);
		QueuedItempanel.add(label_17);

		JLabel label_19 = new JLabel("Trading - Queued Item");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_19.setBounds(10, 45, 414, 23);
		QueuedItempanel.add(label_19);

		JButton button_8 = new JButton("Back");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueuedItempanel.setVisible(false);
				BuyOrSell.setVisible(true);
			}
		});
		button_8.setBounds(334, 227, 89, 23);
		QueuedItempanel.add(button_8);

		TransactionSummary = new JPanel();
		TransactionSummary.setLayout(null);
		frame.getContentPane().add(TransactionSummary, "name_21874399128723");

		JLabel label_20 = new JLabel("Share Market Investment Application");
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_20.setBounds(10, 8, 414, 26);
		TransactionSummary.add(label_20);

		datePicker1 = new DatePicker();
		datePicker2 = new DatePicker();
		TransactionSummary.add(datePicker1);
		TransactionSummary.add(datePicker2);
		datePicker1.setBounds(263, 79, 161, 26);
		datePicker2.setBounds(263, 116, 161, 26);

		JLabel lblTradingTransaction = new JLabel("Trading - Transaction Summary");
		lblTradingTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblTradingTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTradingTransaction.setBounds(10, 45, 414, 23);
		TransactionSummary.add(lblTradingTransaction);

		JButton button_9 = new JButton("Back");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TransactionSummary.setVisible(false);
				TradingAccount.setVisible(true);
			}
		});
		button_9.setBounds(334, 227, 89, 23);
		TransactionSummary.add(button_9);

		JButton btnListTransactions = new JButton("<html><center>List<br/>Transaction</center></html>");
		btnListTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					TransactionSummary.add(transactionOfuser());
					pane.revalidate();
					pane.repaint();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListTransactions.setBounds(285, 173, 113, 37);
		TransactionSummary.add(btnListTransactions);

		Parameter = new JPanel();
		Parameter.setLayout(null);
		frame.getContentPane().add(Parameter, "name_307586522421820");

		JLabel label_14 = new JLabel("Share Market Investment Application");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_14.setBounds(10, 8, 414, 26);
		Parameter.add(label_14);

		JLabel lblAdminModify = new JLabel("Admin - Modify Parameters");
		lblAdminModify.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminModify.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAdminModify.setBounds(10, 45, 414, 23);
		Parameter.add(lblAdminModify);

		JButton button_2 = new JButton("Back");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Parameter.setVisible(false);
				Admin.setVisible(true);

			}
		});
		button_2.setBounds(334, 227, 89, 23);
		Parameter.add(button_2);

		inputParameter = new JTextField();
		inputParameter.setColumns(10);
		inputParameter.setBounds(312, 105, 61, 20);
		Parameter.add(inputParameter);

		JButton button_3 = new JButton("SAVE");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Selected = list.getSelectedIndex();
				Boolean saveCheck = null;
				if (Selected == -1) {

				} else {
					if (inputParameter.getText().isEmpty() || isNumber(inputParameter.getText()) == false
							|| inputParameter.getText().contains("[a-zA-Z]+") == true) {
						showMessage("You Must Input A Valid Input", "Invalid Input");
					} else {
						if (Selected == 0) {
							setTradelimit(Integer.parseInt(inputParameter.getText()));
							model.setElementAt("Trade Limit: " + Integer.parseInt(inputParameter.getText()), 0);
							saveCheck = true;
						} else if (Selected == 1) {
							int input = Integer.parseInt(inputParameter.getText());
							if (input == 1 || input == 5 || input == 15 || input == 30 || input == 60) {
								setPriceInterval(Integer.parseInt(inputParameter.getText()));
								model.setElementAt(
										"Stock Update Interval: " + Integer.parseInt(inputParameter.getText()), 1);
								saveCheck = true;
							} else {
								showMessage("You Must Input either 1,5,15,30 or 60", "Invalid Input");
								saveCheck = false;
							}
						}
					}
					if (saveCheck == true) {
						try {
							saveSettings();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						if (Selected == 0) {
							showMessage("You've successfully edited the trade limit to: " + getTradelimit(), "Success");
						} else if (Selected == 1) {
							showMessage("You've successfully edited the stock price update interval to: "
									+ getPriceInterval(), "Success");
						}
					}
				}

			}
		});
		button_3.setBounds(296, 136, 89, 43);
		Parameter.add(button_3);

		JLabel lblUpdateIntervalsAre = new JLabel("Update Intervals are only able to be changed to");
		lblUpdateIntervalsAre.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblUpdateIntervalsAre.setBounds(35, 202, 268, 36);
		Parameter.add(lblUpdateIntervalsAre);

		JLabel lblAndmins = new JLabel("1,5,15,30 and 60 minutes");
		lblAndmins.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblAndmins.setBounds(80, 231, 162, 14);
		Parameter.add(lblAndmins);

		DelistMember = new JPanel();
		DelistMember.setLayout(null);
		frame.getContentPane().add(DelistMember, "name_36788728442881");

		JLabel label_8 = new JLabel("Share Market Investment Application");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 21));
		label_8.setBounds(10, 8, 414, 26);
		DelistMember.add(label_8);

		JLabel lblAdminDelist = new JLabel("Admin - Delist Member");
		lblAdminDelist.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminDelist.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAdminDelist.setBounds(10, 45, 414, 14);
		DelistMember.add(lblAdminDelist);

		JButton btnNewButton_1 = new JButton("DELETE USER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Selected = list3.getSelectedIndex();

				list3.revalidate();
				list3.repaint();
				pane3.revalidate();
				pane3.repaint();

				if (Selected != -1) {
					if (users.get(Selected) instanceof Admin) {
						showMessage("You're Unable To Remove An Admin", "Error");
					} else {
						showMessage("You've successfully deleted " + users.get(Selected).getUsername(), "Success");
						users.remove(Selected);
						model3.remove(Selected);

						try {
							SaveOnExit();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnNewButton_1.setBounds(283, 70, 121, 63);
		DelistMember.add(btnNewButton_1);

		JButton btnBack_5 = new JButton("Back");
		btnBack_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelistMember.setVisible(false);
				Admin.setVisible(true);
			}
		});
		btnBack_5.setBounds(334, 227, 89, 23);
		DelistMember.add(btnBack_5);

	}
}
