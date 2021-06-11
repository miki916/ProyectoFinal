package es.mordor.morderLloguer.model.BBDD;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface AlmacenDatosDB {

	public static final int ASCENDING = 1;
	public static final int DESCENDING = 2;

	// LLamadas a la base de datos para almacenar Empleados, añadir
	// empleado,actualiza empleado y eliminarlo
	public ArrayList<Employee> getEmployee();

	public Employee getEmployeeByDNI(String dni);

	public boolean addEmployee(String[] data) throws SQLException;

	public boolean updateEmployee(Employee empleado);

	public ArrayList<Employee> getEmployeeOrderBy(String field, int sort);

	public boolean deleteEmployee(String dni) throws SQLException;

	// LLamadas a la base de datos para almacenar clientes, añadir
	// clientes,actualiza clientes y eliminarlo
	public boolean deleteCustomer(String dni) throws SQLException;

	public ArrayList<Customer> getCustomer() throws SQLException;

	public boolean addCustomer(Customer c) throws SQLException;

	public boolean updateCustomer(Customer cliente) throws SQLException;

	// LLamadas a la base de datos para almacenar vehiculos, añadir
	// vehiculos,actualiza vehiculos y eliminarlo
	public ArrayList<Vehicle> getVehicles(String table) throws SQLException, ParseException;

	public boolean addVehicle(String table, Vehicle v) throws SQLException;

	public boolean updateVehicles(Vehicle v) throws SQLException;

	public boolean deleteVehicles(String table, Vehicle v) throws SQLException;

	// LLamadas a la base de datos para almacenar facturas y alquileres, añadir
	// facturas y alquileres ,actualiza alquileres y eliminar alquileres
	public ArrayList<Invoice> getInvoice() throws SQLException;

	public ArrayList<Rent> getRent() throws SQLException;

	public boolean addRent(Rent r, String DNI) throws SQLException;

	public boolean deleteRent(Rent r) throws SQLException;

	public boolean addInvoice(Rent r, String dni) throws SQLException;

	public boolean updateRent(Rent r) throws SQLException;

	// LLamada a la base de datos para autentificarse
	public boolean authenticate(String login, String password);

	// LLamada a la base de datos para chequear vehiculos
	public boolean check(int idfactura) throws SQLException;

}
