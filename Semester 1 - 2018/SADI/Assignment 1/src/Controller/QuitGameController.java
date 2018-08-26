package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Harrison Smith - S3658817
 */

public class QuitGameController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		//Quits the game if the user selects it.
		System.exit(0);
	}

}
