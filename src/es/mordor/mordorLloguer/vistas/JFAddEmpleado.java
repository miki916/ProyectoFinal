package es.mordor.mordorLloguer.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.github.lgooddatepicker.components.DatePicker;

import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;

public class JFAddEmpleado extends JInternalFrame {

	private WebTextField textFieldDNI;
	private WebTextField textFieldNombre;
	private WebTextField textFieldApellidos;
	private WebTextField textFieldCP;
	private WebTextField textFieldEmail;
	private DatePicker datePicker;
	private WebPasswordField textFieldContraseña;
	private WebTextField textFieldDomicilio;
	private JButton btnNewEmpleado;
	private JButton btnCancel;
	private JComboBox<String> comboBoxCargo;

	

	/**
	 * Create the frame.
	 */
	public JFAddEmpleado() {
		
		
		setBounds(100, 100, 460, 328);
		getContentPane().setLayout(null);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(10, 11, 424, 230);
		getContentPane().add(panelSuperior);
		panelSuperior.setLayout(null);
		
		textFieldDNI = new WebTextField();
		textFieldDNI.setBounds(10, 21, 167, 20);
		textFieldDNI.setInputPrompt("DNI");
		panelSuperior.add(textFieldDNI);
		textFieldDNI.setColumns(10);
		
		textFieldNombre = new WebTextField();
		textFieldNombre.setBounds(10, 64, 167, 20);
		textFieldNombre.setInputPrompt("Nombre");
		panelSuperior.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellidos = new WebTextField();
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setInputPrompt("Apellidos");
		textFieldApellidos.setBounds(247, 64, 167, 20);
		panelSuperior.add(textFieldApellidos);
		
		JLabel lblNewLabel = new JLabel("A\u00F1adir Empleado");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(247, 10, 167, 36);
		panelSuperior.add(lblNewLabel);
		
		textFieldCP = new WebTextField();
		textFieldCP.setColumns(10);
		textFieldCP.setInputPrompt("CP");
		textFieldCP.setBounds(10, 107, 167, 20);
		panelSuperior.add(textFieldCP);
		
		textFieldEmail = new WebTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setInputPrompt("Email");
		textFieldEmail.setBounds(247, 107, 167, 20);
		panelSuperior.add(textFieldEmail);
		
		datePicker = new DatePicker();
		datePicker.getComponentDateTextField().setEditable(false);
		panelSuperior.add(datePicker);
		
		textFieldContraseña = new WebPasswordField();
		textFieldContraseña.setColumns(10);
		textFieldContraseña.setInputPrompt("Contraseña");
		textFieldContraseña.setBounds(247, 199, 167, 20);
		panelSuperior.add(textFieldContraseña);
		
		textFieldDomicilio = new WebTextField();
		textFieldDomicilio.setColumns(10);
		textFieldDomicilio.setInputPrompt("Domicilio");
		textFieldDomicilio.setBounds(10, 199, 167, 20);
		panelSuperior.add(textFieldDomicilio);
		
		comboBoxCargo = new JComboBox<String>();
		comboBoxCargo.setBounds(247, 150, 167, 22);
		comboBoxCargo.addItem("mecanico");
		comboBoxCargo.addItem("administrativo");
		comboBoxCargo.addItem("comercial");
		comboBoxCargo.addItem("gerente");
		panelSuperior.add(comboBoxCargo);
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBounds(10, 246, 424, 41);
		getContentPane().add(panelInferior);
		
		btnNewEmpleado = new JButton("Add");
		panelInferior.add(btnNewEmpleado);
		
		btnCancel = new JButton("Cancel");
		panelInferior.add(btnCancel);

	}

	

	public JComboBox<String> getComboBoxCargo() {
		return comboBoxCargo;
	}



	public WebTextField getTextFieldDNI() {
		return textFieldDNI;
	}



	public WebTextField getTextFieldNombre() {
		return textFieldNombre;
	}



	public WebTextField getTextFieldApellidos() {
		return textFieldApellidos;
	}



	public WebTextField getTextFieldCP() {
		return textFieldCP;
	}



	public WebTextField getTextFieldEmail() {
		return textFieldEmail;
	}



	public DatePicker getDatetextField() {
		return datePicker;
	}



	public WebPasswordField getTextFieldContraseña() {
		return textFieldContraseña;
	}



	public WebTextField getTextFieldDomicilio() {
		return textFieldDomicilio;
	}



	public JButton getBtnNewEmpleado() {
		return btnNewEmpleado;
	}



	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	
	
	
}
