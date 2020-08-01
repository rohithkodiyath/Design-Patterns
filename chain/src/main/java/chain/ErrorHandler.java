package chain;

public abstract class ErrorHandler {

	protected ErrorHandler nextHandler;

	public ErrorHandler(ErrorHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public abstract ErrorRequest processError(ErrorRequest request);

}
