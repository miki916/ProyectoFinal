package es.mordor.morderLloguer.model.BBDD;

import java.sql.Date;

public class Truck extends Vehicle {
	
	private int MMA;
	private int nWheels;
	
	public Truck(String registration, float dayPrice, String model, String color, String engine, int displacement, Date shopDay,  String status,
			String drivingLicense, int MMA , int nWheels) {
		
		super(registration,dayPrice, model, color, engine, displacement,shopDay, status, drivingLicense);

		this.MMA = MMA;
		this.nWheels = nWheels;

	}

	public int getMMA() {
		return MMA;
	}

	public void setMMA(int mMA) {
		MMA = mMA;
	}

	public int getnWheels() {
		return nWheels;
	}

	public void setnWheels(int nWheels) {
		this.nWheels = nWheels;
	}
	
	
	
	
}
