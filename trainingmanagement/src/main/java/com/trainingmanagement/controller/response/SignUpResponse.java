package com.trainingmanagement.controller.response;

public class SignUpResponse{
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
	    
		public String message;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	    
	    public SignUpResponse(String username,String psd) {
			this.password=psd;
			this.username=username;
		}
	    
	    public SignUpResponse() {

	 	}
}
