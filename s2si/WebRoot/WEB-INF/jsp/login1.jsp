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
	
	<script src="resources/js/jquery-1.9.1.js"></script>
	


	
	<script>

	
		//登录检查
			
			function loginDataCheck(){
				var username = $('#phone').val(),
					upass = $('#pw').val();
				if(!username || !username.trim() || !upass || !upass.trim()) {
					return false;
				} 
				return true;
			}
	
	
			
	</script>
	<style>
		
	</style>
</head>

<body>
	<div class="main">
		<div class="m-login">
			
			<form action="Login.action" method="get" onsubmit="return loginDataCheck()">
				<label for="phone">手机号:</label>
				<input name="phone" id="phone"  type="tel"/>
				<label for="pw">密码:</label>
				<input name="pw" id="pw" type="password"/>
				<button type="submit">登录</button>
			</form>
		</div>
	</div>
</body>
</html>

