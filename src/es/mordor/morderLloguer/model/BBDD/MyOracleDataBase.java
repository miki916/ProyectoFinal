package es.mordor.morderLloguer.model.BBDD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;
import oracle.sql.DATE;



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
		ArrayList<Customer> clientes = new ArrayList<Customer>();
		
		DataSource ds = MyDataSource.getOracleDataSource();
		
		String query = "{?= call GESTIONALQUILER.listarclientes()}";
		ResultSet rs = null;
		System.out.println(query);
		try(Connection con = ds.getConnection();
				CallableStatement cs = con.prepareCall(query)){
			
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			
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
	public boolean deleteVehicles(String  table, Vehicle v) {
		
		boolean eliminado = false;
		
		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();){
						
			String query = "DELETE FROM VEHICULO WHERE MATRICULA= '" + v.getRegistration() + "'";
			
			String query2 =  "DELETE FROM " + table + " WHERE MATRICULA= '" + v.getRegistration() + "'";
			
			System.out.println(query);
			System.out.println(query2);
			
			if(stmt.executeUpdate(query2)==1 && stmt.executeUpdate(query)==1 )
				eliminado=true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();

		}
		
				
		return eliminado;
	}

	@Override
	public boolean addVehicle(String table, ArrayList<String> l) {
		// TODO Auto-generated method stub
		boolean añadido = false;
				
		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();){
			
			String query = "insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values"
					+ "('" + l.get(0) + "'," + Integer.parseInt(l.get(1)) + ", '" + l.get(2) + "','" + l.get(3) + "', ' " + l.get(4) + "'," + Integer.parseInt(l.get(5)) + ", "
							+ "TO_DATE('"+  l.get(6) +"','yyyy/mm/dd') ,' "+ l.get(7) + "', '" + l.get(8) + "')";
			
			String query2 = "";
			
							
				if(table.equals("FURGONETA")) 
					
					query2 = "insert INTO furgoneta (matricula, mma) values\r\n"
							+ "('"+ l.get(0) + "', "+ Integer.parseInt(l.get(9)) + ")";
				
				else
					query2 = "insert INTO " +  table + " values"
							+ "('"+ l.get(0) + "', "+ Integer.parseInt(l.get(9)) + ", "+ Integer.parseInt(l.get(10)) + ")";
					
					
				
			System.out.println(query + " " + query2);
			
			if(stmt.executeUpdate(query)==1 && stmt.executeUpdate(query2)==1 )
				añadido=true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();

			
		}
		
		
		return añadido;
	}

	@Override
	public ArrayList<Invoice> getInvoice() {
		// TODO Auto-generated method stub
		
		ArrayList<Invoice> facturas = new ArrayList<Invoice>();
		DataSource ds = MyDataSource.getOracleDataSource();
		
		String query = "{?= call GESTIONALQUILER.listarfacturas()}";
		ResultSet rs = null;
		
		try(Connection con = ds.getConnection();
				CallableStatement cs = con.prepareCall(query)){
			
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			
			Invoice i = null;
			
			while (rs.next()) {
				
				i = new Invoice(rs.getInt("IDFACTURA"),rs.getInt("CLIENTEID"),rs.getDate("FECHA"),rs.getFloat("IMPORTEBASE"),rs.getFloat("IMPORTEIVA"));
				facturas.add(i);
				
			}
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		return facturas;
	}
	
	public ArrayList<Rent> getRent(){
		
		ArrayList<Rent> alquileres = new ArrayList<Rent>();
		DataSource ds = MyDataSource.getOracleDataSource();
		
		String query = "{?= call GESTIONALQUILER.listarAlquileres()}";
		ResultSet rs = null;
		
		try(Connection con = ds.getConnection();
				CallableStatement cs = con.prepareCall(query)){
			
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
			
			Rent r = null;
			
			while (rs.next()) {
				
				r = new Rent(rs.getInt("IDALQUILER"),rs.getInt("IDFACTURA"),rs.getString("MATRICULA"),rs.getDate("FECHAINICIO"),rs.getDate("FECHAFIN"),rs.getFloat("PRECIO"));
				alquileres.add(r);
				
			}
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		return alquileres;
		
		
	}

	@Override
	public ArrayList<Vehicle> getVehicles(String table) {
		// TODO Auto-generated method stub
		ArrayList<Vehicle> vehiculos = new ArrayList<Vehicle>();
		DataSource ds = MyDataSource.getOracleDataSource();
		
		String query = "{call GESTIONVEHICULOS.listarVehiculos(?,?)}";
		ResultSet rs = null;
		
		try(Connection con = ds.getConnection();
				CallableStatement cs = con.prepareCall(query)){
			
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.setString(1, table);
			cs.execute();
			rs = (ResultSet) cs.getObject(2);
			
			Vehicle r = null;
			
			String pattern = "dd/MM/yy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			
			while (rs.next()) {
				
				 String registration = rs.getString("c1");
				 int dayPrice = rs.getInt("n1");
				 String model = rs.getString("c2");
				 String color = rs.getString("c4");
				 String engine = rs.getString("c5");
				 int displacement = rs.getInt("n2");
				 System.out.println(rs.getString("c6"));
				 Date shopDay = new Date(simpleDateFormat.parse(rs.getString("c6")).getTime());
				 String status = rs.getString("c7");
				 String drivingLicense=rs.getString("c8");
				 int value1 = rs.getInt("n3");
				
				if(table.equals("COCHE")) {
					
					int value2  = rs.getInt("n4");
					
					r = new Car(registration,dayPrice,model,color,engine,displacement,shopDay,status,drivingLicense,value1,value2);
					
					vehiculos.add(r);
					
				}else if(table.equals("FURGONETA")) {
					
					r = new Van(registration,dayPrice,model,color,engine,displacement,shopDay,status,drivingLicense,value1);
							
					vehiculos.add(r);
					
				}else if(table.equals("CAMION")) {
					
					int value2  = rs.getInt("n4");
					
					r = new Truck(registration,dayPrice,model,color,engine,displacement,shopDay,status,drivingLicense,value1,value2);
									
					vehiculos.add(r);
					
				}else if(table.equals("MICROBUS")) {
					
					int value2  = rs.getInt("n4");
					
					r = new Minibus(registration,dayPrice,model,color,engine,displacement,shopDay,status,drivingLicense,value1,value2);
											
					vehiculos.add(r);
					
				}
				
			
				
			}
			
			System.out.println(vehiculos.toString());
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		
		return vehiculos;
	}
	

}
