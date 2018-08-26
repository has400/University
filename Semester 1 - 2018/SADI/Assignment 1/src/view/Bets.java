package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controller.PlaceBetController;
import model.interfaces.GameEngine;
/**
 * @author Harrison Smith - S3658817
 */

public class Bets extends JPanel{
	
public TextField textField;
	private MainFrame mainFrame;
	
	public Bets(MainFrame mainFrame,GameEngine ge) {
		this.mainFrame = mainFrame;
		//Places bet button and text input for it.
		setLayout(new GridLayout(0, 2));

		textField = new TextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 64));
		add(textField);

		JButton btnNewButton_2 = new JButton("Place Bet");
		btnNewButton_2.addActionListener(new PlaceBetController(ge, this));
		add(btnNewButton_2);
	}
		
	public void updateTable() {
		mainFrame.updateTable();
	}
	
	public JTablePanel getJTable() {
		return mainFrame.getJTablePanel();
	}
}
