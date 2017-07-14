package com.tarena.tedu.cn.dao;

import java.io.Serializable;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import javax.naming.spi.DirStateFactory.Result;

import com.tarena.tedu.cn.entity.NoteBook;
import com.tarena.tedu.cn.utils.DBUtil;

public class NoteBookDaoImpl implements NoteBookDao{
	private Connection conn = DBUtil.getConn();
	
	@Override
	public NoteBook findByName(Serializable name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NoteBook findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoteBook> findByPage(Map<String, Object> page) {
		// TODO Auto-generated method stub
		String sql = "select * from cn_notebook where cn_user_id=? limit ?,?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			String cn_user_id = (String) page.get("cn_user_id");
			int start = (int) page.get("start");
			int pageSize = (int) page.get("pageSize");
			pst.setString(1, cn_user_id);
			pst.setInt(2, start);
			pst.setInt(3, pageSize);
			ResultSet rs = pst.executeQuery();
			List<NoteBook> books = new ArrayList<>();
			while(rs.next()){
				String cn_notebook_id = rs.getString("cn_notebook_id");
				cn_user_id = rs.getString("cn_user_id");
				String cn_notebook_type_id = rs.getString("cn_notebook_type_id");
				String cn_notebook_name = rs.getString("cn_notebook_name");
				String cn_notebook_desc = rs.getString("cn_notebook_desc");
				Timestamp cn_notebook_createtime = rs.getTimestamp("cn_notebook_createtime");
				NoteBook book = new NoteBook();
				book.setCn_notebook_createtime(cn_notebook_createtime);
				book.setCn_notebook_desc(cn_notebook_desc);
				book.setCn_notebook_id(cn_notebook_id);
				book.setCn_notebook_name(cn_notebook_name);
				book.setCn_notebook_type_id(cn_notebook_type_id);
				book.setCn_user_id(cn_user_id);
				books.add(book);
				
			}
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(NoteBook e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(NoteBook e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(NoteBook e) {
		// TODO Auto-generated method stub
		
	}

}
