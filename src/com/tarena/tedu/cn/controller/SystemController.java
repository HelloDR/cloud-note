package com.tarena.tedu.cn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tarena.tedu.cn.entity.Note;
import com.tarena.tedu.cn.entity.NoteBook;
import com.tarena.tedu.cn.entity.User;
import com.tarena.tedu.cn.result.JSONResult;
import com.tarena.tedu.cn.service.NoteBookService;
import com.tarena.tedu.cn.service.NoteBookServiceImpl;
import com.tarena.tedu.cn.service.NoteService;
import com.tarena.tedu.cn.service.NoteServiceImpl;
import com.tarena.tedu.cn.service.UserService;
import com.tarena.tedu.cn.service.UserServiceImpl;

public class SystemController extends HttpServlet {
	private static final int JSONResult = 0;
	private UserService userService;
	private NoteBookService noteBookService;
	private NoteService noteService;
	public SystemController(){
		this.userService = new UserServiceImpl();
		this.noteBookService = new NoteBookServiceImpl();
		this.noteService = new NoteServiceImpl();
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String path = request.getRequestURI();
		path=path.substring(path.lastIndexOf("/")+1);
		if("regist.do".equals(path)){
			JSONResult<User> js=this.regist(request, response);
			//将js转化为json
			JSONObject ob=JSONObject.fromObject(js);
			//输出json格式数据
			out.println(ob);
			//关闭流
			out.close();
			
		}else if("check.do".equals(path)){
			JSONResult<User> js=this.check(request);
			JSONObject ob = JSONObject.fromObject(js);
			out.println(ob);
			out.close();
			
		}else if("login.do".equals(path)){
			JSONResult<User> js=this.login(request);
			JSONObject ob = JSONObject.fromObject(js);
			out.println(ob);
			out.close();
		}else if("loadingBooksBypage.do".equals(path)){
			JSONResult<List<NoteBook>> js = this.loadingInitBooks(request,response);
			JSONArray arrays = JSONArray.fromObject(js);
			out.println(arrays);
			out.close();
		}else if("loadingNotesByPage.do".equals(path)){
			JSONResult<List<Note>> js = this.loadingNote(request);
			JSONArray ob=JSONArray.fromObject(js);
			out.println(ob);
			out.close();
			
		}else if("save.do".equals(path)){
			JSONResult<Note> js = this.saveNote(request);
			JSONObject ob = JSONObject.fromObject(js);
			out.println(ob);
			out.close();
		}else if("createNote.do".equals(path)){
			JSONResult<Note> js = this.addNote(request);
			JSONObject ob = JSONObject.fromObject(js);
			out.println(ob);
			out.close();
		}

	}
	
	
	
	//接受注册请求
	public JSONResult<User> regist(HttpServletRequest request, HttpServletResponse response){
		String cn_user_name = request.getParameter("cn_user_name");
		String cn_user_password = request.getParameter("cn_user_password");
		String cn_user_nick = request.getParameter("cn_user_nick");
		User u = new User();
		u.setCn_user_name(cn_user_name);
		u.setCn_user_password(cn_user_password);
		u.setCn_user_nick(cn_user_nick);
		u = userService.regist(u);
		JSONResult<User> js = new JSONResult<>();
		js.setStatus(0);
		js.setMsg("注册成功");
		js.setResult(u);
		return js;
	}

	public JSONResult<User> check(HttpServletRequest request){
		User u = userService.check_user(request.getParameter("cn_user_name"));
		JSONResult<User> js = new JSONResult<>();
		if(u==null){
			js.setMsg("该用户可以使用");
			js.setStatus(1);
			return js;
		}
		js.setResult(u);
		js.setStatus(0);
		js.setMsg("该用户已被占用");
		return js;
	}
	
	//处理登陆
	public JSONResult<User> login(HttpServletRequest request){
		User u=userService.login(request.getParameter("cn_user_name"),
				request.getParameter("cn_user_password"));
		JSONResult<User> js=new JSONResult<>();
		if(u==null){
			js.setMsg("该用户未注册");
			js.setStatus(1);
			return js;
		}
		js.setResult(u);
		js.setStatus(0);
		js.setMsg("登录正常");
		return js;
	}
	
	//处理加载笔记本请求
	public JSONResult<List<NoteBook>> loadingInitBooks(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String cn_user_id = request.getParameter("cn_user_id");
		int start = Integer.valueOf(request.getParameter("start"));
		List<NoteBook> books=noteBookService.loadingNoteBookByPage(cn_user_id, start, 5);
		JSONResult<List<NoteBook>> js = new JSONResult<>();
		js.setResult(books);
		js.setStatus(0);
		js.setMsg("加载笔记成功");
		return js;
	}
	
	public JSONResult<List<Note>>loadingNote(HttpServletRequest request){
		List<Note> notes = noteService.loadingNoteByPage(request.getParameter("cn_notebook_id"), Integer.valueOf(request.getParameter("start")), 5);
		JSONResult<List<Note>> js = new JSONResult<>();
		if(notes.size()==0){
			js.setStatus(1);
			js.setMsg("此用户没有笔记");
			return js;
		}
		js.setStatus(0);
		js.setResult(notes);
		js.setMsg("加载笔记正常");
		return js;
	}
	
	//保存笔记请求
	public JSONResult<Note> saveNote(HttpServletRequest request){
		String cn_note_id = request.getParameter("cn_note_id");
		String cn_note_title = request.getParameter("cn_note_title");
		String cn_note_body = request.getParameter("cn_note_body");
		noteService.save(cn_note_id, cn_note_title, cn_note_body);
		JSONResult<Note> js = new JSONResult<>();
		js.setStatus(0);
		js.setMsg("保存正常");
		return js;
	}
	
	//增加笔记请求
	public JSONResult<Note> addNote(HttpServletRequest request){
		String cn_user_id = request.getParameter("cn_user_id");
		String cn_notebook_id = request.getParameter("cn_notebook_id");
		String cn_note_title = request.getParameter("cn_note_title");
		
		Note n = noteService.createNote(cn_user_id, cn_notebook_id, cn_note_title);
		JSONResult<Note> js = new JSONResult<>();
		js.setResult(n);
		js.setMsg("创建笔记成功");
		js.setStatus(0);
		return js;
	}
}
