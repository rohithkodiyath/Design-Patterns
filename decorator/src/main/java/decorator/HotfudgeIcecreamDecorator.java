package decorator;

public class HotfudgeIcecreamDecorator extends IcecreamDecorator {
	private static final float ADDITIONAL_COST = 2f;

	protected HotfudgeIcecreamDecorator(Icecream iceCream) {
		super(iceCream);
	}

	public String makeIcecream() {
		return super.iceCream.makeIcecream() + " added some hot fudge";
	}

	public float getPrice() {
		return super.iceCream.getPrice() + ADDITIONAL_COST;
	}

}
