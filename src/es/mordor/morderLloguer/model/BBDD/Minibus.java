package es.mordor.morderLloguer.model.BBDD;

public class Minibus extends Vehicle{

	private int width;
	private int seating;
	
	
	public Minibus(String registration, String model, String color, String engine, int displacement, String status,
			String drivingLicense, int width, int seating) {
		
		super(registration, model, color, engine, displacement, status, drivingLicense);
		
		this.seating = seating;
		this.width = width;
		
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getSeating() {
		return seating;
	}


	public void setSeating(int seating) {
		this.seating = seating;
	}
	
	
	

	
	
}
