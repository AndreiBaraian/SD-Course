package hello.service.bllmodel;

import hello.dao.dbModel.Role;

public class UserBModel {
	
	private int id;
	private String name;
	private String username;
	private String email;
	private String password;
	private boolean isPasswordSet;
	private Role role;
	private ProfileBModel profile;
	
	public UserBModel() {}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPasswordSet() {
		return isPasswordSet;
	}

	public void setPasswordSet(boolean isPasswordSet) {
		this.isPasswordSet = isPasswordSet;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ProfileBModel getProfile() {
		return profile;
	}

	public void setProfile(ProfileBModel profile) {
		this.profile = profile;
	}

}
