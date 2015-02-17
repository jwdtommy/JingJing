package com.jwd.model;

public class LoginBean {
	private boolean isSuccess;
	private String username;
	private String tocken;

	public LoginBean(boolean isSuccess, String username, String tocken) {
		this.isSuccess = isSuccess;
		this.username = username;
		this.tocken = tocken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTocken() {
		return tocken;
	}

	public void setTocken(String tocken) {
		this.tocken = tocken;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
