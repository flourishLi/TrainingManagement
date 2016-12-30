package com.trainingmanagement.controller.response;

import java.util.List;

public class DisplayResponse {
	public String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    public List<Train> getTrains() {
		return trains;
	}
	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}

	public List<Train> trains;
	public DisplayResponse() {

 	}
}
