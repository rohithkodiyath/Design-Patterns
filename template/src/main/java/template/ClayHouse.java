package template;

public class ClayHouse extends HouseTemplate {

	@Override
	void makeFoundation() {
		System.out.println("Make foundation by brick");

	}

	@Override
	void makeWalls() {
		System.out.println("Make walls by bricks");

	}

	@Override
	void makeCeiling() {
		System.out.println("Make ceiling by clay tiles");

	}

}
