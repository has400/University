package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.Bets;
/**
 * @author Harrison Smith - S3658817
 */

public class PlaceBetController implements ActionListener {
	private GameEngine ge;
	private Bets mf;

	public PlaceBetController(GameEngine ge, Bets bets) {
		// TODO Auto-generated constructor stub
		this.ge = ge;
		this.mf = bets;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Places a bet for the selected user.
		int i = 0;
		for (final Player p : ge.getAllPlayers()) {
			if (i == mf.getJTable().getTable().getSelectedRow()) {
				if (mf.textField.getText().length() > 0) {
					new Thread() {
						@Override
						public void run() {
							// Checks that the bet can be placed otherwise it will pop an error message
							// and once the bet is accepted it will update the JTable GUI.
							try {
								if (p.placeBet(Integer.parseInt(mf.textField.getText())) == true) {
									mf.updateTable();
								} else {
									JOptionPane.showMessageDialog(mf, "Enter Valid Bet Ammount.", "Error",
											JOptionPane.ERROR_MESSAGE);
								}
							} catch (NumberFormatException ed) {
								// Catches invalid bet amounts such as numbers.
								JOptionPane.showMessageDialog(mf, "Enter Valid Bet Ammount.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}.start();
				} else {// Makes sure that something has been typed into the bet text field.
					JOptionPane.showMessageDialog(mf, "Enter Valid Bet Ammount.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			i++;
		}

	}

}
