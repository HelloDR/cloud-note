package com.tarena.tedu.cn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tarena.tedu.cn.entity.Note;
import com.tarena.tedu.cn.system.exception.UserException;
import com.tarena.tedu.cn.utils.UUIDUtil;
import com.tarena.tedu.cn.dao.NoteDao;
import com.tarena.tedu.cn.dao.NoteDaoImpl;







public class NoteServiceImpl implements NoteService{
	private NoteDao noteDao = new NoteDaoImpl();
	@Override
	public List<Note> loadingNoteByPage(String cn_notebook_id, int start,
			int pageSize) {
		// TODO Auto-generated method stub
		if(cn_notebook_id==null){
			throw new RuntimeException("�ʼǱ��쳣");
		}
		Map<String,Object> pages = new HashMap<>();
		pages.put("cn_notebook_id",cn_notebook_id);
		start*=pageSize;
		pages.put("start", start);
		pages.put("pageSize", pageSize);
		return noteDao.findByPage(pages);
	}
	@Override
	public void save(String cn_note_id, String cn_note_title,
			String cn_note_body) {
		// TODO Auto-generated method stub
		if(cn_note_id==null){
			throw new RuntimeException("�ʼ�IDΪ��");
		}
		if(cn_note_title==null){
			throw new RuntimeException("�ʼǱ����쳣");
			
		}
		if(cn_note_body==null){
			throw new RuntimeException("�ʼ������쳣");
			
		}
		Note n = noteDao.findById(cn_note_id);
		if(n==null){
			throw new RuntimeException("�ʼǲ������쳣");
		}
		n.setCn_note_last_modify_time(System.currentTimeMillis());
		n.setCn_note_title(cn_note_title);
		n.setCn_note_body(cn_note_body);
		noteDao.update(n);
		
	}
	@Override
	public Note createNote(String cn_user_id, String cn_notebook_id,
			String cn_note_title) {
		// TODO Auto-generated method stub
		if(cn_user_id==null){
			throw new UserException("�û�ID�쳣");
		}
		if(cn_notebook_id==null){
			throw new RuntimeException("�ʼǱ�IDΪ��");
		}
		if(cn_note_title==null){
			throw new RuntimeException("�ʼǱ���Ϊ��");
		}
		Note n = new Note();
		n.setCn_note_id(UUIDUtil.getUUIDString());
		n.setCn_note_create_time(System.currentTimeMillis());
		n.setCn_note_title(cn_note_title);
		n.setCn_notebook_id(cn_notebook_id);
		n.setCn_user_id(cn_user_id);
		noteDao.insert(n);
		return n;
	}

}
