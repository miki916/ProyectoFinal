package es.mordor.mordorLloguer.controladores;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import com.alee.laf.table.editors.WebDateEditor;
import com.github.lgooddatepicker.components.DatePicker;

import es.mordor.morderLloguer.model.BBDD.*;
import es.mordor.mordorLloguer.vistas.*;

public class EmployeeController implements ActionListener, TableModelListener{
	
	private JIFEmployees vistaEmpleados;
	private JIFAddEmployee vistaAddEmpleados;
	private AlmacenDatosDB almacenDatos;
	private MyTableModel mtm;
	private EmployeeController controlador;
	
	public EmployeeController(JIFEmployees vistaEmpleados, AlmacenDatosDB almacenDatos) {
		
		this.almacenDatos = almacenDatos;
		this.vistaEmpleados = vistaEmpleados;
		controlador = this;
		
		inicializar();
		
	}
	
	private void inicializar() {
		
		vistaEmpleados.getBtnAdd().addActionListener(this);
		vistaEmpleados.getBtnRemove().addActionListener(this);
		vistaEmpleados.getComboBoxOrder().addActionListener(this);
		vistaEmpleados.getComboBoxSort().addActionListener(this);
		
		vistaEmpleados.getBtnAdd().setActionCommand("OpenNewEmpleado");
		vistaEmpleados.getBtnRemove().setActionCommand("Remove");

	}
	
	public void go() {
		
		MainController.addJIF(vistaEmpleados);
		sort();		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if(command == "OpenNewEmpleado") {
			
			openAddEmpleado();
		
		}else if(command == "Remove") {
			
			removeRow();
			
		}else if(command == "Cancel") {
			
			vistaAddEmpleados.dispose();
			
		}else if(command == "Add"){
			
			newEmpleado();
			
		}else
			sort();
		
		
	}


	private void newEmpleado() {
		// TODO Auto-generated method stub
		
		SwingWorker<Boolean,Void> task=new SwingWorker<Boolean,Void>(){
			
		
			
			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				String DNI = vistaAddEmpleados.getTextFieldDni().getText();
				String name = vistaAddEmpleados.getTextFieldName().getText();
				String apellidos = vistaAddEmpleados.getTextFieldSurname().getText();
				String CP = vistaAddEmpleados.getTextFieldCP().getText();
				String email = vistaAddEmpleados.getTextFieldEmail().getText();
				String fecha = vistaAddEmpleados.getDateTextField().getDate().toString();
				String cargo = (String) vistaAddEmpleados.getComboBox().getSelectedItem();
				String domicilio = vistaAddEmpleados.getTextFieldAddress().getText();
				String password = String.valueOf(vistaAddEmpleados.getTextFieldPassword().getPassword());
				Boolean error = false;
				try {
					
													
					String[] data = {DNI,name,apellidos,domicilio,CP,email,fecha,cargo,password};
						
					error = almacenDatos.addEmployee(data);
					
					
				}catch(Exception e) {
					
					e.printStackTrace();
					
				}
				
				if(error) {
					
					sort();
					JOptionPane.showMessageDialog(vistaEmpleados, "Empleado añadido correctamente", "Succes", JOptionPane.INFORMATION_MESSAGE);
					vistaAddEmpleados.dispose();
					
				}else {
					
					JOptionPane.showMessageDialog(vistaEmpleados, "Error al añadir al empleado", "Error", JOptionPane.INFORMATION_MESSAGE);

					
				}
				
		
				
				return null;
			}
			
			
			
			
		};

		task.execute();
		
	}

	private void removeRow() {
		
		int row = vistaEmpleados.getTable().getSelectedRow();
		Employee e = (Employee) mtm.getElement(row);
		almacenDatos.deleteEmployee(e.getDNI());
		mtm.removeElement(e);
		
	}

	private void openAddEmpleado() {
				
		if(!MainController.open(vistaAddEmpleados)) {
			vistaAddEmpleados=new JIFAddEmployee();
			MainController.addJIF(vistaAddEmpleados);
			
			vistaAddEmpleados.getBtnCancel().addActionListener(this);
			vistaAddEmpleados.getBtnAdd().addActionListener(this);

			vistaAddEmpleados.getBtnAdd().setActionCommand("Add");
			vistaAddEmpleados.getBtnCancel().setActionCommand("Cancel");
				
				
		}else 			
			JOptionPane.showMessageDialog(vistaEmpleados, "Esta ventana ya ha sido generada", "Error", JOptionPane.ERROR_MESSAGE);
						
	}


	private void sort() {
		
		int sortN;
		
		if(vistaEmpleados.getComboBoxSort().getSelectedItem() == "Ascendente") {
			
			sortN = 1;
			
		}else
			
			sortN = 2;
		
		SwingWorker<Boolean,Void> task=new SwingWorker<Boolean,Void>(){
			
			ArrayList<Employee> empleados;
			
			@Override
			protected Boolean doInBackground() throws Exception {
				
				try{
					
					if(!isCancelled())
						empleados = almacenDatos.getEmployeeOrderBy(
								((String) vistaEmpleados.getComboBoxOrder().getSelectedItem()), sortN);
					
				}catch(Exception e) {
					
					e.printStackTrace();
					
				}
				
				return null;
			}
			
			protected void done() {
				
				if(!isCancelled()) {
					
					try {
						
						mtm=new MyTableModelEmpleados(empleados);
						vistaEmpleados.getTable().setModel(mtm);
						
						vistaEmpleados.getTable().setDefaultEditor(Date.class, new WebDateEditor());

							
						JComboBox<String> combobox = new JComboBox<String>();
						combobox.addItem("mecanico");
						combobox.addItem("administrativo");
						combobox.addItem("comercial");
						combobox.addItem("gerente");
						
						TableColumn column = vistaEmpleados.getTable().getColumn("Cargo");
						column.setCellEditor(new DefaultCellEditor(combobox));
						
											
						mtm.addTableModelListener(controlador);
						
						
						
					}catch(Exception e) {
						
						e.printStackTrace();
						
					}
			
					
				}else {
					
					vistaEmpleados.dispose();
					
				}
					
			}
			
		};
		
		task.execute();
	
		
	}
	
	public class MyTableModelEmpleados extends MyTableModel<Employee>{
		
				
		public MyTableModelEmpleados( List<Employee> data) {
			super(Arrays.asList("DNI","Nombre","Apellidos","Domicilio","CP","Email","Nacimiento","Cargo"), data);
			// TODO Auto-generated constructor stub
		}
		
		
		public void setValueAt(Object value, int row, int col) {
		
			switch(col) {
				
				case 1:
					data.get(row).setNombre(value.toString());
					break;
				case 2:
					data.get(row).setApellidos(value.toString());
					break;
				case 3:
					data.get(row).setDomicilio(value.toString());
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
					data.get(row).setCargo(value.toString());
					break;			
					
					
			}
			
			fireTableCellUpdated(row,  col);
	
		}

		@Override
		public Object getValueAt(int row, int col) {
			// TODO Auto-generated method stub
			
			switch(col) {
			
				case 0:
					 return data.get(row).getDNI();				
				case 1:
					return data.get(row).getNombre();
				case 2:
					return data.get(row).getApellidos();
				case 3:
					return data.get(row).getDomicilio();
				case 4:
					return data.get(row).getCP();		
				case 5:
					return data.get(row).getEmail();
				case 6:
					return data.get(row).getFechaNac();
				case 7:
					return data.get(row).getCargo();
			}
						
			return null;
		}
		
		public Class<?> getColumnClass(int colIndex) {
			switch(colIndex) {
			case 6: return Date.class;
			default: return String.class;
			}
			
		}
			
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getType() == TableModelEvent.UPDATE) {
			
			MyTableModelEmpleados mtm = (MyTableModelEmpleados) vistaEmpleados.getTable().getModel();
			
			Employee empleado = mtm.getElement(e.getFirstRow());
			
			SwingWorker<Boolean,Void> task=new SwingWorker<Boolean,Void>(){

				@Override
				protected Boolean doInBackground() throws Exception {
					// TODO Auto-generated method stub
					
					try {
						
						if(!isCancelled())
							almacenDatos.updateEmployee(empleado);
						
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
