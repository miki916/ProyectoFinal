package es.mordor.mordorLloguer.vistas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

import com.alee.laf.table.WebTable;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class JPVehicles extends JPanel {
	private JTextField textFieldModel;
	private WebTable table;
	private JTextField textFieldRegistration;
	private JComboBox<String> comboBoxEngine;
	private JComboBox<String> comboBoxDrivingLicense;

	/**
	 * Create the panel.
	 */
	public JPVehicles() {
		setLayout(null);
		setBounds(0, 0, 846, 498);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 826, 36);
		add(panel);
		panel.setLayout(new MigLayout("", "[250][250][50][grow][50][grow]", "[]"));
		
		textFieldRegistration = new JTextField();
		panel.add(textFieldRegistration, "cell 0 0,growx");
		textFieldRegistration.setColumns(10);
		
		textFieldModel = new JTextField();
		panel.add(textFieldModel, "cell 1 0,growx");
		textFieldModel.setColumns(10);
		
		JLabel lblEngine = new JLabel("Motor");
		panel.add(lblEngine, "cell 2 0,alignx trailing");
		
		comboBoxEngine = new JComboBox<String>();
		comboBoxEngine.addItem("All");
		comboBoxEngine.addItem("");
		comboBoxEngine.addItem("");
		comboBoxEngine.addItem("");
		comboBoxEngine.addItem("");

		
		panel.add(comboBoxEngine, "cell 3 0,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Carnet");
		panel.add(lblNewLabel_1, "cell 4 0,alignx trailing");
		
		comboBoxDrivingLicense = new JComboBox<String>();
		comboBoxDrivingLicense.addItem("All");
		comboBoxDrivingLicense.addItem("A");
		comboBoxDrivingLicense.addItem("B");
		comboBoxDrivingLicense.addItem("C");
		comboBoxDrivingLicense.addItem("D");
		comboBoxDrivingLicense.addItem("E");
		comboBoxDrivingLicense.addItem("F");

		
		panel.add(comboBoxDrivingLicense, "cell 5 0,growx");
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 62, 826, 425);
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
		
		table = new WebTable();
		scrollPane.setViewportView(table);
		panel_1.setLayout(gl_panel_1);

	}

	
	
	public JTextField getTextFieldRegistration() {
		return textFieldRegistration;
	}

	public JComboBox getComboBoxEngine() {
		return comboBoxEngine;
	}


	public JComboBox getComboBoxDrivingLicense() {
		return comboBoxDrivingLicense;
	}


	public JTextField getTextFieldModel() {
		return textFieldModel;
	}

	public WebTable getTable() {
		return table;
	}
	
	
}
