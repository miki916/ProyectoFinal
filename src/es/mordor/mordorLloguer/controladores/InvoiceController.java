package es.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import es.mordor.morderLloguer.model.BBDD.AlmacenDatosDB;
import es.mordor.morderLloguer.model.BBDD.Invoice;
import es.mordor.mordorLloguer.tableModel.MyInvoiceTableModel;
import es.mordor.mordorLloguer.vistas.JIFInvoice;

public class InvoiceController implements ActionListener{

	
	private AlmacenDatosDB almacenDatos;
	private JIFInvoice vista;
	private MyInvoiceTableModel mtm;
 
	public InvoiceController(JIFInvoice vista, AlmacenDatosDB almacenDatos) {
		
		this.almacenDatos = almacenDatos;
		this.vista = vista;
		
		inicializar();
	}
	
	
	
	private void inicializar() {
		// TODO Auto-generated method stub
		
	}
	
	public void go() {
		
		MainController.addJIF(vista);
		update();
		
	}



	private void update() {
		// TODO Auto-generated method stub
		
		SwingWorker<Boolean,Void> task = new SwingWorker<Boolean,Void>(){

			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				ArrayList<Invoice> facturas = new ArrayList<Invoice>();
				
				try {
					
					if(!isCancelled()) {
						
						facturas = almacenDatos.getInvoice();
						mtm = new MyInvoiceTableModel(facturas);
						vista.getTableDetalles().setModel(mtm);
						
					}
					
					
				}catch(Exception e) {
					
					e.printStackTrace();
					
				}
				
				
				return null;
			}
			
		};
		task.execute();
		
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
