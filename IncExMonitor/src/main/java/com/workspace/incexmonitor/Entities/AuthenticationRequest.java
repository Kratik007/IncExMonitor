package com.workspace.incexmonitor.Entities;


public class AuthenticationRequest {
	public String username;
	public String password;
	public AuthenticationRequest() {
		super();
	}
	public AuthenticationRequest(String userName, String password) {
		super();
		this.username = userName;
		this.password = password;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
