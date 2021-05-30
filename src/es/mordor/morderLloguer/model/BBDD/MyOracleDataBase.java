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

	@Override
	public ArrayList<Vehicle> getVehiclesOrderBy(String orderBy, String table) {
		// TODO Auto-generated method stub
		
		ArrayList<Vehicle> vehiculos = new ArrayList<Vehicle>();

		
		DataSource ds = MyDataSource.getOracleDataSource();
		
		String sql = "SELECT * FROM VEHICULO, " + table + " WHERE VEHICULO.MATRICULA = " + table + ".MATRICULA";
		
		
		
		if(orderBy != null)
			sql += " ORDER BY " + "VEHICULO." + orderBy + " ASC";
		
		System.out.println(sql);
		
		try(Connection con = ds.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)){
			
			while(rs.next()) {
			
				String[] v = { rs.getString("MATRICULA"), rs.getString("MARCA"), rs.getString("COLOR"),
						rs.getString("MOTOR"), rs.getString("ESTADO"), rs.getString("CARNET")};
				
				int cilindrada =  rs.getInt("CILINDRADA");
				
				if(table.equals("COCHE")) {
					
					Car coche =  new Car(v[0],v[1], v[2], v[3],cilindrada,
							v[4],v[5],rs.getInt("NUMPUERTAS"), rs.getInt("NUMPLAZAS"));
					
					vehiculos.add(coche);
					
				}else if(table.equals("FURGONETA")) {
					
					Van furgo =  new Van(v[0],v[1], v[2], v[3],cilindrada,
							v[4],v[5], rs.getInt("MMA"));
					
					vehiculos.add(furgo);
					
				}else if(table.equals("CAMION")) {
					
					Truck camion =  new Truck(v[0],v[1], v[2], v[3],cilindrada,
							v[4],v[5], rs.getInt("MMA"), rs.getInt("NUMRUEDAS"));
					
					vehiculos.add(camion);
					
				}else if(table.equals("MICROBUS")) {
					
					Minibus minibus =  new Minibus(v[0],v[1], v[2], v[3],cilindrada,
							v[4],v[5], rs.getInt("MEDIDA"),rs.getInt("NUMPLAZAS"));
					
					vehiculos.add(minibus);
					
				}
			}
				
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return vehiculos;
	}
	
	
	@Override
	public ArrayList<Vehicle> getCarsOrderBy(String orderBy) {
		// TODO Auto-generated method stub
		return getVehiclesOrderBy(orderBy, "COCHE");
	}

	@Override
	public ArrayList<Vehicle> getVanOrderBy(String orderBy) {
		// TODO Auto-generated method stub
		return getVehiclesOrderBy(orderBy, "FURGONETA");
	}

	@Override
	public ArrayList<Vehicle>  getTruckOrderBy(String orderBy) {
		// TODO Auto-generated method stub
		return getVehiclesOrderBy(orderBy, "CAMION");
	}

	@Override
	public ArrayList<Vehicle> getMiniBusOrderBy(String orderBy) {
		// TODO Auto-generated method stub
		return getVehiclesOrderBy(orderBy, "MICROBUS");
	}

	@Override
	public boolean updateVehicles(Vehicle v) {
		
		boolean actualizado = false;
		
		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();){
			
			String query = "UPDATE VEHICULO SET  marca='" + v.getModel() + "', color='" + v.getColor() + "', motor='" + v.getEngine() + 
							"', cilindrada='" + v.getDisplacement() + "', estado='" + v.getStatus() + "', carnet='" + v.getDrivingLicense() + "' WHERE matricula='" +  v.getRegistration() + "'";
			
			String query2 = "";
			if(v instanceof Car) {
				
				Car o = (Car) v;
				
				query2 = "UPDATE COCHE SET  numplazas='" + o.getSeating() + "', numpuertas='" + o.getDoors() + "' WHERE matricula='" +  v.getRegistration() + "'";
				
			}else if(v instanceof Van) {
				
				Van o = (Van) v;
				query2 = "UPDATE FURGONETA SET  mma='" + o.getMMA() + "' WHERE matricula='" +  v.getRegistration() + "'";

				
			}else if(v instanceof Truck) {
				
				Truck o = (Truck) v;
				query2 = "UPDATE CAMION SET  mma='" + o.getMMA() + "', numruedas='" + o.getnWheels() + "' WHERE matricula='" +  v.getRegistration() + "'";

				
			}else if(v instanceof Minibus) {
				
				Minibus o = (Minibus) v;
				query2 = "UPDATE MICROBUS SET numplazas='" + o.getSeating() + "', medida='" + o.getMedida()  + "' WHERE matricula='" +  v.getRegistration() + "'";

				
			}
			
			
			System.out.println(query);
			
			if(stmt.executeUpdate(query)==1 && stmt.executeUpdate(query2) == 1)
				actualizado=true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();

		}
		
				
		return actualizado;
	}

	@Override
	public boolean deleteVehicles(Vehicle v) {
		
		boolean eliminado = false;
		
		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();){
	
			String query = "DELETE FROM VEHICULO WHERE MATRICULA= '" + v.getRegistration() + "'";
			
			System.out.println(query);
			
			String query2 = "";
			
			if(v instanceof Car) {
				
				query2 = "DELETE FROM COCHE WHERE MATRICULA= '" + v.getRegistration() + "'";
				
			}else if( v instanceof Van) {
				
				query2 = "DELETE FROM FURGONETA WHERE MATRICULA= '" + v.getRegistration() + "'";
				
			}else if( v instanceof Truck) {
				
				query2 = "DELETE FROM CAMION WHERE MATRICULA= '" + v.getRegistration() + "'";

			}else if (v instanceof Minibus) {
				
				query2 = "DELETE FROM MICROBUS WHERE MATRICULA= '" + v.getRegistration() + "'";

			}
			
			if(stmt.executeUpdate(query)==1 && stmt.executeUpdate(query2)==1 )
				eliminado=true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();

		}
		
				
		return eliminado;
	}


}
