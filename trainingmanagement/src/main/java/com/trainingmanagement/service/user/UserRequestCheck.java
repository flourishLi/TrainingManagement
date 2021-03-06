package com.trainingmanagement.service.user;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainingmanagement.controller.UserController;
import com.trainingmanagement.controller.request.SignInRequest;
import com.trainingmanagement.controller.request.SignUpRequest;
import com.trainingmanagement.controller.response.GetAllUsersResponse;
import com.trainingmanagement.controller.response.SignInResponse;
import com.trainingmanagement.model.User;
import com.trainingmanagement.service.util.ErrorCode;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Service
public  class UserRequestCheck {
	 @Autowired
	 UserInterface userInterface;
	 Logger log = Logger.getLogger(UserController.class);
	 
	 public SignInResponse signInCheck(HttpServletRequest request ){
		 SignInResponse signInResponse=new SignInResponse();
		 if(request.getMethod()=="POST"){
			 StringBuffer stringBuffer=new StringBuffer();
			 InputStreamReader reader;
			try {
				reader = new InputStreamReader(request.getInputStream(),"UTF-8");
				 int end=0;
				 while((end= reader.read())!= -1){
					 stringBuffer.append((char)end);
				}
				 
				 log.info("request user string is"+stringBuffer.toString());
				 // request json转bean
				 JSONObject jsonObj=JSONObject.fromObject(stringBuffer.toString());
				 SignInRequest user=(SignInRequest)JSONObject.toBean(jsonObj, SignInRequest.class);
				 log.info("request user bean is"+user.username+user.password);
				 //用户名密码为空
				 if(user.username!=null&&user.password!=null){
				     if(user.username.length()==0||user.password.length()==0){
					     log.info("request user is null");
				         signInResponse.setMessage(ErrorCode.SignInEmpty_Message);
				     }else {
					    User userResult= userInterface.signIn(user.username, user.password);
					    if(userResult!=null){
					    signInResponse.setMessage(ErrorCode.SignInSuccess_Message);
					    signInResponse.setUserid(userResult.getUserid());
					    }else{
					    	signInResponse.setMessage(ErrorCode.SignInFail_Message);
					    }
				     }
				 }else{
					  signInResponse.setMessage(ErrorCode.SignInNull_Message);
				 }
				 
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				 signInResponse.setMessage(ErrorCode.InputStreamToUTFIsError_Message);
			} catch (IOException e) {
				e.printStackTrace();
				 signInResponse.setMessage(ErrorCode.InputStreamIsError_Message);
			}catch (JSONException e) {
				e.printStackTrace();
				 signInResponse.setMessage(ErrorCode.RequestToJsonIsError_Message);
			}
		 }
	 else{	 
		 signInResponse.setMessage(ErrorCode.RequestMethodError_Message);
	     }	
		 
		 return signInResponse;
	 	
	 } 

     public String  signUpCheck(HttpServletRequest request) {
    	 if(request.getMethod()=="POST"){
			 StringBuffer stringBuffer=new StringBuffer();
			 InputStreamReader reader;
			try {		
				//读取request
				reader = new InputStreamReader(request.getInputStream(),"UTF-8");
				 int end=0;
				 while((end= reader.read())!= -1){
					 stringBuffer.append((char)end);
				}
				 
				 log.info("request user string is"+stringBuffer.toString());
				 // request json转bean
				 JSONObject request_jsonObj=JSONObject.fromObject(stringBuffer.toString());
				 SignUpRequest userRequest=(SignUpRequest)JSONObject.toBean(request_jsonObj, SignUpRequest.class);
				 log.info("request user bean is"+userRequest.username+userRequest.password);
				
				 //用户名密码为空
				 if(userRequest.username!=null&&userRequest.password!=null){
				     if(userRequest.username.length()==0||userRequest.password.length()==0){
				         return ErrorCode.SignUpEmpty_Message;
				     }else {
					     User user=new User();
					     user.setUsername(userRequest.username);
					     user.setPassword(userRequest.password);
					     //注册
					     return userInterface.signUp(user);	
				    }
				 }else{
					 return ErrorCode.SignUpNull_Message;
				 }
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ErrorCode.InputStreamToUTFIsError_Message;
			} catch (IOException e) {
				e.printStackTrace();
				return ErrorCode.InputStreamIsError_Message;
			}catch (JSONException e) {
				e.printStackTrace();
				return ErrorCode.RequestToJsonIsError_Message;
			}	
		 }
	 else{
			return ErrorCode.RequestMethodError_Message;
	     }
}
     
     public String  getAllUsersCheck(HttpServletRequest request) {
    	 if(request.getMethod()=="POST"){
	        GetAllUsersResponse response=new GetAllUsersResponse();
	        List<User> users=userInterface.getAllUsers();       
	        response.setUsers(users);
	        
	        JSONObject response_jsonObj=JSONObject.fromObject(response);  
	        
	        return response_jsonObj.toString();
		 }
	 else{
			return ErrorCode.RequestMethodError_Message;
	     }
}


}
