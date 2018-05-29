package hello.apimodel;

public class CustomerAPIModel extends UserAPIModel {

	private int balance;
	//private Set<ReservationBModel> reservations;
	//private Set<ProductBModel> products;
	
	public CustomerAPIModel() {}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
