package com.trainingmanagement.service.train;

import java.util.List;

import com.trainingmanagement.controller.response.Train;
import com.trainingmanagement.model.TrainWithBLOBs;

public interface TrainInterface {
    /**
     * 发起培训
     * @param   发起者 参与人员列表
     * @return  1 成功 else 失败
     */
   String sponsor(TrainWithBLOBs train);
   /**
    * 发起人删除培训
    * @param   发起者id 培训id
    * @return  1 成功 else 失败
    */
   String deleteTrain(int userid,int trainid);
   /**
    * 发起人修改培训
    * @param   发起者id 培训内容  参与人员列表
    * @return  修改成功与否
    */
   String modifyTrain(TrainWithBLOBs train);
    
    List<TrainWithBLOBs> getAllTrains();
    
    /**
     * 
     * @param userid
     * @return 该用户参与的和发起的培训
     */
    List<Train> getTrainsByUserId(int userid);
    
    /**
     * @param 发起者id 培训id
     * return 1成功  else 失败
     */
    TrainWithBLOBs selectByUserIDAndTrainID(int userid,int trainid);
    
    /**
     * 
     * @param trainname
     * @return 根据培训名称模糊查询
     */
    List<Train> fuzzySearch(String trainname);
}
