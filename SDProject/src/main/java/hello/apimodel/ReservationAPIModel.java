package hello.apimodel;

public class ReservationAPIModel {

	private int id;
	private float deposit;
	private String referenceNumber;
	private CustomerAPIModel customer;
	private ActivityAPIModel activity;
	
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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public CustomerAPIModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerAPIModel customer) {
		this.customer = customer;
	}

	public ActivityAPIModel getActivity() {
		return activity;
	}

	public void setActivity(ActivityAPIModel activity) {
		this.activity = activity;
	}
	
}
