package com.trainingmanagement.controller.request;


public class SignInRequest  {
    public String username;
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

	public String password;
    
    
    public SignInRequest(String username,String psd) {
		this.password=psd;
		this.username=username;
	}
    
    public SignInRequest() {

 	}
}
