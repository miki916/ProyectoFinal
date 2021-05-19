package es.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import es.mordor.mordorLloguer.vistas.*;

public class ControladorEmpleados implements ActionListener, TableModelListener{
	
	private JIFEmpleados vistaEmpleados;
	private AlmacenDatosDB almacenDatos;
	private MyTableModel mtm;
	private ControladorEmpleados controlador;
	
	public ControladorEmpleados(JIFEmpleados vistaEmpleados, AlmacenDatosDB almacenDatos) {
		
		this.almacenDatos = almacenDatos;
		this.vistaEmpleados = vistaEmpleados;
		controlador = this;
		
		inicializar();
		
	}
	
	private void inicializar() {
		// TODO Auto-generated method stub
		
		vistaEmpleados.getBtnAdd().addActionListener(this);
		vistaEmpleados.getBtnRemove().addActionListener(this);
		vistaEmpleados.getComboBoxOrder().addActionListener(this);
		vistaEmpleados.getComboBoxSort().addActionListener(this);

				
		
	}
	
	public void go() {
		
		ControladorPrincipal.addJIF(vistaEmpleados);
		sort();		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String command = e.getActionCommand();
		
		sort();
		
		
	}


	private void sort() {
		
		int sortN;
		
		if(vistaEmpleados.getComboBoxSort().getSelectedItem() == "Ascendente") {
			
			sortN = 1;
			
		}else
			
			sortN = 2;
		
		SwingWorker<Boolean,Void> task=new SwingWorker<Boolean,Void>(){
			
			ArrayList<Empleado> empleados;
			
			@Override
			protected Boolean doInBackground() throws Exception {
				
				try{
					
					if(!isCancelled())
						empleados = almacenDatos.getEmpleadosOrdenadosBy(
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
	
	public class MyTableModelEmpleados extends MyTableModel<Empleado>{
		
				
		public MyTableModelEmpleados( List<Empleado> data) {
			super(new String[]{"DNI","Nombre","Apellidos","Domicilio","CP","Email","Nacimiento","Cargo"}, data);
			// TODO Auto-generated constructor stub
		}
		
		public void setValueAt(Object value, int row, int col) {
		
			
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
		
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}	
}
