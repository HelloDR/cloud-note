package com.tarena.tedu.cn.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tarena.tedu.cn.entity.Note;
import com.tarena.tedu.cn.entity.NoteBook;
import com.tarena.tedu.cn.entity.User;
import com.tarena.tedu.cn.service.NoteBookService;
import com.tarena.tedu.cn.service.NoteBookServiceImpl;
import com.tarena.tedu.cn.service.NoteService;
import com.tarena.tedu.cn.service.NoteServiceImpl;
import com.tarena.tedu.cn.service.UserService;
import com.tarena.tedu.cn.service.UserServiceImpl;

public class SystemTest {
	private UserService userService;
	private NoteBookService noteBookService;
	private NoteService noteService;
	@Before
	public void init(){
		userService = new UserServiceImpl(); 
		this.noteBookService = new NoteBookServiceImpl();
		this.noteService = new NoteServiceImpl();
	}
	
	//测试注册
	
	public void testRegist(){
		User u= new User();
		u.setCn_user_name("SXL1");
		u.setCn_user_password("1234");
		userService.regist(u);
	}
	
	
	public void testLoadingBooks(){
		List<NoteBook> books = noteBookService.loadingNoteBookByPage("39295a3d-cc9b-42b4-b206-a2e7fab7e77c",0,5);
		for(int i=0;i<books.size();i++){
			String cn_user_id=books.get(i).getCn_user_id();
			String cn_notebook_id=books.get(i).getCn_notebook_id();
			String cn_notebook_name=books.get(i).getCn_notebook_name();
			System.out.println("用户ID是:"+cn_user_id+";笔记本ID是:"+cn_notebook_id+";笔记本名称是"+cn_notebook_name);
		}
				
	}	

	public void testLoadingNotes(){
		List<Note> notes = noteService.loadingNoteByPage("1db556b9-d1dc-4ed9-8274-45cf0afbe859", 0, 5);
		for(Note n:notes){
			System.out.println(n.getCn_note_title());
		}
	}
	//测试修改笔记
	
	public void testUpdateNotes(){
		noteService.save("046b0110-67f9-48c3-bef3-b0b23bda9d4e", "修改标题", "修改内容");
		
	}
	@Test
	//测试增加笔记
	public void testAddNote(){
		noteService.createNote("39295a3d-cc9b-42b4-b206-a2e7fab7e77c", "0cd94778-4d52-486d-a35d-263b3cfe6de9", "增加的笔记");
	}
	
}
