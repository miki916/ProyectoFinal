package es.mordor.morderLloguer.model.BBDD;

public class Truck extends Vehicle {
	
	private int MMA;
	private int nWheels;
	
	public Truck(String registration, String model, String color, String engine, int displacement, String status,
			String drivingLicense, int MMA , int nWheels) {
		
		super(registration, model, color, engine, displacement, status, drivingLicense);

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
