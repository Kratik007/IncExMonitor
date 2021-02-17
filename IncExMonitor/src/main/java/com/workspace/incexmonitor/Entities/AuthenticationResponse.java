package com.workspace.incexmonitor.Entities;

public class AuthenticationResponse {
	public String jwt;
	public String userid;
	public String name;
	
	public AuthenticationResponse(String jwt, String userid, String name) {
		super();
		this.jwt = jwt;
		this.userid = userid;
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public String getName() {
		return name;
	}

	public String getJwt() {
		return jwt;
	}

}
