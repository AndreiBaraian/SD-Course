package hello.apimodel;

import hello.service.bllmodel.ActivityBModel;
import hello.service.bllmodel.CustomerBModel;

public class ReservationAPIModel {

	private int id;
	private float deposit;
	private CustomerBModel customer;
	private ActivityBModel activity;
	
	public ReservationAPIModel() {}

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
