package com.trainingmanagement.service.train;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainingmanagement.controller.UserController;
import com.trainingmanagement.controller.request.DeleteTrainRequest;
import com.trainingmanagement.controller.request.DispalyTrainRequest;
import com.trainingmanagement.controller.request.FuzzySearchRequest;
import com.trainingmanagement.controller.request.ModifyTrainRequest;
import com.trainingmanagement.controller.request.SponsorRequest;
import com.trainingmanagement.controller.response.DisplayTrainResponse;
import com.trainingmanagement.controller.response.FuzzySearchResponse;
import com.trainingmanagement.model.TrainWithBLOBs;
import com.trainingmanagement.service.util.ByteConvertion;
import com.trainingmanagement.service.util.ErrorCode;

import javafx.scene.shape.TriangleMesh;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Service
public class TrainRequestCheck {
	 @Autowired
	 TrainInterface trainService;
	 Logger log = Logger.getLogger(UserController.class);
	
	public String sponorCheck(HttpServletRequest request) {
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
					 return ErrorCode.SponsorUserIsNull_Message;
				 }else {
					//发布培训
					TrainWithBLOBs train =new TrainWithBLOBs();	
					train.setUserid(sponsorRequest.userid);
				    if(sponsorRequest.content!=null){
				    	train.setTraincontent(sponsorRequest.content.getBytes());
					 }
				    if(sponsorRequest.useridlist!=null){
				    	byte[] byte_userlist = new byte[0];
					    for (int element : sponsorRequest.useridlist) {
						    byte[] byte_userid=ByteConvertion.int2Byte(element);
						    byte_userlist=ByteConvertion.byteMerger(byte_userlist,byte_userid);
					    }
					    train.setUserlist(byte_userlist);
				    }
				    if(sponsorRequest.trainname!=null){
				    	train.setTrainname(sponsorRequest.trainname);
				    }
				    if(sponsorRequest.trainlocation!=null){
				        train.setTrainlocation(sponsorRequest.trainlocation);	
				    } 
				    if(sponsorRequest.time!=0){
				    	train.setData(new Timestamp(sponsorRequest.time));
				    }
					return trainService.sponsor(train);			
				 }
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
	}

	public String modifyTrainCheck(HttpServletRequest request) {
		 if(request.getMethod()=="POST"){
			 StringBuffer stringBuffer=new StringBuffer();
			 InputStreamReader reader;
			try {
				reader = new InputStreamReader(request.getInputStream(),"UTF-8");
				 int end=0;
				 while((end= reader.read())!= -1){
					 stringBuffer.append((char)end);
				}
				 
				 log.info("modifyTrainRequest string is"+stringBuffer.toString());
				 // request json转bean
				 JSONObject jsonObj=JSONObject.fromObject(stringBuffer.toString());
				 ModifyTrainRequest modifyTrainRequest=(ModifyTrainRequest)JSONObject.toBean(jsonObj, ModifyTrainRequest.class);
				 log.info("modifyTrainRequest bean is"+modifyTrainRequest.userid+modifyTrainRequest.content+modifyTrainRequest.useridlist+modifyTrainRequest.time);
				 //发起者或者培训编号为空
				 if(modifyTrainRequest.userid==0||modifyTrainRequest.trainid==0){
					 log.info("modifyTrainRequest userid or trainid is null");
					 return ErrorCode.SponsorUserOrTrainIsEmpty_Message;
				 }else {
					//查询修改者是否有权限
				 TrainWithBLOBs train=trainService.selectByUserIDAndTrainID(modifyTrainRequest.userid, modifyTrainRequest.trainid);
				 if(train!=null){
					 //设置培训内容
					 if(modifyTrainRequest.content!=null){
					     train.setTraincontent( modifyTrainRequest.content.getBytes());
					 }
					 //设置用户列表
					 if(modifyTrainRequest.useridlist!=null){
						 byte[] byte_userlist = new byte[0];
					     for (int element : modifyTrainRequest.useridlist) {
						    byte[] byte_userid=ByteConvertion.int2Byte(element);
						    byte_userlist=ByteConvertion.byteMerger(byte_userlist,byte_userid);
					     }		
					 train.setUserlist(byte_userlist);
					 }
					 //设置培训时间
					 if(modifyTrainRequest.time!=0){
						  train.setData( new Timestamp(modifyTrainRequest.time));
					 }
					 //设置培训名称
					 if(modifyTrainRequest.trainname!=null){
						  train.setTrainname(modifyTrainRequest.trainname);
					 }
					 //设置培训地点
					 if(modifyTrainRequest.time!=0){
						  train.setTrainlocation(modifyTrainRequest.trainlocation);
					 }
					 //修改
					 return trainService.modifyTrain(train);
				 }else{//匹配失败 不具有修改权限
					    return ErrorCode.SearchTrainFailByUserIDAndTrainID_Message;
				     }
				 }
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
	}
	
	public String deleteTrainCheck(HttpServletRequest request) {
		 if(request.getMethod()=="POST"){
			 StringBuffer stringBuffer=new StringBuffer();
			 InputStreamReader reader;
			try {
				reader = new InputStreamReader(request.getInputStream(),"UTF-8");
				 int end=0;
				 while((end= reader.read())!= -1){
					 stringBuffer.append((char)end);
				}
				 
				 log.info("deleteTrainRequest string is"+stringBuffer.toString());
				 // request json转bean
				 JSONObject jsonObj=JSONObject.fromObject(stringBuffer.toString());
				 DeleteTrainRequest deleteTrainRequest=(DeleteTrainRequest)JSONObject.toBean(jsonObj, DeleteTrainRequest.class);
				 log.info("deleteTrainRequest bean is"+deleteTrainRequest.userid+deleteTrainRequest.trainid);
				 //发起者或者培训编号为空
				 if(deleteTrainRequest.userid==0||deleteTrainRequest.trainid==0){
					 log.info("deleteTrainRequest userid or trainid is null");
					 return ErrorCode.DeleteUserOrTrainIsEmpty_Message;
				 }else {
					 //删除培训
					 return trainService.deleteTrain(deleteTrainRequest.userid,deleteTrainRequest.trainid);
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
		 }else{
			return ErrorCode.RequestMethodError_Message;
	    }	
	}
	
	public DisplayTrainResponse displayTrainCheck(HttpServletRequest request) {
		 DisplayTrainResponse displayTrainResponse=new DisplayTrainResponse();
		 if(request.getMethod()=="POST"){
			 StringBuffer stringBuffer=new StringBuffer();
			 InputStreamReader reader;
			try {
				reader = new InputStreamReader(request.getInputStream(),"UTF-8");
				 int end=0;
				 while((end= reader.read())!= -1){
					 stringBuffer.append((char)end);
				}
				 
				 log.info("deleteTrainRequest string is"+stringBuffer.toString());
				 // request json转bean
				 JSONObject jsonObj=JSONObject.fromObject(stringBuffer.toString());
				 DispalyTrainRequest dispalyTrainRequest=(DispalyTrainRequest)JSONObject.toBean(jsonObj, DispalyTrainRequest.class);
				 log.info("DispalyTrainRequest bean is"+dispalyTrainRequest.userid);
				 //用户编号为空
				 if(dispalyTrainRequest.userid==0){
					 log.info("dispalyTrainRequest userid is null");
					 displayTrainResponse.setMessage(ErrorCode.DisplayTrain_UserId_IsNull_Message);
				 }else {
					 //参数检查正确
					 displayTrainResponse.setMessage(ErrorCode.DisplayTrain_CheckPassed_Message);
					 displayTrainResponse.setTrains(trainService.getTrainsByUserId(dispalyTrainRequest.userid));
					
				 }
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				 displayTrainResponse.setMessage(ErrorCode.InputStreamToUTFIsError_Message);
			} catch (IOException e) {
				e.printStackTrace();
			    displayTrainResponse.setMessage(ErrorCode.InputStreamIsError_Message);
			}catch (JSONException e) {
				e.printStackTrace();
				 displayTrainResponse.setMessage(ErrorCode.RequestToJsonIsError_Message);
			}		
		 }else{
			 displayTrainResponse.setMessage( ErrorCode.RequestMethodError_Message);
	    }
		 return displayTrainResponse;
	}

	
	
	public FuzzySearchResponse fuzzySearchCheck(HttpServletRequest request) {
		FuzzySearchResponse fuzzySearchResponse=new FuzzySearchResponse();
		 if(request.getMethod()=="POST"){
			 StringBuffer stringBuffer=new StringBuffer();
			 InputStreamReader reader;
			try {
				reader = new InputStreamReader(request.getInputStream(),"UTF-8");
				 int end=0;
				 while((end= reader.read())!= -1){
					 stringBuffer.append((char)end);
				}
				 
				 log.info("fuzzySearchRequest string is"+stringBuffer.toString());
				 // request json转bean
				 JSONObject jsonObj=JSONObject.fromObject(stringBuffer.toString());
				 FuzzySearchRequest fuzzySearchRequest=(FuzzySearchRequest)JSONObject.toBean(jsonObj, FuzzySearchRequest.class);
				 log.info("fuzzySearchRequest bean is"+fuzzySearchRequest.trainname);
				 //培训名称为空
				 if(fuzzySearchRequest.trainname==null){
					 log.info("fuzzySearchRequest trainname is null");
					 fuzzySearchResponse.setMessage(ErrorCode.FuzzySearchTrain_TrainNameIsNull_Message);
				 }else {
					 //模糊查询
					 if(trainService.fuzzySearch(fuzzySearchRequest.trainname).size()==0){
						 fuzzySearchResponse.setMessage(ErrorCode.FuzzySearchTrain_TrainNameNotMatch_Message);
					 }
					 fuzzySearchResponse.setTrains(trainService.fuzzySearch(fuzzySearchRequest.trainname));
				 }
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				fuzzySearchResponse.setMessage(ErrorCode.InputStreamToUTFIsError_Message);
			} catch (IOException e) {
				e.printStackTrace();
				fuzzySearchResponse.setMessage(ErrorCode.InputStreamIsError_Message);
			}catch (JSONException e) {
				e.printStackTrace();
				fuzzySearchResponse.setMessage(ErrorCode.RequestToJsonIsError_Message);
			}		
		 }else{
			 fuzzySearchResponse.setMessage( ErrorCode.RequestMethodError_Message);
	    }
		 return fuzzySearchResponse;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
