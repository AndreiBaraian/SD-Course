package hello.dao.dbModel;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("student")
public class StudentDB extends UserDB {

	@Column(name = "group_id")
	private String group;
	
	@Column(name = "hobby")
	private String hobby;
	
	@Column(name = "token")
	private String token;
	
	public StudentDB() {}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
}
