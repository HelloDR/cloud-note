function addNote(){
	$("#add_note").click(function(){
		//console.log("测试");
		//获取用户id
		var cn_user_id = getCookie("cn_user_id");
		if(cn_user_id==undefined){
			$("#can").load("alert/alert_msg.html",{},function(){
				$('.opacity_bg').show();
				$("#can").find("#tip").html("请先登陆");
				$("#close_msg").on("click",".close,.cancle,.sure",function(){
					$("#can").empty();
					$('.opacity_bg').hide();
					window.location.href="log_in.html";
				});
			});
			return;
		}
		
		//获取id
		var cn_notebook_id = model.cn_notebook_id_cache;
		//alert(cn_notebook_id);
		if(cn_notebook_id==undefined){
			$("#can").load("alert/alert_msg.html",{},function(){
				$('.opacity_bg').show();
				$("#can").find("#tip").html("请选择笔记本");
				$("#close_msg").on("click",".close,.cancle,.sure",function(){
					$("#can").empty();
					$('.opacity_bg').hide();
				});
			});
			return;
		}
		
		//加载笔记选项框
		$("#can").load("alert/alert_note.html",{},function(){
			$('.opacity_bg').show();
			$("#can").on("click",".close,.cancle",function(){
				$("#can").empty();
				$('.opacity_bg').hide();
			});
			$("#can").on("click",".sure",function(){
				//console.log("测试");
				
				var cn_note_title = $('#input_note').val();
				if(cn_note_title.trim()==""){
					$("#can").empty();
					$("#can").load("alert/alert_msg.html",{},function(){
						$('.opacity_bg').show();
						$("#can").find("#tip").html("笔记标题不能为空");
						$("#close_msg").on("click",".close,.cancle,.sure",function(){
							$("#can").empty();
							$('.opacity_bg').hide();
						});
					});
				}
				//数据无误发送请求
				$.ajax({
					url:"createNote.do",
					type:"post",
					data:{"cn_notebook_id":cn_notebook_id,"cn_user_id":cn_user_id,"cn_note_title":cn_note_title},
					dataType:"json",
					success:function(r){
						if(r.status==0){
							var tem = [r.result];
							model.notes = tem.concat(model.notes);
							model.updateNotes(model.notes, true);
							$("$can").empty();
							$('.opacity_bg').hide();
						}
					},
					error:function(){
						$("#can").load("alert/alert_msg.html",{},function(){
							$('.opacity_bg').show();
							$("#can").find("#tip").html("创建笔记失败");
							$("#close_msg").on("click",".close,.cancle,.sure",function(){
								$("#can").empty();
								$('.opacity_bg').hide();
							});
						});
						
					}
				});
			});
		});
	});
}






function saveNote(){
	$("#save_note").click(function(){
		//alert("测试");
		//alert(model.cn_save_note_id);
		if(model.cn_save_note_id == undefined){
			$("#can").load("alert/alert_msg.html",{},function(){
				$('.opacity_bg').show();
				$("#close_msg").on("click",".close,.cancle,.sure",function(){
					$("#can").empty();
					$('.opacity_bg').hide();
				});
			});
			return;
		}
		
		var title=$("#input_note_title").val();
		var content=um.getContent();
		if(title.trim()==""){
			$("#can").load("alert/alert_msg.html",{},function(){
				$('.opacity_bg').show();
				$("#can").find("#tip").html("笔记标题不能为空");
				$("#close_msg").on("click",".close,.cancle,.sure",function(){
					$("#can").empty();
					$('.opacity_bg').hide();
				});
			});
			return;
		}
		if(content.trim()==""){
			$("#can").load("alert/alert_msg.html",{},function(){
				$('.opacity_bg').show();
				$("#can").find("#tip").html("笔记内容不能为空");
				$("#close_msg").on("click",".close,.cancle,.sure",function(){
					$("#can").empty();
					$('.opacity_bg').hide();
				});
			});
			return;
		}
		
		$.ajax({
			url:"save.do",
			type:"post",
			data:{"cn_note_id":model.cn_save_note_id,"cn_note_title":title,"cn_note_body":content},
			dataType:"json",
			success:function(r){
				if(r.status==0){
					$("#can").load("alert/alert_msg.html",{},function(){
						$('.opacity_bg').show();
						$("#can").find("#tip").html(r.msg);
						var index = model.note_save_index;
						var cur_note = model.notes[index];
						cur_note.cn_note_title=title;
						cur_note.cn_note_body = content;
						//再次更新笔记
						model.updateNotes(model.notes, true);
						$("#close_msg").on("click",".close,.cancle,.sure",function(){
							$("#can").empty();
							$('.opacity_bg').hide();
						});
					});
				}
			}
		});
	});
}





//显示笔记内容
function displayNoteContent(){
	$("#note").on("click","li",function(){
		$("#note").find("a").removeClass("checked");
		$(this).find("a").addClass("checked");
		//console.log("测试");
		//获取当前元素绑定的索引
		var index = $(this).data("note_index_cur");
		if(index==undefined){
			return;
		}
		
		model.note_save_index = index;
		//console.log(index);
		//根据索引取元素
		var note = model.notes[index];
		model.cn_save_note_id = note.cn_note_id;
		$("#input_note_title").val(note.cn_note_title);
		um.setContent(note.cn_note_body);
	});
}



//点击加载更多笔记
function loadingMoreNotes(){
	//console.log("测试1");
	$("#note").on("click","#morenotes",function(){
		//console.log(model.cn_notebook_id_cache);
		var cn_notebook_id = model.cn_notebook_id_cache;
		if(cn_notebook_id==null){
			alert("没有id");
			return;
			
		}
		
		$.ajax({
			url:"loadingNotesByPage.do",
			type:"post",
			data:{"cn_notebook_id":cn_notebook_id,"start":++model.note_page_param},
			dataType:"json",
			success:function(r){
				if(r[0].status==0){
					//alert("加载数据成功");
					model.notes = model.notes.concat(r[0].result);
					model.updateNotes(model.notes,true);
				}
					
			},
			error:function(){
				alert("加载失败");
			}
		});
		
	});
	
	
}


//点击笔记本显示笔记列表

function loadingInitNotes(){

	$("#notebooks").on("click","li",function(){
		//调节视觉样式
		$("#notebooks").find("a").removeClass("checked");
		$(this).find("a").addClass("checked");
		model.note_page_param=0;
		//alert("测试");
		//获取当前元素索引
		var cur_index = $(this).data("currentIndex");
		if(cur_index==undefined){
			return;
		}
		//alert(cur_index);
		var noteBook = model.notebooks[cur_index];
		var cn_notebook_id = noteBook.cn_notebook_id;
		if(cn_notebook_id==undefined){
			return;
		}
		model.cn_notebook_id_cache = cn_notebook_id;
		$("#input_note_title").val("");
		um.setContent("");
		//数据无误发送请求
		$.ajax({
			url:"loadingNotesByPage.do",
			type:"post",
			data:{"cn_notebook_id":cn_notebook_id,"start":model.note_page_param},
			dataType:"json",
			success:function(r){
				if(r[0].status==0){
					//alert("加载数据成功");
					model.notes = r[0].result;
					model.updateNotes(model.notes,false);
				}
					
			},
			error:function(){
				alert("加载失败");
			}
		});
	});
}
model.note_page_param=0;
model.cn_notebook_id_cache = null;
model.updateNotes = function(notes,flag){
	if(!flag){
		$("#note").empty();
		for(var i=0; i < notes.length;i++){
			var note__li = "<li class='online'>"+
			"<a>"+
			"<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'>"+"</i>"+notes[i].cn_note_title+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>"
		+"</a>"+
		"<div class='note_menu' tabindex='-1'>"+
			"<dl>"+
				"<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>"+
				"<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>"+
				"<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='删除'><i class='fa fa-times'></i></button></dt>"+
			"</dl>"+
		"</div>"+
	"</li>";
			note_li = $(note__li).data("note_index_cur",i);
			$("#note").append(note_li);
		}
		var more = "<li class='online' id='morenotes'>"+
		"<a>"+
		"<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'>"+"</i>more<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>"
	+"</a>"+
	"<div class='note_menu' tabindex='-1'>"+
		"<dl>"+
			"<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>"+
			"<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>"+
			"<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='删除'><i class='fa fa-times'></i></button></dt>"+
		"</dl>"+
	"</div>"+
"</li>";
		$("#note").append($(more));
	}else{
		model.updateNotes(notes, false);
	}
};
model.notes = [];
