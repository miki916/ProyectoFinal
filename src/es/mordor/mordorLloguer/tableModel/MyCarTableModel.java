package es.mordor.mordorLloguer.tableModel;

import java.util.ArrayList;
import java.util.List;

import es.mordor.morderLloguer.model.BBDD.Car;
import es.mordor.morderLloguer.model.BBDD.Vehicle;

public class MyCarTableModel extends MyVehicleTableModel<Car>{

	public MyCarTableModel(List<Car> cars) {
		super(cars);

		super.HEADER.add("Plazas");
		super.HEADER.add("Puertas");
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		// TODO Auto-generated method stub
		
		switch(col) {
		
			case 7: 
				data.get(row).setDoors(Integer.valueOf( value.toString()));
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
			
			case 8: return data.get(row).getDoors();
			
			default: return super.getValueAt(row, col);
		
		}
		
	
	}


}
