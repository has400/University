package view;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Controller.QuitGameController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
/**
 * @author Harrison Smith - S3658817
 */

public class MainFrame extends JFrame {

	// Game Engine
	public GameEngine ge;

	private Bets bets;
	private ImagePanels imagepanels;
	private JTablePanel jtablepanel;
	private LabelPanel labelpanel;
	private Results results;
	private TopBar topbar;
	
	
	public MainFrame(GameEngine ge) {
		super("Casino Style Dice Game");
		
		this.ge = ge;
		
		bets = new Bets(this, ge);
		imagepanels = new ImagePanels(this);
		jtablepanel = new JTablePanel(this);
		labelpanel = new LabelPanel(this);
		results = new Results(this);
		topbar = new TopBar(this,ge);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 509);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Adds menu to the GUI.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("Quit Game");
		menuItem.addActionListener(new QuitGameController());
		menu.add(menuItem);
		
		//Sets layout to grid.
		getContentPane().setLayout(new GridLayout(6, 1));
		
		//Adds the different components to the GUI.
		add(topbar);
		add(imagepanels);
		add(jtablepanel);
		add(bets);
		add(labelpanel);
		add(results);
		
		setVisible(true);
	}

	// Updates the JTable with the relevent information for why its being called eg.
	// a dice roll result.
	public void updateTable() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel();
				String[] tableHeads = new String[] { "ID", "Name", "Points", "Bets", "Results" };
				dtm.setColumnIdentifiers(tableHeads);

				for (Player p : ge.getAllPlayers()) {
					// If a bet hasnt been placed yet.
					if (p.getRollResult() == null) {
						Object[] dataRow = { p.getPlayerId(), p.getPlayerName(), p.getPoints(), p.getBet(), "" };
						dtm.addRow(dataRow);
					} else {// if the bet has been placed yet.
						Object[] dataRow = { p.getPlayerId(), p.getPlayerName(), p.getPoints(), p.getBet(),
								p.getRollResult().getDice1() + p.getRollResult().getDice2() };
						dtm.addRow(dataRow);
					}

				}
				
				getJTablePanel().getTable().setModel(dtm);
			}
		});
	}

	public Results getResults() {
		return this.results;
	}
	
	public JTablePanel getJTablePanel() {
		return this.jtablepanel;
		
	}
	
	public LabelPanel getLabelPanel() {
		return this.labelpanel;
		
	}
	
	public ImagePanels getImagePanels() {
		return this.imagepanels;
		
	}
}
