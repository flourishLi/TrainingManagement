package com.trainingmanagement.controller.response;

public class SignInResponse {
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int userid; 
	
	public String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public SignInResponse() {

 	}
}
