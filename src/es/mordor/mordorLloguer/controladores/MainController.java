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



public class MainController implements ActionListener{
	
	private static JFMain vista;
	private JIFLogin vistaLogin;
	private AlmacenDatosDB almacenDatos;
	private JIFEmployees vistaEmpleados;
	private JIFCustomer vistaClientes;
	private JIFVehicles vistaVehiculos;
	private EmployeeController controladorEmpleados;
	private CustomerController controladorClientes;
	private VehiclesController controladorVehiculos;

	
	public MainController(JFMain vistaPrincipal, AlmacenDatosDB almacenDatos ) {
		
		this.vista = vistaPrincipal;
		this.almacenDatos = almacenDatos;
		
		incializar();
		
	}
	
	
	
	private void incializar() {
		// TODO Auto-generated method stub
		vista.getBtnLogOut().setEnabled(false);
		vista.getBtnEmpleados().setEnabled(false);
		vista.getBtnClientes().setEnabled(false);
		vista.getBtnRent().setEnabled(false);
		
		vista.getBtnLogin().addActionListener(this);
		vista.getBtnLogOut().addActionListener(this);
		vista.getBtnEmpleados().addActionListener(this);
		vista.getBtnClientes().addActionListener(this);
		vista.getBtnRent().addActionListener(this);
		
		vista.getBtnLogin().setActionCommand("AbrirLogin");
		vista.getBtnLogOut().setActionCommand("Logout");
		vista.getBtnEmpleados().setActionCommand("AbrirEmpleados");
		vista.getBtnClientes().setActionCommand("OpenClients");
		vista.getBtnRent().setActionCommand("OpenRent");

		
		
	}
	
	public void go() {
		
		vista.setVisible(true);
		
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
			
		}else if(command == "OpenRent") {
			
			openJIFRent();
			
		}
		
		
	}


	private void openJIFRent() {
		// TODO Auto-generated method stub
		
		if(!open(vistaVehiculos)) {
			
			vistaVehiculos = new JIFVehicles();
			controladorVehiculos = new VehiclesController(vistaVehiculos,almacenDatos);
			controladorVehiculos.go();
			
		}else {
			
			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error", JOptionPane.ERROR_MESSAGE);
		
		}
		
	}



	private void openJIFClientes() {
		// TODO Auto-generated method stub
		
		if(!open(vistaClientes)) {
			
			vistaClientes = new JIFCustomer();
			controladorClientes = new CustomerController(vistaClientes,almacenDatos);
			controladorClientes.go();
			
		}else {
			
			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error", JOptionPane.ERROR_MESSAGE);
		
		}
		
		
	}



	private void openJIFEmpleados() {
		// TODO Auto-generated method stub
		
		if(!open(vistaEmpleados)) {
			
			vistaEmpleados=new JIFEmployees();
			controladorEmpleados = new EmployeeController(vistaEmpleados,almacenDatos);
			controladorEmpleados.go();
		
		}else {
			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}



	private void logout() {
		// TODO Auto-generated method stub
					
			vista.getBtnEmpleados().setEnabled(false);
			vista.getBtnLogOut().setEnabled(false);
			vista.getBtnLogin().setEnabled(true);
			vista.getBtnClientes().setEnabled(false);
			vista.getBtnRent().setEnabled(false);

			if(open(vistaEmpleados)) {
				
				removeJIF(vistaEmpleados);
				
			}
			
			JOptionPane.showMessageDialog(vista, "Sesion finalizada con exito", "Succes", JOptionPane.INFORMATION_MESSAGE);
				
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
						
						vista.getBtnEmpleados().setEnabled(true);
						vista.getBtnLogin().setEnabled(false);
						vista.getBtnLogOut().setEnabled(true);
						vista.getBtnClientes().setEnabled(true);
						vista.getBtnRent().setEnabled(true);


						JOptionPane.showMessageDialog(vista, "Sesion iniciada con exito", "Succes", JOptionPane.INFORMATION_MESSAGE);
						vistaLogin.doDefaultCloseAction();
						
					}else {
						vistaLogin.getProgressBar().setVisible(false);
						vistaLogin.getBtnLogin().setVisible(true);
						JOptionPane.showMessageDialog(vista, "Contraseña o usuario incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
						
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
			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public static void center(JInternalFrame jif) {
		Dimension deskSize=vista.getDesktopPane().getSize();
		Dimension ifSize=jif.getSize();
		jif.setLocation((deskSize.width - ifSize.width) / 2,(deskSize.height-ifSize.height)/ 2);
	}

	
	static void addJIF(JInternalFrame jif) {
		vista.getDesktopPane().add(jif);
		center(jif);
		jif.setVisible(true);
		
	}
	
	static void removeJIF(JInternalFrame jif) {
		
		jif.dispose();
	}
	
	public static boolean open(JInternalFrame jif) {
		boolean existe=false;
		JInternalFrame[] frames= vista.getDesktopPane().getAllFrames();
		for(JInternalFrame frame:frames)
			if(frame==jif)
				existe=true;
		return existe;
	}

	
	
}
