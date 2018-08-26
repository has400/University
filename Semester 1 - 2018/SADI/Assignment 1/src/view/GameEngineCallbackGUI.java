package view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Controller.AddNewPlayerController;
import Controller.PlaceBetController;
import Controller.QuitGameController;
import Controller.RollHouseController;
import Controller.RollPlayersController;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JScrollPane;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.Icon;
/**
 * @author Harrison Smith - S3658817
 */

public class GameEngineCallbackGUI implements GameEngineCallback {

	private MainFrame mf;
	
	public GameEngineCallbackGUI(MainFrame mf) {
		this.mf = mf;
	}

	@Override
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine) {
		final DicePair dicepairFinal = dicePair;
		final Player playerFinal = player;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Updates the game info label, dice and table information.
				updateLabel(playerFinal, dicepairFinal, 1);
				updateDice(dicepairFinal);
				mf.updateTable();
			}
		});
	}

	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine) {
		final DicePair dicepairFinal = result;
		final Player playerFinal = player;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Updates the game info label, dice and table information.
				updateLabel(playerFinal, dicepairFinal, 0);
				updateDice(dicepairFinal);
				mf.updateTable();
			}
		});
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
		final DicePair dicepairFinal = dicePair;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Updates the game info label, dice and table information.
				updateLabel(null, dicepairFinal, 1);
				updateDice(dicepairFinal);
				mf.updateTable();
			}
		});
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		final DicePair dicepairFinal = result;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Updates the game info label, dice and table information.
				//And outputs the player.toString information to the text field at the bottom of the GUI.
				updateLabel(null, dicepairFinal, 0);
				updateDice(dicepairFinal);
				mf.updateTable();
				updateTextField(dicepairFinal);
			}
		});
	}

	//Updates the text field at the bottom of the gui with all the players information and the house information.
	public void updateTextField(DicePair result) {
		String output = String.format("%s: *RESULT* %s \n", "House", result.toString());
		String textFieldText = output;

		for (Player allPlayers : mf.ge.getAllPlayers()) {
			textFieldText += allPlayers.toString() + System.lineSeparator();
		}
		mf.getResults().getTextArea().setText(textFieldText);
	}
	
	//Updates the label as the dice is rolling or when a player or the house does their final role.
	public void updateLabel(Player player, DicePair dicePair, Integer roll) {
		if (player != null) {
			if (roll == 1) {
				String output = String.format("%s: ROLLING %s", player.getPlayerName(), dicePair.toString());
				mf.getLabelPanel().getPanel().setText(output);
			} else {
				String output = String.format("%s: *RESULT* %s", player.getPlayerName(), dicePair.toString());
				mf.getLabelPanel().getPanel().setText(output);
			}
		} else {
			if (roll == 1) {
				String output = String.format("%s: ROLLING %s", "House", dicePair.toString());
				mf.getLabelPanel().getPanel().setText(output);
			} else {
				String output = String.format("%s: *RESULT* %s", "House", dicePair.toString());
				mf.getLabelPanel().getPanel().setText(output);
			}
		}
	}
	
	//Updates the dice based off what is rolled.
	public void updateDice(DicePair dice) {

		switch (dice.getDice1()) {
		case 1:
			mf.getImagePanels().getDice1().setIcon(new ImageIcon(mf.getImagePanels().diceImage1));
			break;
		case 2:
			mf.getImagePanels().getDice1().setIcon(new ImageIcon(mf.getImagePanels().diceImage2));
			break;
		case 3:
			mf.getImagePanels().getDice1().setIcon(new ImageIcon(mf.getImagePanels().diceImage3));
			break;
		case 4:
			mf.getImagePanels().getDice1().setIcon(new ImageIcon(mf.getImagePanels().diceImage4));
			break;
		case 5:
			mf.getImagePanels().getDice1().setIcon(new ImageIcon(mf.getImagePanels().diceImage5));
			break;
		case 6:
			mf.getImagePanels().getDice1().setIcon(new ImageIcon(mf.getImagePanels().diceImage6));
			break;
		}

		switch (dice.getDice2()) {
		case 1:
			mf.getImagePanels().getDice2().setIcon(new ImageIcon(mf.getImagePanels().diceImage1));
			break;
		case 2:
			mf.getImagePanels().getDice2().setIcon(new ImageIcon(mf.getImagePanels().diceImage2));
			break;
		case 3:
			mf.getImagePanels().getDice2().setIcon(new ImageIcon(mf.getImagePanels().diceImage3));
			break;
		case 4:
			mf.getImagePanels().getDice2().setIcon(new ImageIcon(mf.getImagePanels().diceImage4));
			break;
		case 5:
			mf.getImagePanels().getDice2().setIcon(new ImageIcon(mf.getImagePanels().diceImage5));
			break;
		case 6:
			mf.getImagePanels().getDice2().setIcon(new ImageIcon(mf.getImagePanels().diceImage6));
			break;
		}

	}
}
