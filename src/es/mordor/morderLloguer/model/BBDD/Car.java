package es.mordor.morderLloguer.model.BBDD;

import java.sql.Date;

public class Car extends Vehicle {
	
	private int seating;
	private int doors;
	
	public Car(String registration, float dayPrice, String model, String color, String engine, int displacement, Date shopDay,  String status,
			String drivingLicense, int seating, int doors) {
		
		super(registration,dayPrice, model, color, engine, displacement,shopDay, status, drivingLicense);
		
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


	@Override
	public String toString() {
		return "Car [seating=" + seating + ", doors=" + doors + ", getDayPrice()=" + getDayPrice() + ", getShopDay()="
				+ getShopDay() + ", getRegistration()=" + getRegistration() + ", getModel()=" + getModel()
				+ ", getColor()=" + getColor() + ", getEngine()=" + getEngine() + ", getDisplacement()="
				+ getDisplacement() + ", getStatus()=" + getStatus() + ", getDrivingLicense()=" + getDrivingLicense()
				+ "]";
	}
	
		
}
