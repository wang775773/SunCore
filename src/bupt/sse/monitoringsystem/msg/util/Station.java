package bupt.sse.monitoringsystem.msg.util;

import java.util.Date;

public class Station {
	private String stationDN;
	private String stationName;
	private Date date;
	private Float capacity;

	public Station() {
	}

	public String getStationDN() {
		return stationDN;
	}

	public void setStationDN(String stationDN) {
		this.stationDN = stationDN;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getCapacity() {
		return capacity;
	}

	public void setCapacity(Float capacity) {
		this.capacity = capacity;
	}
}
