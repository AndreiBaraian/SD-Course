package hello.service.bllmodel;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeBModel extends UserBModel {

	private String token;
	private String contractReference;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime contractExpiration;
	private float rating;
	
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

	public LocalDateTime getContractExpiration() {
		return contractExpiration;
	}

	public void setContractExpiration(LocalDateTime contractExpiration) {
		this.contractExpiration = contractExpiration;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "EmployeeBModel [token=" + token + ", contractReference=" + contractReference + ", contractExpiration="
				+ contractExpiration + ", rating=" + rating + "]";
	}
	
}
