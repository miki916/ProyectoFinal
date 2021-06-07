package es.mordor.mordorLloguer.tableModel;

import java.util.List;

import es.mordor.morderLloguer.model.BBDD.*;

public class MyVanTableModel extends MyVehicleTableModel<Van> {

	public MyVanTableModel(List<Van> data) {
		
		super(data);

		super.HEADER.add("MMA");
		
	}
	
	@Override	
	public void setValueAt(Object value, int row, int col) {
		
		switch(col) {
		
		case 9: 
			
			data.get(row).setMMA(Integer.valueOf( value.toString()));

			break;
			
		default: super.setValueAt(value, row, col);
		
	}

	fireTableCellUpdated(row,  col);
		
	}
	
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		switch(col) {
		
			case 9: return data.get(row).getMMA();
					
			default: return super.getValueAt(row, col);
		
		}
		
	
	}

}
