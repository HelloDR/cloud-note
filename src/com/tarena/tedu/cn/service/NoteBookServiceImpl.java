package com.tarena.tedu.cn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.tarena.tedu.cn.dao.NoteBookDao;
import com.tarena.tedu.cn.dao.NoteBookDaoImpl;
import com.tarena.tedu.cn.entity.NoteBook;
import com.tarena.tedu.cn.system.exception.UserException;

//实现类
public class NoteBookServiceImpl implements NoteBookService{
	private NoteBookDao noteBookDao =new NoteBookDaoImpl();
	@Override
	public List<NoteBook> loadingNoteBookByPage(String cn_user_id, int start,
			int count) {
		// TODO Auto-generated method stub
		if(cn_user_id==null||cn_user_id.trim()==""){
			throw new UserException("用户ID为空");
		}
		Map<String,Object> page = new HashMap<>();
		page.put("cn_user_id", cn_user_id);
		start = count*start;
		page.put("start", start);
		page.put("pageSize", count);
		
		List<NoteBook> books = noteBookDao.findByPage(page);
		return books;
	}
	
}
