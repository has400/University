package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
/**
 * @author Harrison Smith - S3658817
 */

public class RollHouseController implements ActionListener {
	private GameEngine ge;
	private MainFrame mf;

	public RollHouseController(GameEngine ge, MainFrame mf) {
		// TODO Auto-generated constructor stub
		this.ge = ge;
		this.mf = mf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Boolean playerCheck = true;
		//Checks that all players have been rolled.
		for (final Player p : ge.getAllPlayers()) {
			if ((p.getBet() == 0) || p.getRollResult() == null) {
				JOptionPane.showMessageDialog(mf, "Roll Bets For All Players First.", "Error",
						JOptionPane.ERROR_MESSAGE);
				playerCheck = false;
				break;
			}
		}
		
		//Rolls the house.
		if (playerCheck == true) {
			new Thread() {
				@Override
				public void run() {
					ge.rollHouse(1, 100, 20);
				}
			}.start();
			
			mf.updateTable();
		}

	}

}
