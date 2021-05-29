package es.mordor.morderLloguer.model.BBDD;

public class Car extends Vehicle {
	
	private int seating;
	private int doors;
	
	public Car(String registration, String model, String color, String engine, int displacement, String status,
			String drivingLicense, int seating, int doors) {
		
		super(registration, model, color, engine, displacement, status, drivingLicense);
		
		this.seating = seating;
		this.doors = doors;
		
	
	}

	public int getSeating() {
		return seating;
	}

	public void setSeating(int seating) {
		this.seating = seating;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}
	
		
	
}
