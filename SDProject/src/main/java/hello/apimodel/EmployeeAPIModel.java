package hello.apimodel;

public class EmployeeAPIModel extends UserAPIModel {

	private String token;
	private String contractReference;
	
	public EmployeeAPIModel() {}

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
