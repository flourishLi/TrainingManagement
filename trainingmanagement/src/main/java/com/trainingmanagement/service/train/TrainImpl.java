package com.trainingmanagement.service.train;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainingmanagement.dao.TrainMapper;
import com.trainingmanagement.model.TrainWithBLOBs;
import com.trainingmanagement.service.util.ErrorCode;

@Service
public class TrainImpl implements TrainInterface {

	@Autowired
	TrainMapper trainDAO;
	public int Sponsor(int userid, byte[] userlist,byte[] content) {
		TrainWithBLOBs train=new TrainWithBLOBs();
		train.setUserid(userid);
		train.setTraincontent(content);
		train.setUserlist(userlist);
		int no=trainDAO.insert(train);
		if(no==ErrorCode.Success){
			return ErrorCode.Success;
		}else{
			return ErrorCode.SponsorFail;
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
	public int ModifyTrain(int userid,int trainid,byte[] content, byte[] userlist) {
		TrainWithBLOBs train=new TrainWithBLOBs();
		train.setUserid(userid);
		train.setTraincontent(content);
		train.setUserlist(userlist);
		int no=trainDAO.updateByPrimaryKeyWithBLOBs(train, trainid, userid);
		if(no==ErrorCode.Success){
			return ErrorCode.Success;
		}else{
			return ErrorCode.UpdateTrainFail;
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

}
