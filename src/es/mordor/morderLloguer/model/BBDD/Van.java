package es.mordor.morderLloguer.model.BBDD;

import java.sql.Date;

public class Van extends Vehicle {

	private int MMA;

	public Van(String registration, float dayPrice, String model, String color, String engine, int displacement,
			Date shopDay, String status, String drivingLicense, int MMA) {

		super(registration, dayPrice, model, color, engine, displacement, shopDay, status, drivingLicense);

		this.MMA = MMA;

	}

	public int getMMA() {
		return MMA;
	}

	public void setMMA(int mMA) {
		MMA = mMA;
	}

}
