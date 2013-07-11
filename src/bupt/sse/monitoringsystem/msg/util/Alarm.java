package bupt.sse.monitoringsystem.msg.util;

import java.util.Date;

public class Alarm {
	private String alarmObjDN;
	private String alarmObjType;
	private int alarmLevel;
	private Date beginTime;
	private Date endTime;
	private String alarmtext;
	private String instancename;
	private String unitid;

	public Alarm() {
	}

	public String getUnitid() {
		return unitid;
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}

	public String getAlarmtext() {
		return alarmtext;
	}

	public void setAlarmtext(String alarmtext) {
		this.alarmtext = alarmtext;
	}

	public String getInstancename() {
		return instancename;
	}

	public void setInstancename(String instancename) {
		this.instancename = instancename;
	}

	public String getAlarmObjDN() {
		return alarmObjDN;
	}

	public void setAlarmObjDN(String alarmObjDN) {
		this.alarmObjDN = alarmObjDN;
	}

	public String getAlarmObjType() {
		return alarmObjType;
	}

	public void setAlarmObjType(String alarmObjType) {
		this.alarmObjType = alarmObjType;
	}

	public int getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(int alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
