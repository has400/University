package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
/**
 * @author Harrison Smith - S3658817
 */

public class RollPlayersController implements ActionListener {
	private GameEngine ge;
	private MainFrame mf;

	public RollPlayersController(GameEngine ge, MainFrame mf) {
		this.ge = ge;
		this.mf = mf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread() {
			@Override
			public void run() {
				int counter = 0;
				//Rolls the player that was selected.
				for (final Player p : ge.getAllPlayers()) {
					if (counter == mf.getJTablePanel().getTable().getSelectedRow()) {
						ge.rollPlayer(p, 1, 400, 50);
					}
					counter++;
				}
			}
		}.start();
	}

}
