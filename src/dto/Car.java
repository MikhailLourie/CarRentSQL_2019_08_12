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
	

}
