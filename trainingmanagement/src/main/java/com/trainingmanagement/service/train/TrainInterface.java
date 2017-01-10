package com.trainingmanagement.service.train;

import java.sql.Timestamp;
import java.util.List;

import com.trainingmanagement.model.TrainWithBLOBs;

public interface TrainInterface {
    /**
     * 发起培训
     * @param   发起者 参与人员列表
     * @return  1 成功 else 失败
     */
   String Sponsor(int userid,byte[] userlist,byte[] content,Timestamp dateTime);
   /**
    * 发起人删除培训
    * @param   发起者id 培训id
    * @return  1 成功 else 失败
    */
   int DeleteTrain(int userid,int trainid);
   /**
    * 发起人修改培训
    * @param   发起者id 培训内容  参与人员列表
    * @return  修改成功与否
    */
   String ModifyTrain(int userid,int trainid,byte[] content,byte[] userlist,Timestamp time);
    /**
     * 显示培训
     * @param   用户id 
     * @return  培训列表
     */
    TrainWithBLOBs DisplayTrain(int userid);
    
    List<TrainWithBLOBs> GetAllTrains();
    
    /**
     * @param 发起者id 培训id
     * return 1成功  else 失败
     */
    TrainWithBLOBs SelectByUserIDAndTrainID(int userid,int trainid);
}
