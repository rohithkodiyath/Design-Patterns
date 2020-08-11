package builder;

public class Vehicle {

	private String name;
	private int enginePower;
	private String engineType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnginePower() {
		return enginePower;
	}
	public void setEnginePower(int enginePower) {
		this.enginePower = enginePower;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	@Override
	public String toString() {
		return "Vehicle [name=" + name + ", enginePower=" + enginePower + ", engineType=" + engineType + "]";
	}
	
	
	
	

}