package es.mordor.mordorLloguer.controladores;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import es.mordor.morderLloguer.model.BBDD.*;
import es.mordor.mordorLloguer.vistas.*;



public class ControladorPrincipal implements ActionListener{
	
	private static JFPrincipal vistaPrincipal;
	private JIFLogin vistaLogin;
	private AlmacenDatosDB almacenDatos;
	private JIFEmpleados vistaEmpleados;
	private JIFClientes vistaClientes;
	private ControladorEmpleados controladorEmpleados;
	private ControladorClientes controladorClientes;

	
	public ControladorPrincipal(JFPrincipal vistaPrincipal, AlmacenDatosDB almacenDatos ) {
		
		this.vistaPrincipal = vistaPrincipal;
		this.almacenDatos = almacenDatos;
		
		incializar();
		
	}
	
	
	
	private void incializar() {
		// TODO Auto-generated method stub
		vistaPrincipal.getBtnLogOut().setEnabled(false);
		vistaPrincipal.getBtnEmpleados().setEnabled(false);
		vistaPrincipal.getBtnClientes().setEnabled(false);

		vistaPrincipal.getBtnLogin().addActionListener(this);
		vistaPrincipal.getBtnLogOut().addActionListener(this);
		vistaPrincipal.getBtnEmpleados().addActionListener(this);
		vistaPrincipal.getBtnClientes().addActionListener(this);

		
		vistaPrincipal.getBtnLogin().setActionCommand("AbrirLogin");
		vistaPrincipal.getBtnLogOut().setActionCommand("Logout");
		vistaPrincipal.getBtnEmpleados().setActionCommand("AbrirEmpleados");
		vistaPrincipal.getBtnClientes().setActionCommand("OpenClients");

		
		
	}
	
	public void go() {
		
		vistaPrincipal.setVisible(true);
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String command = e.getActionCommand();
		
		
		if(command == "AbrirLogin") {
			
			abrirLogin();
			
		}else if(command == "Login") {
			
			login();			
			
		}else if(command == "Logout") {
			
			logout();
			
		}else if(command == "AbrirEmpleados") {
			
			openJIFEmpleados();
			
		}else if(command == "OpenClients") {
			
			openJIFClientes();
			
		}
		
		
	}


	private void openJIFClientes() {
		// TODO Auto-generated method stub
		
		if(!open(vistaClientes)) {
			
			vistaClientes = new JIFClientes();
			controladorClientes = new ControladorClientes(vistaClientes,almacenDatos);
			controladorClientes.go();
			
		}else {
			
			JOptionPane.showMessageDialog(vistaPrincipal, "Esta ventana ya ha sido generada", "Error", JOptionPane.ERROR_MESSAGE);
		
		}
		
		
	}



	private void openJIFEmpleados() {
		// TODO Auto-generated method stub
		
		if(!open(vistaEmpleados)) {
			
			vistaEmpleados=new JIFEmpleados();
			controladorEmpleados = new ControladorEmpleados(vistaEmpleados,almacenDatos);
			controladorEmpleados.go();
		
		}else {
			JOptionPane.showMessageDialog(vistaPrincipal, "Esta ventana ya ha sido generada", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}



	private void logout() {
		// TODO Auto-generated method stub
					
			vistaPrincipal.getBtnEmpleados().setEnabled(false);
			vistaPrincipal.getBtnLogOut().setEnabled(false);
			vistaPrincipal.getBtnLogin().setEnabled(true);
			
			if(open(vistaEmpleados)) {
				
				removeJIF(vistaEmpleados);
				
			}
			
			JOptionPane.showMessageDialog(vistaPrincipal, "Sesion finalizada con exito", "Succes", JOptionPane.INFORMATION_MESSAGE);
				
	}



	private void login() {
		// TODO Auto-generated method stub
		
		SwingWorker<Boolean,Void> task=new SwingWorker<Boolean,Void>(){

			protected Boolean doInBackground() throws Exception {
				
				Boolean autentificado = false;
				String user = vistaLogin.getTextFieldUser().getText();
				String password = String.valueOf(vistaLogin.getTextFieldPassword().getPassword());
				
				try {
					
					vistaLogin.getProgressBar().setVisible(true);
					vistaLogin.getBtnLogin().setVisible(false);
					autentificado = almacenDatos.authenticate(user, password);
					

				}catch(Exception e) {
					
					e.printStackTrace();
				
					
				}
				
				return autentificado;
			}
		
			protected void done() {
				
				 try {
					 
					if(get()) {
						
						vistaPrincipal.getBtnEmpleados().setEnabled(true);
						vistaPrincipal.getBtnLogin().setEnabled(false);
						vistaPrincipal.getBtnLogOut().setEnabled(true);
						vistaPrincipal.getBtnClientes().setEnabled(true);

						JOptionPane.showMessageDialog(vistaPrincipal, "Sesion iniciada con exito", "Succes", JOptionPane.INFORMATION_MESSAGE);
						vistaLogin.doDefaultCloseAction();
						
					}else {
						vistaLogin.getProgressBar().setVisible(false);
						vistaLogin.getBtnLogin().setVisible(true);
						JOptionPane.showMessageDialog(vistaPrincipal, "Contraseña o usuario incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
					 
					
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			 
			}
		};
		
		task.execute();
	
		
	}



	private void abrirLogin() {
		// TODO Auto-generated method stub
		
		if(!open(vistaLogin)) {
			vistaLogin=new JIFLogin();
			addJIF(vistaLogin);
			
			vistaLogin.getBtnLogin().addActionListener(this);
			vistaLogin.getBtnLogin().setActionCommand("Login");
			
		}else {
			JOptionPane.showMessageDialog(vistaPrincipal, "Esta ventana ya ha sido generada", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public static void center(JInternalFrame jif) {
		Dimension deskSize=vistaPrincipal.getDesktopPane().getSize();
		Dimension ifSize=jif.getSize();
		jif.setLocation((deskSize.width - ifSize.width) / 2,(deskSize.height-ifSize.height)/ 2);
	}

	
	static void addJIF(JInternalFrame jif) {
		vistaPrincipal.getDesktopPane().add(jif);
		center(jif);
		jif.setVisible(true);
		
	}
	
	static void removeJIF(JInternalFrame jif) {
		
		jif.dispose();
	}
	
	public static boolean open(JInternalFrame jif) {
		boolean existe=false;
		JInternalFrame[] frames= vistaPrincipal.getDesktopPane().getAllFrames();
		for(JInternalFrame frame:frames)
			if(frame==jif)
				existe=true;
		return existe;
	}

	
	
}
