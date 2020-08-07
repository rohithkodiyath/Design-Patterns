package template;

public class ComcreteHouse extends HouseTemplate {

	@Override
	void makeFoundation() {
		System.out.println("make concrete foundation");

	}

	@Override
	void makeWalls() {
		System.out.println("make walls by concrete bricks");

	}

	@Override
	void makeCeiling() {
		System.out.println("make walls by ceiling by concrete");

	}

}
