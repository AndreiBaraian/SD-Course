package hello.dao.dbModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")
public class ReservationDB {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "deposit")
	private float deposit;
	
	@Column(name = "reference_number")
	private String referenceNumber;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CustomerDB customer;
	
	@ManyToOne
	@JoinColumn(name = "activity_id")
	private ActivityDB activity;
	
	public ReservationDB() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getDeposit() {
		return deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	public CustomerDB getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDB customer) {
		this.customer = customer;
	}

	public ActivityDB getActivity() {
		return activity;
	}

	public void setActivity(ActivityDB activity) {
		this.activity = activity;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	
	
}
