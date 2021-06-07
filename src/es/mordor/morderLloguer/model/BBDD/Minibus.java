package es.mordor.morderLloguer.model.BBDD;

import java.util.Date;

public class Minibus extends Vehicle{

	private int mma;
	private int seating;
	
	
	public Minibus(String registration, int dayPrice, String model, String color, String engine, int displacement, Date shopDay,  String status,
			String drivingLicense, int width, int seating) {
		
		super(registration,dayPrice, model, color, engine, displacement,shopDay, status, drivingLicense);
		
		this.seating = seating;
		this.mma = width;
		
	}


	public int getMedida() {
		return mma;
	}


	public void setMMA(int width) {
		this.mma = width;
	}


	public int getSeating() {
		return seating;
	}


	public void setSeating(int seating) {
		this.seating = seating;
	}


	@Override
	public String toString() {
		return "Minibus [width=" + mma + ", seating=" + seating + "]";
	}
	
	
	

	
	
}
