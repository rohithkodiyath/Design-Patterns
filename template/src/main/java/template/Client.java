package template;

public class Client {

	public static void main(String[] args) {
		HouseTemplate house1 = new ClayHouse();
		HouseTemplate house2 = new ComcreteHouse();
		house1.buildHouse();
		house2.buildHouse();
		
	}

}
