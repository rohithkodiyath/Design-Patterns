package chain;

public class BadPasswordErrorHandler extends ErrorHandler {

	public BadPasswordErrorHandler(ErrorHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public ErrorRequest processError(ErrorRequest request) {
		if (request.getErrorCode() == 2) {
			request.setErrorMessage("Sorry bad password .Try again Handles by "+getClass());
			return request;
		}
		if (super.nextHandler != null)
			return super.nextHandler.processError(request);
		return request;
	}

}
