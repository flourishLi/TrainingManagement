package com.trainingmanagement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.trainingmanagement.model.Train;
import com.trainingmanagement.model.TrainWithBLOBs;

public interface TrainMapper {
    int deleteByPrimaryKey(@Param("trainid")Integer trainid,@Param("userid")Integer userid);

    int insert(TrainWithBLOBs record);

    int insertSelective(TrainWithBLOBs record);

    TrainWithBLOBs selectByPrimaryKey(Integer trainid);

    int updateByPrimaryKeySelective(TrainWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TrainWithBLOBs record);

    int updateByPrimaryKey(Train record);

	List<TrainWithBLOBs> selectAll();

	TrainWithBLOBs selectByUserIDAndTrainID(@Param("trainid")int trainid, @Param("userid")int userid);
	
	List<TrainWithBLOBs> selectByLikeTrainName(String trainname);

}