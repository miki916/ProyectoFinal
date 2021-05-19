package es.mordor.morderLloguer.model.BBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;



public class MyOracleDataBase implements AlmacenDatosDB {
	
	public ArrayList<Empleado> getCustomEmpleados(String where) {

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "SELECT * FROM EMPLEADO";

		if (where != null)
			query += " WHERE " + where;

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			Empleado empleado;

			while (rs.next()) {
				empleado = new Empleado(rs.getInt("idEmpleado"), rs.getString("DNI"), rs.getString("nombre"),
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
	
	public ArrayList<Empleado> getCustomEmpleadosOrdenados(String orderBy) {

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "SELECT * FROM EMPLEADO";

		if (orderBy != null)
			query += " ORDER BY " + orderBy;

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			Empleado empleado;

			while (rs.next()) {
				empleado = new Empleado(rs.getInt("idEmpleado"), rs.getString("DNI"), rs.getString("nombre"),
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
	public ArrayList<Empleado> getEmpleados() {
		// TODO Auto-generated method stub
		return getCustomEmpleados(null);
	}

	
	@Override
	public ArrayList<Empleado> getEmpleadosPorCP(String cp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Empleado> getEmpleadosPorCargo(String cargo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado getEmpleadoPorDNI(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmpleado(String dni) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public ArrayList<Empleado> getEmpleadosOrdenadosBy(String field, int sort) {
		// TODO Auto-generated method stub
		
		String order = "";
		
		if(sort == AlmacenDatosDB.ASCENDING) {
			
			order = "ASC";
			
		}else if( sort == AlmacenDatosDB.DESCENDING)
			
			order = "DESC";
			
			
		
		
		return getCustomEmpleadosOrdenados(field + " " + order);
	}

}
