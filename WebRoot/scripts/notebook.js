//加载笔记本
model.start = 0;
model.notebooks={};
function loadingInitNoteBooks(){
	//alert("正确触发");
	var cn_user_id = getCookie("cn_user_id");
	if(cn_user_id.trim()==""||cn_user_id==undefined){
		alert("用户ID异常");
		return;
	}
	//alert(cn_user_id);
	//数据无误向服务器请求数据
	$.ajax({
		url:"loadingBooksBypage.do",
		type:"post",
		data:{"cn_user_id":cn_user_id,"start":model.start},
		dataType:"json",
		success:function(r){
			
			if(r[0].status==0){
				//alert("加载数据正常");
				//更新笔记本
				model.notebooks = r[0].result;
				model.updateNoteBooks(model.notebooks,false);
				
			}
		},
		error:function(){
			alert("加载数据失败，请您联系客服");
		}
	});
}

function loadingMoreBooks(){
	$("#notebooks").on("click","#moreBooks",function(){
//		alert("hah");
		var cn_user_id = getCookie("cn_user_id");
		if(cn_user_id==undefined){
			alert("用户ID异常");
			return;
		}
		
		$.ajax({
			url:"loadingBooksBypage.do",
			type:"post",
			data:{"cn_user_id":cn_user_id,"start":++model.start},
			dataType:"json",
			success:function(r){
				
				if(r[0].status==0){
					//alert("加载数据正常");
					//更新笔记本
					model.notebooks = model.notebooks.concat(r[0].result);
					model.updateNoteBooks(model.notebooks,true);
					
				}
			},
			error:function(){
				alert("加载数据失败，请您联系客服");
			}
		});
	});
}

//更新笔记本

model.updateNoteBooks=function(books,flag){
	if(!flag){
		//清空元素
		$("#notebooks").empty();
		//遍历笔记本
		for(var i = 0; i < books.length; i++){
			var cur_li = "<li class='online'>"+
									"<a>"+
									"<i class='fa fa-book' title='online' rel='tooltip-bottom'>"+
									"</i>"+books[i].cn_notebook_name+"</a></li>";
			//绑定当前索引
			cur_li = $(cur_li).data("currentIndex",i);
			$("#notebooks").append(cur_li);
		}
		
		var more = "<li class='online' id='moreBooks'>"+
		"<a>"+
		"<i class='fa fa-book' title='online' rel='tooltip-bottom'>"+
		"</i>more</a></li>";
		$("#notebooks").append($(more));

	}else{
		model.updateNoteBooks(books, false);
	}
};