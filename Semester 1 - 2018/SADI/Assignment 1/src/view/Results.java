package view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * @author Harrison Smith - S3658817
 */

public class Results extends JPanel {
	public JTextArea textArea_1;
	
	public Results(MainFrame mf) {
		setLayout(new GridLayout(0, 1));
		
		//Results of the house roll in textfield.
		textArea_1 = new JTextArea();
		
		textArea_1.setEditable(false);
		add(textArea_1);
	}
	
	public JTextArea getTextArea() {
		return textArea_1;
	}
}
