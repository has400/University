package view;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @author Harrison Smith - S3658817
 */

public class ImagePanels extends JPanel {
	JLabel labelDice1;
	JLabel labelDice2;
	
	// Images of dice
	public BufferedImage diceImage1;
	public BufferedImage diceImage2;
	public BufferedImage diceImage3;
	public BufferedImage diceImage4;
	public BufferedImage diceImage5;
	public BufferedImage diceImage6;

	public ImagePanels(MainFrame mainFrame) {
		//Loads bufferedImages
		LoadImages();
		
		//Only 2 JPanels are used to display the image.
		JPanel ImagePanels = new JPanel();
		add(ImagePanels);

		GridLayout gl_ImagePanels = new GridLayout(0, 5);
		ImagePanels.setLayout(gl_ImagePanels);

		Panel panel_1 = new Panel();
		ImagePanels.add(panel_1);

		JPanel panel_4 = new JPanel();
		ImagePanels.add(panel_4);

		labelDice1 = new JLabel(new ImageIcon(diceImage1));
		panel_4.add(labelDice1);

		Panel dicePanel1 = new Panel();
		ImagePanels.add(dicePanel1);

		JPanel dicePanel2 = new JPanel();
		ImagePanels.add(dicePanel2);

		labelDice2 = new JLabel(new ImageIcon(diceImage1));
		dicePanel2.add(labelDice2);

		JPanel panel_6 = new JPanel();
		ImagePanels.add(panel_6);
	}

	public JLabel getDice1() {
		return labelDice1;
	}

	public JLabel getDice2() {
		return labelDice2;
	}
	
	// Loads images so that they can be used when the dice is rolling.
		public void LoadImages() {
			try {
				diceImage1 = ImageIO.read(new File("dice1.jpg"));
				diceImage2 = ImageIO.read(new File("dice2.jpg"));
				diceImage3 = ImageIO.read(new File("dice3.jpg"));
				diceImage4 = ImageIO.read(new File("dice4.jpg"));
				diceImage5 = ImageIO.read(new File("dice5.jpg"));
				diceImage6 = ImageIO.read(new File("dice6.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void setDice1i1(){
		//	labelDice1.setIcon(diceImage1);
		}
}
