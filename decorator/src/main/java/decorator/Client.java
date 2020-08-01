package decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
	
	public static void main(String[] args) {
		// add oreo topping
		Icecream oreoIcecream = new OreoIcecreamDecorator(new RegularIcecream());
		LOGGER.info("# add oreo topping {}"+oreoIcecream.makeIcecream());

		// add hotfudge topping
		Icecream hotFudgeIcecream = new HotfudgeIcecreamDecorator(new RegularIcecream());
		LOGGER.info("# hot fudge topping {}"+hotFudgeIcecream.makeIcecream());

		// add both oreo and hot fudge
		Icecream hotFudgeOreoIcecream = new OreoIcecreamDecorator(new HotfudgeIcecreamDecorator(new RegularIcecream()));
		LOGGER.info("# hot fudge and oreo topping {}"+hotFudgeOreoIcecream.makeIcecream());

	}
}
