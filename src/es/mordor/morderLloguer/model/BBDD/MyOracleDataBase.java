package es.mordor.morderLloguer.model.BBDD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;



public class MyOracleDataBase implements AlmacenDatosDB {
	
	
	public boolean addEmployee(String[] data) {
		
		boolean añadido = false;
		

		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();){
			
			
			String query = "insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES"
                    + "('"+ data[0] +"','" + data[1] +"', '"+ data[2] +"', '"+ data[3] +"', '"+ data[4] +"', '"+data[5]+"', TO_DATE('"+ data[6] +"','yyyy/mm/dd'), '"+data[7]+"',ENCRYPT_PASWD.encrypt_val("+data[8]+"))";

			
			System.out.println(query);
			
			if(stmt.executeUpdate(query)==1)
				añadido=true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();

			
		}
		
		
		return añadido;
		
	}
	
	public ArrayList<Employee> getCustomEmpleados(String where) {

		ArrayList<Employee> empleados = new ArrayList<Employee>();

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "SELECT * FROM EMPLEADO";

		if (where != null)
			query += " WHERE " + where;

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			Employee empleado;

			while (rs.next()) {
				empleado = new Employee(rs.getInt("idEmpleado"), rs.getString("DNI"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("CP"), rs.getString("email"), rs.getDate("fechaNac"),
						rs.getString("cargo"), rs.getString("domicilio"),rs.getString("password"));

				empleados.add(empleado);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empleados;
	}
	
	public ArrayList<Employee> getCustomEmpleadosOrdenados(String orderBy) {

		ArrayList<Employee> empleados = new ArrayList<Employee>();

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "SELECT * FROM EMPLEADO";

		if (orderBy != null)
			query += " ORDER BY " + orderBy;

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			Employee empleado;

			while (rs.next()) {
				empleado = new Employee(rs.getInt("idEmpleado"), rs.getString("DNI"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("CP"), rs.getString("email"), rs.getDate("fechaNac"),
						rs.getString("cargo"), rs.getString("domicilio"),rs.getString("password"));

				empleados.add(empleado);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empleados;
	}
	
	public ArrayList<Customer> getCustomClient(String where){
		
		ArrayList<Customer> clientes = new ArrayList<Customer>();
		
		DataSource ds = MyDataSource.getOracleDataSource();
		
		String query = "SELECT * FROM CLIENTE";
		
		if(where != null)
			
			query += "WHERE " + where; 
			
		
		try(Connection con = ds.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query)){
			
			Customer cliente;
			
			while(rs.next()) {
				
				cliente = new Customer(rs.getInt("idCliente"), rs.getString("DNI"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("CP"), rs.getString("domicilio"), rs.getString("email"), rs.getDate("fechaNac"),
						rs.getString("carnet"));
				
						
				clientes.add(cliente);
				
			}
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		return clientes;
		
	}
	

	
	@Override
	public boolean authenticate (String login,String password) {
		
		boolean registrado=false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "SELECT COUNT(*) FROM EMPLEADO WHERE DNI=? AND PASSWORD=ENCRYPT_PASWD.encrypt_val(?)";
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);
				){
			
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			
			ResultSet rs=pstmt.executeQuery();
			
			rs.next();
			if(rs.getInt(1)==1)
				registrado=true;
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();

		}
		
		
		return registrado;
	}
	
		
	@Override
	public ArrayList<Employee> getEmployee() {
		// TODO Auto-generated method stub
		return getCustomEmpleados(null);
	}

	
	@Override
	public ArrayList<Employee> getEmployeeByCP(String cp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Employee> getEmployeeByCargo(String cargo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeByDNI(String dni) {
		// TODO Auto-generated method stub
		
		return getCustomEmpleados("DNI= '" + dni + "'").get(0);
	}

	@Override
	public boolean updateEmployee(Employee empleado) {

		boolean actualizado = false;
		
		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();){
	
			String query = "UPDATE EMPLEADO SET nombre='" + empleado.getNombre() + "', " +
												"apellidos='" + empleado.getApellidos() + "'," + 
												((empleado.getDomicilio()!=null)?"domicilio='" + empleado.getDomicilio() + "',":"") + 
												((empleado.getCP()!=null)?"CP='"	+ empleado.getCP() + "',":"") + 
												"email='" + empleado.getEmail() + "'," + 
												"fechaNac=TO_DATE('" + empleado.getFechaNac() + "','yyyy-mm-dd')," + 
												"cargo='" + empleado.getCargo() + "' " + 
												"WHERE DNI='" + empleado.getDNI() + "'";
			
			System.out.println(query);
			
			if(stmt.executeUpdate(query)==1)
				actualizado=true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();

		}
		
				
		return actualizado;
	}

	@Override
	public boolean deleteEmployee(String dni) {
		
		boolean eliminado = false;
		
		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();){
	
			String query = "DELETE FROM EMPLEADO WHERE DNI= '" + dni + "'";
			
			System.out.println(query);
			
			if(stmt.executeUpdate(query)==1)
				eliminado=true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();

		}
		
				
		return eliminado;
	}

	

	@Override
	public ArrayList<Employee> getEmployeeOrderBy(String field, int sort) {
		// TODO Auto-generated method stub
		
		String order = "";
		
		if(sort == AlmacenDatosDB.ASCENDING) {
			
			order = "ASC";
			
		}else if( sort == AlmacenDatosDB.DESCENDING)
			
			order = "DESC";
			
			
		
		
		return getCustomEmpleadosOrdenados(field + " " + order);
	}

	@Override
	public ArrayList<Customer> getCustomer() {
		// TODO Auto-generated method stub
		return getCustomClient(null);
	}

	@Override
	public boolean deleteCustomer(String dni) {
		// TODO Auto-generated method stub
		
		boolean eliminado = false;
		
		DataSource ds = MyDataSource.getOracleDataSource();
		
		try(Connection con = ds.getConnection();
				Statement st = con.createStatement()){
			
			String query="DELETE FROM CLIENTE WHERE DNI= '" + dni + "'";
			
			if(st.executeUpdate(query) == 1) {
				
				eliminado = true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return eliminado;
	}

	@Override
	public boolean addCustomer(String[] data) {
		
		boolean añadido = false;		

		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();){
			
			
			String query = "insert into cliente(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES"
                    + "('"+ data[0] +"','" + data[1] +"', '"+ data[2] +"', '"+ data[3] +"', '"+ data[4] +"', '"+data[5]+"', TO_DATE('"+ data[6] +"','yyyy/mm/dd'), '"+data[7]+"')";

			
			System.out.println(query);
			
			if(stmt.executeUpdate(query)==1)
				añadido=true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();

			
		}
		
		
		return añadido;
	}

	@Override
	public boolean updateCustomer(Customer cliente) {
		// TODO Auto-generated method stub
		boolean actualizado = false;
		
		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();){
	
			String query = "UPDATE CLIENTE SET nombre='" + cliente.getName() + "', " +
												"apellidos='" + cliente.getSurname() + "'," + 
												((cliente.getAddress()!=null)?"domicilio='" + cliente.getAddress() + "',":"") + 
												((cliente.getCP()!=null)?"CP='"	+ cliente.getCP() + "',":"") + 
												"email='" + cliente.getEmail() + "'," + 
												"fechaNac=TO_DATE('" + cliente.getFechaNac() + "','yyyy-mm-dd')," + 
												"carnet='" + cliente.getCarnet() + "' " + 
												"WHERE DNI='" + cliente.getDNI() + "'";
			
			System.out.println(query);
			
			if(stmt.executeUpdate(query)==1)
				actualizado=true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();

		}
		
				
		return actualizado;
		
	}


	@Override
	public ArrayList<Customer> getCustomerOrderBy(String orderBy) {
		
		ArrayList<Customer> clientes = new ArrayList<Customer>();

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "SELECT * FROM CLIENTE";

		if (orderBy != null)
			query += " ORDER BY " + orderBy;

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			Customer cliente;

			while (rs.next()) {
				
				cliente = new Customer(rs.getInt("idCliente"), rs.getString("DNI"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("CP"), rs.getString("domicilio"), rs.getString("email"), rs.getDate("fechaNac"),
						rs.getString("carnet"));
				
						
				clientes.add(cliente);
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientes;
	}

}
