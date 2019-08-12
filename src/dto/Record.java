package dto;

import java.time.LocalDate;

public class Record {
	
	private int id;
	private Car car;
	private Driver driver;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int rentDays;
	private int tankPercent;
	private double cost;
	
	public Record() {
		super();
	}

	public Record(int id, Car car, Driver driver, LocalDate rentDate, LocalDate returnDate, int rentDays,
			int tankPercent, double cost) {
		super();
		this.id = id;
		this.car = car;
		this.driver = driver;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.rentDays = rentDays;
		this.tankPercent = tankPercent;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public LocalDate getRentDate() {
		return rentDate;
	}

	public void setRentDate(LocalDate rentDate) {
		this.rentDate = rentDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public int getRentDays() {
		return rentDays;
	}

	public void setRentDays(int rentDays) {
		this.rentDays = rentDays;
	}

	public int getTankPercent() {
		return tankPercent;
	}

	public void setTankPercent(int tankPercent) {
		this.tankPercent = tankPercent;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", car=" + car + ", driver=" + driver + ", rentDate=" + rentDate + ", returnDate="
				+ returnDate + ", rentDays=" + rentDays + ", tankPercent=" + tankPercent + ", cost=" + cost + "]";
	}
	
	
	
	
	

}
