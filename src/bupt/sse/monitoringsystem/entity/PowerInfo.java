package bupt.sse.monitoringsystem.entity;

import java.util.Date;

import bupt.sse.monitoringsystem.msg.util.EquArray;
import bupt.sse.monitoringsystem.msg.util.Station;
import bupt.sse.monitoringsystem.msg.util.Unit;

public class PowerInfo {
	private String powerId;
	private String powerName;
	private Date time;
	private float capacity;

	
	
	public PowerInfo(EquArray equArray) {
		this.powerId = equArray.getEquDN();
		this.powerName = equArray.getEquName();
		this.time = equArray.getDate();
		this.capacity = equArray.getCapacity();
	}
	
	public PowerInfo(Station station) {
		this.powerId = station.getStationDN();
		this.powerName = station.getStationName();
		this.time = station.getDate();
		this.capacity = station.getCapacity();
	}
	
	public PowerInfo(Unit unit) {
		this.powerId = unit.getUnitDN();
		this.powerName = unit.getUnitName();
		this.time = unit.getDate();
		this.capacity = unit.getCapacity();
	}

	public PowerInfo(String powerId, String powerName, Date time, float capacity) {
		super();
		this.powerId = powerId;
		this.powerName = powerName;
		this.time = time;
		this.capacity = capacity;
	}

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public float getCapacity() {
		return capacity;
	}

	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}

}
