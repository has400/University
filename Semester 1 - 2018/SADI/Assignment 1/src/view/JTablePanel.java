package view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * @author Harrison Smith - S3658817
 */
public class JTablePanel extends JPanel{
	private JTable table;
	
	public JTablePanel(MainFrame mainFrame) {
		setLayout(new GridLayout());
		
		//Jtable to show the player information.
		table = new JTable();
		Object[] columns = { "ID", "Name", "Points", "Bets", "Results" };
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		table.setModel(model);
		table.setFont(new Font("", 1, 8));
		table.setRowHeight(25);

		JScrollPane pane = new JScrollPane(table);
		
		add(pane);
	}
	
	public JTable getTable() {
		return table;
	}
}
