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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Model)) return false;

		Model model = (Model) o;

		if (tank != model.tank) return false;
		if (Double.compare(model.dayPrice, dayPrice) != 0) return false;
		return modelName != null ? modelName.equals(model.modelName) : model.modelName == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = modelName != null ? modelName.hashCode() : 0;
		result = 31 * result + tank;
		temp = Double.doubleToLongBits(dayPrice);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
