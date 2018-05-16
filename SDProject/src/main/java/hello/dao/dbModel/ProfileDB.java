package hello.dao.dbModel;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "profiles")
public class ProfileDB {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "menu_functions")
	private String allowedFunctions;
	
	@JsonBackReference
	@OneToMany(mappedBy="profile")
	private Set<UserDB> users;
	
	public ProfileDB() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAllowedFunctions() {
		return allowedFunctions;
	}

	public void setAllowedFunctions(String allowedFunctions) {
		this.allowedFunctions = allowedFunctions;
	}

	public Set<UserDB> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDB> users) {
		this.users = users;
	}

}
