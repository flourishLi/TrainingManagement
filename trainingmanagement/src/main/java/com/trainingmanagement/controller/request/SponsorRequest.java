package com.trainingmanagement.controller.request;

public class SponsorRequest {
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


		public int userid;
	   
		public String content;
		public int[] useridlist;
	    
	    
	    public SponsorRequest() {

	 	}
}
