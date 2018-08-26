package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Controller.AddNewPlayerController;
/**
 * @author Harrison Smith - S3658817
 */

public class DialogClass {
	private static JDialog d;
	private JTextField textField;
	private JTextField textField2;

	public DialogClass(JFrame frame, final AddNewPlayerController addNewPlayerController) {

		JFrame f = new JFrame();

		d = new JDialog(f, "Add new Player", true);
		d.getContentPane().setLayout(new FlowLayout());

		textField = new JTextField();
		d.getContentPane().add(textField);
		textField.setColumns(10);

		textField2 = new JTextField();
		d.getContentPane().add(textField2);
		textField2.setColumns(10);

		JButton b = new JButton("Add New Player");

		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Checks that a name has been input and that the points have been input.
				if (textField.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Please Enter Player Name", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (textField2.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Please Enter Initial Points.", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					//Then it will take the information and try to create a new player, and if the text for the number
					//isnt accepted the user will need to enter a valid number.
					try {
						if (Integer.parseInt(textField2.getText()) > 0) {
							addNewPlayerController.createNewPlayer(textField.getText(), textField2.getText());
						}
						DialogClass.d.setVisible(false);
					} catch (NumberFormatException ed) {
						JOptionPane.showMessageDialog(null, "Please A Valid Number for Initial Points.", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		d.getContentPane().add(b);
		d.setLocationRelativeTo(frame);

		d.setSize(350, 100);
		d.setVisible(true);

	}

}