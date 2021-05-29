package es.mordor.morderLloguer.model.BBDD;

public class Van extends Vehicle {
	
	private int MMA;
		
	public Van(String registration, String model, String color, String engine, int displacement, String status,
			String drivingLicense, int MMA) {
		
		super(registration, model, color, engine, displacement, status, drivingLicense);
		
		this.MMA = MMA;
	
		
	}

	public int getMMA() {
		return MMA;
	}

	public void setMMA(int mMA) {
		MMA = mMA;
	}
	
	

	
	
}
