package es.mordor.morderLloguer.model.BBDD;

import java.util.Date;

public class Rent {

	private int idAlquiler;
	private int idFactura;
	private String matricula;
	private Date fInicio;
	private Date fFin;
	private float precio;
	
	public Rent(int idAlquiler, int idFactura, String matricula, Date fInicio, Date fFin, float precio) {
		super();
		this.idAlquiler = idAlquiler;
		this.idFactura = idFactura;
		this.matricula = matricula;
		this.fInicio = fInicio;
		this.fFin = fFin;
		this.precio = precio;
	}

	public int getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getfInicio() {
		return fInicio;
	}

	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}

	public Date getfFin() {
		return fFin;
	}

	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Rent [idAlquiler=" + idAlquiler + ", idFactura=" + idFactura + ", matricula=" + matricula + ", fInicio="
				+ fInicio + ", fFin=" + fFin + ", precio=" + precio + "]";
	}
	
	
	
	
	
	
}
