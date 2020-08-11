package builder;

public class VehicleBuilder {

	private VehicleBuilder() {

	}

	public static VehicleNameSetter getBuilder() {
		return new VehicleBuilderStep();
	}

	public interface VehicleNameSetter {
		VehicleEnginePowerSetter setName(String name);
	}

	public interface VehicleEnginePowerSetter {
		VehicleEngineTypeSetter setPower(int power);
	}

	public interface VehicleEngineTypeSetter {
		BuilderInterface setEngineType(String type);
	}

	public interface BuilderInterface {
		Vehicle build();
	}

	private static class VehicleBuilderStep
			implements VehicleNameSetter, VehicleEnginePowerSetter, VehicleEngineTypeSetter, BuilderInterface {

		private String name;
		private int enginePower;
		private String engineType;

		public Vehicle build() {
			Vehicle vehicle = new Vehicle();
			vehicle.setName(name);
			vehicle.setEngineType(engineType);
			vehicle.setEnginePower(enginePower);
			return vehicle;

		}

		public BuilderInterface setEngineType(String engineType) {
			this.engineType = engineType;
			return this;
		}

		public VehicleEngineTypeSetter setPower(int power) {
			this.enginePower = power;
			return this;
		}

		public VehicleEnginePowerSetter setName(String name) {
			this.name = name;
			return this;
		}


	}

}