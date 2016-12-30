package com.trainingmanagement.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.trainingmanagement.model.User;

@Repository(value="UserMapper")
public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);
    
    User selectByUserName(String username);
    
    User selectByUserNameAndPassword(@Param("username")String username,@Param("password")String password);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}