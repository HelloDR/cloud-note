package com.tarena.tedu.cn.service;

import com.tarena.tedu.cn.entity.User;

public interface UserService {
	//�û�ע��
	User regist(User user);
	//�û���¼
	User login(String cn_user_name,String cn_user_password);
	
	//����û��Ƿ����
	User check_user(String cn_user_name);
	
}
