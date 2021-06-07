package es.mordor.morderLloguer.model.BBDD;

import java.util.Date;

public class Invoice {
	
	private int idfactura;
	private int clienteid;
	private Date fecha;
	private float importebase;
	private float importeiva;
	
	public Invoice(int idfactura, int clienteid, Date fecha, float importebase, float importeiva) {
		super();
		this.idfactura = idfactura;
		this.clienteid = clienteid;
		this.fecha = fecha;
		this.importebase = importebase;
		this.importeiva = importeiva;
	}
	
	public int getIdfactura() {
		return idfactura;
	}
	public void setIdfactura(int idfactura) {
		this.idfactura = idfactura;
	}
	public int getClienteid() {
		return clienteid;
	}
	public void setClienteid(int clienteid) {
		this.clienteid = clienteid;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getImportebase() {
		return importebase;
	}
	public void setImportebase(float importebase) {
		this.importebase = importebase;
	}
	public float getImporteiva() {
		return importeiva;
	}
	public void setImporteiva(float importeiva) {
		this.importeiva = importeiva;
	}

	@Override
	public String toString() {
		return "Invoice [idfactura=" + idfactura + ", clienteid=" + clienteid + ", fecha=" + fecha + ", importebase="
				+ importebase + ", importeiva=" + importeiva + "]";
	}
	
	
	
	
	
	
	
	
}
