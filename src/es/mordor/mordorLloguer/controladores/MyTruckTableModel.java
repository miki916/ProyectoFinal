package es.mordor.mordorLloguer.controladores;

import java.util.List;

import es.mordor.morderLloguer.model.BBDD.Truck;

public class MyTruckTableModel extends MyVehicleTableModel<Truck>{

	public MyTruckTableModel(List<Truck> data) {
	
		super(data);
		
		super.HEADER.add("MMA");
		super.HEADER.add("Numero ruedas");
	
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		
		
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		switch(col) {
		
			case 7: return data.get(row).getMMA();
			
			case 8: return data.get(row).getnWheels();
			
			default: return super.getValueAt(row, col);
		
		}
		
	
	}

}
