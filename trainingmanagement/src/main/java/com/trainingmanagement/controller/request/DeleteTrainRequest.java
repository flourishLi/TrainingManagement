package com.trainingmanagement.controller.request;

public class DeleteTrainRequest {

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getTrainid() {
		return trainid;
	}
	public void setTrainid(int trainid) {
		this.trainid = trainid;
	}
	public int userid;
	public int trainid;
	
	public DeleteTrainRequest(){
		
	}
}
