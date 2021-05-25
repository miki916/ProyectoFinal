package es.mordor.mordorLloguer.vistas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class JPVehicles extends JPanel {
	private JTextField textFieldModel;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public JPVehicles() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 596, 36);
		add(panel);
		panel.setLayout(new MigLayout("", "[175][175][50][grow][50][grow]", "[]"));
		
		JTextField textFieldRegistration = new JTextField();
		panel.add(textFieldRegistration, "cell 0 0,growx");
		textFieldRegistration.setColumns(10);
		
		textFieldModel = new JTextField();
		panel.add(textFieldModel, "cell 1 0,growx");
		textFieldModel.setColumns(10);
		
		JLabel lblEngine = new JLabel("Motor");
		panel.add(lblEngine, "cell 2 0,alignx trailing");
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox, "cell 3 0,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Licencia");
		panel.add(lblNewLabel_1, "cell 4 0,alignx trailing");
		
		JComboBox comboBox_1 = new JComboBox();
		panel.add(comboBox_1, "cell 5 0,growx");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 62, 596, 415);
		add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		panel_1.setLayout(gl_panel_1);

	}
}
