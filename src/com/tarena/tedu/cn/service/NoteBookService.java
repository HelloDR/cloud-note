package com.tarena.tedu.cn.service;

import java.util.List;

import com.tarena.tedu.cn.entity.NoteBook;

public interface NoteBookService {
	//分页的形式加载笔记本:用户ID、开始记录、每页显示的条数
	List<NoteBook> loadingNoteBookByPage(String cn_user_id,int start,int count);
}
