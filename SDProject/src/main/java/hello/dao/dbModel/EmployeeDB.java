package hello.dao.dbModel;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@DiscriminatorValue("employee")
public class EmployeeDB extends UserDB {

	@Column(name = "token")
	private String token;
	
	@Column(name = "contract_reference")
	private String contractReference;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "contract_expiration")
	private LocalDateTime contractExpiration;
	
	@Column(name = "rating")
	private float rating;
	
	public EmployeeDB() {}

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
		return "EmployeeDB [token=" + token + ", contractReference=" + contractReference + ", contractExpiration="
				+ contractExpiration + ", rating=" + rating + "]";
	}

}
