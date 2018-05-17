package hello.service.bllmodel;

public class EmployeeBModel extends UserBModel {

	private String token;
	private String contractReference;
	
	public EmployeeBModel() {}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getContractReference() {
		return contractReference;
	}

	public void setContractReference(String contractReference) {
		this.contractReference = contractReference;
	}
	
	
	
}
