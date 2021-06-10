package es.mordor.mordorLloguer.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alee.extended.image.WebImage;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JFPreferencias extends JInternalFrame {

	private JPanel contentPane;
	private JTextField textFieldDriver;
	private JTextField textFieldURL;
	private WebTextField textFieldUser;
	private WebPasswordField textFieldPassword;
	private JButton btnCancelar;
	private JButton btnSave;



	/**
	 * Create the frame.
	 */
	public JFPreferencias() {
		setBounds(100, 100, 460, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		btnSave = new JButton("Guardar");
		panel.add(btnSave);
		
		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		panel_1.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][]"));
		
		JLabel lblDriver = new JLabel("Driver ");
		panel_1.add(lblDriver, "cell 1 0");
		
		textFieldDriver = new JTextField();
		panel_1.add(textFieldDriver, "cell 3 0,growx");
		textFieldDriver.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("URL");
		panel_1.add(lblNewLabel, "cell 1 2");
		
		textFieldURL = new JTextField();
		panel_1.add(textFieldURL, "cell 3 2,growx");
		textFieldURL.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		panel_1.add(lblNewLabel_1, "cell 1 4");
		
		textFieldUser = new WebTextField();
		panel_1.add(textFieldUser, "cell 3 4,growx");
		textFieldUser.setTrailingComponent(new WebImage(new ImageIcon(JFMain.class.getResource("/es/mordor/mordorLloguer/recursos/username.png"))));
		textFieldUser.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		panel_1.add(lblNewLabel_2, "cell 1 6");
		
		textFieldPassword = new WebPasswordField();
		
		panel_1.add(textFieldPassword, "cell 3 6,growx");
		textFieldPassword.setTrailingComponent(new WebImage(new ImageIcon(JFMain.class.getResource("/es/mordor/mordorLloguer/recursos/candado.png"))));
		textFieldPassword.setColumns(10);
		contentPane.setLayout(gl_contentPane);
	}



	public JPanel getContentPane() {
		return contentPane;
	}



	public JButton getBtnCancelar() {
		return btnCancelar;
	}



	public JTextField getTextFieldDriver() {
		return textFieldDriver;
	}

	public JTextField getTextFieldURL() {
		return textFieldURL;
	}



	public WebTextField getTextFieldUser() {
		return textFieldUser;
	}


	public WebPasswordField getTextFieldPassword() {
		return textFieldPassword;
	}


	public JButton getBtnSave() {
		return btnSave;
	}
}
