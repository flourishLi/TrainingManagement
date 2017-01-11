package com.trainingmanagement.service.train;

import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.ExpandVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;
import com.trainingmanagement.controller.response.Train;
import com.trainingmanagement.dao.TrainMapper;
import com.trainingmanagement.model.TrainWithBLOBs;
import com.trainingmanagement.service.util.ByteConvertion;
import com.trainingmanagement.service.util.ContainerCheck;
import com.trainingmanagement.service.util.ErrorCode;

import java.sql.Time;
import java.sql.Timestamp;
@Service
public class TrainImpl implements TrainInterface {

	@Autowired
	TrainMapper trainDAO;
	public String Sponsor(TrainWithBLOBs train) {
		try{
		int no=trainDAO.insert(train);
		if(no==ErrorCode.Success){
			return ErrorCode.SponsorSuccess_Message;
		}else{
			return ErrorCode.SponsorFail_Message;
		}
		}catch(Exception e){
			return ErrorCode.DataBaseError_Message;
		}
	}

	@Override
	public String DeleteTrain(int userid, int trainid) {
		try{
		int no=trainDAO.deleteByPrimaryKey(trainid, userid);
		if(no==ErrorCode.Success){
			return ErrorCode.DeleteTrainSuccess_Message;
		}else{
			return ErrorCode.DeleteTrainFail_Message;
		}
		}catch(Exception e){
			return ErrorCode.DataBaseError_Message;
		}
	}

	@Override
	public String ModifyTrain(TrainWithBLOBs train) {
		try{
		int no=trainDAO.updateByPrimaryKeyWithBLOBs(train);
		if(no==ErrorCode.Success){
			return ErrorCode.UpdateTrainSuccess_Message;
		}else{
			return ErrorCode.UpdateTrainFail_Message;
		}
		}catch(Exception e){
			return ErrorCode.DataBaseError_Message;
		}
	}

	@Override
	public List<TrainWithBLOBs> GetAllTrains() {
		try{
		List<TrainWithBLOBs> trainsAll=trainDAO.selectAll();
		return trainsAll;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public TrainWithBLOBs SelectByUserIDAndTrainID(int userid, int trainid) {
		try{
		    TrainWithBLOBs train=trainDAO.selectByUserIDAndTrainID(trainid, userid);
		    if(train!=null){
			    return train;
		    }else{
			    return null;
		    }
		    }catch(Exception e){
			    return null;
		    }
	}

	@Override
	public List<Train> GetTrainsByUserId(int userid) {
		List<TrainWithBLOBs> trainBlobs=GetAllTrains();
		if(trainBlobs!=null){
			//response trains
		    List<Train> responseTrains=new LinkedList<Train>();
		     
		     for (TrainWithBLOBs trainWithBLOBs : trainBlobs) {
				  String strContent=new String(trainWithBLOBs.getTraincontent());
				  int [] useridlist =ByteConvertion.ByteToIntArray(trainWithBLOBs.getUserlist());
				  Train train=new Train();
				  //创建者
				  if(userid==trainWithBLOBs.getUserid()){			 
					  train.userid=trainWithBLOBs.getUserid();
					  train.useridlist=useridlist;
					  train.content=strContent;
					  train.trainid=trainWithBLOBs.getTrainid();
					  train.establishdata=trainWithBLOBs.getData();	 
					  train.trainlocation=trainWithBLOBs.getTrainlocation();
					  train.trainname=trainWithBLOBs.getTrainname();
				  }else{
					  //参与者
					  if(ContainerCheck.isContain(useridlist, userid)){
						  train.userid=trainWithBLOBs.getUserid();
						  train.useridlist=useridlist;
						  train.content=strContent;
						  train.trainid=trainWithBLOBs.getTrainid();
						  train.establishdata=trainWithBLOBs.getData();	
						  train.trainlocation=trainWithBLOBs.getTrainlocation();
						  train.trainname=trainWithBLOBs.getTrainname();
					  }else{
						  continue;
					  }
				  }
				  responseTrains.add(train);		  	  
			  }		     
			return responseTrains;
		}else{
			return null;
		}
	}

}
