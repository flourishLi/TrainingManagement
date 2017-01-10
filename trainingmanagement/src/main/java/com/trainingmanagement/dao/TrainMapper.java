package com.trainingmanagement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.trainingmanagement.model.Train;
import com.trainingmanagement.model.TrainWithBLOBs;

public interface TrainMapper {
    int deleteByPrimaryKey(@Param("trainid")Integer trainid,@Param("userid")Integer userid );

    int insert(TrainWithBLOBs record);

    int insertSelective(TrainWithBLOBs record);

    TrainWithBLOBs selectByPrimaryKey(Integer trainid);
    
    List<TrainWithBLOBs> selectAll();
    List<TrainWithBLOBs> selectByUserID(int userid);

    int updateByPrimaryKeySelective(TrainWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TrainWithBLOBs record,@Param("trainid")Integer trainid,@Param("userid")Integer userid );

    int updateByPrimaryKey(Train record);
    
    TrainWithBLOBs selectByUserIDAndTrainID(@Param("trainid")Integer trainid,@Param("userid")Integer userid );
}