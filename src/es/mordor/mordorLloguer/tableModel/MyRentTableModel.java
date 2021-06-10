package es.mordor.mordorLloguer.tableModel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import es.mordor.morderLloguer.model.BBDD.*;

public class MyRentTableModel extends MyTableModel<Rent> {
	
	private List<Vehicle> dataV;
	private List<Vehicle> v;
	
	public MyRentTableModel(List<Rent> data, List<Vehicle> dataV) {
		super(new ArrayList<String>(Arrays.asList("Matricula","Modelo","Precio","F.Inicio","F.Fin")), data);
		// TODO Auto-generated constructor stub
		v = new ArrayList<Vehicle>();
		this.dataV = dataV;
	}
	
	public Class<?> getColumnClass(int colIndex) {
		switch(colIndex) {
		case 3: return Date.class;
		case 4: return Date.class;

		default: return String.class;
		}
		
	}
	

	@Override
	public void setValueAt(Object value, int row, int col) {
		// TODO Auto-generated method stub
		
		switch(col) {
		
		case 3: 
			java.util.Date fecha=(java.util.Date)value;
			data.get(row).setfInicio(new java.sql.Date(fecha.getTime()));
			break;
			
		case 4:
			java.util.Date fechaFin=(java.util.Date)value;
			data.get(row).setfFin(new java.sql.Date(fechaFin.getTime()));
			break;
	
	}
	
	fireTableCellUpdated(row,  col);
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		 
		switch(col) {
		
			case 0: return data.get(row).getMatricula();
			case 1: 
				
					return obtenerModelo(data.get(row).getMatricula());
					
			case 2: return data.get(row).getPrecio();
			case 3: return data.get(row).getfInicio();
			case 4: return data.get(row).getfFin();
			
		
		}
		
		return null;
	}

	private String obtenerModelo(String matricula) {
		// TODO Auto-generated method stub
		
		String modelo = null;
		
		Optional<Vehicle> ve = dataV.stream().filter(v-> v.getDrivingLicense().toLowerCase().equals(matricula.toLowerCase())).findFirst();
		
		
		if(ve.isPresent())
			modelo = ve.get().getModel();
		return modelo;
	}

}
