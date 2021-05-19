package es.mordor.mordorLloguer.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JFPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton btnLogin;
	private JButton btnLogOut;
	private JButton btnEmpleados;
	private JDesktopPane desktopPane;

	/**
	 * Create the frame.
	 */
	public JFPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 936, 649);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon(JFPrincipal.class.getResource("/es/mordor/mordorLloguer/recursos/login.png")));
		menuBar.add(btnLogin);
		
		btnLogOut = new JButton("");
		btnLogOut.setIcon(new ImageIcon(JFPrincipal.class.getResource("/es/mordor/mordorLloguer/recursos/logout.png")));
		menuBar.add(btnLogOut);
		
		btnEmpleados = new JButton("Empleados");
		btnEmpleados.setIcon(new ImageIcon(JFPrincipal.class.getResource("/es/mordor/mordorLloguer/recursos/employe.png")));
		menuBar.add(btnEmpleados);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JButton getBtnLogOut() {
		return btnLogOut;
	}

	public JButton getBtnEmpleados() {
		return btnEmpleados;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
}
