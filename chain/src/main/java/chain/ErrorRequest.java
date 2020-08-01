package chain;

public class ErrorRequest {

	private int errorCode;
	private String errorMessage;
	private boolean isHandled;

	public ErrorRequest(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isHandled() {
		return isHandled;
	}

	public void setHandled(boolean isHandled) {
		this.isHandled = isHandled;
	}

	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return "ErrorRequest [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", isHandled=" + isHandled
				+ "]";
	}

}
