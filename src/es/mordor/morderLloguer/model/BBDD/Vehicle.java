package es.mordor.morderLloguer.model.BBDD;

import java.sql.Date;

public abstract class Vehicle {

	private String registration;
	private float dayPrice;
	private String model;
	private String color;
	private String engine;
	private int displacement;
	private Date shopDay;
	private String status;
	private String drivingLicense;

	public Vehicle(String registration, float dayPrice, String model, String color, String engine, int displacement,
			Date shopDay, String status, String drivingLicense) {
		super();
		this.registration = registration;
		this.dayPrice = dayPrice;
		this.model = model;
		this.color = color;
		this.engine = engine;
		this.displacement = displacement;
		this.shopDay = shopDay;
		this.status = status;
		this.drivingLicense = drivingLicense;
	}

	public float getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(float dayPrice) {
		this.dayPrice = dayPrice;
	}

	public Date getShopDay() {
		return shopDay;
	}

	public void setShopDay(Date shopDay) {
		this.shopDay = shopDay;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public int getDisplacement() {
		return displacement;
	}

	public void setDisplacement(int displacement) {
		this.displacement = displacement;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	@Override
	public String toString() {
		return "Vehicle [registration=" + registration + ", dayPrice=" + dayPrice + ", model=" + model + ", color="
				+ color + ", engine=" + engine + ", displacement=" + displacement + ", shopDay=" + shopDay + ", status="
				+ status + ", drivingLicense=" + drivingLicense + "]";
	}

}
