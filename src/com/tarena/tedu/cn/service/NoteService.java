package com.tarena.tedu.cn.service;

import java.util.List;

import com.tarena.tedu.cn.entity.Note;

public interface NoteService {
	//分页加载笔记
	List<Note> loadingNoteByPage(String cn_notebook_id,int start,int pageSize);
	//更新笔记
	void save(String cn_note_id,String cn_note_title,String cn_note_body);
	//增加笔记
	Note createNote(String cn_user_id,String cn_notebook_id,String cn_note_title);
}
