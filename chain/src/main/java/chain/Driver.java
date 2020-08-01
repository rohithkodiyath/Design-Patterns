package chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Driver.class);
	
	public static void main(String[] args) {
		
		ErrorRequest badUserNameError = new ErrorRequest(1);
		ErrorRequest badPasswordError = new ErrorRequest(2);
		ErrorRequest unknownError = new ErrorRequest(7);
		
		badUserNameError = ErrorHandlerChain.handlerChain.processError(badUserNameError);
		LOGGER.info("# {}"+badUserNameError);
		badPasswordError = ErrorHandlerChain.handlerChain.processError(badPasswordError);
		LOGGER.info("# {}"+badPasswordError);
		unknownError = ErrorHandlerChain.handlerChain.processError(unknownError);
		LOGGER.info("# {}"+unknownError);
		
		
		
		
	}
}
