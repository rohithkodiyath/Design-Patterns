package builder;

public class Client {
	public static void main(String[] args) {
		
		Vehicle vehicle = VehicleBuilder.getBuilder().setName("Maruthi celerio").setPower(999)
				.setEngineType("Four stroke").build();

		System.out.println(vehicle);
	}
}
