package bupt.sse.monitoringsystem.msg.util;

import java.util.Date;

public class EquArray {
	private String equDN;
	private String equName;
	private Date date;
	private Float capacity;

	public EquArray() {
	}

	public String getEquDN() {
		return equDN;
	}

	public void setEquDN(String equDN) {
		this.equDN = equDN;
	}

	public String getEquName() {
		return equName;
	}

	public void setEquName(String equName) {
		this.equName = equName;
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
