package es.mordor.mordorLloguer.tableModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.mordor.morderLloguer.model.BBDD.*;

public abstract class MyVehicleTableModel<T extends Vehicle> extends MyTableModel<T> {

	public MyVehicleTableModel( List<T> data) {
		super(new ArrayList<String>(Arrays.asList("Matricula","Precio","Modelo","Color","Motor","Cilindrada","Fecha Adq","Estado","Carnet")), data);
		// TODO Auto-generated constructor stub
	}
	
	
	public Class<?> getColumnClass(int colIndex) {
		switch(colIndex) {
		case 6: return Date.class;
		default: return String.class;
		}
		
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		// TODO Auto-generated method stub
		
		switch(col) {
			
			case 1:
				data.get(row).setDayPrice(Integer.parseInt(value.toString()));
				break;
		
			case 2:
				data.get(row).setModel(value.toString());
				break;
			
			case 3:
				data.get(row).setColor(value.toString());
				break;
			
			case 4: 
				data.get(row).setEngine(value.toString());
				break;
			
			case 5:
				data.get(row).setDisplacement(Integer.valueOf( value.toString()));
				break;
				
			case 6:
				java.util.Date fecha=(java.util.Date)value;
				data.get(row).setShopDay(new java.sql.Date(fecha.getTime()));
				break;
			
			case 7:
				data.get(row).setStatus(value.toString());
				break;
								
			case 8:
				data.get(row).setDrivingLicense(value.toString());
				break;
		}
	
		fireTableCellUpdated(row,  col);
		
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		switch(col) {
		
			case 0: return data.get(row).getRegistration();
			
			case 1: return data.get(row).getDayPrice();
			
			case 2: return data.get(row).getModel();
							
			case 3: return data.get(row).getColor();
			
			case 4: return data.get(row).getEngine();
			
			case 5: return data.get(row).getDisplacement();
			
			case 6: return data.get(row).getShopDay();
			
			case 7: return data.get(row).getStatus();
			
			case 8: return data.get(row).getDrivingLicense();
		
		}
		
		
		return null;
	}
	


}
