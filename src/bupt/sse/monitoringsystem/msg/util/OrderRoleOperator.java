package bupt.sse.monitoringsystem.msg.util;

public class OrderRoleOperator {

	private String roleName;
	private String operatorName;

	public OrderRoleOperator() {
		super();
	}

	public OrderRoleOperator(String roleName, String operatorName) {
		super();
		this.roleName = roleName;
		this.operatorName = operatorName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

}
