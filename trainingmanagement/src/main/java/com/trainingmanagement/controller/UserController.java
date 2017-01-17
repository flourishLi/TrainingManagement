package com.trainingmanagement.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trainingmanagement.controller.response.SignInResponse;
import com.trainingmanagement.controller.response.SignUpResponse;
import com.trainingmanagement.service.user.UserRequestCheck;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
	 @Autowired
	 UserRequestCheck userService;
	 Logger log = Logger.getLogger(UserController.class);
	 
	 @ResponseBody
	 @RequestMapping(value="/signin")
	 public String signIn(HttpServletRequest request,HttpServletResponse response){
		 log.info("========== signin controller begin==========");		 
		 //response
		 JSONObject response_jsonObj;	 
		 
		 //调用service
		 SignInResponse signInResponse=userService.signInCheck(request);
		 
		 //result to json
		 response_jsonObj=JSONObject.fromObject(signInResponse);
		 
		 log.info(response_jsonObj.toString());
		 log.info("========== signin controller end==========");
		 return response_jsonObj.toString();		
	 }	 
	 
	 @ResponseBody
	 @RequestMapping(value="/signup")
	 public String signUp(HttpServletRequest request,HttpServletResponse response){
		 //response.setHeader("Access-Control-Allow-Origin", "*");
		 log.info("========== signup controller begin==========");
		 //response
		 SignUpResponse signUpResponse=new SignUpResponse();
		 JSONObject response_jsonObj;    
	     
		 //调用service
		 String signUpResult=userService.signUpCheck(request);
	     
		 //result to json
		 signUpResponse.setMessage(signUpResult);
		 response_jsonObj=JSONObject.fromObject(signUpResponse);
		
		 log.info(response_jsonObj.toString());
		 log.info("========== signup controller end==========");
		 return response_jsonObj.toString();
	 }
	 
	 
	 @ResponseBody
	 @RequestMapping(value="/getusers")
	 public String getAllUsers(HttpServletRequest request,HttpServletResponse response){
		 //response.setHeader("Access-Control-Allow-Origin", "*");
		 log.info("========== getAllUsers controller begin==========");
		
	     
		 //调用service
		 String usersResult=userService.getAllUsersCheck(request);
	     
		
		 log.info(usersResult);
		 log.info("========== getAllUsers controller end==========");
		 return usersResult;
	 }
	 



}