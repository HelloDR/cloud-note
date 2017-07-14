package com.tarena.tedu.cn.service;

import com.tarena.tedu.cn.entity.User;

public interface UserService {
	//用户注册
	User regist(User user);
	//用户登录
	User login(String cn_user_name,String cn_user_password);
	
	//检查用户是否存在
	User check_user(String cn_user_name);
	
}
