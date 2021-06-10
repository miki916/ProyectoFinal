package es.mordor.morderLloguer.model.BBDD;

import java.sql.Date;

public class Customer {

	private int idCliente;
	private String DNI;
	private String name;
	private String surname;
	private String address;
	private String CP;
	private String email;
	private Date fechaNac;
	private String carnet;
	
	
	public Customer(int idCliente, String dNI, String name, String surname, String address, String cP, String email,
			Date fechaNac, String carnet) {
		super();
		this.idCliente = idCliente;
		DNI = dNI;
		this.name = name;
		this.surname = surname;
		this.address = address;
		CP = cP;
		this.email = email;
		this.fechaNac = fechaNac;
		this.carnet = carnet;
	}
	
	public Customer(String dNI, String name, String surname, String address, String cP, String email,
			Date fechaNac, String carnet) {
		super();
		DNI = dNI;
		this.name = name;
		this.surname = surname;
		this.address = address;
		CP = cP;
		this.email = email;
		this.fechaNac = fechaNac;
		this.carnet = carnet;
	}


	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public String getDNI() {
		return DNI;
	}


	public void setDNI(String dNI) {
		DNI = dNI;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
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


	public String getCarnet() {
		return carnet;
	}


	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}


	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", DNI=" + DNI + ", name=" + name + ", surname=" + surname
				+ ", address=" + address + ", CP=" + CP + ", email=" + email + ", fechaNac=" + fechaNac + ", carnet="
				+ carnet + "]";
	}
	
	
	
	
	
	
	
	
}
