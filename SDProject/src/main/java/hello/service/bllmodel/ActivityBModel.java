package hello.service.bllmodel;

import java.time.LocalDateTime;
import java.util.Set;

import hello.dao.dbModel.EmployeeDB;
import hello.dao.dbModel.ReservationDB;

public class ActivityBModel {
	
	private int id;
	private String name;
	private String location;
	private String description;
	private int availableSpots;
	private float price;
	private int maxPersons;
	private LocalDateTime startDate;
	private Set<ReservationDB> reservations;
	private EmployeeDB employee;
	
	public ActivityBModel() {}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getMaxPersons() {
		return maxPersons;
	}

	public void setMaxPersons(int maxPersons) {
		this.maxPersons = maxPersons;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public Set<ReservationDB> getReservations() {
		return reservations;
	}

	public void setReservations(Set<ReservationDB> reservations) {
		this.reservations = reservations;
	}

	public int getAvailableSpots() {
		return availableSpots;
	}

	public void setAvailableSpots(int availableSpots) {
		this.availableSpots = availableSpots;
	}

	public EmployeeDB getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDB employee) {
		this.employee = employee;
	}

}
