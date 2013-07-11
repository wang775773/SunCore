package bupt.sse.monitoringsystem.msg.util;

import java.util.Date;

public class Unit {
	private String unitDN;
	private String unitName;
	private Date date;
	private Float capacity;

	public Unit() {
	}

	public String getUnitDN() {
		return unitDN;
	}

	public void setUnitDN(String unitDN) {
		this.unitDN = unitDN;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
