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

import com.trainingmanagement.controller.request.DispalyTrainRequest;

import com.trainingmanagement.controller.request.SponsorRequest;
import com.trainingmanagement.controller.response.DisplayResponse;

import com.trainingmanagement.controller.response.SponsorResponse;
import com.trainingmanagement.controller.response.Train;
import com.trainingmanagement.model.TrainWithBLOBs;
import com.trainingmanagement.service.train.TrainInterface;
import com.trainingmanagement.service.util.ByteConvertion;
import com.trainingmanagement.service.util.ErrorCode;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/train")
public class TrainController {

	 @Autowired
	 TrainInterface trainService;
	 Logger log = Logger.getLogger(UserController.class);
	 
	 @RequestMapping("/sponsor")
	 public void SignIn(HttpServletRequest request,HttpServletResponse response){
		 log.info("==========begin sponsor controller==========");
		 
		 //response
		 SponsorResponse sponsorResponse=new SponsorResponse();
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
				 
				 log.info("request sponsor string is"+stringBuffer.toString());
				 // request json转bean
				 JSONObject jsonObj=JSONObject.fromObject(stringBuffer.toString());
				 SponsorRequest sponsorRequest=(SponsorRequest)JSONObject.toBean(jsonObj, SponsorRequest.class);
				 log.info("sponsorRequest bean is"+sponsorRequest.userid+sponsorRequest.content+sponsorRequest.useridlist);
				 //发起者为空
				 if(sponsorRequest.userid==0){
					 log.info("sponsorRequest user is null");
					 sponsorResponse.setMessage(ErrorCode.SponsorUserIsNull_Message);
					 response_jsonObj=JSONObject.fromObject(sponsorResponse);
				     response.getWriter().write(response_jsonObj.toString());
				 }else {
					//发布培训
					byte[] byte_content = sponsorRequest.content.getBytes();
					byte[] byte_userlist = new byte[0];
					for (int element : sponsorRequest.useridlist) {
						byte[] byte_userid=ByteConvertion.int2Byte(element);
						byte_userlist=ByteConvertion.byteMerger(byte_userlist,byte_userid);
					}
					int no=trainService.Sponsor(sponsorRequest.userid, byte_userlist,byte_content);
					if(no==ErrorCode.Success){
						sponsorResponse.setMessage(ErrorCode.SponsorSuccess_Message);
					    response_jsonObj=JSONObject.fromObject(sponsorResponse);
						response.getWriter().write(response_jsonObj.toString());	
				    }else{
					sponsorResponse.setMessage(ErrorCode.SponsorFail_Message);
				    response_jsonObj=JSONObject.fromObject(sponsorResponse);
					response.getWriter().write(response_jsonObj.toString());	
				    }
				 } 
			}
				 catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (JSONException e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
			
		 }else{
		  try {
			    sponsorResponse.setMessage(ErrorCode.RequestMethodError_Message);
			    response_jsonObj=JSONObject.fromObject(sponsorResponse);
				response.getWriter().write(response_jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }		 	 
		 log.info("==========end sponsor controller==========");
	 }	 
	 

	 
	 @RequestMapping("/display")
	 public void DisplayTrain(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 log.info("==========begin DisplayTrain controller==========");
		 
		 //response
		 DisplayResponse dispalyResponse=new DisplayResponse();
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
				 // request json转bean
				 JSONObject jsonObj=JSONObject.fromObject(stringBuffer.toString());
				 DispalyTrainRequest sponsorRequest=(DispalyTrainRequest)JSONObject.toBean(jsonObj, DispalyTrainRequest.class);
				 log.info("sponsorRequest bean is");
					
			     List<TrainWithBLOBs> trains=trainService.GetAllTrains();
			     List<Train> strTrains=new LinkedList<Train>();
			     
			     for (TrainWithBLOBs trainWithBLOBs : trains) {
					  String strContent=new String(trainWithBLOBs.getTraincontent());
					  int [] useridlist =ByteConvertion.ByteToIntArray(trainWithBLOBs.getUserlist());
					 
					  Train train=new Train();
					  train.userid=trainWithBLOBs.getUserid();
					  train.useridlist=useridlist;
					  train.content=strContent;
					  
					  strTrains.add(train);								  
				  }
			     //JSONArray jsonList = JSONArray.fromObject(strTrains);			     
			     dispalyResponse.setTrains(strTrains);			
			    }
				 catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
		        dispalyResponse.setMessage("code is err");		
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (JSONException e) {
				// TODO: handle exception
				dispalyResponse.setMessage(ErrorCode.RequestToJsonIsError_Message);		
				e.printStackTrace();
			}		
		 }else{		        
			    dispalyResponse.setMessage(ErrorCode.RequestMethodError_Message);	        
	     }	
		 
		 //返回客户端
		 response_jsonObj=JSONObject.fromObject(dispalyResponse);	  
		 response.getWriter().write(response_jsonObj.toString());
		 log.info("==========end DisplayTrain controller==========");
	 }	 
	 


	 
	 
}
