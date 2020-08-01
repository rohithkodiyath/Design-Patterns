package chain;

public class BadUserNameErrorHandler extends ErrorHandler {

	public BadUserNameErrorHandler(ErrorHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public ErrorRequest processError(ErrorRequest request) {
		if (request.getErrorCode() == 1) {
			request.setErrorMessage("Sorry bad user name .Try again Handles by "+getClass());
			return request;
		}
		if (super.nextHandler != null)
			return super.nextHandler.processError(request);
		return request;
	}

}
