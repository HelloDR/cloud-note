
$(function(){
	$("#regist_username").blur(function(){
		//获取文本框的值
		var cn_user_name=$("#regist_username").val();
		if(cn_user_name.trim()==""){
			$("#warning_1").show();
			$("#warning_1>span").html("用户名不能为空");
			return;
		}
		var f=detect_user();
		if(!f){
			return;
		}
		//获取昵称
		var nick_name=$("#nickname").val();
		
		
		
	});
	$("#regist_username").focus(function(){
		$("#warning_1").hide();
		$("#warning_1>span").html("");
		
	});
	//获取密码
	
	$("#regist_password").blur(function(){
		var password=$("#regist_password").val();
		if(password.trim()==""||password==undefined){
			$("#warning_2").show();
			$("#warning_2>span").html("密码不能为空");
			return;
		}
	});
	//确认密码
	$("#final_password").blur(function(){
		//获取第一次输入的密码
		var password=$("#regist_password").val();
		//获取第二次输入的密码
		var final_password=$("#final_password").val();
		if(final_password.trim()==""){
			return;
		}
		if(password!=final_password){
			return;
		}
		
	});
	//登录事件
	$("#regist_button").click(regist);
	$("#login").click(login);
});

function regist(){
	//alert("正确触发");
	//获取用户名
	var cn_user_name=$("#regist_username").val();
	//获取密码器
	var cn_user_password=$("#regist_password").val();
	//获取昵称
	var cn_user_nick=$("#nickname").val();
	//发送请求
	$.ajax({
		url:"regist.do",
		type:"post",
		data:{"cn_user_name":cn_user_name,
		"cn_user_password":cn_user_password,"cn_user_nick":cn_user_nick},
		dataType:"json",
		success:function(r){
			if(r.status==0){
				$("#count").val(cn_user_name);
				$("#back").click();
			}
		},
		error:function(){
			alert("注册失败");
		}
	});
}

/*
 * 检测用户是否存在
 */
function  detect_user(){
	//获取用户名
	var cn_user_name=$("#regist_username").val();
	var flag=false;
	$.ajax({
		url:"check.do",
		type:"post",
		data:{"cn_user_name":cn_user_name},
		dataType:"json",
		success:function(r){
			if(r.status==0){
				$("#warning_1").show();
				$("#warning_1>span").text(r.msg).css({"color":"red"});
				flag=true;
				
			}else{
				$("#warning_1").show();
				$("#warning_1>span").text(r.msg).css({"color":"green"});
				flag=false;
			}
		},
		error:function(){
			alert("服务器繁忙！");
		}
		
	});
	return flag;
}

function login(){
	//获取用户名
	var cn_user_name=$("#count").val();
	if(cn_user_name.trim()==""){
		alert("用户名不能为空");
		return;
	}
	//获取密码
	var pwd=$("#password").val();
	if(pwd.trim()==""){
		alert("密码不能为空");		
		return;
	}
	$.ajax({
		url:"login.do",
		type:"post",
		data:{"cn_user_name":cn_user_name,"cn_user_password":pwd},
	    dataType:"json",
	    success:function(r){
	    	if(r.status==0){
	    		var cn_user_id=r.result.cn_user_id;
	    		SetCookie("cn_user_id",cn_user_id);
	    		window.location.href="edit.html";
	    	}
	    },
	    error:function(){
	    	alert("error");
	    }
	});
}


















