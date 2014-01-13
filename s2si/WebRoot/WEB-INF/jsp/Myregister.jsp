<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">
<head>
    <title>注册界面</title>
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache"> 
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="expires" content="0">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width">
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<link href="<%=path%>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="<%=path%>/resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <script language="javascript" type="text/javascript" src="<%=path%>/resources/js/jquery-1.9.1.js"></script>
	<script src="<%=path%>/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  </head>
	<style type="text/css">
		outline:none
		button,a {
			outline: none !important;
		}
	</style>


	
	<script>
			$(function(){
				$("p").addClass("hide");
				//登录检查
				$("form").find(".register").bind("click",function(){
					console.log("click");
					$(this).trigger("submit");
					$.ajax({
						url : "",
						type : "POST",
						data : {"aid":aid,
						},
						success : function(data) {
							
						},
					});
				});
				$("form").find(".reset").bind("click",function(){
					//console.log("click");
					//$(this).trigger("subit");
					$("p").addClass("hide");
					$("form").find("div").css("margin-top","30px");
					$('input').val("");
				});
				$("form").find(".register").submit(function(){
					var username = $('#username').val(),
						upass = $('#password').val(),
						confirm=$("#confirm").val(),
						place=$("#place").val(),
						tel=$("#tel").val();
					var flag1=true;
					if(!tel || !tel.trim()) {
						$("p.tel").removeClass("hide");
						$("p.tel").parent().css("margin-top","0px");
						flag1=false;
					}
					if(!username || !username.trim()) {
						$("p.username").removeClass("hide");
						$("p.username").parent().css("margin-top","0px");
						flag1=false;
					}
					if(!upass || !upass.trim()) {
						$("p.password").removeClass("hide");
						$("p.password").parent().css("margin-top","0px");
						flag1=false;
					}
					if(!confirm || !confirm.trim()) {
						$("p.confirm").removeClass("hide");
						$("p.confirm").parent().css("margin-top","0px");
						flag1=false;
					}
					console.log(flag1);
					if(flag1==false){
						return false;
					}else{					
						return true;
					}    
				});
			});
	</script>
<body>
	<div class="main" style="width:90%;margin-left:5%;margin-top:5px">
		<form action="Login.action" style="margin:0 auto;">
		  <div style="width:206px;margin-left:auto;margin-right:auto;margin-top:30px">
		  	 <p class="text-error tel hide">手机不能为空</p>
		      <input type="text" id="tel" placeholder="手机号（必填）" style="display:block;
		      height:30px;
		      font-size:25px"/>
		  </div>
		  <div style="width:206px;margin-left:auto;margin-right:auto;margin-top:30px">
		  	<p class="text-error username hide">用户名不能为空</p>
		      <input type="text" id="username" placeholder="用户名（必填）" style="display:block;
		      height:30px;
		      font-size:25px"/>
		  </div>
		  <div style="width:206px;margin-left:auto;margin-right:auto;margin-top:30px">
		  	  <p class="text-error password hide">密码不能为空</p>
		      <input type="password" id="password" placeholder="密码（必填）" style="display:block;
		      margin-left:auto;margin-right:auto;
		      height:30px;font-size:25px;"/>
		  </div>
		   <div style="width:206px;margin-left:auto;margin-right:auto;margin-top:30px">
		  	  <p class="text-error confirm hide">确认密码不能为空</p>
		      <input type="password" id="confirm" placeholder="确认密码（必填）" style="display:block;
		      margin-left:auto;margin-right:auto;
		      height:30px;font-size:25px;"/>
		  </div>
		   <div style="width:206px;margin-left:auto;margin-right:auto;margin-top:30px">
		      <input type="text" id="place" placeholder="门店（可选）" style="display:block;
		      margin-left:auto;margin-right:auto;
		      height:30px;font-size:25px;"/>
		  </div>
		  <div></div>
		  <div style="width:230px;margin-left:auto;margin-right:auto;margin-top:5%">
		      <button type="button" class="btn btn-large register" style="margin-left:18px;outline:none;">注册</button>
		      <button type="button" class="btn btn-large reset" style="margin-left:50px">重置</button>
		  </div>
		</form>
	</div>
</body>
</html>

