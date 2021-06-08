package es.mordor.mordorLloguer.tableModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.mordor.morderLloguer.model.BBDD.*;

public class MyRentTableModel extends MyTableModel<Rent> {
	
	private List<Vehicle> dataV;
	
	public MyRentTableModel(List<Rent> data, List<Vehicle> dataV) {
		super(new ArrayList<String>(Arrays.asList("Matricula","Modelo","Precio","F.Inicio","F.Fin")), data);
		// TODO Auto-generated constructor stub
		
		this.dataV = dataV;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		 
		switch(col) {
		
			case 0: return data.get(row).getMatricula();
			case 1: 
				
					for(Rent r : data) {			
						for(Vehicle v : dataV) {
							
							if(v.getRegistration().equals(r.getMatricula())) 								
								return v.getModel();						
										
						}
					}
					
			case 2: return data.get(row).getPrecio();
			case 3: return data.get(row).getfInicio();
			case 4: return data.get(row).getfFin();
			
		
		}
		
		return null;
	}

}
