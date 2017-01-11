package com.trainingmanagement.controller.request;


public class SponsorRequest {

	public int userid;
   
	public String content;
	public int[] useridlist;
	public long time;
	public String trainname;
	public String trainlocation;
	
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






	
	    public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
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


		
		public long getTime() {
			return time;
		}


		public void setTime(long time) {
			this.time = time;
		}



	    
	    
	    public SponsorRequest() {

	 	}
}
