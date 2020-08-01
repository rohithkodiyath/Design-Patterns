package decorator;

public class OreoIcecreamDecorator extends IcecreamDecorator {
	private static final float ADDITIONAL_COST = 20f;

	protected OreoIcecreamDecorator(Icecream iceCream) {
		super(iceCream);
	}

	public String makeIcecream() {
		return super.iceCream.makeIcecream() + " added some oreo";
	}

	public float getPrice() {
		return super.iceCream.getPrice() + ADDITIONAL_COST;
	}

}
