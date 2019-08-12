package dto;

public class Model {
	
	private String modelName;
	private int tank;
	private double dayPrice;

	public Model() {
	}

	public Model(String modelName, int tank, double dayPrice) {
		this.modelName = modelName;
		this.tank = tank;
		this.dayPrice = dayPrice;
	}

	public String getModelName() { return modelName; }

	public void setModelName(String modelName) { this.modelName = modelName; }

	public int getTank() { return tank; }

	public void setTank(int tank) { this.tank = tank; }

	public double getDayPrice() { return dayPrice; }

	public void setDayPrice(double dayPrice) { this.dayPrice = dayPrice; }

	@Override
	public String toString() {
		return "Model{" +
				"modelName='" + modelName + '\'' +
				", tank=" + tank +
				", dayPrice=" + dayPrice +
				'}';
	}
}
