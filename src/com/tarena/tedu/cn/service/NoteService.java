package com.tarena.tedu.cn.service;

import java.util.List;

import com.tarena.tedu.cn.entity.Note;

public interface NoteService {
	//��ҳ���رʼ�
	List<Note> loadingNoteByPage(String cn_notebook_id,int start,int pageSize);
	//���±ʼ�
	void save(String cn_note_id,String cn_note_title,String cn_note_body);
	//���ӱʼ�
	Note createNote(String cn_user_id,String cn_notebook_id,String cn_note_title);
}
