package es.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.alee.extended.image.WebImage;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class JIFLogin extends JInternalFrame {
	
	private WebPasswordField textFieldPassword;
	private WebTextField textFieldUser;
	private JButton btnLogin;
	private JProgressBar progressBar;


	/**
	 * Create the frame.
	 */
	public JIFLogin() {
		setBounds(100, 100, 450, 315);
		getContentPane().setLayout(null);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(10, 11, 414, 53);
		getContentPane().add(panelSuperior);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMember = new JLabel("Iniciar Sesi\u00F3n");
		lblMember.setFont(new Font("Rockwell", Font.BOLD, 21));
		lblMember.setHorizontalAlignment(SwingConstants.CENTER);
		panelSuperior.add(lblMember, BorderLayout.CENTER);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBounds(10, 75, 414, 120);
		getContentPane().add(panelCentral);
		panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));
		
		textFieldUser = new WebTextField();
		textFieldUser.setInputPrompt("  Username");
		textFieldUser.setToolTipText("");
		textFieldUser.setLeadingComponent ( new WebImage(new ImageIcon(JFPrincipal.class.getResource("/es/mordor/mordorLloguer/recursos/username.png"))));
		panelCentral.add(textFieldUser);
		textFieldUser.setColumns(30);
		
		textFieldPassword = new WebPasswordField();
		textFieldPassword.setInputPrompt("  Password");
		textFieldPassword.setLeadingComponent ( new WebImage(new ImageIcon(JFPrincipal.class.getResource("/es/mordor/mordorLloguer/recursos/candado.png"))));
		panelCentral.add(textFieldPassword);
		textFieldPassword.setColumns(30);
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBounds(20, 237, 391, 37);
		getContentPane().add(panelInferior);
		panelInferior.setLayout(new BorderLayout(0, 0));
		
		btnLogin = new JButton("Iniciar sesion");
		panelInferior.add(btnLogin, BorderLayout.CENTER);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(20, 210, 391, 28);
		progressBar.setForeground(Color.GREEN);
		progressBar.setIndeterminate(true);
		progressBar.setVisible(false);
		getContentPane().add(progressBar);

	}
	
	

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public WebPasswordField getTextFieldPassword() {
		return textFieldPassword;
	}


	public WebTextField getTextFieldUser() {
		return textFieldUser;
	}


	public JButton getBtnLogin() {
		return btnLogin;
	}
	
	
}
