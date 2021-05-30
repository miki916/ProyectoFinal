package es.mordor.mordorLloguer.tableModel;

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
		
		switch(col) {
		
			case 7: 
				data.get(row).setMMA(Integer.valueOf( value.toString()));
	
				break;
				
			case 8:
				
				data.get(row).setSeating(Integer.valueOf(value.toString()));

				break;
				
			default: super.setValueAt(value, row, col);
			
		}
	
		fireTableCellUpdated(row,  col);
		
	}
	
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		switch(col) {
		
			case 7: return data.get(row).getSeating();
			
			case 8: return data.get(row).getMedida();
			
			default: return super.getValueAt(row, col);
		
		}
		
	
	}
	
	

}
