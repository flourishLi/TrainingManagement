package com.trainingmanagement.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trainingmanagement.controller.request.SignInRequest;
import com.trainingmanagement.controller.request.SignUpRequest;
import com.trainingmanagement.controller.response.SignInResponse;
import com.trainingmanagement.controller.response.SignUpResponse;
import com.trainingmanagement.model.User;
import com.trainingmanagement.service.user.UserInterface;
import com.trainingmanagement.service.util.ErrorCode;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
	 @Autowired
	 UserInterface userService;
	 Logger log = Logger.getLogger(UserController.class);
	 
	 @RequestMapping("/signin")
	 public void SignIn(HttpServletRequest request,HttpServletResponse response){
		 log.info("==========begin signin controller==========");
		 
		 //response
		 SignInResponse signInResponse=new SignInResponse();
		 JSONObject response_jsonObj;
	     response.setCharacterEncoding("UTF-8");
	     response.setContentType("application/json");     

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
				 if(user.username.length()==0||user.password.length()==0){
					 log.info("request user is null");
					 signInResponse.setMessage(ErrorCode.SignInEmpty_Message);
					 response_jsonObj=JSONObject.fromObject(signInResponse);
				     response.getWriter().write(response_jsonObj.toString());
				 }else {
					//登陆
					int no=userService.SignIn(user.username,user.password);
					switch (no) {
					case ErrorCode.Success:
						signInResponse.setMessage(ErrorCode.SignInSuccess_Message);
					    response_jsonObj=JSONObject.fromObject(signInResponse);
						response.getWriter().write(response_jsonObj.toString());
						break;
					case ErrorCode.SignInFail:
						signInResponse.setMessage(ErrorCode.SignInFail_Message);
					    response_jsonObj=JSONObject.fromObject(signInResponse);
						response.getWriter().write(response_jsonObj.toString());
						break;
					case ErrorCode.UserNotExist:
						signInResponse.setMessage(ErrorCode.UserNotExist_Message);
					    response_jsonObj=JSONObject.fromObject(signInResponse);
						response.getWriter().write(response_jsonObj.toString());
						break;
						default :
					    signInResponse.setMessage("未知错误");
						response_jsonObj=JSONObject.fromObject(signInResponse);
					    response.getWriter().write(response_jsonObj.toString());
						break;
						
					}
				}
				 
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		 }
	 else{
		  try {
			    signInResponse.setMessage(ErrorCode.RequestMethodError_Message);
			    response_jsonObj=JSONObject.fromObject(signInResponse);
				response.getWriter().write(response_jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }		 	 
		 log.info("==========end signin controller==========");
	 }	 
	 
	 
	 @RequestMapping("/signup")
	 public void SignUp(HttpServletRequest request,HttpServletResponse response){
		 log.info("==========begin signup controller==========");
		 //response
		 SignUpResponse signUpResponse=new SignUpResponse();
		 JSONObject response_jsonObj;
	     response.setCharacterEncoding("UTF-8");
	     response.setContentType("application/json");     
	     
	     
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
				 if(userRequest.username.length()==0||userRequest.password.length()==0){
					 signUpResponse.setMessage(ErrorCode.SignUpEmpty_Message);
					 response_jsonObj=JSONObject.fromObject(signUpResponse);
				     response.getWriter().write(response_jsonObj.toString());
				 }else {
					 User user=new User();
					 user.setUsername(userRequest.username);
					 user.setPassword(userRequest.password);
					 //注册
					int no=userService.SignUp(user);				 
					switch (no) {
					case ErrorCode.Success:
						signUpResponse.setMessage(ErrorCode.SignUpSuccess_Message);
						signUpResponse.setUsername(userRequest.username);
						signUpResponse.setPassword(userRequest.password);
					    response_jsonObj=JSONObject.fromObject(signUpResponse);
						response.getWriter().write(response_jsonObj.toString());
						break;
					case ErrorCode.SignUpFail:
						signUpResponse.setMessage(ErrorCode.SignUpFail_Message);
					    response_jsonObj=JSONObject.fromObject(signUpResponse);
						response.getWriter().write(response_jsonObj.toString());
						break;
					default :
						signUpResponse.setMessage("未知错误");
						response_jsonObj=JSONObject.fromObject(signUpResponse);
					    response.getWriter().write(response_jsonObj.toString());
						break;
					}
				}
				 
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		 }
	 else{
		  try {
			     signUpResponse.setMessage(ErrorCode.RequestMethodError_Message);
				 response_jsonObj=JSONObject.fromObject(signUpResponse);
			     response.getWriter().write(response_jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }	 	 
		 log.info("==========end signup controller==========");
	 }
	 



}