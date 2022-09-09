package Execise_2.entity;

public class Zip {
	private String id, city, state, zips;
	private int pop;
	private Location location;

	public Zip(String id, String city, String state, String zips, int pop, Location location) {
		super();
		this.id = id;
		this.city = city;
		this.state = state;
		this.zips = zips;
		this.pop = pop;
		this.location = location;
	}

	public Zip(String city, String state, String zips, int pop, Location location) {
		super();
		this.city = city;
		this.state = state;
		this.zips = zips;
		this.pop = pop;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZips() {
		return zips;
	}

	public void setZips(String zips) {
		this.zips = zips;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getPop() {
		return pop;
	}

	public void setPop(int pop) {
		this.pop = pop;
	}

	@Override
	public String toString() {
		return "Zip [id=" + id + ", city=" + city + ", state=" + state + ", zips=" + zips + ", pop=" + pop
				+ ", location=" + location + "]";
	}

}
