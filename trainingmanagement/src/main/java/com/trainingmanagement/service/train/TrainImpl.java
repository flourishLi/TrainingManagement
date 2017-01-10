package com.trainingmanagement.service.train;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainingmanagement.dao.TrainMapper;
import com.trainingmanagement.model.TrainWithBLOBs;
import com.trainingmanagement.service.util.ErrorCode;

import java.sql.Time;
import java.sql.Timestamp;
@Service
public class TrainImpl implements TrainInterface {

	@Autowired
	TrainMapper trainDAO;
	public String Sponsor(int userid, byte[] userlist,byte[] content,Timestamp time) {
		TrainWithBLOBs train=new TrainWithBLOBs();
		train.setUserid(userid);
		train.setTraincontent(content);
		train.setUserlist(userlist);
		train.setData(time);
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
	public int DeleteTrain(int userid, int trainid) {
		int no=trainDAO.deleteByPrimaryKey(trainid, userid);
		if(no==ErrorCode.Success){
			return ErrorCode.Success;
		}else{
			return ErrorCode.DeleteTrainFail;
		}
	}

	@Override
	public String ModifyTrain(int userid,int trainid,byte[] content, byte[] userlist,Timestamp time) {
		TrainWithBLOBs train=new TrainWithBLOBs();
		train.setUserid(userid);
		train.setTraincontent(content);
		train.setUserlist(userlist);
		train.setData(time);
		int no=trainDAO.updateByPrimaryKeyWithBLOBs(train, trainid, userid);
		if(no==ErrorCode.Success){
			return ErrorCode.UpdateTrainSuccess_Message;
		}else{
			return ErrorCode.UpdateTrainFail_Message;
		}
	}

	@Override
	public TrainWithBLOBs DisplayTrain(int userid) {
		//查找所有的培训
		List<TrainWithBLOBs> trainsAll=trainDAO.selectAll();
		return null;
	}

	@Override
	public List<TrainWithBLOBs> GetAllTrains() {
		List<TrainWithBLOBs> trainsAll=trainDAO.selectAll();
		return trainsAll;
	}

	@Override
	public TrainWithBLOBs SelectByUserIDAndTrainID(int userid, int trainid) {
		TrainWithBLOBs train=trainDAO.selectByUserIDAndTrainID(trainid, userid);
		if(train!=null){
			return train;
		}else{
			return null;
		}
	}

}
