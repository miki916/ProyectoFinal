package es.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import es.mordor.morderLloguer.model.BBDD.AlmacenDatosDB;
import es.mordor.mordorLloguer.vistas.JFPrincipal;
import es.mordor.mordorLloguer.vistas.JIFLogin;


public class ControladorPrincipal implements ActionListener{
	
	public static JFPrincipal vistaPrincipal;
	public JIFLogin vistaLogin;
	public AlmacenDatosDB almacenDatos;
	
	public ControladorPrincipal(JFPrincipal vistaPrincipal, AlmacenDatosDB almacenDatos ) {
		
		this.vistaPrincipal = vistaPrincipal;
		this.almacenDatos = almacenDatos;
		
		incializar();
		
	}
	
	
	
	private void incializar() {
		// TODO Auto-generated method stub
		
		vistaPrincipal.getBtnLogin().addActionListener(this);
		vistaPrincipal.getBtnLogOut().addActionListener(this);
		
		vistaPrincipal.getBtnLogin().setActionCommand("AbrirLogin");
		vistaPrincipal.getBtnLogOut().setActionCommand("Logout");

		
		
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
			
		}
		
		
	}


	private void logout() {
		// TODO Auto-generated method stub
		
	}



	private void login() {
		// TODO Auto-generated method stub
		
		SwingWorker<Boolean,Void> task=new SwingWorker<Boolean,Void>(){

			protected Boolean doInBackground() throws Exception {
				
				Boolean autentificado = false;
				String user = vistaLogin.getTextFieldUser().getText();
				String password = String.valueOf(vistaLogin.getTextFieldPassword().getPassword());
				
				try {
					
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
						JOptionPane.showMessageDialog(vistaPrincipal, "Login correcto", "Succes", JOptionPane.INFORMATION_MESSAGE);
						vistaLogin.doDefaultCloseAction();
						
					}else {
						
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
		
		if(!isOpen(vistaLogin)) {
			vistaLogin=new JIFLogin();
			addInternalFrame(vistaLogin);
			
			vistaLogin.getBtnLogin().addActionListener(this);
			vistaLogin.getBtnLogin().setActionCommand("Login");
			
		}else {
			JOptionPane.showMessageDialog(vistaPrincipal, "Esta ventana ya ha sido generada", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	static void addInternalFrame(JInternalFrame jif) {
		vistaPrincipal.getDesktopPane().add(jif);
		//centrar(jif);
		jif.setVisible(true);
		
	}
	
	public boolean isOpen(JInternalFrame jif) {
		boolean existe=false;
		JInternalFrame[] frames= vistaPrincipal.getDesktopPane().getAllFrames();
		for(JInternalFrame frame:frames)
			if(frame==jif)
				existe=true;
		return existe;
	}

	
	
}
