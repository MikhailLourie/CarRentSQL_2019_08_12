package dto;

public class Driver {
	
	private int id;
	private String name;
	public Driver() {
		super();
	}
	public Driver(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Driver)) return false;

		Driver driver = (Driver) o;

		if (id != driver.id) return false;
		return name != null ? name.equals(driver.name) : driver.name == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
