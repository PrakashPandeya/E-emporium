package model;

public class LoginModel {
	private int uid;
	private String username;
	private String password;
	private String role;
	
	public LoginModel(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public LoginModel(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role=role;
	}
	
	public LoginModel() {}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}