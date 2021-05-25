package es.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
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
import es.mordor.mordorLloguer.controladores.EmployeeController.MyTableModelEmpleados;
import es.mordor.mordorLloguer.vistas.*;

public class CustomerController implements ActionListener, TableModelListener {
	
	private JIFCustomer vista;
	private AlmacenDatosDB almacenDatos;
	private JIFAddCustomer vistaAddCustomer;
	private MyTableModel mtm;
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private CustomerController controlador;
	
	public CustomerController(JIFCustomer vistaClientes,AlmacenDatosDB almacenDatos) {
		
		this.vista = vistaClientes;
		this.almacenDatos = almacenDatos;
		controlador = this;
		inicializar();
		
	}
	
	
	private void inicializar() {
		// TODO Auto-generated method stub

		vista.getBtnAdd().addActionListener(this);
		vista.getBtnRemove().addActionListener(this);
		vista.getBtnPrint().addActionListener(this);
		vista.getComboBox().addActionListener(this);
		
		vista.getTextFieldDni().getDocument().addDocumentListener((SimpleDocumentListener) e -> {

            filter();

        });
		
		vista.getTextFieldName().getDocument().addDocumentListener((SimpleDocumentListener) e -> {

            filter();

        });
		
		vista.getTextFieldSurname().getDocument().addDocumentListener((SimpleDocumentListener) e -> {

            filter();

        });
		
		
		
		vista.getBtnAdd().setActionCommand("OpenNewCliente");
		vista.getBtnRemove().setActionCommand("Remove");
		vista.getBtnPrint().setActionCommand("Print");
		vista.getComboBox().setActionCommand("Combobox");

		
		
	}
	
	private void filter() {
		// TODO Auto-generated method stub
		
		ArrayList<Customer> result;
		
		
		if(vista.getComboBox().getSelectedItem().toString().equals("All")) {
			
			 result = (ArrayList<Customer>) customers.stream()
					.filter((c) -> c.getDNI().contains(vista.getTextFieldDni().getText()))
					.filter((c) -> c.getName().contains(vista.getTextFieldName().getText()))
					.filter((c) -> c.getSurname().contains(vista.getTextFieldSurname().getText()))
					.collect(Collectors.toList());
			
		}else {
			
			result = (ArrayList<Customer>) customers.stream()
					.filter((c) -> c.getDNI().contains(vista.getTextFieldDni().getText()))
					.filter((c) -> c.getName().contains(vista.getTextFieldName().getText()))
					.filter((c) -> c.getSurname().contains(vista.getTextFieldSurname().getText()))
					.filter((c) -> c.getCarnet().contains(vista.getComboBox().getSelectedItem().toString()))
					.collect(Collectors.toList());
			
		}
		
		
		
		System.out.println("hehe");
		mtm = new MyTableModelCustomer(result);
		vista.getTable().setModel(mtm);
		
		
	}


	public void go() {
			
			MainController.addJIF(vista);
			sort();	
	}

	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String command = e.getActionCommand();
		
		if(command == "OpenNewCliente") {
			
			openAddCliente();
			
		}else if(command == "Remove") {
			
			removeRow();
			
		}else if( command == "Combobox") {
		
			filter();			
			
		}else if(command =="Add"){
			
			newCustomer();
			
		}else if(command == "Cancel") {
			
			vistaAddCustomer.dispose();
			
		}
			
		
	}
	
	private void openAddCliente() {
		// TODO Auto-generated method stub
		
		if(!MainController.open(vistaAddCustomer)) {
			
			vistaAddCustomer = new JIFAddCustomer();
			MainController.addJIF(vistaAddCustomer);
			vistaAddCustomer.getBtnAdd().addActionListener(this);
			vistaAddCustomer.getBtnCancel().addActionListener(this);
			
			vistaAddCustomer.getBtnAdd().setActionCommand("Add");
			vistaAddCustomer.getBtnCancel().setActionCommand("Cancel");
			
		}else {
			
			JOptionPane.showMessageDialog(vista, "Esta ventana ya ha sido generada", "Error", JOptionPane.ERROR_MESSAGE);

		}
		
	}
	
	private void newCustomer() {
		// TODO Auto-generated method stub
		
		SwingWorker<Boolean,Void> task=new SwingWorker<Boolean,Void>(){
			
		
			
			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				String DNI = vistaAddCustomer.getTextFieldDni().getText();
				String name = vistaAddCustomer.getTextFieldName().getText();
				String apellidos = vistaAddCustomer.getTextFieldSurname().getText();
				String CP = vistaAddCustomer.getTextFieldCP().getText();
				String email = vistaAddCustomer.getTextFieldEmail().getText();
				String fecha = vistaAddCustomer.getDateTextField().getDate().toString();
				String carnet = (String) vistaAddCustomer.getComboBox().getSelectedItem();
				String domicilio = vistaAddCustomer.getTextFieldAddress().getText();
				Boolean error = false;
				try {
					
													
					String[] data = {DNI,name,apellidos,domicilio,CP,email,fecha,carnet};
						
					error = almacenDatos.addCustomer(data);
					
					
				}catch(Exception e) {
					
					e.printStackTrace();
					
				}
				
				if(error) {
					
					sort();
					JOptionPane.showMessageDialog(vista, "Cliente añadido correctamente", "Succes", JOptionPane.INFORMATION_MESSAGE);
					vistaAddCustomer.dispose();
					
				}else {
					
					JOptionPane.showMessageDialog(vista, "Error al añadir al cliente", "Error", JOptionPane.INFORMATION_MESSAGE);

					
				}
				
		
				
				return null;
			}
			
			
			
			
		};

		task.execute();
		
	}

	private void removeRow() {
		// TODO Auto-generated method stub
		
		int row = vista.getTable().getSelectedRow();
		Customer e = (Customer) mtm.getElement(row);
		almacenDatos.deleteEmployee(e.getDNI());
		mtm.removeElement(e);
		
	}


	private void sort() {
		// TODO Auto-generated method stub
		
		SwingWorker<Boolean,Void> task = new SwingWorker<Boolean,Void>(){
			
		
			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				try {
					
					if(!isCancelled()) {
						
						customers = almacenDatos.getClient();
					
					}
					
				}catch(Exception e) {
					
					e.printStackTrace();
					
				}
				
				return null;
			}
			
			protected void done() {
				
				if(!isCancelled()) {
					
					try {
						
						mtm=new MyTableModelCustomer(customers);
						vista.getTable().setModel(mtm);
						
						vista.getTable().setDefaultEditor(Date.class, new WebDateEditor());

							
						JComboBox<String> combobox = new JComboBox<String>();
						combobox.addItem("A");
						combobox.addItem("B");
						combobox.addItem("C");
						combobox.addItem("D");
						combobox.addItem("E");
						combobox.addItem("Z");
						
						TableColumn column = vista.getTable().getColumn("Carnet");
						column.setCellEditor(new DefaultCellEditor(combobox));
						
											
						mtm.addTableModelListener(controlador);
						
						
						
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
	
	
	public class MyTableModelCustomer extends MyTableModel<Customer>{
		
		public MyTableModelCustomer(List<Customer> data) {
			
			super(new String[] {"DNI","Nombre","Apellidos","Domicilio","CP","Email","FechaNac","Carnet"}, data);
			
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			
			switch(col) {
				
				case 0:
					return data.get(row).getDNI();
				case 1:
					return data.get(row).getName();
				case 2: 
					return data.get(row).getSurname();
				case 3:
					return data.get(row).getAddress();
				case 4: 
					return data.get(row).getCP();
				case 5: 
					return data.get(row).getEmail();
				case 6:
					return data.get(row).getFechaNac();
				case 7: 
					return data.get(row).getCarnet();
				
			
			}
			
			return null;
		}
		
		
	}
	
	
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

}
