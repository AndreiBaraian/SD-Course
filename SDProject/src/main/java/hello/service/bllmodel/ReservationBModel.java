package hello.service.bllmodel;

public class ReservationBModel {
	
	private int id;
	private float deposit;
	private CustomerBModel customer;
	private ActivityBModel activity;
	
	public ReservationBModel() {}

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

	public CustomerBModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBModel customer) {
		this.customer = customer;
	}

	public ActivityBModel getActivity() {
		return activity;
	}

	public void setActivity(ActivityBModel activity) {
		this.activity = activity;
	}

}
