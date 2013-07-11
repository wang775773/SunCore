package bupt.sse.monitoringsystem.msg.util;

public class Confirm {
	private boolean success;
	private String reason;

	public Confirm() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
