package decorator;

public abstract class IcecreamDecorator implements Icecream {

	protected Icecream iceCream;
	
	protected IcecreamDecorator(Icecream iceCream) {
		this.iceCream = iceCream;
	}

}
