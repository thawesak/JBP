package com.th.jbp.security;

import java.io.Serializable;


public class WebRole implements Serializable{
	private static final long serialVersionUID = -8997235528288977577L;
	private String role;

	public WebRole() {
	}

	public WebRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
