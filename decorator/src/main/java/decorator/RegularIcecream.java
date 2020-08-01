package decorator;

public class RegularIcecream implements Icecream{

	
	public String makeIcecream() {
		//do all the work for building simple ice cream
		return "Make normal ice cream";
	}

	public float getPrice() {
		// minimum prize is 10 
		return 10;
	}

}
