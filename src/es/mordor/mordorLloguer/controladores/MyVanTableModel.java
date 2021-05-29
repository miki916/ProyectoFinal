package es.mordor.mordorLloguer.controladores;

import java.util.List;

import es.mordor.morderLloguer.model.BBDD.*;

public class MyVanTableModel extends MyVehicleTableModel<Van> {

	public MyVanTableModel(List<Van> data) {
		
		super(data);

		super.HEADER.add("MMA");
		
	}
	
	@Override	
	public void setValueAt(Object value, int row, int vol) {
		
		
	}
	
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		switch(col) {
		
			case 7: return data.get(row).getMMA();
					
			default: return super.getValueAt(row, col);
		
		}
		
	
	}

}
