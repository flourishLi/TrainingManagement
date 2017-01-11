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
	 @RequestMapping(value="/signin",produces ="application/json;charset=UTF-8")
	 public String SignIn(HttpServletRequest request,HttpServletResponse response){
		 log.info("========== signin controller begin==========");		 
		 //response
		 JSONObject response_jsonObj;	 
		 
		 //调用service
		 SignInResponse signInResponse=userService.SignInCheck(request);
		 
		 //result to json
		 response_jsonObj=JSONObject.fromObject(signInResponse);
		 
		 log.info("========== signin controller end==========");
		 return response_jsonObj.toString();		
	 }	 
	 
	 @ResponseBody
	 @RequestMapping(value="/signup",produces ="application/json;charset=UTF-8")
	 public String SignUp(HttpServletRequest request,HttpServletResponse response){
		 log.info("========== signup controller begin==========");
		 //response
		 SignUpResponse signUpResponse=new SignUpResponse();
		 JSONObject response_jsonObj;    
	     
		 //调用service
		 String signUpResult=userService.SignUpCheck(request);
	     
		 //result to json
		 signUpResponse.setMessage(signUpResult);
		 response_jsonObj=JSONObject.fromObject(signUpResponse);
		
	 	 
		 log.info("========== signup controller end==========");
		 return response_jsonObj.toString();
	 }
	 



}