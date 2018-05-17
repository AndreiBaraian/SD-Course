package hello.apimodel;

import java.util.Set;

import hello.dao.dbModel.UserDB;

public class ProfileAPIModel {

	private int id;
	private String name;
	private String allowedFunctions;
	private Set<UserDB> users;
	
	public ProfileAPIModel() {}

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
