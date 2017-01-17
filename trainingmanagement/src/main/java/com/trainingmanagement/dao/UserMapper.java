package com.trainingmanagement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.trainingmanagement.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectByUserName(String username);

	User selectByUserNameAndPassword(@Param("username")String username,@Param("password")String password);
	
	List<User> selectAllUsers();
}