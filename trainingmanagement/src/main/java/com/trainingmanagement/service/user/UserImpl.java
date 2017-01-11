package com.trainingmanagement.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.trainingmanagement.dao.UserMapper;
import com.trainingmanagement.model.User;
import com.trainingmanagement.service.util.ErrorCode;

@Service
public class UserImpl implements UserInterface {

	@Autowired
	UserMapper userDAO;
	public User SignIn(String username, String password) {
		//查询用户是否存在
		try{
		User user=userDAO.selectByUserName(username);
		if(user !=null){//用户名密码核对
			user=userDAO.selectByUserNameAndPassword(username,password);
			if(user!=null){
				return user;
			}else{
				return null;
			}
		}else{
			return null;
		}
		}catch(Exception e){
			return null;
		}
	}

	public String SignUp(User user) {
		try{
		int no=userDAO.insert(user);
		if(no==ErrorCode.Success){
			return ErrorCode.SignUpSuccess_Message;
		}else{
			return ErrorCode.SignUpFail_Message;
		}
		}catch(Exception e){
			return ErrorCode.DataBaseError_Message;
		}
	}

}
