package com.shopping.kart.users.model;

import javax.validation.constraints.NotNull;

public class User {
	
	@NotNull(message = "Firstname is Empty")
	private String firstName;
	
	@NotNull(message = "LastName is Empty")
	private String lastName;
	
	@NotNull(message = "password is Empty")
	private String password;
	
	@NotNull(message = "email is Empty")
	private String email;
	
	private String userId;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	

}
