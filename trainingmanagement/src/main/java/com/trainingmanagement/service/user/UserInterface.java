package com.trainingmanagement.service.user;

import com.trainingmanagement.model.User;

public interface UserInterface {
    /**
     * 用户登陆
     * @param   用户名 密码
     * @return  1 登陆成功 101 失败 102 用户不存在
     */
	User  SignIn(String username,String password);
     /**
      * 用户注册
      * @param   用户名
      * @return  1 注册成功 else 失败
      * 
      */
    String  SignUp(User user);
}
