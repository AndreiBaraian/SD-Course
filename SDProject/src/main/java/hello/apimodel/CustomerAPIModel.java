package hello.apimodel;

import java.util.Set;

import hello.service.bllmodel.ReservationBModel;

public class CustomerAPIModel extends UserAPIModel {

	private int balance;
	private Set<ReservationBModel> reservations;
	
	public CustomerAPIModel() {}

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
