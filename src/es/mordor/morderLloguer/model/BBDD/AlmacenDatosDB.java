package es.mordor.morderLloguer.model.BBDD;

import java.sql.Date;
import java.util.ArrayList;


public interface AlmacenDatosDB {
	
	public static final int ASCENDING = 1;
	public static final int DESCENDING = 2;
	
	public ArrayList<Employee> getEmployee();
	public Employee getEmployeeByDNI(String dni);
	public boolean addEmployee(String[] data);
	public boolean updateEmployee(Employee empleado); 
	public  ArrayList<Employee> getEmployeeOrderBy(String field,int sort);
	public boolean deleteEmployee(String dni);
	
	public boolean deleteCustomer(String dni);
	public ArrayList<Customer> getCustomer();
	public boolean addCustomer(Customer c);
	public boolean updateCustomer(Customer cliente); 
	
	public ArrayList<Vehicle> getVehicles(String table);
	public boolean addVehicle(String table, Vehicle v);
	public boolean  updateVehicles(Vehicle v);
	public boolean deleteVehicles(String  table, Vehicle v);
	
	public ArrayList<Invoice> getInvoice();
	public boolean authenticate (String login,String password);
	public ArrayList<Rent> getRent();
	public boolean addRent(Rent r,String DNI);
	public boolean deleteRent(Rent r);
	public boolean addInvoice(Rent r, String dni);
	public boolean check(int idfactura);
	
	
	
}
