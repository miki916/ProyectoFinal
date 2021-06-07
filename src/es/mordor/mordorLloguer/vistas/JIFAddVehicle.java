package es.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.alee.laf.text.WebTextField;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class JIFAddVehicle extends JInternalFrame {
	
	private WebTextField textFieldRegistration;
	private WebTextField textFieldModel;
	private WebTextField textFieldColor;
	private JComboBox comboBoxEngine;
	private JComboBox comboBoxStatus;
	private WebTextField textFieldOpcion1;
	private WebTextField textFieldOpcion2;
	private JButton btnAdd;
	private JButton btnCancel;
	private JSpinner spinnerDisplacement;
	private JComboBox comboBoxDrivingLicense;
	private DatePicker date;
	private JSpinner spinnerPrecio;

	/**
	 * Create the frame.
	 */
	public JIFAddVehicle() {
		setBounds(100, 100, 508, 413);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 472, 309);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		textFieldRegistration = new WebTextField();
		textFieldRegistration.setInputPrompt("Matricula");
		textFieldRegistration.setBounds(144, 21, 200, 25);
		panel.add(textFieldRegistration);
		textFieldRegistration.setColumns(10);
		
		textFieldModel = new WebTextField();
		textFieldModel.setInputPrompt("Marca");
		textFieldModel.setBounds(10, 112, 200, 25);
		panel.add(textFieldModel);
		textFieldModel.setColumns(10);
		
		textFieldColor = new WebTextField();
		textFieldColor.setInputPrompt("Color");
		textFieldColor.setBounds(262, 112, 200, 25);
		panel.add(textFieldColor);
		textFieldColor.setColumns(10);
		
		comboBoxEngine = new JComboBox();
		
		comboBoxEngine.addItem("Electrico");
		comboBoxEngine.addItem("Gasolina");
		comboBoxEngine.addItem("Hibrido enchufable");
		comboBoxEngine.addItem("Diesel");

		
		comboBoxEngine.setBounds(10, 167, 200, 25);
		panel.add(comboBoxEngine);
		
		spinnerDisplacement = new JSpinner();
		spinnerDisplacement.setBounds(262, 168, 200, 25);
		panel.add(spinnerDisplacement);
		
		comboBoxStatus = new JComboBox();
		comboBoxStatus.setBounds(10, 222, 200, 25);
		
		comboBoxStatus.addItem("preparado");
		comboBoxStatus.addItem("alquilado");

		
		panel.add(comboBoxStatus);
		
		comboBoxDrivingLicense = new JComboBox();
		comboBoxDrivingLicense.setBounds(262, 222, 200, 25);
		
		comboBoxDrivingLicense.addItem("A");
		comboBoxDrivingLicense.addItem("B");
		comboBoxDrivingLicense.addItem("C");
		comboBoxDrivingLicense.addItem("D");
		comboBoxDrivingLicense.addItem("E");
		comboBoxDrivingLicense.addItem("Z");
		
		panel.add(comboBoxDrivingLicense);
		
		
		textFieldOpcion1 = new WebTextField();
		textFieldOpcion1.setBounds(10, 278, 200, 25);
		panel.add(textFieldOpcion1);
		textFieldOpcion1.setColumns(10);
		
		textFieldOpcion2 = new WebTextField();
		textFieldOpcion2.setColumns(10);
		textFieldOpcion2.setBounds(262, 278, 200, 25);
		panel.add(textFieldOpcion2);
		
		spinnerPrecio = new JSpinner();
		spinnerPrecio.setBounds(10, 67, 200, 25);
		panel.add(spinnerPrecio);
		
		date = new DatePicker();
		date.setBounds(262, 67, 200, 25);
		panel.add(date);
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 331, 472, 41);
		getContentPane().add(panel_2);
		
		btnAdd = new JButton("A\u00F1adir");
		panel_2.add(btnAdd);
		
		btnCancel = new JButton("Cancelar");
		panel_2.add(btnCancel);

	}
	
	

	public DatePicker getDate() {
		return date;
	}



	public JSpinner getSpinnerPrecio() {
		return spinnerPrecio;
	}



	public JComboBox getComboBoxDrivingLicense() {
		return comboBoxDrivingLicense;
	}



	public JSpinner getSpinnerDisplacement() {
		return spinnerDisplacement;
	}

	public WebTextField getTextFieldRegistration() {
		return textFieldRegistration;
	}

	public WebTextField getTextFieldModel() {
		return textFieldModel;
	}

	public WebTextField getTextFieldColor() {
		return textFieldColor;
	}

	public JComboBox getComboBoxEngine() {
		return comboBoxEngine;
	}

	public JComboBox getComboBoxStatus() {
		return comboBoxStatus;
	}

	public WebTextField getTextFieldOpcion1() {
		return textFieldOpcion1;
	}

	public WebTextField getTextFieldOpcion2() {
		return textFieldOpcion2;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}
}
