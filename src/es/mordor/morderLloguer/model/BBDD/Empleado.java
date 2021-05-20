package es.mordor.morderLloguer.model.BBDD;

import java.sql.Date;

public class Empleado {

	private int idEmpleado;
	private String DNI;
	private String nombre;
	private String apellidos;
	private String CP;
	private String email;
	private Date fechaNac;
	private String cargo;
	private String domicilio;
	private String password;
	
	public Empleado(int idEmpleado, String dNI, String nombre, String apellidos, String cP, String email, Date fechaNac,
			String cargo, String domicilio, String password) {
		super();
		this.idEmpleado = idEmpleado;
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		CP = cP;
		this.email = email;
		this.fechaNac = fechaNac;
		this.cargo = cargo;
		this.domicilio = domicilio;
		this.password = password;
	}
	
	public Empleado(String dni) {
		this.DNI=dni;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Empleado) {
			return ((Empleado) o).getDNI().compareToIgnoreCase(DNI)==0;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Empleado [idEmpleado=" + idEmpleado + ", DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", CP=" + CP + ", email=" + email + ", fechaNac=" + fechaNac + ", cargo=" + cargo + ", domicilio="
				+ domicilio + ", password=" + password + "]";
	}
	
	
}
