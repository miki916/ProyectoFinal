package es.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
import es.mordor.mordorLloguer.controladores.EmployeeController.MyTableModelEmpleados;
import es.mordor.mordorLloguer.tableModel.MyTableModel;
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
		
			result = (ArrayList<Customer>) customers.stream()
			.filter((c) -> c.getDNI().toUpperCase().contains(vista.getTextFieldDni().getText().toUpperCase()))
			.filter((c) -> c.getName().toUpperCase().contains(vista.getTextFieldName().getText().toUpperCase()))
			.filter((c) -> c.getSurname().toUpperCase().contains(vista.getTextFieldSurname().getText().toUpperCase()))
			.filter((c) -> c.getCarnet().toUpperCase().contains(vista.getComboBox().getSelectedItem().toString()) || 
					vista.getComboBox().getSelectedItem().toString().equals("All") )
			.collect(Collectors.toList());
			
	
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
						
					}else 
			
						JOptionPane.showMessageDialog(vista, "Error al añadir al cliente", "Error", JOptionPane.INFORMATION_MESSAGE);

					
				return null;
			}
			
		};

		task.execute();
		
	}

	private void removeRow() {
		// TODO Auto-generated method stub
		
		 int input = JOptionPane.showConfirmDialog(null, "Estas seguro?", "Elige una opcion...",JOptionPane.YES_NO_OPTION);
		 
		 if(input == 0) {
			 
				int row = vista.getTable().getSelectedRow();
				Customer e = (Customer) mtm.getElement(row);
				almacenDatos.deleteCustomer(e.getDNI());
				mtm.removeElement(e); 
			 
		 }
	
		
	}


	private void sort() {
		// TODO Auto-generated method stub
		
		SwingWorker<Boolean,Void> task = new SwingWorker<Boolean,Void>(){
			
		
			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				try {
					
					if(!isCancelled()) {
						
						customers = almacenDatos.getCustomerOrderBy("APELLIDOS" + " ASC");
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
			
			super(Arrays.asList("DNI","Nombre","Apellidos","Domicilio","CP","Email","FechaNac","Carnet"), data);
			
		}
		
		public Class<?> getColumnClass(int colIndex) {
			switch(colIndex) {
			case 6: return Date.class;
			default: return String.class;
			}
			
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


		@Override
		public void setValueAt(Object value, int row, int col) {
			// TODO Auto-generated method stub
			
			switch(col) {
				
				case 1:
					data.get(row).setName(value.toString());
					break;
				
				case 2:
					data.get(row).setSurname(value.toString());
					break;
				
				case 3: 
					data.get(row).setAddress(value.toString());
					break;
				
				case 4:
					data.get(row).setCP(value.toString());
					break;
				
				case 5:
					data.get(row).setEmail(value.toString());
					break;
				
				case 6: 
					java.util.Date fecha=(java.util.Date)value;
					data.get(row).setFechaNac(new java.sql.Date(fecha.getTime()));
					break;
					
				case 7:
					data.get(row).setCarnet(value.toString());
					break;
			}
			
			fireTableCellUpdated(row,  col);

		}
		
		
	}
	
	
	@Override
	public void tableChanged(TableModelEvent e) {

		if(e.getType() == TableModelEvent.UPDATE) {
			
		
			MyTableModelCustomer mtm = (MyTableModelCustomer) vista.getTable().getModel();
			
			Customer cliente = mtm.getElement(e.getFirstRow());
			
			SwingWorker<Boolean,Void> task=new SwingWorker<Boolean,Void>(){
				

				@Override
				protected Boolean doInBackground() throws Exception {
					// TODO Auto-generated method stub
					try {
						
						if(!isCancelled())
							almacenDatos.updateCustomer(cliente);
						
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
