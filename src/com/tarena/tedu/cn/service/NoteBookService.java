package com.tarena.tedu.cn.service;

import java.util.List;

import com.tarena.tedu.cn.entity.NoteBook;

public interface NoteBookService {
	//��ҳ����ʽ���رʼǱ�:�û�ID����ʼ��¼��ÿҳ��ʾ������
	List<NoteBook> loadingNoteBookByPage(String cn_user_id,int start,int count);
}
