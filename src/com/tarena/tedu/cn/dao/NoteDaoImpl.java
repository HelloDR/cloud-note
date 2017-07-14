package com.tarena.tedu.cn.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;






//import javax.naming.spi.DirStateFactory.Result;



import com.tarena.tedu.cn.entity.Note;
//import com.tarena.tedu.cn.entity.User;
//import com.tarena.tedu.cn.entity.NoteBook;
import com.tarena.tedu.cn.utils.DBUtil;

public class NoteDaoImpl implements NoteDao{
	private Connection conn = DBUtil.getConn();
	
	@Override
	public Note findByName(Serializable name) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Note findById(Serializable id) {
		// TODO Auto-generated method stub
		String sql = "select * from cn_note where cn_note_id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, (String)id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				Note n = new Note();
				String cn_note_id = rs.getString("cn_note_id");
				String cn_notebook_id = rs.getString("cn_notebook_id");
				String cn_user_id = rs.getString("cn_user_id");
				String cn_note_status_id = rs.getString("cn_note_status_id");
				String cn_note_type_id = rs.getString("cn_note_type_id");
				String cn_note_title = rs.getString("cn_note_title");
				String cn_note_body = rs.getString("cn_note_body");
				long cn_note_create_time = rs.getLong("cn_note_create_time");
				long cn_note_last_modify_time = rs.getLong("cn_note_last_modify_time");
				n.setCn_note_body(cn_note_body);
				n.setCn_note_create_time(cn_note_create_time);
				n.setCn_note_id(cn_note_id);
				n.setCn_note_last_modify_time(cn_note_last_modify_time);
				n.setCn_note_status_id(cn_note_status_id);
				n.setCn_note_title(cn_note_title);
				n.setCn_note_type_id(cn_note_type_id);
				n.setCn_notebook_id(cn_notebook_id);
				n.setCn_user_id(cn_user_id);
				return n;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Note> findByPage(Map<String, Object> page) {
		// TODO Auto-generated method stub
		String sql = "select * from cn_note  where cn_notebook_id=? order by cn_note_create_time desc limit ?,?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, (String)page.get("cn_notebook_id"));
			pst.setInt(2, (int)page.get("start"));
			pst.setInt(3, (int)(page.get("pageSize")));
			ResultSet rs = pst.executeQuery();
			List<Note> notes = new ArrayList<>();
			while(rs.next()){
				Note note = new Note();
				String cn_note_id = rs.getString("cn_note_id");
				String cn_notebook_id = rs.getString("cn_notebook_id");
				String cn_user_id = rs.getString("cn_user_id");
				String cn_note_status_id = rs.getString("cn_note_status_id");
				String cn_note_type_id = rs.getString("cn_note_type_id");
				String cn_note_title = rs.getString("cn_note_title");
				String cn_note_body = rs.getString("cn_note_body");
				long cn_note_create_time = rs.getLong("cn_note_create_time");
				long cn_note_last_modify_time = rs.getLong("cn_note_last_modify_time");
				note.setCn_note_body(cn_note_body);
				note.setCn_note_create_time(cn_note_create_time);
				note.setCn_note_id(cn_note_id);
				note.setCn_note_last_modify_time(cn_note_last_modify_time);
				note.setCn_note_status_id(cn_note_status_id);
				note.setCn_note_title(cn_note_title);
				note.setCn_note_type_id(cn_note_type_id);
				note.setCn_notebook_id(cn_notebook_id);
				note.setCn_user_id(cn_user_id);
				notes.add(note);
			}
			return notes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Note e) {
		// TODO Auto-generated method stub
		String sql = "update cn_note set cn_note_title=?,"
		+"cn_note_body=?,"
		+"cn_note_last_modify_time=? where cn_note_id=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, e.getCn_note_title());
			pst.setString(2, e.getCn_note_body());
			pst.setLong(3, e.getCn_note_last_modify_time());
			pst.setString(4, e.getCn_note_id());
			int count=pst.executeUpdate();
			System.out.println("成功更新了"+count+"条语句");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public void update(Serializable id) {
		// TODO Auto-generated method stub
		
		Note n = this.findById(id);
		if(n==null){
			throw new RuntimeException("笔记id为空");
		}
		this.update(n);
	}

	@Override
	public void delete(Note e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Note e) {
		// TODO Auto-generated method stub
		String sql = "insert into cn_note(cn_note_id,cn_user_id,cn_notebook_id,cn_note_title,cn_note_create_time)"+
		"values(?,?,?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, e.getCn_note_id());
			pst.setString(2, e.getCn_user_id());
			pst.setString(3, e.getCn_notebook_id());
			pst.setString(4, e.getCn_note_title());
			pst.setLong(5, e.getCn_note_create_time());
			int count=pst.executeUpdate();
			System.out.println("成功插入"+count+"条语句");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

}
