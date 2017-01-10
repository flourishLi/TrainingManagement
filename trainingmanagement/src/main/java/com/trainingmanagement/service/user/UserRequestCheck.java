package com.trainingmanagement.service.user;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.trainingmanagement.controller.UserController;
import com.trainingmanagement.controller.request.SignInRequest;
import com.trainingmanagement.controller.request.SignUpRequest;
import com.trainingmanagement.model.User;
import com.trainingmanagement.service.util.ErrorCode;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Service
public  class UserRequestCheck {
	 @Autowired
	 UserInterface userInterface;
	 Logger log = Logger.getLogger(UserController.class);
	 
	 public String SignInCheck(HttpServletRequest request ){
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
				         return ErrorCode.SignInEmpty_Message;
				     }else {
					    return userInterface.SignIn(user.username, user.password);
				     }
				 }else{
					 return ErrorCode.SignInNull_Message;
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

     public String  SignUpCheck(HttpServletRequest request) {
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
					     return userInterface.SignUp(user);	
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
}
