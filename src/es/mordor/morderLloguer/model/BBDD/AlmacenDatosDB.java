package es.mordor.morderLloguer.model.BBDD;

import java.sql.Date;
import java.util.ArrayList;


public interface AlmacenDatosDB {
	
	public static final int ASCENDING = 1;
	public static final int DESCENDING = 2;
	
	public ArrayList<Empleado> getEmployee();
	public ArrayList<Empleado> getEmployeeByCP(String cp);
	public ArrayList<Empleado> getEmployeeByCargo(String cargo);
	public Empleado getEmployeeByDNI(String dni);
	public boolean addEmployee(String[] data);
	public boolean updateEmployee(Empleado empleado); 
	public boolean deleteEmployee(String dni);
	public boolean authenticate (String login,String password);
	public ArrayList<Cliente> getClient();
	

	public  ArrayList<Empleado> getEmpleadosOrdenadosBy(String field,int sort);
	
	
	
	
}
