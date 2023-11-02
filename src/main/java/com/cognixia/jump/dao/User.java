package com.cognixia.jump.dao;

public class User {
	
	private int user_id;
	private String username;
	private String password;
	private String role;
	
    /**
     * When retrieving an Account from the database, all fields will be needed. In that case, a constructor with all
     * fields is needed.
     * @param user_id
     * @param username
     * @param password
     * @param role
     */
    public User(int user_id, String username, String password, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [id=" + user_id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
	
}
