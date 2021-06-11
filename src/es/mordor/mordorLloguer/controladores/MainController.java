package es.mordor.mordorLloguer.controladores;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import es.mordor.morderLloguer.model.BBDD.*;
import es.mordor.mordorLloguer.config.MyConfig;
import es.mordor.mordorLloguer.controladores.CustomerController.MyTableModelCustomer;
import es.mordor.mordorLloguer.tableModel.MyVehicleTableModel;
import es.mordor.mordorLloguer.vistas.*;

public class MainController implements ActionListener {

	private static JFMain vista;
	private JIFLogin vistaLogin;
	private AlmacenDatosDB almacenDatos;
	private JIFEmployees vistaEmpleados;
	private JIFCustomer vistaClientes;
	private JIFVehicles vistaVehiculos;
	private JIFInvoice vistaFacturas;
	private JFPreferencias vistaPreferencias;
	private EmployeeController controladorEmpleados;
	private CustomerController controladorClientes;
	private VehiclesController controladorVehiculos;
	private InvoiceController controladorFacturas;

	public MainController(JFMain vistaPrincipal, AlmacenDatosDB almacenDatos) {

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
		vista.getBtnFacturas().setEnabled(false);

		vista.getBtnLogin().addActionListener(this);
		vista.getBtnLogOut().addActionListener(this);
		vista.getBtnEmpleados().addActionListener(this);
		vista.getBtnClientes().addActionListener(this);
		vista.getBtnRent().addActionListener(this);
		vista.getBtnFacturas().addActionListener(this);
		vista.getMntmPreferences().addActionListener(this);

		vista.getBtnLogin().setActionCommand("AbrirLogin");
		vista.getBtnLogOut().setActionCommand("Logout");
		vista.getBtnEmpleados().setActionCommand("AbrirEmpleados");
		vista.getBtnClientes().setActionCommand("OpenClients");
		vista.getBtnRent().setActionCommand("OpenRent");
		vista.getBtnFacturas().setActionCommand("Invoice");
		vista.getMntmPreferences().setActionCommand("Preferences");

	}

	public void go() {

		vista.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String command = e.getActionCommand();

		if (command.equals("AbrirLogin")) {

			openJIFLogin();

		} else if (command.equals("Login")) {

			login();

		} else if (command.equals("Logout")) {

			logout();

		} else if (command.equals("AbrirEmpleados")) {

			openJIFEmployees();

		} else if (command.equals("OpenClients")) {

			openJIFCustomer();

		} else if (command.equals("OpenRent")) {

			openJIFVehicles();

		} else if (command.equals("Invoice")) {

			openJIFInvoice();

		} else if (command.equals("Preferences")) {

			openJIFPreferences();

		} else if (command.equals("RegistrarDatosOracle"))
			changePropertiesData();

		else if (command.equals("Cancel"))
			vistaPreferencias.dispose();

	}

	// Abre el JIF de facturas
	private void openJIFInvoice() {
		// TODO Auto-generated method stub

		if (!open(vistaFacturas)) {

			vistaFacturas = new JIFInvoice();
			controladorFacturas = new InvoiceController(almacenDatos, vistaFacturas);
			controladorFacturas.go();

		} else {

			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	// Abre el JIF de vehiculos
	private void openJIFVehicles() {
		// TODO Auto-generated method stub

		if (!open(vistaVehiculos)) {

			vistaVehiculos = new JIFVehicles();
			controladorVehiculos = new VehiclesController(vistaVehiculos, almacenDatos);
			controladorVehiculos.go();

		} else {

			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	// Abre el JIF de Clientes
	private void openJIFCustomer() {
		// TODO Auto-generated method stub

		if (!open(vistaClientes)) {

			vistaClientes = new JIFCustomer();
			controladorClientes = new CustomerController(vistaClientes, almacenDatos);
			controladorClientes.go();

		} else {

			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	// Abre el JIF de empleados
	private void openJIFEmployees() {
		// TODO Auto-generated method stub

		if (!open(vistaEmpleados)) {

			vistaEmpleados = new JIFEmployees();
			controladorEmpleados = new EmployeeController(vistaEmpleados, almacenDatos);
			controladorEmpleados.go();

		} else {
			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	// Desactiva todos los botones y cierra las ventanas abiertas
	private void logout() {
		// TODO Auto-generated method stub

		vista.getBtnEmpleados().setEnabled(false);
		vista.getBtnLogOut().setEnabled(false);
		vista.getBtnLogin().setEnabled(true);
		vista.getBtnClientes().setEnabled(false);
		vista.getBtnRent().setEnabled(false);
		vista.getBtnFacturas().setEnabled(false);

		if (open(vistaEmpleados))
			removeJIF(vistaEmpleados);

		if (open(vistaClientes))
			removeJIF(vistaClientes);

		if (open(vistaVehiculos))
			removeJIF(vistaVehiculos);

		if (open(vistaFacturas))
			removeJIF(vistaFacturas);

		JOptionPane.showMessageDialog(vista, "Sesion finalizada con exito", "Succes", JOptionPane.INFORMATION_MESSAGE);

	}

	// Inicia sesion
	private void login() {
		// TODO Auto-generated method stub

		SwingWorker<Boolean, Void> task = new SwingWorker<Boolean, Void>() {

			protected Boolean doInBackground() throws Exception {

				Boolean autentificado = false;
				String user = vistaLogin.getTextFieldUser().getText();
				String password = String.valueOf(vistaLogin.getTextFieldPassword().getPassword());

				try {

					vistaLogin.getProgressBar().setVisible(true);
					vistaLogin.getBtnLogin().setVisible(false);
					autentificado = almacenDatos.authenticate(user, password);

				} catch (Exception e) {

					e.printStackTrace();

				}

				return autentificado;
			}

			protected void done() {

				try {

					if (get()) {

						vista.getBtnEmpleados().setEnabled(true);
						vista.getBtnLogin().setEnabled(false);
						vista.getBtnLogOut().setEnabled(true);
						vista.getBtnClientes().setEnabled(true);
						vista.getBtnRent().setEnabled(true);
						vista.getBtnFacturas().setEnabled(true);

						JOptionPane.showMessageDialog(vista, "Sesion iniciada con exito", "Succes",
								JOptionPane.INFORMATION_MESSAGE);
						vistaLogin.doDefaultCloseAction();

					} else {
						vistaLogin.getProgressBar().setVisible(false);
						vistaLogin.getBtnLogin().setVisible(true);
						JOptionPane.showMessageDialog(vista, "Contraseña o usuario incorrectos", "Error",
								JOptionPane.ERROR_MESSAGE);

					}

				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};

		task.execute();

	}

	// Abre el JIF de Login
	private void openJIFLogin() {
		// TODO Auto-generated method stub

		if (!open(vistaLogin)) {
			vistaLogin = new JIFLogin();
			addJIF(vistaLogin);

			vistaLogin.getBtnLogin().addActionListener(this);
			vistaLogin.getBtnLogin().setActionCommand("Login");

		} else {
			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	// Cambia las properties a las indicadas
	private void changePropertiesData() {

		String cadena = "";
		char[] c = vistaPreferencias.getTextFieldPassword().getPassword();

		for (int i = 0; i < c.length; i++) {
			cadena += c[(char) i];
		}

		MyConfig.getInstancia().setOracleDriver(vistaPreferencias.getTextFieldDriver().getText());
		MyConfig.getInstancia().setOracleURL(vistaPreferencias.getTextFieldURL().getText());
		MyConfig.getInstancia().setOracleUsername(vistaPreferencias.getTextFieldUser().getText());
		MyConfig.getInstancia().setOraclePassword(cadena);
		vistaPreferencias.dispose();

	}

	// Abre el JIF de Preferencias
	private void openJIFPreferences() {

		if (!open(vistaPreferencias)) {

			vistaPreferencias = new JFPreferencias();

			addJIF(vistaPreferencias);
			vistaPreferencias.getTextFieldDriver().setText(MyConfig.getInstancia().getOracleDriver());
			vistaPreferencias.getTextFieldURL().setText(MyConfig.getInstancia().getOracleURL());
			vistaPreferencias.getTextFieldUser().setText(MyConfig.getInstancia().getOracleUsername());
			vistaPreferencias.getTextFieldPassword().setText(MyConfig.getInstancia().getOraclePassword());

			vistaPreferencias.getBtnSave().addActionListener(this);
			vistaPreferencias.getBtnCancelar().addActionListener(this);

			vistaPreferencias.getBtnSave().setActionCommand("RegistrarDatosOracle");
			vistaPreferencias.getBtnCancelar().setActionCommand("Cancel");

		} else {

			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	// Centra el JIF
	public static void center(JInternalFrame jif) {
		Dimension deskSize = vista.getDesktopPane().getSize();
		Dimension ifSize = jif.getSize();
		jif.setLocation((deskSize.width - ifSize.width) / 2, (deskSize.height - ifSize.height) / 2);
	}

	// Abre un JIF en el DesktopPane
	static void addJIF(JInternalFrame jif) {
		vista.getDesktopPane().add(jif);
		center(jif);
		jif.setVisible(true);

	}

	// Cierra un JIF
	static void removeJIF(JInternalFrame jif) {

		jif.dispose();
	}

	// Dice si esta abierto un JIF o no
	public static boolean open(JInternalFrame jif) {
		boolean existe = false;
		JInternalFrame[] frames = vista.getDesktopPane().getAllFrames();
		for (JInternalFrame frame : frames)
			if (frame == jif)
				existe = true;
		return existe;
	}

}
