package es.mordor.morderLloguer.model.BBDD;

import java.sql.Date;
import java.util.ArrayList;


public interface AlmacenDatosDB {
	
	public static final int ASCENDING = 1;
	public static final int DESCENDING = 2;
	
	public ArrayList<Employee> getEmployee();
	public ArrayList<Employee> getEmployeeByCP(String cp);
	public ArrayList<Employee> getEmployeeByCargo(String cargo);
	public Employee getEmployeeByDNI(String dni);
	public boolean addEmployee(String[] data);
	public boolean addCustomer(String[] data);
	public boolean updateEmployee(Employee empleado); 
	public boolean deleteEmployee(String dni);
	public boolean deleteCustomer(String dni);
	public boolean authenticate (String login,String password);
	public ArrayList<Customer> getClient();
	

	public  ArrayList<Employee> getEmpleadosOrdenadosBy(String field,int sort);
	
	
	
	
}
