package es.mordor.morderLloguer.model.BBDD;

public class Minibus extends Vehicle{

	private int mma;
	private int seating;
	
	
	public Minibus(String registration, String model, String color, String engine, int displacement, String status,
			String drivingLicense, int width, int seating) {
		
		super(registration, model, color, engine, displacement, status, drivingLicense);
		
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
