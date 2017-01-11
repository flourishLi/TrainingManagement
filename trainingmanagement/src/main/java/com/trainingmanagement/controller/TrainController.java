package com.trainingmanagement.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trainingmanagement.controller.response.DeleteTrainResponse;
import com.trainingmanagement.controller.response.DisplayTrainResponse;
import com.trainingmanagement.controller.response.ModifyResponse;
import com.trainingmanagement.controller.response.SponsorResponse;
import com.trainingmanagement.controller.response.Train;
import com.trainingmanagement.model.TrainWithBLOBs;
import com.trainingmanagement.service.train.TrainInterface;
import com.trainingmanagement.service.train.TrainRequestCheck;
import com.trainingmanagement.service.util.ByteConvertion;
import com.trainingmanagement.service.util.ErrorCode;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/train")
public class TrainController {

	 @Autowired
	 TrainRequestCheck trainService;
	 @Autowired
	 TrainInterface trainIn;
	 Logger log = Logger.getLogger(UserController.class);
	 
	 @ResponseBody
	 @RequestMapping(value="/sponsor",produces ="application/json;charset=UTF-8")
	 public String Sponsor(HttpServletRequest request,HttpServletResponse response){
		 log.info("==========begin sponsor controller==========");
		 
		 //response
		 SponsorResponse sponsorResponse=new SponsorResponse();
		 JSONObject response_jsonObj;

	     
		 //调用service
		 String signUpResult=trainService.SponorCheck(request);
	     
		 //result to json
		 sponsorResponse.setMessage(signUpResult);
		 response_jsonObj=JSONObject.fromObject(sponsorResponse);
		
	 	 
		 log.info("==========end sponsor controller==========");
		 return response_jsonObj.toString();

	 	 

	 }	 
	 
	 @ResponseBody
	 @RequestMapping(value="/modify",produces ="application/json;charset=UTF-8")
	 public String Modify(HttpServletRequest request,HttpServletResponse response){
		 log.info("==========begin modify controller==========");
		 
		 //response
		 ModifyResponse modifyResponse=new ModifyResponse();
		 JSONObject response_jsonObj;

	     
		 //调用service
		 String signUpResult=trainService.ModifyTrainCheck(request);
	     
		 //result to json
		 modifyResponse.setMessage(signUpResult);
		 response_jsonObj=JSONObject.fromObject(modifyResponse);
		
	 	 
		 log.info("==========end modify controller==========");
		 return response_jsonObj.toString();

	 	 

	 }	 
	 

	 @ResponseBody
	 @RequestMapping(value="/delete",produces ="application/json;charset=UTF-8")
	 public String DeleteTrain(HttpServletRequest request,HttpServletResponse response){
		 log.info("==========begin DeleteTrain controller==========");
		 
		 //response
		 DeleteTrainResponse deleteResponse=new DeleteTrainResponse();
		 JSONObject response_jsonObj;

	     
		 //调用service
		 String deleteResult=trainService.DeleteTrainCheck(request);
	     
		 //result to json
		 deleteResponse.setMessage(deleteResult);
		 response_jsonObj=JSONObject.fromObject(deleteResponse);
		
	 	 
		 log.info("==========end DeleteTrain controller==========");
		 return response_jsonObj.toString(); 	 
	 }	 
	 
	 @ResponseBody
	 @RequestMapping(value="/display",produces ="application/json;charset=UTF-8")
	 public String displayTrains(HttpServletRequest request,HttpServletResponse response){
		 log.info("==========begin displayTrains controller==========");		 
		 //response
		 JSONObject response_jsonObj;	     
		 //调用service
		 DisplayTrainResponse displayTrainResponse=trainService.displayTrainCheck(request);
	     
		 //result to json
		 response_jsonObj=JSONObject.fromObject(displayTrainResponse);
			 
		 log.info("==========end displayTrains controller==========");
		 return response_jsonObj.toString(); 	 
	 }	 
	 
	 
	 @ResponseBody
	 @RequestMapping(value="/getAll",produces ="application/json;charset=UTF-8")
	 public String GetAllTrains(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 log.info("==========begin DisplayTrain controller==========");
		 
		 //response
		 DisplayTrainResponse dispalyResponse=new DisplayTrainResponse();
		 JSONObject response_jsonObj = null;
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
				 
				 log.info("request sponsor string is"+stringBuffer.toString());
					
			     List<TrainWithBLOBs> trains=trainIn.GetAllTrains();
			     List<Train> strTrains=new LinkedList<Train>();
			     
			     for (TrainWithBLOBs trainWithBLOBs : trains) {
					  String strContent=new String(trainWithBLOBs.getTraincontent());
					  int [] useridlist =ByteConvertion.ByteToIntArray(trainWithBLOBs.getUserlist());
					 
					  Train train=new Train();
					  train.userid=trainWithBLOBs.getUserid();
					  train.useridlist=useridlist;
					  train.content=strContent;
					  train.trainid=trainWithBLOBs.getTrainid();
					  train.establishdata=trainWithBLOBs.getData();
					  
					  strTrains.add(train);								  
				  }		     
			     dispalyResponse.setTrains(strTrains);			
			    }
				 catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ErrorCode.InputStreamToUTFIsError_Message;
			} catch (IOException e) {
				e.printStackTrace();
				return ErrorCode.InputStreamIsError_Message;
			}catch (JSONException e) {	
				e.printStackTrace();
				return ErrorCode.RequestToJsonIsError_Message;
			}		
		 }else{		        
			   return ErrorCode.RequestMethodError_Message;	        
	     }	
		 log.info("==========end DisplayTrain controller==========");
		 //返回客户端
		 response_jsonObj=JSONObject.fromObject(dispalyResponse);	  
		 return response_jsonObj.toString();
		
	 }	 
	 


	 
	 
}
