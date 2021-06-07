package es.mordor.mordorLloguer.tableModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.mordor.morderLloguer.model.BBDD.Invoice;

public class MyInvoiceTableModel extends MyTableModel<Invoice> {

	public MyInvoiceTableModel(List<Invoice> data) {
		super(new ArrayList<String>(Arrays.asList("Matricula","Modelo","Precio","F.Inicio","F.Fin")), data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		switch(col) {
		
//			case 0: return data.get(row).getMatricula();
//			case 1: return data.get(row).getModelo();
//			case 2: return data.get(row).getfInicio();
//			case 3: return data.get(row).getfFin();
//		
		}
		
		return null;
	}

}
