package bupt.sse.monitoringsystem.msg.util;

import java.util.List;

public class OrderDetail {
	private String instanceName;
	private String tenantName;
	private String status;
	private List<OrderProperty> orderProperties;
	private List<OrderRoleOperator> orderRoleOperators;

	public OrderDetail() {
	}

	public OrderDetail(String instanceName, String tenantName, String status,
			List<OrderProperty> orderProperties,
			List<OrderRoleOperator> orderRoleOperators) {
		super();
		this.instanceName = instanceName;
		this.tenantName = tenantName;
		this.status = status;
		this.orderProperties = orderProperties;
		this.orderRoleOperators = orderRoleOperators;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderProperty> getOrderProperties() {
		return orderProperties;
	}

	public void setOrderProperties(List<OrderProperty> orderProperties) {
		this.orderProperties = orderProperties;
	}

	public List<OrderRoleOperator> getOrderRoleOperators() {
		return orderRoleOperators;
	}

	public void setOrderRoleOperators(List<OrderRoleOperator> orderRoleOperators) {
		this.orderRoleOperators = orderRoleOperators;
	}

}
