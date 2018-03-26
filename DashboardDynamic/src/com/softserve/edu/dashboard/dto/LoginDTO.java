package com.softserve.edu.dashboard.dto;

public class LoginDTO {

	private String login;
	private String password;

	public LoginDTO(String login, String password) {
		this.login = login;
		this.password = password;
	}

	// setters
	
	protected void setLogin(String login) {
		this.login = login;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	// getters

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

}
