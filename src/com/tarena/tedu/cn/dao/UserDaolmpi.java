package com.tarena.tedu.cn.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;





import com.tarena.tedu.cn.entity.User;
import com.tarena.tedu.cn.utils.DBUtil;

public class UserDaolmpi implements UserDao{
	private Connection conn;
	public UserDaolmpi(){
		conn=DBUtil.getConn();
	}
	@Override
	public User findByName(Serializable name) {
		// TODO Auto-generated method stub
		String sql = "select * from cn_user where cn_user_name=?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, (String)name);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				User u = new User();
				String cn_user_id= rs.getString("cn_user_id");
				String cn_user_name= rs.getString("cn_user_name");
				String cn_user_password= rs.getString("cn_user_password");
				String cn_user_nick= rs.getString("cn_user_nick");
				String cn_user_token= rs.getString("cn_user_token");
				u.setCn_user_id(cn_user_id);
				u.setCn_user_name(cn_user_name);
				u.setCn_user_nick(cn_user_nick);
				u.setCn_user_password(cn_user_password);
				u.setCn_user_token(cn_user_token);
				return u;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByPage(Map<String, Object> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(User e) {
		// TODO Auto-generated method stub
		String sql = "insert into cn_user(cn_user_id,cn_user_name,cn_user_password,cn_user_nick)values"
				+"(?,?,?,?)";
		
		
		try {
			 
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, e.getCn_user_id());
			pst.setString(2, e.getCn_user_name());
			pst.setString(3, e.getCn_user_password());
			pst.setString(4, e.getCn_user_nick());
			int count = pst.executeUpdate();
			System.out.println("注册成功！成功的执行了"+count+"条sql语句");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
}
