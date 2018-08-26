package view;

import java.awt.GridLayout;

import javax.swing.*;
/**
 * @author Harrison Smith - S3658817
 */

public class LabelPanel extends JPanel{
	private JLabel lblNewLabel;
	
	public LabelPanel(MainFrame mf) {
		setLayout(new GridLayout(0, 1));
		
		//JLabel to show the game information.
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(lblNewLabel);
	}
	
	public JLabel getPanel() {
		return lblNewLabel;
	}
}
