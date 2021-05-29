package es.mordor.mordorLloguer.controladores;

import java.util.List;

import es.mordor.morderLloguer.model.BBDD.Minibus;

public class MyMinibusTableModel extends MyVehicleTableModel<Minibus>{

	public MyMinibusTableModel(List<Minibus> data) {
		
		super(data);
		
		super.HEADER.add("Plazas");
		super.HEADER.add("Tamaño");
		
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		
		
		
	}
	
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		switch(col) {
		
			case 7: return data.get(row).getSeating();
			
			case 8: return data.get(row).getWidth();
			
			default: return super.getValueAt(row, col);
		
		}
		
	
	}
	
	

}
