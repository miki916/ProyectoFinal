package es.mordor.morderLloguer.model.BBDD;

import java.util.ArrayList;


public interface AlmacenDatosDB {
	
	public ArrayList<Empleado> getEmpleados();
	public ArrayList<Empleado> getEmpleadosPorCP(String cp);
	public ArrayList<Empleado> getEmpleadosPorCargo(String cargo);
	public Empleado getEmpleadoPorDNI(String dni);
	public boolean updateEmpleado(Empleado empleado); 
	public boolean deleteEmpleado(String dni);
	public boolean authenticate (String login,String password);

	public  ArrayList<Empleado> getEmpleadosOrdenadosBy(String field,int sort);
	
	
	
	
}
