package dto;

public class Car {
	
	private String regNumber;
	private Model model;
	private boolean inUse;
	public Car() {
		super();
	}
	public Car(String regNumber, Model model, boolean inUse) {
		super();
		this.regNumber = regNumber;
		this.model = model;
		this.inUse = inUse;
	}
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public boolean isInUse() {
		return inUse;
	}
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	@Override
	public String toString() {
		return "Car [regNumber=" + regNumber + ", model=" + model + ", inUse=" + inUse + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Car)) return false;

		Car car = (Car) o;

		if (inUse != car.inUse) return false;
		if (regNumber != null ? !regNumber.equals(car.regNumber) : car.regNumber != null) return false;
		return model != null ? model.equals(car.model) : car.model == null;
	}

	@Override
	public int hashCode() {
		int result = regNumber != null ? regNumber.hashCode() : 0;
		result = 31 * result + (model != null ? model.hashCode() : 0);
		result = 31 * result + (inUse ? 1 : 0);
		return result;
	}
}
