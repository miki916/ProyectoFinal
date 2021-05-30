package es.mordor.mordorLloguer.vistas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebTextField;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class JPVehicles extends JPanel {
	private WebTextField textFieldModel;
	private WebTable table;
	private WebTextField textFieldRegistration;
	private JComboBox<String> comboBoxEngine;
	private JComboBox<String> comboBoxDrivingLicense;
	private JPanel panel_2;
	private JButton btnAdd;
	private JButton btnRemove;

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
		
		textFieldRegistration = new WebTextField();
		textFieldRegistration.setInputPrompt("Matricula");
		panel.add(textFieldRegistration, "cell 0 0,growx");
		textFieldRegistration.setColumns(10);
		
		textFieldModel = new WebTextField();
		textFieldModel.setInputPrompt("Marca");

		panel.add(textFieldModel, "cell 1 0,growx");
		textFieldModel.setColumns(10);
		
		JLabel lblEngine = new JLabel("Motor");
		panel.add(lblEngine, "cell 2 0,alignx trailing");
		
		comboBoxEngine = new JComboBox<String>();
		comboBoxEngine.addItem("All");
	

		
		panel.add(comboBoxEngine, "cell 3 0,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Carnet");
		panel.add(lblNewLabel_1, "cell 4 0,alignx trailing");
		
		comboBoxDrivingLicense = new JComboBox<String>();
		comboBoxDrivingLicense.addItem("All");
		
		panel.add(comboBoxDrivingLicense, "cell 5 0,growx");
		
		
	
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 62, 826, 376);
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
		
		panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_2.setBounds(10, 449, 826, 38);
		add(panel_2);
		
		btnAdd = new JButton("A\u00F1adir");
		panel_2.add(btnAdd);
		
		btnRemove = new JButton("Eliminar");
		panel_2.add(btnRemove);

	}

	


	public JButton getBtnAdd() {
		return btnAdd;
	}



	public JButton getBtnRemove() {
		return btnRemove;
	}



	public WebTextField getTextFieldRegistration() {
		return textFieldRegistration;
	}

	public JComboBox getComboBoxEngine() {
		return comboBoxEngine;
	}


	public JComboBox getComboBoxDrivingLicense() {
		return comboBoxDrivingLicense;
	}


	public WebTextField getTextFieldModel() {
		return textFieldModel;
	}

	public WebTable getTable() {
		return table;
	}
}
