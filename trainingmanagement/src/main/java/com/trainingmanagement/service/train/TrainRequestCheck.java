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
import com.trainingmanagement.controller.request.ModifyTrainRequest;
import com.trainingmanagement.controller.request.SponsorRequest;
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
	
	public String SponorCheck(HttpServletRequest request) {
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
					byte[] byte_content = sponsorRequest.content.getBytes();
					byte[] byte_userlist = new byte[0];
					for (int element : sponsorRequest.useridlist) {
						byte[] byte_userid=ByteConvertion.int2Byte(element);
						byte_userlist=ByteConvertion.byteMerger(byte_userlist,byte_userid);
					}
					return trainService.Sponsor(sponsorRequest.userid, byte_userlist,byte_content,new Timestamp(sponsorRequest.time));			
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

	public String ModifyTrainCheck(HttpServletRequest request) {
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
				 ModifyTrainRequest modifyTrainRequest=(ModifyTrainRequest)JSONObject.toBean(jsonObj, ModifyTrainRequest.class);
				 log.info("sponsorRequest bean is"+modifyTrainRequest.userid+modifyTrainRequest.content+modifyTrainRequest.useridlist+modifyTrainRequest.time);
				 //发起者或者培训编号为空
				 if(modifyTrainRequest.userid==0||modifyTrainRequest.trainid==0){
					 log.info("sponsorRequest userid or trainid is null");
					 return ErrorCode.SponsorUserOrTrainIsEmpty_Message;
				 }else {
					//查询修改者是否有权限
				 TrainWithBLOBs train=trainService.SelectByUserIDAndTrainID(modifyTrainRequest.userid, modifyTrainRequest.trainid);
				 if(train!=null){
					byte[] byte_content = modifyTrainRequest.content.getBytes();
					byte[] byte_userlist = new byte[0];
					for (int element : modifyTrainRequest.useridlist) {
						byte[] byte_userid=ByteConvertion.int2Byte(element);
						byte_userlist=ByteConvertion.byteMerger(byte_userlist,byte_userid);
					}
					train.setTraincontent(byte_content);
					train.setUserlist(byte_userlist);
			        train.setData( new Timestamp(modifyTrainRequest.time));
					
					return trainService.ModifyTrain(train.getUserid(), train.getTrainid(),train.getTraincontent(), train.getUserlist(),train.getData());
				    }else{
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
