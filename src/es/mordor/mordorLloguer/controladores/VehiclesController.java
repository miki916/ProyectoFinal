package es.mordor.mordorLloguer.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import com.alee.laf.table.editors.WebDateEditor;

import es.mordor.morderLloguer.model.BBDD.*;
import es.mordor.mordorLloguer.controladores.CustomerController.MyTableModelCustomer;
import es.mordor.mordorLloguer.tableModel.MyCarTableModel;
import es.mordor.mordorLloguer.tableModel.MyMinibusTableModel;
import es.mordor.mordorLloguer.tableModel.MyTruckTableModel;
import es.mordor.mordorLloguer.tableModel.MyVanTableModel;
import es.mordor.mordorLloguer.tableModel.MyVehicleTableModel;
import es.mordor.mordorLloguer.vistas.*;

public class VehiclesController implements ActionListener, TableModelListener {

	private JIFVehicles vista;
	private AlmacenDatosDB almacenDatos;
	private MyVehicleTableModel mtmCar;
	private MyVehicleTableModel mtmVan;
	private MyVehicleTableModel mtmTruck;
	private MyVehicleTableModel mtmMiniBus;
	private JIFAddVehicle vistaJIFVehicle;
	private JIFCargar vistaCargar;
	private VehiclesController controller;
	private ArrayList<Car> cars = new ArrayList<Car>();
	private ArrayList<Van> vans = new ArrayList<Van>();
	private ArrayList<Truck> trucks = new ArrayList<Truck>();
	private ArrayList<Minibus> miniBus = new ArrayList<Minibus>();

	public VehiclesController(JIFVehicles vista, AlmacenDatosDB almacenDatos) {

		this.vista = vista;
		this.almacenDatos = almacenDatos;
		controller = this;

		inicializar();

	}

	public void go() {

		MainController.addJIF(vista);
		fillOracle();
	}

	private void inicializar() {
		// TODO Auto-generated method stub

		for (Component c : vista.getTabbedPane().getComponents()) {

			if (c instanceof JPVehicles) {

				((JPVehicles) c).getTextFieldModel().getDocument().addDocumentListener((SimpleDocumentListener) e -> {
					filter();
				});
				((JPVehicles) c).getTextFieldRegistration().getDocument()
						.addDocumentListener((SimpleDocumentListener) e -> {
							filter();
						});
				((JPVehicles) c).getComboBoxEngine().addActionListener(this);
				((JPVehicles) c).getComboBoxDrivingLicense().addActionListener(this);
				((JPVehicles) c).getBtnAdd().addActionListener(this);
				((JPVehicles) c).getBtnRemove().addActionListener(this);
				((JPVehicles) c).getBtnAdd().setActionCommand("OpenNewVehicle");
				((JPVehicles) c).getBtnRemove().setActionCommand("Remove");
				((JPVehicles) c).getComboBoxEngine().setActionCommand("Model");
				((JPVehicles) c).getComboBoxDrivingLicense().setActionCommand("Registration");
			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String command = e.getActionCommand();

		if (command.equals("Model") || command.equals("Registration"))

			filter();

		else if (command.equals("Remove"))

			removeRow();

		else if (command.equals("OpenNewVehicle"))

			openNewVehicle();

		else if (command.equals("Cancel"))

			vistaJIFVehicle.dispose();

		else if (command.equals("Add"))

			addNewVehicle();

	}

	// A?ade un vehiculo
	private void addNewVehicle() {
		// TODO Auto-generated method stub

		SwingWorker<Boolean, Void> task = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub

				String pattern = "yyyy-MM-dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

				String registration = vistaJIFVehicle.getTextFieldRegistration().getText();
				int priceDay = (int) vistaJIFVehicle.getSpinnerPrecio().getValue();
				String model = vistaJIFVehicle.getTextFieldModel().getText();
				String color = vistaJIFVehicle.getTextFieldColor().getText();
				String engine = vistaJIFVehicle.getComboBoxEngine().getSelectedItem().toString();
				int displacement = (int) vistaJIFVehicle.getSpinnerDisplacement().getValue();
				Date shopDay = new java.sql.Date(
						simpleDateFormat.parse(vistaJIFVehicle.getDate().toString()).getTime());
				String status = vistaJIFVehicle.getComboBoxStatus().getSelectedItem().toString();
				String drivingLicense = vistaJIFVehicle.getComboBoxDrivingLicense().getSelectedItem().toString();
				int opcion1 = Integer.parseInt(vistaJIFVehicle.getTextFieldOpcion1().getText());
				int opcion2 = 0;

				Vehicle v = null;

				int index = vista.getTabbedPane().getSelectedIndex();
				boolean error = false;

				try {

					switch (index) {

					case 0:

						opcion2 = Integer.parseInt(vistaJIFVehicle.getTextFieldOpcion2().getText());
						v = (Car) new Car(registration, priceDay, model, color, engine, displacement, shopDay, status,
								drivingLicense, opcion1, opcion2);
						error = almacenDatos.addVehicle("COCHE", v);
						updateTable(error, mtmCar, v);

						break;

					case 1:

						v = (Van) new Van(registration, priceDay, model, color, engine, displacement, shopDay, status,
								drivingLicense, opcion1);
						error = almacenDatos.addVehicle("FURGONETA", v);
						updateTable(error, mtmVan, v);

						break;

					case 2:

						opcion2 = Integer.parseInt(vistaJIFVehicle.getTextFieldOpcion2().getText());
						v = (Truck) new Truck(registration, priceDay, model, color, engine, displacement, shopDay,
								status, drivingLicense, opcion1, opcion2);
						error = almacenDatos.addVehicle("CAMION", v);
						updateTable(error, mtmTruck, v);

						break;

					case 3:

						opcion2 = Integer.parseInt(vistaJIFVehicle.getTextFieldOpcion2().getText());
						v = (Minibus) new Minibus(registration, priceDay, model, color, engine, displacement, shopDay,
								status, drivingLicense, opcion1, opcion2);
						error = almacenDatos.addVehicle("MICROBUS", v);
						updateTable(error, mtmMiniBus, v);

						break;
					}

				} catch (SQLException e) {

					if (e.getErrorCode() == 20005)
						JOptionPane.showMessageDialog(vista, "intentando duplicar una columna/s que son ?nicas",
								"Error", JOptionPane.ERROR_MESSAGE);
					else if (e.getErrorCode() == 20002)
						JOptionPane.showMessageDialog(vista,
								"Has intentado poner un valor m?s grande que el permitido por la variable", "Error",
								JOptionPane.ERROR_MESSAGE);
					else if (e.getErrorCode() == 20003)
						JOptionPane.showMessageDialog(vista, "letra en un campo num?rico", "Error",
								JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(vista, "error no esperado", "Error", JOptionPane.ERROR_MESSAGE);

				}

				return null;
			}

			private void updateTable(boolean error, MyVehicleTableModel mtm, Vehicle v) {
				// TODO Auto-generated method stub

				if (error) {

					mtm.addElementAtRow(v);
					JOptionPane.showMessageDialog(vista, "Empleado a?adido correctamente", "Succes",
							JOptionPane.INFORMATION_MESSAGE);
					vistaJIFVehicle.dispose();

				} else
					JOptionPane.showMessageDialog(vista, "Error al a?adir al empleado", "Error",
							JOptionPane.INFORMATION_MESSAGE);

			}
		};

		task.execute();

	}

	// Abre el JIF de crear vehiculo
	private void openNewVehicle() {
		// TODO Auto-generated method stub

		int index = vista.getTabbedPane().getSelectedIndex();

		if (!MainController.open(vistaJIFVehicle)) {
			vistaJIFVehicle = new JIFAddVehicle();
			MainController.addJIF(vistaJIFVehicle);

			switch (index) {

			case 0:
				vistaJIFVehicle.getTextFieldOpcion1().setInputPrompt("Num Plazas");
				vistaJIFVehicle.getTextFieldOpcion2().setInputPrompt("Num Puertas");
				break;
			case 1:
				vistaJIFVehicle.getTextFieldOpcion1().setInputPrompt("MMA");
				vistaJIFVehicle.getTextFieldOpcion2().setVisible(false);
				break;
			case 2:
				vistaJIFVehicle.getTextFieldOpcion1().setInputPrompt("Num Ruedas");
				vistaJIFVehicle.getTextFieldOpcion2().setInputPrompt("MMA");
				break;
			case 3:
				vistaJIFVehicle.getTextFieldOpcion1().setInputPrompt("Num Plazas");
				vistaJIFVehicle.getTextFieldOpcion2().setInputPrompt("Medidas");
				break;

			}

			vistaJIFVehicle.getBtnCancel().addActionListener(this);
			vistaJIFVehicle.getBtnAdd().addActionListener(this);
			vistaJIFVehicle.getBtnAdd().setActionCommand("Add");
			vistaJIFVehicle.getBtnCancel().setActionCommand("Cancel");

		} else
			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error",
					JOptionPane.ERROR_MESSAGE);

	}

	// filtra los arraylist segun lo indicado
	private void filter() {
		// TODO Auto-generated method stub

		int index = vista.getTabbedPane().getSelectedIndex();
		ArrayList<Vehicle> result;
		ArrayList<Vehicle> vehicle;

		switch (index) {

		case 0:
			vehicle = (ArrayList<Vehicle>) cars.stream().map((v) -> (Vehicle) v).collect(Collectors.toList());
			result = arrayListF(vehicle, vista.getPanelCar());
			ArrayList<Car> rCar = (ArrayList<Car>) result.stream().map((v) -> (Car) v).collect(Collectors.toList());
			mtmCar = new MyCarTableModel(rCar);
			vista.getPanelCar().getTable().setModel(mtmCar);
			break;

		case 1:
			vehicle = (ArrayList<Vehicle>) vans.stream().map((v) -> (Vehicle) v).collect(Collectors.toList());
			result = arrayListF(vehicle, vista.getPanelVan());
			ArrayList<Van> rVan = (ArrayList<Van>) result.stream().map((v) -> (Van) v).collect(Collectors.toList());
			mtmVan = new MyVanTableModel(rVan);
			vista.getPanelVan().getTable().setModel(mtmVan);
			break;

		case 2:
			vehicle = (ArrayList<Vehicle>) trucks.stream().map((v) -> (Vehicle) v).collect(Collectors.toList());
			result = arrayListF(vehicle, vista.getPanelTruck());
			ArrayList<Truck> rTruck = (ArrayList<Truck>) result.stream().map((v) -> (Truck) v)
					.collect(Collectors.toList());
			mtmTruck = new MyTruckTableModel(rTruck);
			vista.getPanelTruck().getTable().setModel(mtmTruck);
			break;

		case 3:
			vehicle = (ArrayList<Vehicle>) miniBus.stream().map((v) -> (Vehicle) v).collect(Collectors.toList());
			result = arrayListF(vehicle, vista.getPanelMiniBus());
			ArrayList<Minibus> rMiniB = (ArrayList<Minibus>) result.stream().map((v) -> (Minibus) v)
					.collect(Collectors.toList());
			mtmMiniBus = new MyMinibusTableModel(rMiniB);
			vista.getPanelMiniBus().getTable().setModel(mtmMiniBus);
			break;

		}

	}

	private ArrayList<Vehicle> arrayListF(ArrayList<Vehicle> vehicle, JPVehicles JPv) {

		ArrayList<Vehicle> result;
		result = (ArrayList<Vehicle>) vehicle.stream()
				.filter((c) -> ((Vehicle) c).getModel().toUpperCase()
						.contains(JPv.getTextFieldModel().getText().toUpperCase()))
				.filter((c) -> ((Vehicle) c).getRegistration().toUpperCase()
						.contains(JPv.getTextFieldRegistration().getText().toUpperCase()))
				.filter((c) -> ((Vehicle) c).getEngine().toUpperCase()
						.contains(JPv.getComboBoxEngine().getSelectedItem().toString().toUpperCase())
						|| JPv.getComboBoxEngine().getSelectedItem().toString().equals("All"))
				.filter((c) -> ((Vehicle) c).getDrivingLicense().toUpperCase()
						.contains(JPv.getComboBoxDrivingLicense().getSelectedItem().toString().toUpperCase())
						|| JPv.getComboBoxDrivingLicense().getSelectedItem().toString().equals("All"))
				.collect(Collectors.toList());
		return result;
	}

	// elimina un vehiculo
	private void removeRow() {
		// TODO Auto-generated method stub

		int input = JOptionPane.showConfirmDialog(null, "Estas seguro?", "Elige una opcion...",
				JOptionPane.YES_NO_OPTION);

		if (input == 0) {
			int row = -1;
			int index = vista.getTabbedPane().getSelectedIndex();
			Vehicle v = null;
			JPVehicles JPv = null;
			String table = " ";

			switch (index) {

			case 0:
				JPv = vista.getPanelCar();
				row = JPv.getTable().getSelectedRow();
				v = (Car) mtmCar.getElement(row);
				try {

					almacenDatos.deleteVehicles("COCHE", v);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					if (e.getErrorCode() == 20001)
						JOptionPane.showMessageDialog(vista, "No existe un coche con esta matricula", "Error",
								JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(vista, "error no esperado", "Error", JOptionPane.ERROR_MESSAGE);
				}
				mtmCar.removeElement(v);
				break;

			case 1:

				JPv = vista.getPanelVan();
				row = JPv.getTable().getSelectedRow();
				v = (Van) mtmVan.getElement(row);
				try {

					almacenDatos.deleteVehicles("FURGONETA", v);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					if (e.getErrorCode() == 20001)
						JOptionPane.showMessageDialog(vista, "No existe una furgoneta con esta matricula", "Error",
								JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(vista, "error no esperado", "Error", JOptionPane.ERROR_MESSAGE);
				}
				mtmVan.removeElement(v);

				break;

			case 2:

				JPv = vista.getPanelTruck();
				row = JPv.getTable().getSelectedRow();
				v = (Truck) mtmTruck.getElement(row);
				try {

					almacenDatos.deleteVehicles("CAMION", v);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					if (e.getErrorCode() == 20001)
						JOptionPane.showMessageDialog(vista, "No existe un camion con esta matricula", "Error",
								JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(vista, "error no esperado", "Error", JOptionPane.ERROR_MESSAGE);
				}
				mtmTruck.removeElement(v);

				break;

			case 3:

				JPv = vista.getPanelMiniBus();
				row = JPv.getTable().getSelectedRow();
				v = (Minibus) mtmMiniBus.getElement(row);
				try {

					almacenDatos.deleteVehicles("MICROBUS", v);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					if (e.getErrorCode() == 20001)
						JOptionPane.showMessageDialog(vista, "No existe un microbus con esta matricula", "Error",
								JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(vista, "error no esperado", "Error", JOptionPane.ERROR_MESSAGE);
				}
				mtmMiniBus.removeElement(v);

				break;

			}
		}

	}

	// Almacena los vehiculos de la base de datos en un ArrayList
	private void fillOracle() {
		// TODO Auto-generated method stub

		SwingWorker<Boolean, Void> task = new SwingWorker<Boolean, Void>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub

				vistaCargar.setVisible(true);

				try {

					if (!isCancelled()) {

						cars = (ArrayList<Car>) almacenDatos.getVehicles("COCHE").stream().map((v) -> (Car) v)
								.collect(Collectors.toList());

						vans = (ArrayList<Van>) almacenDatos.getVehicles("FURGONETA").stream().map((v) -> (Van) v)
								.collect(Collectors.toList());

						trucks = (ArrayList<Truck>) almacenDatos.getVehicles("CAMION").stream().map((v) -> (Truck) v)
								.collect(Collectors.toList());

						miniBus = (ArrayList<Minibus>) almacenDatos.getVehicles("MICROBUS").stream()
								.map((v) -> (Minibus) v).collect(Collectors.toList());

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

						for (Component c : vista.getTabbedPane().getComponents()) {

							if (c instanceof JPVehicles) {

								((JPVehicles) c).getTable().setDefaultEditor(Date.class, new WebDateEditor());

							}

						}

						ArrayList<Vehicle> vehicles;

						mtmCar = new MyCarTableModel(cars);
						vista.getPanelCar().getTable().setModel(mtmCar);
						mtmCar.addTableModelListener(controller);
						vehicles = (ArrayList<Vehicle>) cars.stream().map((v) -> (Vehicle) v)
								.collect(Collectors.toList());
						setComboBoxes(vehicles, vista.getPanelCar());

						mtmVan = new MyVanTableModel(vans);
						vista.getPanelVan().getTable().setModel(mtmVan);
						mtmVan.addTableModelListener(controller);
						vehicles = (ArrayList<Vehicle>) vans.stream().map((v) -> (Vehicle) v)
								.collect(Collectors.toList());
						setComboBoxes(vehicles, vista.getPanelVan());

						mtmTruck = new MyTruckTableModel(trucks);
						vista.getPanelTruck().getTable().setModel(mtmTruck);
						mtmTruck.addTableModelListener(controller);
						vehicles = (ArrayList<Vehicle>) trucks.stream().map((v) -> (Vehicle) v)
								.collect(Collectors.toList());
						setComboBoxes(vehicles, vista.getPanelTruck());

						mtmMiniBus = new MyMinibusTableModel(miniBus);
						vista.getPanelMiniBus().getTable().setModel(mtmMiniBus);
						mtmMiniBus.addTableModelListener(controller);
						vehicles = (ArrayList<Vehicle>) miniBus.stream().map((v) -> (Vehicle) v)
								.collect(Collectors.toList());
						setComboBoxes(vehicles, vista.getPanelMiniBus());

						vistaCargar.doDefaultCloseAction();

					} catch (Exception e) {

						e.printStackTrace();

					}

				} else {

					vista.dispose();

				}

			}

			private void setComboBoxes(ArrayList<Vehicle> v, JPVehicles vFrame) {

				List<String> c_engine = v.stream().map((p) -> p.getEngine()).distinct().collect(Collectors.toList());
				List<String> c_license = v.stream().map((p) -> p.getDrivingLicense()).distinct()
						.collect(Collectors.toList());

				for (String a : c_engine) {
					vFrame.getComboBoxEngine().addItem(a);
				}

				for (String a : c_license) {
					vFrame.getComboBoxDrivingLicense().addItem(a);
				}

			}
		};

		vistaCargar = new JIFCargar(task);
		MainController.addJIF(vistaCargar);

		vistaCargar.getLblTask().setText("Cargando vista de facturas");

		task.execute();

	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		if (e.getType() == TableModelEvent.UPDATE) {

			MyCarTableModel mtmCar = (MyCarTableModel) vista.getPanelCar().getTable().getModel();
			MyVanTableModel mtmVan = (MyVanTableModel) vista.getPanelVan().getTable().getModel();
			MyTruckTableModel mtmTruck = (MyTruckTableModel) vista.getPanelTruck().getTable().getModel();
			MyMinibusTableModel mtmMinibus = (MyMinibusTableModel) vista.getPanelMiniBus().getTable().getModel();

			SwingWorker<Boolean, Void> task = new SwingWorker<Boolean, Void>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					// TODO Auto-generated method stub
					try {

						if (!isCancelled()) {

							int index = vista.getTabbedPane().getSelectedIndex();

							if (index == 0) {

								Car car = mtmCar.getElement(e.getFirstRow());
								almacenDatos.updateVehicles(car);

							} else if (index == 1) {

								Van van = mtmVan.getElement(e.getFirstRow());
								almacenDatos.updateVehicles(van);

							} else if (index == 2) {

								Truck truck = mtmTruck.getElement(e.getFirstRow());
								almacenDatos.updateVehicles(truck);

							} else if (index == 3) {

								Minibus miniBus = mtmMinibus.getElement(e.getFirstRow());
								almacenDatos.updateVehicles(miniBus);

							}

						}

					} catch (SQLException e) {

						e.printStackTrace();

					}

					return null;
				}

			};

			task.execute();

		}

	}

}
