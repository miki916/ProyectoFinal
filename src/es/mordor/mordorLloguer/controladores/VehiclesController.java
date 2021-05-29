package es.mordor.mordorLloguer.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import com.alee.laf.table.editors.WebDateEditor;

import es.mordor.morderLloguer.model.BBDD.*;
import es.mordor.mordorLloguer.controladores.CustomerController.MyTableModelCustomer;
import es.mordor.mordorLloguer.vistas.*;

public class VehiclesController implements ActionListener, TableModelListener {
	
	private JIFVehicles vista;
	private AlmacenDatosDB almacenDatos;
	private MyVehicleTableModel mtmCar;
	private MyVehicleTableModel mtmVan;
	private MyVehicleTableModel mtmTruck;
	private MyVehicleTableModel mtmMiniBus;
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
	
	private void inicializar() {
		// TODO Auto-generated method stub
		
		vista.getPanelCar().getTextFieldModel().getDocument().addDocumentListener((SimpleDocumentListener) e -> { filterC(); });
		vista.getPanelCar().getTextFieldRegistration().getDocument().addDocumentListener((SimpleDocumentListener) e -> { filterC(); });
		vista.getPanelCar().getComboBoxEngine().addActionListener(this);
		vista.getPanelCar().getComboBoxDrivingLicense().addActionListener(this);
		vista.getPanelCar().getComboBoxEngine().setActionCommand("ModelCar");
		vista.getPanelCar().getComboBoxDrivingLicense().setActionCommand("RegistrationCar");
		
		vista.getPanelVan().getTextFieldModel().getDocument().addDocumentListener((SimpleDocumentListener) e -> { filterV(); });
		vista.getPanelVan().getTextFieldRegistration().getDocument().addDocumentListener((SimpleDocumentListener) e -> { filterV(); });
		vista.getPanelVan().getComboBoxEngine().addActionListener(this);
		vista.getPanelVan().getComboBoxDrivingLicense().addActionListener(this);
		vista.getPanelVan().getComboBoxEngine().setActionCommand("ModelVan");
		vista.getPanelVan().getComboBoxDrivingLicense().setActionCommand("RegistrationVan");
		
		vista.getPanelTruck().getTextFieldModel().getDocument().addDocumentListener((SimpleDocumentListener) e -> { filterT(); });
		vista.getPanelTruck().getTextFieldRegistration().getDocument().addDocumentListener((SimpleDocumentListener) e -> { filterT(); });
		vista.getPanelTruck().getComboBoxEngine().addActionListener(this);
		vista.getPanelTruck().getComboBoxDrivingLicense().addActionListener(this);
		vista.getPanelTruck().getComboBoxEngine().setActionCommand("ModelTruck");
		vista.getPanelTruck().getComboBoxDrivingLicense().setActionCommand("RegistrationTruck");
		
		vista.getPanelMiniBus().getTextFieldModel().getDocument().addDocumentListener((SimpleDocumentListener) e -> { filterM(); });
		vista.getPanelMiniBus().getTextFieldRegistration().getDocument().addDocumentListener((SimpleDocumentListener) e -> { filterM(); });
		vista.getPanelMiniBus().getComboBoxEngine().addActionListener(this);
		vista.getPanelMiniBus().getComboBoxDrivingLicense().addActionListener(this);
		vista.getPanelMiniBus().getComboBoxEngine().setActionCommand("ModelMinibus");
		vista.getPanelMiniBus().getComboBoxDrivingLicense().setActionCommand("RegistrationMinibus");
		
		
		
	}
	
	
	private void filterM() {
		// TODO Auto-generated method stub
		
		ArrayList<Minibus> result;
		
		result = (ArrayList<Minibus>) miniBus.stream()
		.filter((c) -> c.getModel().toUpperCase().contains(vista.getPanelMiniBus().getTextFieldModel().getText().toUpperCase()))
		.filter((c) -> c.getRegistration().toUpperCase().contains(vista.getPanelMiniBus().getTextFieldRegistration().getText().toUpperCase()))
		.filter((c) -> c.getEngine().toUpperCase().contains(vista.getPanelMiniBus().getComboBoxEngine().getSelectedItem().toString()) 
				|| vista.getPanelMiniBus().getComboBoxEngine().getSelectedItem().toString().equals("All"))
		.filter((c) -> c.getDrivingLicense().toUpperCase().contains(vista.getPanelMiniBus().getComboBoxDrivingLicense().getSelectedItem().toString()) || 
				vista.getPanelMiniBus().getComboBoxDrivingLicense().getSelectedItem().toString().equals("All") )
		.collect(Collectors.toList());
		

	mtmMiniBus = new MyMinibusTableModel(result);
	vista.getPanelMiniBus().getTable().setModel(mtmMiniBus);
		
		
	}

	private void filterT() {
		// TODO Auto-generated method stub
		
		ArrayList<Truck> result;
		
		result = (ArrayList<Truck>) trucks.stream()
		.filter((c) -> c.getModel().toUpperCase().contains(vista.getPanelTruck().getTextFieldModel().getText().toUpperCase()))
		.filter((c) -> c.getRegistration().toUpperCase().contains(vista.getPanelTruck().getTextFieldRegistration().getText().toUpperCase()))
		.filter((c) -> c.getEngine().toUpperCase().contains(vista.getPanelTruck().getComboBoxEngine().getSelectedItem().toString()) 
				|| vista.getPanelTruck().getComboBoxEngine().getSelectedItem().toString().equals("All"))
		.filter((c) -> c.getDrivingLicense().toUpperCase().contains(vista.getPanelTruck().getComboBoxDrivingLicense().getSelectedItem().toString()) || 
				vista.getPanelTruck().getComboBoxDrivingLicense().getSelectedItem().toString().equals("All") )
		.collect(Collectors.toList());
		

	mtmTruck = new MyTruckTableModel(result);
	vista.getPanelTruck().getTable().setModel(mtmTruck);
		
	}

	private void filterV() {
		// TODO Auto-generated method stub
		
	ArrayList<Van> result;
		
		result = (ArrayList<Van>) vans.stream()
		.filter((c) -> c.getModel().toUpperCase().contains(vista.getPanelVan().getTextFieldModel().getText().toUpperCase()))
		.filter((c) -> c.getRegistration().toUpperCase().contains(vista.getPanelVan().getTextFieldRegistration().getText().toUpperCase()))
		.filter((c) -> c.getEngine().toUpperCase().contains(vista.getPanelVan().getComboBoxEngine().getSelectedItem().toString()) 
				|| vista.getPanelVan().getComboBoxEngine().getSelectedItem().toString().equals("All"))
		.filter((c) -> c.getDrivingLicense().toUpperCase().contains(vista.getPanelVan().getComboBoxDrivingLicense().getSelectedItem().toString()) || 
				vista.getPanelVan().getComboBoxDrivingLicense().getSelectedItem().toString().equals("All") )
		.collect(Collectors.toList());
		

	mtmVan = new MyVanTableModel(result);
	vista.getPanelVan().getTable().setModel(mtmVan);
		
	}

	private void filterC() {
		// TODO Auto-generated method stub
		
		ArrayList<Car> result;
		
		result = (ArrayList<Car>) cars.stream()
		.filter((c) -> c.getModel().toUpperCase().contains(vista.getPanelCar().getTextFieldModel().getText().toUpperCase()))
		.filter((c) -> c.getRegistration().toUpperCase().contains(vista.getPanelCar().getTextFieldRegistration().getText().toUpperCase()))
		.filter((c) -> c.getEngine().toUpperCase().contains(vista.getPanelCar().getComboBoxEngine().getSelectedItem().toString()) 
				|| vista.getPanelCar().getComboBoxEngine().getSelectedItem().toString().equals("All"))
		.filter((c) -> c.getDrivingLicense().toUpperCase().contains(vista.getPanelCar().getComboBoxDrivingLicense().getSelectedItem().toString()) || 
				vista.getPanelCar().getComboBoxDrivingLicense().getSelectedItem().toString().equals("All") )
		.collect(Collectors.toList());
		

	mtmCar = new MyCarTableModel(result);
	vista.getPanelCar().getTable().setModel(mtmCar);
		
		
	}

	public void go() {
		
		MainController.addJIF(vista);
		sort();	
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void sort() {
		// TODO Auto-generated method stub
		
		SwingWorker<Boolean,Void> task = new SwingWorker<Boolean,Void>(){

			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				try {
					
					if(!isCancelled()) {
						
						cars = (ArrayList<Car>) almacenDatos.getCarsOrderBy("MATRICULA").stream()
																						.map((v)->(Car)v)
																						.collect(Collectors.toList());
						
						
						vans = (ArrayList<Van>) almacenDatos.getVanOrderBy("MATRICULA").stream()
																					   .map((v)->(Van)v)
																					   .collect(Collectors.toList());
						
						trucks = (ArrayList<Truck>) almacenDatos.getTruckOrderBy("MATRICULA").stream()
																							 .map((v)->(Truck)v)
																							 .collect(Collectors.toList());
						
						miniBus = (ArrayList<Minibus>) almacenDatos.getMiniBusOrderBy("MATRICULA").stream()
								 																  .map((v)->(Minibus)v)
								 																  .collect(Collectors.toList());
						
					}
					
				}catch(Exception e) {
					
					e.printStackTrace();
				}
				
				return null;
			}
			
			protected void done() {
				
				if(!isCancelled()) {
					
					try {
						
						mtmCar=new MyCarTableModel(cars);
						vista.getPanelCar().getTable().setModel(mtmCar);
						mtmCar.addTableModelListener(controller);

						mtmVan = new MyVanTableModel(vans);
						vista.getPanelVan().getTable().setModel(mtmVan);
						mtmVan.addTableModelListener(controller);
									
						mtmTruck = new MyTruckTableModel(trucks);
						vista.getPanelTruck().getTable().setModel(mtmTruck);
						mtmTruck.addTableModelListener(controller);
						
						mtmMiniBus = new MyMinibusTableModel(miniBus);
						vista.getPanelTruck().getTable().setModel(mtmMiniBus);
						mtmMiniBus.addTableModelListener(controller);
						
						
						
					}catch(Exception e) {
						
						e.printStackTrace();
						
					}
			
					
				}else {
					
					vista.dispose();
					
				}
					
			}
			
			
		};
		
		task.execute();
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
