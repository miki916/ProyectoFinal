package es.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import com.alee.laf.table.editors.WebDateEditor;

import es.mordor.morderLloguer.model.BBDD.*;
import es.mordor.mordorLloguer.controladores.ControladorEmpleados.MyTableModelEmpleados;
import es.mordor.mordorLloguer.vistas.*;

public class ControladorClientes implements ActionListener, TableModelListener {
	
	private JIFClientes vista;
	private AlmacenDatosDB almacenDatos;
	private MyTableModel mtm;
	private ControladorClientes controlador;
	
	public ControladorClientes(JIFClientes vistaClientes,AlmacenDatosDB almacenDatos) {
		
		this.vista = vistaClientes;
		this.almacenDatos = almacenDatos;
		controlador = this;
		inicializar();
		
	}
	
	
	private void inicializar() {
		// TODO Auto-generated method stub
		vista.getTextFieldDni().addActionListener(this);
		vista.getTextFieldName().addActionListener(this);
		vista.getTextFieldSurname().addActionListener(this);
		vista.getBtnAdd().addActionListener(this);
		vista.getBtnRemove().addActionListener(this);
		vista.getBtnPrint().addActionListener(this);
		
		vista.getTextFieldDni().setActionCommand(null);
		vista.getTextFieldName().setActionCommand(null);
		vista.getTextFieldSurname().setActionCommand(null);
		vista.getBtnAdd().setActionCommand("OpenNewCliente");
		vista.getBtnRemove().setActionCommand("Remove");
		vista.getBtnPrint().setActionCommand(null);

		
		
	}
	
	public void go() {
			
			ControladorPrincipal.addJIF(vista);
			sort();	
	}

	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String command = e.getActionCommand();
		
		if(command == "OpenNewCliente") {
			
		}else if(command == "Remove") {
			
		}
		
		
		
		
	}
	
	private void sort() {
		// TODO Auto-generated method stub
		
		SwingWorker<Boolean,Void> task = new SwingWorker<Boolean,Void>(){
			
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			
			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				try {
					
					if(!isCancelled()) {
						
						clientes = almacenDatos.getClient();
						
						System.out.println(clientes);
					}
					
				}catch(Exception e) {
					
					e.printStackTrace();
					
				}
				
				return null;
			}
			
			protected void done() {
				
				if(!isCancelled()) {
					
					try {
						
						mtm=new MyTableModelCliente(clientes);
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
	
	
	public class MyTableModelCliente extends MyTableModel<Cliente>{
		
		public MyTableModelCliente(List<Cliente> data) {
			
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
