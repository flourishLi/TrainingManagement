package com.trainingmanagement.controller.response;

import java.sql.Timestamp;
public class Train {
	public int userid;
	public int trainid;
	public Timestamp establishdata;
	public String content;
	public int[] useridlist;
	public String trainname;
	public String trainlocation;
	
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

	public Timestamp getEstablishdata() {
		return establishdata;
	}

	public void setEstablishdata(Timestamp establishdata) {
		this.establishdata = establishdata;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int[] getUseridlist() {
		return useridlist;
	}

	public void setUseridlist(int[] useridlist) {
		this.useridlist = useridlist;
	}


	
	public String getTrainname() {
		return trainname;
	}

	public void setTrainname(String trainname) {
		this.trainname = trainname;
	}

	public String getTrainlocation() {
		return trainlocation;
	}

	public void setTrainlocation(String trainlocation) {
		this.trainlocation = trainlocation;
	}


	
	public Train(){
		
	}
}
