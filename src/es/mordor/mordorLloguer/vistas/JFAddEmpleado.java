package es.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;



import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import com.github.lgooddatepicker.components.DatePicker;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class JFAddEmpleado extends JInternalFrame {
	private JPanel panel;
	private WebTextField textFieldDni;
	private WebTextField textFieldName;
	private WebTextField textFieldSurname;
	private WebTextField textFieldCP;
	private WebTextField textFieldAddress;
	private DatePicker dateTextField;
	private WebTextField textFieldEmail;
	private WebPasswordField textFieldPassword;
	private JComboBox<String> comboBox;
	private JPanel panel_1;
	private JButton btnAdd;
	private JButton btnCancel;


	/**
	 * Create the frame.
	 */
	public JFAddEmpleado() {
		setClosable(true);
		setBounds(100, 100, 456, 344);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 0, 420, 254);
		getContentPane().add(panel);
		
		textFieldDni = new WebTextField();
		textFieldDni.setBounds(10, 28, 168, 20);
		textFieldDni.setInputPrompt("DNI");
		panel.add(textFieldDni);
		
		textFieldName = new WebTextField();
		textFieldName.setBounds(10, 77, 168, 20);
		textFieldName.setInputPrompt("Nombre");
		panel.add(textFieldName);
		
		textFieldSurname = new WebTextField();
		textFieldSurname.setBounds(242, 77, 168, 20);
		textFieldSurname.setInputPrompt("Apellidos");
		panel.add(textFieldSurname);
		
		textFieldCP = new WebTextField();
		textFieldCP.setBounds(10, 126, 168, 20);
		textFieldCP.setInputPrompt("CP");
		panel.add(textFieldCP);
		
		textFieldAddress = new WebTextField();
		textFieldAddress.setBounds(242, 126, 168, 20);
		textFieldAddress.setInputPrompt("Direccion");
		panel.add(textFieldAddress);
		
		dateTextField = new DatePicker();
		dateTextField.getComponentDateTextField().setEditable(false);
		dateTextField.setBounds(10, 174, 168, 20);
		panel.add(dateTextField);
		
		textFieldEmail = new WebTextField();
		textFieldEmail.setBounds(10, 225, 168, 20);
		textFieldEmail.setInputPrompt("Email");
		panel.add(textFieldEmail);
		
		textFieldPassword = new WebPasswordField();
		textFieldPassword.setBounds(242, 225, 168, 20);
		textFieldPassword.setInputPrompt("Contraseña");
		panel.add(textFieldPassword);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(242, 172, 168, 22);
		comboBox.addItem("mecanico");
		comboBox.addItem("administrativo");
		comboBox.addItem("comercial");
		comboBox.addItem("gerente");
	
		panel.add(comboBox);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 265, 420, 31);
		getContentPane().add(panel_1);
		
		btnAdd = new JButton("A\u00F1adir");
		panel_1.add(btnAdd);
		
		btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);

	}


	public DatePicker getDateTextField() {
		return dateTextField;
	}


	public JPanel getPanel() {
		return panel;
	}


	public WebTextField getTextFieldDni() {
		return textFieldDni;
	}


	public WebTextField getTextFieldName() {
		return textFieldName;
	}


	public WebTextField getTextFieldSurname() {
		return textFieldSurname;
	}


	public WebTextField getTextFieldCP() {
		return textFieldCP;
	}


	public WebTextField getTextFieldAddress() {
		return textFieldAddress;
	}




	public WebTextField getTextFieldEmail() {
		return textFieldEmail;
	}


	public WebPasswordField getTextFieldPassword() {
		return textFieldPassword;
	}


	public JComboBox getComboBox() {
		return comboBox;
	}


	public JPanel getPanel_1() {
		return panel_1;
	}


	public JButton getBtnAdd() {
		return btnAdd;
	}


	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	

	

}