package com.trainingmanagement.controller.response;

import java.util.Date;

public class Train {
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

	public Date getEstablishdata() {
		return establishdata;
	}

	public void setEstablishdata(Date establishdata) {
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

	public int userid;
	public int trainid;
	public Date establishdata;
	public String content;
	public int[] useridlist;
	
	public Train(){
		
	}
}
