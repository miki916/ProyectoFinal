package es.mordor.mordorLloguer.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import es.mordor.morderLloguer.model.BBDD.*;
import es.mordor.mordorLloguer.controladores.*;
import es.mordor.mordorLloguer.controladores.CustomerController.MyTableModelCustomer;
import es.mordor.mordorLloguer.tableModel.*;

import es.mordor.mordorLloguer.vistas.JIFInvoice;

public class InvoiceController implements ActionListener{

	
	private AlmacenDatosDB almacenDatos;
	private JIFInvoice vista;
	private MyRentTableModel mtm;
	private ArrayList<Rent> alquileres;
	private ArrayList<Invoice> facturas;
	private ArrayList<Customer> listC;
	private int index = 0;
	private ArrayList<Vehicle> listV;
 
		
	public InvoiceController(AlmacenDatosDB almacenDatos, JIFInvoice vista) {
		super();
		this.almacenDatos = almacenDatos;
		this.vista = vista;
		alquileres = new ArrayList<Rent>();
		facturas = new ArrayList<Invoice>();
		listV = new ArrayList<Vehicle>();
		listC = new ArrayList<Customer>();
		inicializar();
	}

	private void inicializar() {
		// TODO Auto-generated method stub
		
		vista.getBtnPreviousInvoice().setEnabled(false);
		
		vista.getBtnNextInvoice().addActionListener(this);
		vista.getBtnPreviousInvoice().addActionListener(this);

		vista.getBtnNextInvoice().setActionCommand("Next");
		vista.getBtnPreviousInvoice().setActionCommand("Previous");
		
	}
	
	public void go() {
		
		MainController.addJIF(vista);
		fillOracle();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String command = e.getActionCommand();
		
		if(command.equals("Next")) {
			
			nextInvoice();
			
		}else if(command.equals("Previous")) {
			
			prevInvoice();			
		}
		
	}

	private void prevInvoice() {
		// TODO Auto-generated method stub
		
		index--;
		mtm = new MyRentTableModel(getRent(facturas.get(index)), listV);						
		vista.getTableDetalles().setModel(mtm);
		fillValues();
		btnEnabled();
	
	}

	private void nextInvoice() {
		// TODO Auto-generated method stub
						
		index++;
		mtm = new MyRentTableModel(getRent(facturas.get(index)), listV);						
		vista.getTableDetalles().setModel(mtm);	
		fillValues();
		btnEnabled();
	}
	
	private void btnEnabled() {
		
		if(index == facturas.size()-1) 
			vista.getBtnNextInvoice().setEnabled(false);
		else
			vista.getBtnNextInvoice().setEnabled(true);

		if(index == 0) 
			vista.getBtnPreviousInvoice().setEnabled(false);
		else
			vista.getBtnPreviousInvoice().setEnabled(true);
		
	}

 	private void fillOracle() {
		// TODO Auto-generated method stub
		
		SwingWorker<Boolean,Void> task = new SwingWorker<Boolean,Void>(){
					
			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				try {
					
					if(!isCancelled()) {
						
						alquileres = almacenDatos.getRent();
						facturas = almacenDatos.getInvoice();
						listV.addAll(almacenDatos.getVehicles("COCHE"));
						listV.addAll(almacenDatos.getVehicles("FURGONETA"));
						listV.addAll(almacenDatos.getVehicles("CAMION"));
						listV.addAll(almacenDatos.getVehicles("MICROBUS"));
						listC = almacenDatos.getCustomer();
						
						fillValues();
						mtm = new MyRentTableModel(getRent(facturas.get(index)), listV);						
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
	
	
	private List<Rent> getRent(Invoice i) {
		// TODO Auto-generated method stub
		
		ArrayList<Rent> alquilerFinal = new ArrayList<Rent>();
			
			for(Rent r : alquileres) {
		
				if(i.getIdfactura() == r.getIdFactura())
					alquilerFinal.add(r);
			
			}				
				
		return alquilerFinal;
	}
	
	
	private void fillValues() {
		// TODO Auto-generated method stub
		
		vista.getTxtFieldNumeroFactura().setText(String.valueOf(facturas.get(index).getIdfactura()));
		vista.getWebDateFieldFechaFactura().setDate(facturas.get(index).getFecha());
		
		for(Customer c : listC) {
			
			if(facturas.get(index).getClienteid() == c.getIdCliente()) {
				
				vista.getTxtFieldNombre().setText(c.getName());
				vista.getTxtFieldApellidos().setText(c.getSurname());
				vista.getTxtFieldDNI().setText(c.getDNI());
							
			}	
		}		
	}
	
	



	

	
	
}
