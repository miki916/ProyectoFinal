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
	public boolean updateEmployee(Employee empleado); 
	public  ArrayList<Employee> getEmployeeOrderBy(String field,int sort);
	public boolean deleteEmployee(String dni);
	
	public boolean deleteCustomer(String dni);
	public ArrayList<Customer> getCustomer();
	public boolean addCustomer(String[] data);
	public boolean updateCustomer(Customer cliente); 
	
	public ArrayList<Vehicle> getVehiclesOrderBy(String orderBy, String table);
	public ArrayList<Vehicle>  getCarsOrderBy(String orderBy);
	public ArrayList<Vehicle>  getVanOrderBy(String orderBy);
	public ArrayList<Vehicle>  getTruckOrderBy(String orderBy);
	public ArrayList<Vehicle>  getMiniBusOrderBy(String orderBy);
	public boolean addVehicle(String table, ArrayList<String> l);
	public boolean  updateVehicles(Vehicle v);
	public boolean deleteVehicles(String  table, Vehicle v);
	
	public ArrayList<Invoice> getInvoice();




	
	public boolean authenticate (String login,String password);
	public ArrayList<Rent> getRent();
	public ArrayList<Vehicle> getVehicles(String table);
	
	
	
}
