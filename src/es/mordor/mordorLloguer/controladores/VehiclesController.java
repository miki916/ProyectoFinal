package es.mordor.mordorLloguer.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
		update();	
	}
	
	private void inicializar() {
		// TODO Auto-generated method stub
		
		for(Component c : vista.getTabbedPane().getComponents()) {
			
			if(c instanceof JPVehicles) {
			
				((JPVehicles) c).getTextFieldModel().getDocument().addDocumentListener((SimpleDocumentListener) e -> { filter(); });
				((JPVehicles) c).getTextFieldRegistration().getDocument().addDocumentListener((SimpleDocumentListener) e -> { filter(); });
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
		
		if(command.equals("Model") || command.equals("Registration")) 
			
			filter();
				
		else if(command.equals("Remove")) 
			
			removeRow();
		
		else if(command.equals("OpenNewVehicle")) 
			
			openNewVehicle();
		
		else if(command.equals("Cancel"))
			
			vistaJIFVehicle.dispose();
		
		else if(command.equals("Add"))			
		
			addNewVehicle();
		
			

	}
	
	private void addNewVehicle() {
		// TODO Auto-generated method stub
	
			SwingWorker<Boolean,Void> task=new SwingWorker<Boolean,Void>(){
				
				@Override
				protected Boolean doInBackground() throws Exception {
					// TODO Auto-generated method stub
					
					String pattern = "yyyy-MM-dd";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					
					 String registration = vistaJIFVehicle.getTextFieldRegistration().getText();
					 int priceDay = (int) vistaJIFVehicle.getSpinnerPrecio().getValue();
					 String model = vistaJIFVehicle.getTextFieldModel().getText();
					 String color = vistaJIFVehicle.getTextFieldColor().getText();
					 String engine= vistaJIFVehicle.getComboBoxEngine().getSelectedItem().toString();
					 int displacement= (int) vistaJIFVehicle.getSpinnerDisplacement().getValue();
					 Date shopDay=  new Date(simpleDateFormat.parse(vistaJIFVehicle.getDate().toString()).getTime());
					 String status= vistaJIFVehicle.getComboBoxStatus().getSelectedItem().toString();
					 String drivingLicense= vistaJIFVehicle.getComboBoxDrivingLicense().getSelectedItem().toString();
					 int opcion1 = Integer.parseInt(vistaJIFVehicle.getTextFieldOpcion1().getText());
					 int opcion2 = 0;
					 
					 Vehicle v = null;
					
					 int index = vista.getTabbedPane().getSelectedIndex();
					 boolean error = false;
					
					try {
						
						switch(index) {
						
							case 0:
								
								opcion2 = Integer.parseInt(vistaJIFVehicle.getTextFieldOpcion2().getText());

								v = new Car(registration,priceDay,model,color,engine,displacement,shopDay,status,drivingLicense,opcion1,opcion2);
							
								error = almacenDatos.addVehicle("COCHE", v);
								
								break;
							
							case 1: 
								
								v = new Van(registration,priceDay,model,color,engine,displacement,shopDay,status,drivingLicense,opcion1);
								error = almacenDatos.addVehicle("FURGONETA", v);
	
								break;
							
							case 2:
								
								opcion2 = Integer.parseInt(vistaJIFVehicle.getTextFieldOpcion2().getText());

								v = new Truck(registration,priceDay,model,color,engine,displacement,shopDay,status,drivingLicense,opcion1,opcion2);
								error = almacenDatos.addVehicle("CAMION", v);
	
								break;
								
							case 3:
								opcion2 = Integer.parseInt(vistaJIFVehicle.getTextFieldOpcion2().getText());

								v = new Minibus(registration,priceDay,model,color,engine,displacement,shopDay,status,drivingLicense,opcion1,opcion2);
								error = almacenDatos.addVehicle("MICROBUS", v);
	
								break;
						}
						
						
					}catch(Exception e) {
						
						e.printStackTrace();
						
					}
					
					
					if(error) {
						
						update();
						JOptionPane.showMessageDialog(vista, "Empleado añadido correctamente", "Succes", JOptionPane.INFORMATION_MESSAGE);
						vistaJIFVehicle.dispose();			
					}else {
						
						JOptionPane.showMessageDialog(vista, "Error al añadir al empleado", "Error", JOptionPane.INFORMATION_MESSAGE);

						
					}
					
			
					
					return null;
				}
				
				
				
				
			};

			task.execute();					

		
	}

	private void openNewVehicle() {
		// TODO Auto-generated method stub
		
		int index = vista.getTabbedPane().getSelectedIndex();

		
		if(!MainController.open(vistaJIFVehicle)) {
			vistaJIFVehicle = new JIFAddVehicle();
			MainController.addJIF(vistaJIFVehicle);
			
			switch(index) {
			
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
				
				
		}else 			
			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error", JOptionPane.ERROR_MESSAGE);
		
		
		
	}

	private void filter() {
		// TODO Auto-generated method stub
		
		int index = vista.getTabbedPane().getSelectedIndex();
		ArrayList<Vehicle> result;
		ArrayList<Vehicle> vehicle;
		
		switch(index) {
			
			case 0 :
					vehicle =  (ArrayList<Vehicle>) cars.stream().map((v)->(Vehicle)v).collect(Collectors.toList());
					result = arrayListF(vehicle, vista.getPanelCar());		
					ArrayList<Car> rCar = (ArrayList<Car>) result.stream().map((v)->(Car)v).collect(Collectors.toList()); 	
					mtmCar = new MyCarTableModel(rCar);
					vista.getPanelCar().getTable().setModel(mtmCar);
					break;
					
			case 1:
					vehicle =  (ArrayList<Vehicle>) vans.stream().map((v)->(Vehicle)v).collect(Collectors.toList());
					result = arrayListF(vehicle, vista.getPanelVan());		
					ArrayList<Van> rVan = (ArrayList<Van>) result.stream().map((v)->(Van)v).collect(Collectors.toList()); 	
					mtmVan = new MyVanTableModel(rVan);
					vista.getPanelVan().getTable().setModel(mtmVan);
					break;
			
			case 2: 
					vehicle =  (ArrayList<Vehicle>) trucks.stream().map((v)->(Vehicle)v).collect(Collectors.toList());
					result = arrayListF(vehicle, vista.getPanelTruck());		
					ArrayList<Truck> rTruck = (ArrayList<Truck>) result.stream().map((v)->(Truck)v).collect(Collectors.toList()); 	
					mtmTruck = new MyTruckTableModel(rTruck);
					vista.getPanelTruck().getTable().setModel(mtmTruck);
					break;
			
			case 3: 
					vehicle =  (ArrayList<Vehicle>) miniBus.stream().map((v)->(Vehicle)v).collect(Collectors.toList());
					result = arrayListF(vehicle, vista.getPanelMiniBus());		
					ArrayList<Minibus> rMiniB = (ArrayList<Minibus>) result.stream().map((v)->(Minibus)v).collect(Collectors.toList()); 	
					mtmMiniBus = new MyMinibusTableModel(rMiniB);
					vista.getPanelMiniBus().getTable().setModel(mtmMiniBus);
					break;

		}

	}
	
	

	private ArrayList<Vehicle> arrayListF(ArrayList<Vehicle> vehicle, JPVehicles JPv) {
		
		ArrayList<Vehicle> result;
		result = (ArrayList<Vehicle>) vehicle.stream()
		.filter((c) -> ((Vehicle) c).getModel().toUpperCase().contains(JPv.getTextFieldModel().getText().toUpperCase()))
		.filter((c) -> ((Vehicle) c).getRegistration().toUpperCase().contains(JPv.getTextFieldRegistration().getText().toUpperCase()))
		.filter((c) -> ((Vehicle) c).getEngine().toUpperCase().contains(JPv.getComboBoxEngine().getSelectedItem().toString().toUpperCase()) 
				|| JPv.getComboBoxEngine().getSelectedItem().toString().equals("All"))
		.filter((c) -> ((Vehicle) c).getDrivingLicense().toUpperCase().contains(JPv.getComboBoxDrivingLicense().getSelectedItem().toString().toUpperCase()) || 
				JPv.getComboBoxDrivingLicense().getSelectedItem().toString().equals("All") )
		.collect(Collectors.toList());
		return result;
	}
	
	
			
	private void removeRow() {
		// TODO Auto-generated method stub
		
		 int input = JOptionPane.showConfirmDialog(null, "Estas seguro?", "Elige una opcion...",JOptionPane.YES_NO_OPTION);
		
		if(input == 0) {
			int row = -1;
			int index = vista.getTabbedPane().getSelectedIndex();
			Vehicle v = null;
			JPVehicles JPv = null;
			String table =" ";
			
			switch(index) {
			
				case 0: 
					JPv = vista.getPanelCar();
					row = JPv.getTable().getSelectedRow();
					v = (Car) mtmCar.getElement(row);
					almacenDatos.deleteVehicles("COCHE",v);
					mtmCar.removeElement(v);
					break;
					
				case 1:
					
					JPv = vista.getPanelVan();
					row = JPv.getTable().getSelectedRow();
					v = (Van) mtmVan.getElement(row);
					almacenDatos.deleteVehicles("FURGONETA",v);
					mtmVan.removeElement(v);
	
					break;
				
				case 2:
					
					JPv = vista.getPanelTruck();
					row = JPv.getTable().getSelectedRow();
					v = (Truck) mtmTruck.getElement(row);
					almacenDatos.deleteVehicles("CAMION",v);
					mtmTruck.removeElement(v);
				
	
					break;
				
				case 3:
					
					JPv = vista.getPanelMiniBus();
					row = JPv.getTable().getSelectedRow();
					v = (Minibus) mtmMiniBus.getElement(row);	
					almacenDatos.deleteVehicles("MICROBUS",v);
					mtmMiniBus.removeElement(v);
					
	
					break;
			
			}
		}
				
	}
	

	
	private void update() {
		// TODO Auto-generated method stub
		
		SwingWorker<Boolean,Void> task = new SwingWorker<Boolean,Void>(){

			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				try {
					
					if(!isCancelled()) {
						
						cars = (ArrayList<Car>) almacenDatos.getVehicles("COCHE").stream()
																				   .map((v)->(Car)v)
																				   .collect(Collectors.toList());
										
						vans = (ArrayList<Van>) almacenDatos.getVehicles("FURGONETA").stream()
																					   .map((v)->(Van)v)
																					   .collect(Collectors.toList());
						
						trucks = (ArrayList<Truck>) almacenDatos.getVehicles("CAMION").stream()
																							 .map((v)->(Truck)v)
																							 .collect(Collectors.toList());
						
						miniBus = (ArrayList<Minibus>) almacenDatos.getVehicles("MICROBUS").stream()
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
						
						for(Component c : vista.getTabbedPane().getComponents()) {
							
							
							if(c instanceof JPVehicles) {
							
								((JPVehicles) c).getTable().setDefaultEditor(Date.class, new WebDateEditor());
								
							}
							
						}
											
						mtmCar=new MyCarTableModel(cars);
						vista.getPanelCar().getTable().setModel(mtmCar);
						mtmCar.addTableModelListener(controller);
						comboBox( cars.get(0), vista.getPanelCar());
						
												
						mtmVan = new MyVanTableModel(vans);
						vista.getPanelVan().getTable().setModel(mtmVan);
						mtmVan.addTableModelListener(controller);
						comboBox( vans.get(0), vista.getPanelVan());

														
						mtmTruck = new MyTruckTableModel(trucks);
						vista.getPanelTruck().getTable().setModel(mtmTruck);
						mtmTruck.addTableModelListener(controller);
						comboBox( trucks.get(0), vista.getPanelTruck());
				
						
						mtmMiniBus = new MyMinibusTableModel(miniBus);
						vista.getPanelMiniBus().getTable().setModel(mtmMiniBus);
						mtmMiniBus.addTableModelListener(controller);
						comboBox( miniBus.get(0), vista.getPanelMiniBus());
						
						

															
						
					}catch(Exception e) {
						 
						e.printStackTrace();
						
					}
			
					
				}else {
					
					vista.dispose();
					
				}
					
			}

			private void comboBox( Object o,  JPVehicles JPv ) {
				// TODO Auto-generated method stub
				
				ArrayList<Vehicle> p = new ArrayList<Vehicle>();
				
				if( o instanceof Car) 
					
					p = (ArrayList<Vehicle>) cars.stream()
							.map((v)->(Vehicle)v)
							.collect(Collectors.toList());
				
				else if ( o instanceof Van) 
					
					p = (ArrayList<Vehicle>) vans.stream()
							.map((v)->(Vehicle)v)
							.collect(Collectors.toList());
					
				else if ( o instanceof Truck) 
					
					p = (ArrayList<Vehicle>) trucks.stream()
							.map((v)->(Vehicle)v)
							.collect(Collectors.toList());
					
				else 
					
					p = (ArrayList<Vehicle>) miniBus.stream()
							.map((v)->(Vehicle)v)
							.collect(Collectors.toList());
					
				
				ArrayList<String> l = new ArrayList<String>();
				ArrayList<String> e = new ArrayList<String>();
			
				for(Vehicle v : p) {
					
					String license = v.getDrivingLicense();
					String engine =  v.getEngine();
					
					if(!l.contains(license)) {
						
						l.add(license);
						JPv.getComboBoxDrivingLicense().addItem(license);
						
					}
					
					if (!e.contains(engine)) {
						
						e.add(engine);
						JPv.getComboBoxEngine().addItem(engine);

						
					}	
				}
			}
		};
		
		task.execute();
		
	}

	
	
	@Override
	
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		if(e.getType() == TableModelEvent.UPDATE) {
			
			
			MyCarTableModel mtmCar = (MyCarTableModel) vista.getPanelCar().getTable().getModel();
			MyVanTableModel mtmVan = (MyVanTableModel) vista.getPanelVan().getTable().getModel();
			MyTruckTableModel mtmTruck = (MyTruckTableModel) vista.getPanelTruck().getTable().getModel();
			MyMinibusTableModel mtmMinibus = (MyMinibusTableModel) vista.getPanelMiniBus().getTable().getModel();

			
			Car car = mtmCar.getElement(e.getFirstRow());
			Van van = mtmVan.getElement(e.getFirstRow());
			Truck truck = mtmTruck.getElement(e.getFirstRow());
			Minibus miniBus = mtmMinibus.getElement(e.getFirstRow());

			SwingWorker<Boolean,Void> task=new SwingWorker<Boolean,Void>(){
				

				@Override
				protected Boolean doInBackground() throws Exception {
					// TODO Auto-generated method stub
					try {
						
						if(!isCancelled()) {

							almacenDatos.updateVehicles(car);
							almacenDatos.updateVehicles(van);
							almacenDatos.updateVehicles(truck);
							almacenDatos.updateVehicles(miniBus);							
						}
							
						
					}catch(Exception e) {
						
						e.printStackTrace();
						
					}
					
					return null;
				}
				
				
				
			};
			
			task.execute();
		
		}
		
		
	}

	
}
