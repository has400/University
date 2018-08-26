package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Controller.AddNewPlayerController;
import Controller.RollHouseController;
import Controller.RollPlayersController;
import model.interfaces.GameEngine;

/**
 * @author Harrison Smith - S3658817
 */

public class TopBar extends JPanel{

	public TopBar(MainFrame mainFrame, GameEngine ge) {
				// Adding player and roll house and player button panel (outer panel).
				setLayout(new GridLayout(2, 0));
				
				JPanel panel_2 = new JPanel(new GridLayout(0, 1));
				add(panel_2);

				JToolBar toolBar = new JToolBar();
				panel_2.add(toolBar);

				// Add player menu inner panel.
				JButton button = new JButton("Add New Player");
				button.addActionListener(new AddNewPlayerController(ge, mainFrame));
				toolBar.add(button);

				// Dice rolling inner panel
				JPanel panel_5 = new JPanel();
				add(panel_5);

				JButton btnNewButton_1 = new JButton("Roll Player");
				btnNewButton_1.addActionListener(new RollPlayersController(ge, mainFrame));
				panel_5.add(btnNewButton_1);

				JButton btnNewButton = new JButton("Roll House");
				btnNewButton.addActionListener(new RollHouseController(ge, mainFrame));
				panel_5.add(btnNewButton);
	}

}
