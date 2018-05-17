package hello.service.bllmodel;

import java.util.Set;

public class CustomerBModel extends UserBModel {
	
	private int balance;
	private Set<ReservationBModel> reservations;
	
	public CustomerBModel() {}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Set<ReservationBModel> getReservations() {
		return reservations;
	}

	public void setReservations(Set<ReservationBModel> reservations) {
		this.reservations = reservations;
	}

}
