$(function(){
	//加载最初的笔记本
	loadingInitNoteBooks();
	//加载更多
	loadingMoreBooks();
	//加载笔记
	loadingInitNotes();
	//点击加载更多笔记
	loadingMoreNotes();
	//显示笔记标题与笔记内容
	displayNoteContent();
	//更新笔记
	saveNote();
	//增加笔记
	addNote();
});

//创建一个空的json对象用于存储缓冲的属性
var model = {};