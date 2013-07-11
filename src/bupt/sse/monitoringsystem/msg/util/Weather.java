package bupt.sse.monitoringsystem.msg.util;

import java.util.Date;

public class Weather {
	private Date date;
	private float temperature;
	private float humidity;
	private float windSpeed;
	private float windDirection;
	private float airPressure;
	private float radiation;

	public Weather() {
	}

	public float getRadiation() {
		return radiation;
	}

	public void setRadiation(float radiation) {
		this.radiation = radiation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}

	public float getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(float windDirection) {
		this.windDirection = windDirection;
	}

	public float getAirPressure() {
		return airPressure;
	}

	public void setAirPressure(float airPressure) {
		this.airPressure = airPressure;
	}
}
