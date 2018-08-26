package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.DialogClass;
import view.MainFrame;

/**
 * @author Harrison Smith - S3658817
 */

public class AddNewPlayerController implements ActionListener {

	private GameEngine ge;
	private MainFrame mf;

	public AddNewPlayerController(GameEngine ge, MainFrame mf) {
		this.ge = ge;
		this.mf = mf;

	}

	public void createNewPlayer(final String name, final String points) {
		new Thread() {
			@Override
			public void run() {
				// creates a new simplePlayer object and it is added to the game.
				SimplePlayer newPlayer = new SimplePlayer(Integer.toString(ge.getAllPlayers().size() + 1), name,
						Integer.parseInt(points));
				ge.addPlayer(newPlayer);
			}
		}.start();

		mf.updateTable();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Creates a pop-up to create the new player.
		new DialogClass(mf, this);
	}
}
