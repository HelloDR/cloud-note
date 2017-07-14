package com.tarena.tedu.cn.service;

import com.tarena.tedu.cn.dao.UserDao;
import com.tarena.tedu.cn.dao.UserDaolmpi;
import com.tarena.tedu.cn.entity.User;
import com.tarena.tedu.cn.system.exception.PasswordException;
import com.tarena.tedu.cn.system.exception.UserException;
import com.tarena.tedu.cn.utils.UUIDUtil;

public class UserServiceImpl implements UserService{
	public UserDao userDao;
	public UserServiceImpl(){
		userDao = new UserDaolmpi();
	}

	@Override
	public User regist(User user) {
		// TODO Auto-generated method stub
		if(user.getCn_user_name().trim()=="" || user.getCn_user_name()==null){
			throw new UserException("用户名不能为空");
		}
		if(user.getCn_user_password().trim()==""||user.getCn_user_password()==null){
			throw new PasswordException("密码不能为空");
		}
		if(user.getCn_user_nick()==null){
			user.setCn_user_nick(user.getCn_user_name());
			
		}
		user.setCn_user_id(UUIDUtil.getUUIDString());
		userDao.insert(user);
		return user;
		
	}

	@Override
	public User check_user(String cn_user_name) {
		// TODO Auto-generated method stub
		if(cn_user_name.trim()==""|| cn_user_name==null){
			throw new UserException("用户名不合法");
		}
		return userDao.findByName(cn_user_name);
	}

	@Override
	public User login(String cn_user_name,String cn_user_password) {
		// TODO Auto-generated method stub
		if(cn_user_name.trim()==""){
			throw new UserException("用户名不能为空");
		}
		User u = userDao.findByName(cn_user_name);
		if(u!=null){
			//匹配密码
			if(!cn_user_password.equals(u.getCn_user_password())){
				throw new PasswordException("密码错误");
			}
		}
		return u;
	}
	
}
