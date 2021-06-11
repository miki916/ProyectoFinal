package es.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.alee.laf.table.editors.WebDateEditor;

import es.mordor.morderLloguer.model.BBDD.*;
import es.mordor.mordorLloguer.controladores.*;
import es.mordor.mordorLloguer.controladores.CustomerController.MyTableModelCustomer;
import es.mordor.mordorLloguer.tableModel.*;
import es.mordor.mordorLloguer.vistas.*;

public class InvoiceController implements ActionListener, TableModelListener {

	private AlmacenDatosDB almacenDatos;
	private JIFInvoice vista;
	private JIFCargar vistaCargar;
	private MyRentTableModel mtm;
	private ArrayList<Rent> alquileres;
	private ArrayList<Invoice> facturas;
	private ArrayList<Customer> listC;
	private int index = 0;
	private InvoiceController controller;
	private ArrayList<Vehicle> listV;
	private ArrayList<Rent> alquilerFinal;

	public InvoiceController(AlmacenDatosDB almacenDatos, JIFInvoice vista) {
		super();
		this.almacenDatos = almacenDatos;
		this.vista = vista;
		alquileres = new ArrayList<Rent>();
		facturas = new ArrayList<Invoice>();
		listV = new ArrayList<Vehicle>();
		listC = new ArrayList<Customer>();
		controller = this;
		inicializar();
	}

	private void inicializar() {
		// TODO Auto-generated method stub

		vista.getBtnPreviousInvoice().setEnabled(false);

		vista.getBtnNextInvoice().addActionListener(this);
		vista.getBtnPreviousInvoice().addActionListener(this);
		vista.getBtnAddDetail().addActionListener(this);
		vista.getBtnRemoveDetail().addActionListener(this);
		vista.getBtnNewInvoce().addActionListener(this);
		vista.getBtnCheck().addActionListener(this);
		vista.getBtnRemoveInvoice().addActionListener(this);

		vista.getBtnNextInvoice().setActionCommand("Next");
		vista.getBtnPreviousInvoice().setActionCommand("Previous");
		vista.getBtnAddDetail().setActionCommand("AddDetail");
		vista.getBtnRemoveDetail().setActionCommand("RemoveDetail");
		vista.getBtnNewInvoce().setActionCommand("AddInvoice");
		vista.getBtnCheck().setActionCommand("Check");
		vista.getBtnRemoveInvoice().setActionCommand("RemoveInvoice");

	}

	public void go() {

		MainController.addJIF(vista);
		fillOracle();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String command = e.getActionCommand();

		if (command.equals("Next"))
			nextInvoice();

		else if (command.equals("Previous"))
			prevInvoice();

		else if (command.equals("AddDetail"))
			addDetail();

		else if (command.equals("RemoveDetail"))
			removeDetail();

		else if (command.equals("AddInvoice"))
			addInvoice();

		else if (command.equals("Check"))
			check();

		else if (command.equals("RemoveInvoice"))
			removeInvoice();

	}

	// Elimina la factura
	private void removeInvoice() {
		// TODO Auto-generated method stub

		try {

			for (Rent r : alquilerFinal) {

				almacenDatos.deleteRent(r);

			}

			facturas.remove(index);
			fillOracle();

		} catch (SQLException e) {

			if (e.getErrorCode() == 200014)
				JOptionPane.showMessageDialog(vista, "Alquiler invalido", "Error", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(vista, "error no esperado", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	// Pone todos los vehiculos de la factura en alquilado
	private void check() {
		// TODO Auto-generated method stub

		try {

			almacenDatos.check(facturas.get(index).getIdfactura());

		} catch (SQLException e) {

			if (e.getErrorCode() == 20004)
				JOptionPane.showMessageDialog(vista, "Introduciendo un texto en un numérico", "Error",
						JOptionPane.ERROR_MESSAGE);
			else if (e.getErrorCode() == 20003)
				JOptionPane.showMessageDialog(vista, "Valor mayor que el tamaño del campo", "Error",
						JOptionPane.ERROR_MESSAGE);
			else if (e.getErrorCode() == 20001)
				JOptionPane.showMessageDialog(vista, "Insertando, error FK no encontrada", "Error",
						JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(vista, "error no esperado", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	// Crea una nueva factura
	private void addInvoice() {
		// TODO Auto-generated method stub

		SwingWorker<Boolean, Void> task = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				int idcliente = Integer.parseInt(JOptionPane.showInputDialog("Añadir id de cliente"));
				String matricula = JOptionPane.showInputDialog("Añadir Matricula");
				String pattern = "dd/MM/yy";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				Date date = new java.sql.Date(simpleDateFormat.parse("09/06/2040").getTime());
				Customer customer = null;

				for (Customer v : listC) {
					if (v.getIdCliente() == idcliente)

						customer = v;

				}

				try {

					if (!isCancelled()) {

						Rent r = new Rent(mtm.getRowCount() + 1, matricula, date, date);
						almacenDatos.addInvoice(r, customer.getDNI());
						fillOracle();

					}

				} catch (SQLException e) {

					if (e.getErrorCode() == 20004)
						JOptionPane.showMessageDialog(vista, "Introduciendo un texto en un numérico", "Error",
								JOptionPane.ERROR_MESSAGE);
					else if (e.getErrorCode() == 20001)
						JOptionPane.showMessageDialog(vista, "Insertando, error FK no encontrada", "Error",
								JOptionPane.ERROR_MESSAGE);
					else if (e.getErrorCode() == 20003)
						JOptionPane.showMessageDialog(vista, "Valor mayor que el tamaño del campo", "Error",
								JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(vista, "error no esperado", "Error", JOptionPane.ERROR_MESSAGE);
				}

				return null;
			}

		};

		task.execute();

	}

	// Elimina un alquiler
	private void removeDetail() {
		// TODO Auto-generated method stub
		try {

			int row = vista.getTableDetalles().getSelectedRow();
			Rent r = mtm.getElement(row);
			almacenDatos.deleteRent(r);
			fillOracle();

		} catch (SQLException e) {

			if (e.getErrorCode() == 200014)
				JOptionPane.showMessageDialog(vista, "Alquiler invalido", "Error", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(vista, "error no esperado", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	// Añade un alquiler
	private void addDetail() {
		// TODO Auto-generated method stub

		SwingWorker<Boolean, Void> task = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				String matricula = JOptionPane.showInputDialog("Añadir Matricula");
				String pattern = "dd/MM/yy";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				Date date = new java.sql.Date(simpleDateFormat.parse("09/06/2040").getTime());
				Vehicle vehicle;
				Customer customer = null;

				for (Customer v : listC) {

					if (v.getIdCliente() == facturas.get(index).getClienteid())

						customer = v;

				}

				try {

					if (!isCancelled()) {

						Rent r = new Rent(mtm.getRowCount() + 1, facturas.get(index).getIdfactura(), matricula, date,
								date, 0);
						almacenDatos.addRent(r, customer.getDNI());
						fillOracle();

					}

				} catch (SQLException e) {

					if (e.getErrorCode() == 20004)
						JOptionPane.showMessageDialog(vista, "Introduciendo un texto en un numérico", "Error",
								JOptionPane.ERROR_MESSAGE);
					else if (e.getErrorCode() == 20001)
						JOptionPane.showMessageDialog(vista, "Insertando, error FK no encontrada", "Error",
								JOptionPane.ERROR_MESSAGE);
					else if (e.getErrorCode() == 20003)
						JOptionPane.showMessageDialog(vista, "Valor mayor que el tamaño del campo", "Error",
								JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(vista, "error no esperado", "Error", JOptionPane.ERROR_MESSAGE);
				}

				return null;
			}

		};

		task.execute();

	}

	// Pasa a la anterior factura
	private void prevInvoice() {
		// TODO Auto-generated method stub

		index--;
		mtm = new MyRentTableModel(getRent(facturas.get(index)), listV);
		vista.getTableDetalles().setModel(mtm);
		fillValues();
		btnEnabled();

	}

	// Pasa a la siguiente factura
	private void nextInvoice() {
		// TODO Auto-generated method stub

		index++;
		mtm = new MyRentTableModel(getRent(facturas.get(index)), listV);
		vista.getTableDetalles().setModel(mtm);
		fillValues();
		btnEnabled();
	}

	// Deshabilita los botones de siguiente y anterior
	private void btnEnabled() {

		if (index == facturas.size() - 1)
			vista.getBtnNextInvoice().setEnabled(false);
		else
			vista.getBtnNextInvoice().setEnabled(true);

		if (index == 0)
			vista.getBtnPreviousInvoice().setEnabled(false);
		else
			vista.getBtnPreviousInvoice().setEnabled(true);

		if (facturas.size() <= 1) {

			vista.getBtnPreviousInvoice().setEnabled(false);
			vista.getBtnNextInvoice().setEnabled(false);

		}

	}

	// Almacena todos los datos de la base de datos en un ArrayList
	private void fillOracle() {
		// TODO Auto-generated method stub

		SwingWorker<Boolean, Void> task = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				vistaCargar.setVisible(true);

				try {

					if (!isCancelled()) {

						alquileres = almacenDatos.getRent();
						facturas = almacenDatos.getInvoice();
						listV.addAll(almacenDatos.getVehicles("COCHE"));
						listV.addAll(almacenDatos.getVehicles("FURGONETA"));
						listV.addAll(almacenDatos.getVehicles("CAMION"));
						listV.addAll(almacenDatos.getVehicles("MICROBUS"));
						listC = almacenDatos.getCustomer();

					}

				} catch (SQLException e) {

					JOptionPane.showMessageDialog(vista, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}

				return null;
			}

			// Indicamos el mtm de la tabla y customizamos la tabla
			protected void done() {

				if (!isCancelled()) {

					try {

						btnEnabled();
						fillValues();
						vista.getTableDetalles().setDefaultEditor(Date.class, new WebDateEditor());
						mtm = new MyRentTableModel(getRent(facturas.get(index)), listV);
						vista.getTableDetalles().setModel(mtm);

						mtm.addTableModelListener(controller);
						vistaCargar.doDefaultCloseAction();

					} catch (Exception e) {

						e.printStackTrace();

					}

				}

			}

		};

		vistaCargar = new JIFCargar(task);
		MainController.addJIF(vistaCargar);

		vistaCargar.getLblTask().setText("Cargando vista de facturas");

		task.execute();

	}

	// Almacena los alquileres de una factura en un arraylist
	private List<Rent> getRent(Invoice i) {
		// TODO Auto-generated method stub

		alquilerFinal = new ArrayList<Rent>();

		for (Rent r : alquileres) {

			if (i.getIdfactura() == r.getIdFactura())
				alquilerFinal.add(r);

		}

		return alquilerFinal;
	}

	// Establece un dato a los txtfield de la vista factura
	private void fillValues() {
		// TODO Auto-generated method stub

		vista.getTxtFieldNumeroFactura().setText(String.valueOf(facturas.get(index).getIdfactura()));
		vista.getWebDateFieldFechaFactura().setDate(facturas.get(index).getFecha());
		vista.getTxtFieldSuma().setText(String.valueOf(facturas.get(index).getImportebase()));
		vista.getTxtFieldTotal()
				.setText(String.valueOf((facturas.get(index).getImportebase() + facturas.get(index).getImporteiva())));
		vista.getTxtFieldImpuestos().setText(String.valueOf(facturas.get(index).getImporteiva()));

		for (Customer c : listC) {

			if (facturas.get(index).getClienteid() == c.getIdCliente()) {

				vista.getTxtFieldNombre().setText(c.getName());
				vista.getTxtFieldApellidos().setText(c.getSurname());
				vista.getTxtFieldDNI().setText(c.getDNI());

			}
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {

		if (e.getType() == TableModelEvent.UPDATE) {

			MyRentTableModel mtm = (MyRentTableModel) vista.getTableDetalles().getModel();

			Rent r = mtm.getElement(e.getFirstRow());

			SwingWorker<Boolean, Void> task = new SwingWorker<Boolean, Void>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					// TODO Auto-generated method stub
					try {

						if (!isCancelled())
							almacenDatos.updateRent(r);

					} catch (SQLException e) {

						JOptionPane.showMessageDialog(vista, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

					}

					return null;
				}

			};

			task.execute();

		}

	}

}
