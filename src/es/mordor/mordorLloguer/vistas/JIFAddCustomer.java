package es.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;



import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import com.github.lgooddatepicker.components.DatePicker;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

@SuppressWarnings("serial")
public class JIFAddCustomer extends JInternalFrame {
	private JPanel panel_1;
	private JButton btnAdd;
	private JButton btnCancel;
	private JLabel lblNewLabel;
	private WebTextField textFieldDni;
	private WebTextField textFieldSurname;
	private WebTextField textFieldCP;
	private WebTextField textFieldName;
	private DatePicker dateTextField;
	private WebTextField textFieldEmail;
	private JComboBox<String> comboBox;
	private WebTextField textFieldAddress;


	/**
	 * Create the frame.
	 */
	public JIFAddCustomer() {
		setClosable(true);
		setBounds(100, 100, 516, 394);
		getContentPane().setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 311, 480, 31);
		getContentPane().add(panel_1);
		
		btnAdd = new JButton("A\u00F1adir");
		panel_1.add(btnAdd);
		
		btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 47, 480, 264);
		getContentPane().add(panel);
		panel.setLayout(null);
		Image image = (new ImageIcon(JFMain.class.getResource("/es/mordor/mordorLloguer/recursos/adduser.png")).getImage());
		ImageIcon imageIconResized = new ImageIcon(getScaledImage(image,50));
		
		textFieldDni = new WebTextField();
		textFieldDni.setBounds(10, 48, 199, 25);
		textFieldDni.setInputPrompt("DNI");
		panel.add(textFieldDni);
		
		textFieldName = new WebTextField();
		textFieldName.setBounds(10, 95, 199, 25);
		textFieldName.setInputPrompt("Nombre");
		panel.add(textFieldName);
		
		textFieldSurname = new WebTextField();
		textFieldSurname.setBounds(271, 95, 199, 25);
		textFieldSurname.setInputPrompt("Apellidos");
		panel.add(textFieldSurname);
		
		textFieldCP = new WebTextField();
		textFieldCP.setBounds(271, 145, 199, 25);
		textFieldCP.setInputPrompt("CP");
		panel.add(textFieldCP);
		
		textFieldAddress = new WebTextField();
		textFieldAddress.setBounds(10, 145, 199, 25);
		textFieldAddress.setInputPrompt("Direccion");
		panel.add(textFieldAddress);
		
		dateTextField = new DatePicker();
		dateTextField.getComponentDateTextField().setEditable(false);
		dateTextField.setBounds(271, 48, 199, 25);
		panel.add(dateTextField);
		
		textFieldEmail = new WebTextField();
		textFieldEmail.setBounds(271, 193, 199, 25);
		textFieldEmail.setInputPrompt("Email");
		panel.add(textFieldEmail);
		
	
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 193, 199, 25);
		
		comboBox.addItem("A");
		comboBox.addItem("B");
		comboBox.addItem("C");
		comboBox.addItem("D");
		comboBox.addItem("E");
		comboBox.addItem("Z");
		
		panel.add(comboBox);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(10, 0, 480, 49);
			getContentPane().add(panel_2);
			panel_2.setLayout(null);
			
			lblNewLabel = new JLabel();
			lblNewLabel.setBounds(215, 0, 51, 49);
			panel_2.add(lblNewLabel);
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
				lblNewLabel.setIcon(imageIconResized);

	}
	
	private Image getScaledImage(Image srcImg, int size){
		
		int h = size, w = size;
		
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    
	    return resizedImg;
	}

	public DatePicker getDateTextField() {
		return dateTextField;
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