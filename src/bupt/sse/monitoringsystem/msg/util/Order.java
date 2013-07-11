package bupt.sse.monitoringsystem.msg.util;


public class Order {
	private String instanceName;
	private String tenantName;

	public Order() {
		super();
	}

	public Order(String instanceName, String tenantName) {
		super();
		this.instanceName = instanceName;
		this.tenantName = tenantName;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

}