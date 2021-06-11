package es.mordor.morderLloguer.model.BBDD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

public class MyOracleDataBase implements AlmacenDatosDB {

	// Empleados
	public boolean addEmployee(String[] data) throws SQLException {

		boolean añadido = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		Connection con = ds.getConnection();
		Statement stmt = con.createStatement();

		String query = "insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES"
				+ "('" + data[0] + "','" + data[1] + "', '" + data[2] + "', '" + data[3] + "', '" + data[4] + "', '"
				+ data[5] + "', TO_DATE('" + data[6] + "','yyyy/mm/dd'), '" + data[7] + "',ENCRYPT_PASWD.encrypt_val("
				+ data[8] + "))";

		if (stmt.executeUpdate(query) == 1)
			añadido = true;

		return añadido;

	}

	public ArrayList<Employee> getCustomEmployees(String where) {

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
						rs.getString("cargo"), rs.getString("domicilio"), rs.getString("password"));

				empleados.add(empleado);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empleados;
	}

	public ArrayList<Employee> getCustomEmployeesOrderBy(String orderBy) {

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
						rs.getString("cargo"), rs.getString("domicilio"), rs.getString("password"));

				empleados.add(empleado);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empleados;
	}

	@Override
	public ArrayList<Employee> getEmployee() {
		// TODO Auto-generated method stub
		return getCustomEmployees(null);
	}

	@Override
	public Employee getEmployeeByDNI(String dni) {
		// TODO Auto-generated method stub

		return getCustomEmployees("DNI= '" + dni + "'").get(0);
	}

	@Override
	public boolean updateEmployee(Employee empleado) {

		boolean actualizado = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection(); Statement stmt = con.createStatement();) {

			String query = "UPDATE EMPLEADO SET nombre='" + empleado.getNombre() + "', " + "apellidos='"
					+ empleado.getApellidos() + "',"
					+ ((empleado.getDomicilio() != null) ? "domicilio='" + empleado.getDomicilio() + "'," : "")
					+ ((empleado.getCP() != null) ? "CP='" + empleado.getCP() + "'," : "") + "email='"
					+ empleado.getEmail() + "'," + "fechaNac=TO_DATE('" + empleado.getFechaNac() + "','yyyy-mm-dd'),"
					+ "cargo='" + empleado.getCargo() + "' " + "WHERE DNI='" + empleado.getDNI() + "'";

			if (stmt.executeUpdate(query) == 1)
				actualizado = true;

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return actualizado;
	}

	@Override
	public boolean deleteEmployee(String dni) throws SQLException {

		boolean eliminado = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		Connection con = ds.getConnection();
		Statement stmt = con.createStatement();

		String query = "DELETE FROM EMPLEADO WHERE DNI= '" + dni + "'";

		if (stmt.executeUpdate(query) == 1)
			eliminado = true;

		return eliminado;
	}

	@Override
	public ArrayList<Employee> getEmployeeOrderBy(String field, int sort) {
		// TODO Auto-generated method stub

		String order = "";

		if (sort == AlmacenDatosDB.ASCENDING) {

			order = "ASC";

		} else if (sort == AlmacenDatosDB.DESCENDING)

			order = "DESC";

		return getCustomEmployeesOrderBy(field + " " + order);
	}

	// Clientes
	@Override
	public ArrayList<Customer> getCustomer() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Customer> clientes = new ArrayList<Customer>();

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{?= call GESTIONALQUILER.listarclientes()}";
		ResultSet rs = null;

		Connection con = ds.getConnection();
		CallableStatement cs = con.prepareCall(query);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		rs = (ResultSet) cs.getObject(1);

		Customer cliente;

		while (rs.next()) {

			cliente = new Customer(rs.getInt("idCliente"), rs.getString("DNI"), rs.getString("nombre"),
					rs.getString("apellidos"), rs.getString("CP"), rs.getString("domicilio"), rs.getString("email"),
					rs.getDate("fechaNac"), rs.getString("carnet"));

			clientes.add(cliente);

		}

		return clientes;
	}

	@Override
	public boolean deleteCustomer(String dni) throws SQLException {
		// TODO Auto-generated method stub

		boolean delete = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONALQUILER.bajaCliente(?)}";

		Connection con = ds.getConnection();
		CallableStatement cstmt = con.prepareCall(query);

		cstmt.setString(1, dni);

		delete = (cstmt.executeUpdate() == 1) ? true : false;

		return delete;
	}

	@Override
	public boolean addCustomer(Customer c) throws SQLException {

		boolean añadido = false;

		String query = "{call GESTIONALQUILER.grabarcliente(?,?,?,?,?,?,?,?)}";

		DataSource ds = MyDataSource.getOracleDataSource();

		Connection con = ds.getConnection();
		CallableStatement cs = con.prepareCall(query);

		cs.setString(1, c.getDNI());
		cs.setString(2, c.getName());
		cs.setString(3, c.getSurname());
		cs.setString(4, c.getAddress());
		cs.setString(5, c.getCP());
		cs.setString(6, c.getEmail());
		cs.setDate(7, c.getFechaNac());
		cs.setString(8, c.getCarnet());

		añadido = (cs.executeUpdate() == 1) ? true : false;

		return añadido;
	}

	@Override
	public boolean updateCustomer(Customer cliente) throws SQLException {
		// TODO Auto-generated method stub
		boolean actualizado = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		Connection con = ds.getConnection();
		Statement stmt = con.createStatement();

		String query = "UPDATE CLIENTE SET nombre='" + cliente.getName() + "', " + "apellidos='" + cliente.getSurname()
				+ "'," + ((cliente.getAddress() != null) ? "domicilio='" + cliente.getAddress() + "'," : "")
				+ ((cliente.getCP() != null) ? "CP='" + cliente.getCP() + "'," : "") + "email='" + cliente.getEmail()
				+ "'," + "fechaNac=TO_DATE('" + cliente.getFechaNac() + "','yyyy-mm-dd')," + "carnet='"
				+ cliente.getCarnet() + "' " + "WHERE DNI='" + cliente.getDNI() + "'";

		if (stmt.executeUpdate(query) == 1)
			actualizado = true;

		return actualizado;

	}

	// Vehiculos
	@Override
	public ArrayList<Vehicle> getVehicles(String table) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		ArrayList<Vehicle> vehiculos = new ArrayList<Vehicle>();
		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{call GESTIONVEHICULOS.listarVehiculos(?,?)}";
		ResultSet rs = null;

		Connection con = ds.getConnection();
		CallableStatement cs = con.prepareCall(query);

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
			Date shopDay = new Date(simpleDateFormat.parse(rs.getString("c6")).getTime());
			String status = rs.getString("c7");
			String drivingLicense = rs.getString("c8");
			int value1 = rs.getInt("n3");

			if (table.equals("COCHE")) {

				int value2 = rs.getInt("n4");

				r = new Car(registration, dayPrice, model, color, engine, displacement, shopDay, status, drivingLicense,
						value1, value2);

				vehiculos.add(r);

			} else if (table.equals("FURGONETA")) {

				r = new Van(registration, dayPrice, model, color, engine, displacement, shopDay, status, drivingLicense,
						value1);

				vehiculos.add(r);

			} else if (table.equals("CAMION")) {

				int value2 = rs.getInt("n4");

				r = new Truck(registration, dayPrice, model, color, engine, displacement, shopDay, status,
						drivingLicense, value1, value2);

				vehiculos.add(r);

			} else if (table.equals("MICROBUS")) {

				int value2 = rs.getInt("n4");

				r = new Minibus(registration, dayPrice, model, color, engine, displacement, shopDay, status,
						drivingLicense, value1, value2);

				vehiculos.add(r);

			}
		}

		return vehiculos;
	}

	@Override
	public boolean updateVehicles(Vehicle v) throws SQLException {

		boolean actualizado = false;

		if (v instanceof Car)
			actualizado = callCar("actualizarCoche", (Car) v);

		else if (v instanceof Van)
			actualizado = callVan("actualizarFurgo", (Van) v);

		else if (v instanceof Truck)
			actualizado = callTruck("actualizarCamion", (Truck) v);

		else if (v instanceof Minibus)
			actualizado = callMinibus("actualizarMicrobus", (Minibus) v);

		return actualizado;
	}

	@Override
	public boolean deleteVehicles(String table, Vehicle v) throws SQLException {

		boolean delete = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONVEHICULOS.";

		if (v instanceof Car)
			query += "borrarCoche('" + v.getRegistration() + "')}";

		else if (v instanceof Van)
			query += "borrarFurgo('" + v.getRegistration() + "')}";

		else if (v instanceof Truck)
			query += "borrarCamion('" + v.getRegistration() + "')}";

		else if (v instanceof Minibus)
			query += "borrarMicrobus('" + v.getRegistration() + "')}";

		Connection con = ds.getConnection();
		CallableStatement cstmt = con.prepareCall(query);

		delete = (cstmt.executeUpdate() == 1) ? true : false;

		return delete;
	}

	@Override
	public boolean addVehicle(String table, Vehicle v) throws SQLException {
		// TODO Auto-generated method stub
		boolean añadido = false;

		if (v instanceof Car)
			añadido = callCar("insertarCoche", (Car) v);

		else if (v instanceof Van)
			añadido = callVan("insertarFurgo", (Van) v);

		else if (v instanceof Truck)
			añadido = callTruck("insertarCamion", (Truck) v);

		else if (v instanceof Minibus)
			añadido = callMinibus("insertarMicrobus", (Minibus) v);

		return añadido;
	}

	private boolean callMinibus(String call, Minibus m) throws SQLException {

		boolean added = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONVEHICULOS." + call + "(?,?,?,?,?,?,?,?,?,?,?,?)}";

		Connection con = ds.getConnection();
		CallableStatement cstmt = con.prepareCall(query);

		int pos = 0;

		cstmt.setString(++pos, m.getRegistration());
		cstmt.setFloat(++pos, m.getDayPrice());
		cstmt.setString(++pos, m.getModel());
		cstmt.setString(++pos, "");
		cstmt.setString(++pos, m.getColor());
		cstmt.setString(++pos, m.getEngine());
		cstmt.setDate(++pos, m.getShopDay());
		cstmt.setString(++pos, m.getStatus());
		cstmt.setString(++pos, m.getDrivingLicense());
		cstmt.setInt(++pos, m.getSeating());
		cstmt.setInt(++pos, m.getMedida());
		cstmt.setInt(++pos, m.getDisplacement());

		added = (cstmt.executeUpdate() == 1) ? true : false;

		return added;
	}

	private boolean callTruck(String call, Truck t) throws SQLException {
		// TODO Auto-generated method stub

		boolean added = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONVEHICULOS." + call + "(?,?,?,?,?,?,?,?,?,?,?,?)}";

		Connection con = ds.getConnection();
		CallableStatement cstmt = con.prepareCall(query);

		int pos = 0;

		cstmt.setString(++pos, t.getRegistration());
		cstmt.setFloat(++pos, t.getDayPrice());
		cstmt.setString(++pos, t.getModel());
		cstmt.setString(++pos, "");
		cstmt.setString(++pos, t.getColor());
		cstmt.setString(++pos, t.getEngine());
		cstmt.setDate(++pos, t.getShopDay());
		cstmt.setString(++pos, t.getStatus());
		cstmt.setString(++pos, t.getDrivingLicense());
		cstmt.setInt(++pos, t.getnWheels());
		cstmt.setInt(++pos, t.getMMA());
		cstmt.setInt(++pos, t.getDisplacement());

		added = (cstmt.executeUpdate() == 1) ? true : false;

		return added;

	}

	private boolean callVan(String call, Van v) throws SQLException {

		boolean added = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONVEHICULOS." + call + "(?,?,?,?,?,?,?,?,?,?,?)}";

		Connection con = ds.getConnection();
		CallableStatement cstmt = con.prepareCall(query);

		int pos = 0;

		cstmt.setString(++pos, v.getRegistration());
		cstmt.setFloat(++pos, v.getDayPrice());
		cstmt.setString(++pos, v.getModel());
		cstmt.setString(++pos, "");
		cstmt.setString(++pos, v.getColor());
		cstmt.setString(++pos, v.getEngine());
		cstmt.setDate(++pos, v.getShopDay());
		cstmt.setString(++pos, v.getStatus());
		cstmt.setString(++pos, v.getDrivingLicense());
		cstmt.setInt(++pos, v.getMMA());
		cstmt.setInt(++pos, v.getDisplacement());

		added = (cstmt.executeUpdate() == 1) ? true : false;

		return added;

	}

	private boolean callCar(String call, Car c) throws SQLException {
		// TODO Auto-generated method stub

		boolean added = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONVEHICULOS." + call + "(?,?,?,?,?,?,?,?,?,?,?,?)}";

		Connection con = ds.getConnection();
		CallableStatement cstmt = con.prepareCall(query);

		int pos = 0;

		cstmt.setString(++pos, c.getRegistration());
		cstmt.setFloat(++pos, c.getDayPrice());
		cstmt.setString(++pos, c.getModel());
		cstmt.setString(++pos, "");
		cstmt.setString(++pos, c.getColor());
		cstmt.setString(++pos, c.getEngine());
		cstmt.setDate(++pos, c.getShopDay());
		cstmt.setString(++pos, c.getStatus());
		cstmt.setString(++pos, c.getDrivingLicense());
		cstmt.setInt(++pos, c.getSeating());
		cstmt.setInt(++pos, c.getDoors());
		cstmt.setInt(++pos, c.getDisplacement());

		added = (cstmt.executeUpdate() == 1) ? true : false;

		return added;

	}

	// Facturas
	@Override
	public ArrayList<Invoice> getInvoice() throws SQLException {
		// TODO Auto-generated method stub

		ArrayList<Invoice> facturas = new ArrayList<Invoice>();
		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{?= call GESTIONALQUILER.listarfacturas()}";
		ResultSet rs = null;

		Connection con = ds.getConnection();
		CallableStatement cs = con.prepareCall(query);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		rs = (ResultSet) cs.getObject(1);

		Invoice i = null;

		while (rs.next()) {

			i = new Invoice(rs.getInt("IDFACTURA"), rs.getInt("CLIENTEID"), rs.getDate("FECHA"),
					rs.getFloat("IMPORTEBASE"), rs.getFloat("IMPORTEIVA"));
			facturas.add(i);

		}

		return facturas;
	}

	public ArrayList<Rent> getRent() throws SQLException {

		ArrayList<Rent> alquileres = new ArrayList<Rent>();
		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{?= call GESTIONALQUILER.listarAlquileres()}";
		ResultSet rs = null;

		Connection con = ds.getConnection();
		CallableStatement cs = con.prepareCall(query);

		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		rs = (ResultSet) cs.getObject(1);

		Rent r = null;

		while (rs.next()) {

			r = new Rent(rs.getInt("IDALQUILER"), rs.getInt("IDFACTURA"), rs.getString("MATRICULA"),
					rs.getDate("FECHAINICIO"), rs.getDate("FECHAFIN"), rs.getFloat("PRECIO"));
			alquileres.add(r);

		}

		return alquileres;

	}

	@Override
	public boolean addRent(Rent r, String DNI) throws SQLException {
		// TODO Auto-generated method stub

		boolean added = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{?= call GESTIONALQUILER.insertarAlquiler(?,?,?,?,?,?)}";

		Connection con = ds.getConnection();
		CallableStatement cstmt = con.prepareCall(query);

		int pos = 0;

		cstmt.setInt(++pos, r.getIdAlquiler());
		cstmt.setInt(++pos, r.getIdFactura());
		cstmt.setString(++pos, DNI);
		cstmt.setString(++pos, r.getMatricula());
		cstmt.setDate(++pos, r.getfInicio());
		cstmt.setDate(++pos, r.getfFin());
		cstmt.setInt(++pos, r.getIdAlquiler());

		added = (cstmt.executeUpdate() == 1) ? true : false;

		return added;

	}

	@Override
	public boolean deleteRent(Rent r) throws SQLException {
		// TODO Auto-generated method stub
		boolean delete = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONALQUILER.bajaAlquiler(?)";

		Connection con = ds.getConnection();
		CallableStatement cstmt = con.prepareCall(query);

		cstmt.setInt(1, r.getIdAlquiler());

		delete = (cstmt.executeUpdate() == 1) ? true : false;

		return delete;

	}

	@Override
	public boolean addInvoice(Rent r, String dni) throws SQLException {
		// TODO Auto-generated method stub

		boolean added = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{?= call GESTIONALQUILER.insertarAlquiler(?,?,?,?,?,?)}";

		Connection con = ds.getConnection();
		CallableStatement cstmt = con.prepareCall(query);

		int pos = 0;

		cstmt.setInt(++pos, r.getIdAlquiler());
		cstmt.setNull(++pos, r.getIdFactura());
		cstmt.setString(++pos, dni);
		cstmt.setString(++pos, r.getMatricula());
		cstmt.setDate(++pos, r.getfInicio());
		cstmt.setDate(++pos, r.getfFin());
		cstmt.setInt(++pos, r.getIdAlquiler());

		added = (cstmt.executeUpdate() == 1) ? true : false;

		return added;

	}

	@Override
	public boolean updateRent(Rent r) throws SQLException {
		// TODO Auto-generated method stub
		boolean added = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{call GESTIONALQUILER.modificarAlquiler(?,?,?)}";

		Connection con = ds.getConnection();
		CallableStatement cstmt = con.prepareCall(query);

		int pos = 0;
		cstmt.setInt(++pos, r.getIdAlquiler());
		cstmt.setDate(++pos, r.getfInicio());
		cstmt.setDate(++pos, r.getfFin());

		added = (cstmt.executeUpdate() == 1) ? true : false;

		return added;
	}

	// Otros
	@Override
	public boolean check(int idfactura) throws SQLException {
		// TODO Auto-generated method stub
		boolean check = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{call GESTIONALQUILER.facturacobrada(?)}";

		Connection con = ds.getConnection();
		CallableStatement cstmt = con.prepareCall(query);

		cstmt.setInt(1, idfactura);

		check = (cstmt.executeUpdate() == 1) ? true : false;

		return check;
	}

	@Override
	public boolean authenticate(String login, String password) {

		boolean registrado = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "SELECT COUNT(*) FROM EMPLEADO WHERE DNI=? AND PASSWORD=ENCRYPT_PASWD.encrypt_val(?)";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(query);) {

			pstmt.setString(1, login);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			rs.next();
			if (rs.getInt(1) == 1)
				registrado = true;

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return registrado;
	}

}
