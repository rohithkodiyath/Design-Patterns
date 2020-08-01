package chain;

public class ErrorHandlerChain {
	
	public static ErrorHandler handlerChain ;
	
	static {
		handlerChain = new BadPasswordErrorHandler(
					new BadUserNameErrorHandler(null)
				);
	}
	
	

}
