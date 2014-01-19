<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">
<head>
	<base href="<%=basePath%>">  
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Document</title>
	
	<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
	<script src="resources/js/jquery-1.9.1.js"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>



	
	<script>

	
		//登录检查
			
			function loginDataCheck(){
				var username = $('#u-phone').val(),
					upass = $('#u-pass').val();
				if(!username || !username.trim() || !upass || !upass.trim()) {
					return false;
				} 
				return true;
			}
		//to register
		$(function(){
			$("#register-btn").click(function(){
				location.href = "goTo.action?url=register.jsp"; 
			});
		});
	
			
	</script>
	<style>
		body{
			width:100%;
			min-height: 100%;
			background: url(resources/image/mobilebg.png) repeat-y fixed;	
			background-size:cover;
		}
		ul {
			padding: 0px;
			margin: 0px;
		}
		.main {
			margin:0px auto;
			width:100%;
			overflow:hidden;
			zoom:1;
			text-align:center;
			
		}
		.header{
			margin: 0 auto;
			margin-bottom: 10px;
			height: 135px;
	 		border: 0px solid red;
	 		width : 95%;
		}
		
		.m-m-uinfo ul {
			list-style : none;
			margin-top : 20px;
			margin-left:auto;border: 1px solid black;
		}
		
		
		.m-uinfo ul input.input-lg {
			display: block;
			width: 238px;
			height: 36px;
			background-color: #54695F;
			line-height : 36px;
			
			margin: 0 auto;
			margin-bottom:25px;
			border: 0px;
			border-radius: 23px;
			background-image: url("resources/image/输入框-1.png");
			
			background-size: cover;
			font-weight: bold;
			text-align: center;
		}
		.btns{
		
			margin-top: 25px;
		}
		.btn-d1{
			width: 260px;
		  	height:43px;
		  	
			background-color: #83908A;
			
			border-radius: 25px;
			background-image: url("resources/image/按钮－绿.png");
			background-size: cover;
		
		}
		.btn-d2{
			width: 260px;
		  	height:43px;
		  	
			background-color: #83908A;
		
			border-radius: 25px;
			background-image: url("resources/image/按钮－蓝.png");
			background-size: cover;
		
		}
		
		.btn-d3{
			margin-top: 25px;
			width: 144px;
		  	height:45px;
		  	
			background-color: #586B71;
		 
			border-radius: 29px;
			background-image: url("resources/image/按钮－找回密码.png");
			background-size: cover;
		}
		.foot{
			
			margin: 0 auto;
			margin-top: 20px;
			width: 320px;
			height: 100px;
			
			background-image: url("resources/image/底部照片.png");
			background-size: cover;
			
		}
	</style>
</head>

<body>
	<div class="main">
		<div class="header">
			<div class="m-logo">
				<img src="resources/image/logobig.png" style="height: 135px; width: 135px;"/>
			</div>
		</div>
		<div class="m-login">
			
			<form class="m-uinfo" action="Login.action" method="post" onsubmit="return loginDataCheck()">
				<ul>
					<li>
						<input name="phone" class="input-lg" id="u-phone" type="tel" placeholder="请输入手机号码"/>			
					</li>
							
					<li>
						<input name="pw" class="input-lg" id="u-pass" type="password" placeholder="请输入密码" />					
					</li>
					
				</ul>
				<div class="btns" >
						<button class="btn-d1" type="submit">登录</button>
				</div></br>
						<button class="btn-d2" id="register-btn">注册</button></br>
				<div>
				
					<button class="btn-d3" type="button">找回密码</button></div>
					
				
			</form>
		</div>
		<div class="foot"></div>
	</div>
</body>
</html>

