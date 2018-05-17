package hello.dao.dbModel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DiscriminatorValue("customer")
public class CustomerDB extends UserDB {
	
	@Column(name = "balance")
	private int balance;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="customer")
	private Set<ReservationDB> reservations;

	public CustomerDB() {}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public Set<ReservationDB> getReservations() {
		return reservations;
	}

	public void setReservations(Set<ReservationDB> reservations) {
		this.reservations = reservations;
	}
	
}
