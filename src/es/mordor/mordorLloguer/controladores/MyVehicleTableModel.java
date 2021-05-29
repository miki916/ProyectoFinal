package es.mordor.mordorLloguer.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.mordor.morderLloguer.model.BBDD.*;

public abstract class MyVehicleTableModel<T extends Vehicle> extends MyTableModel<T> {

	public MyVehicleTableModel( List<T> data) {
		super(new ArrayList<String>(Arrays.asList("Matricula","Modelo","Color","Motor","Cilindrada","Estado","Carnet")), data);
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
		
			case 0: return data.get(row).getRegistration();
						
			case 1: return data.get(row).getModel();
							
			case 2: return data.get(row).getColor();
			
			case 3: return data.get(row).getEngine();
			
			case 4: return data.get(row).getDisplacement();
			
			case 5: return data.get(row).getStatus();
			
			case 6: return data.get(row).getDrivingLicense();
		
		}
		
		
		return null;
	}

}
