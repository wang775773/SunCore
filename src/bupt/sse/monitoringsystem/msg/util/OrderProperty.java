package bupt.sse.monitoringsystem.msg.util;

public class OrderProperty {

	private String propertyName;
	private String propertyValue;

	public OrderProperty() {
		super();
	}

	public OrderProperty(String propertyName, String propertyValue) {
		super();
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

}
