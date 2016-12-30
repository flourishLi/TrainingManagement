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
	public int SignIn(String username, String password) {
		//查询用户是否存在
		User user=userDAO.selectByUserName(username);
		if(user !=null){//用户名密码核对
			user=userDAO.selectByUserNameAndPassword(username,password);
			if(user!=null){
				return ErrorCode.Success;
			}else{
				return ErrorCode.SignInFail;
			}
		}else{
			return ErrorCode.UserNotExist;
		}
	}

	public int SignUp(User user) {
		int no=userDAO.insert(user);
		if(no==ErrorCode.Success){
			return ErrorCode.Success;
		}else{
			return ErrorCode.SignUpFail;
		}
	}

}
