package com.trainingmanagement.service.train;

import java.util.List;

import com.trainingmanagement.model.TrainWithBLOBs;

public interface TrainInterface {
    /**
     * 发起培训
     * @param   发起者 参与人员列表
     * @return  1 成功 else 失败
     */
   int Sponsor(int userid,byte[] userlist,byte[] content);
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
    int ModifyTrain(int userid,int trainid,byte[] content,byte[] userlist);
    /**
     * 显示培训
     * @param   用户id 
     * @return  培训列表
     */
    TrainWithBLOBs DisplayTrain(int userid);
    
    List<TrainWithBLOBs> GetAllTrains();
}
