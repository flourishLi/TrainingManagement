package com.trainingmanagement.controller.response;

import java.util.List;

import com.trainingmanagement.model.User;

public class GetAllUsersResponse {

	public String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> trains) {
		this.users = trains;
	}

	public List<User> users;
	public GetAllUsersResponse() {

 	}
}
