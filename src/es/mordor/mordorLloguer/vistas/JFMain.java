package es.mordor.mordorLloguer.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JFMain extends JFrame {

	private JPanel contentPane;
	private JButton btnLogin;
	private JButton btnLogOut;
	private JButton btnEmpleados;
	private JDesktopPane desktopPane;
	private JButton btnClientes;
	private JButton btnRent;

	/**
	 * Create the frame.
	 */
	public JFMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 936, 649);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon(JFMain.class.getResource("/es/mordor/mordorLloguer/recursos/login.png")));
		menuBar.add(btnLogin);
		
		btnLogOut = new JButton("");
		btnLogOut.setIcon(new ImageIcon(JFMain.class.getResource("/es/mordor/mordorLloguer/recursos/logout.png")));
		menuBar.add(btnLogOut);
		
		btnEmpleados = new JButton("Empleados");
		btnEmpleados.setIcon(new ImageIcon(JFMain.class.getResource("/es/mordor/mordorLloguer/recursos/employe.png")));
		menuBar.add(btnEmpleados);
		
		btnClientes = new JButton("Clientes");
		btnClientes.setIcon(new ImageIcon(JFMain.class.getResource("/es/mordor/mordorLloguer/recursos/customers.png")));
		menuBar.add(btnClientes);
		
		btnRent = new JButton("Alquilar");
		Image image = (new ImageIcon(JFMain.class.getResource("/es/mordor/mordorLloguer/recursos/rent.png")).getImage());
		ImageIcon imageIconResized = new ImageIcon(getScaledImage(image,32));
			btnRent.setIcon(imageIconResized);
		menuBar.add(btnRent);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
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
	

	public JButton getBtnClientes() {
		return btnClientes;
	}
	
	
	public JButton getBtnRent() {
		return btnRent;
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
