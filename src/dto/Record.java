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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Record)) return false;

		Record record = (Record) o;

		if (id != record.id) return false;
		if (rentDays != record.rentDays) return false;
		if (tankPercent != record.tankPercent) return false;
		if (Double.compare(record.cost, cost) != 0) return false;
		if (car != null ? !car.equals(record.car) : record.car != null) return false;
		if (driver != null ? !driver.equals(record.driver) : record.driver != null) return false;
		if (rentDate != null ? !rentDate.equals(record.rentDate) : record.rentDate != null) return false;
		return returnDate != null ? returnDate.equals(record.returnDate) : record.returnDate == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id;
		result = 31 * result + (car != null ? car.hashCode() : 0);
		result = 31 * result + (driver != null ? driver.hashCode() : 0);
		result = 31 * result + (rentDate != null ? rentDate.hashCode() : 0);
		result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
		result = 31 * result + rentDays;
		result = 31 * result + tankPercent;
		temp = Double.doubleToLongBits(cost);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
