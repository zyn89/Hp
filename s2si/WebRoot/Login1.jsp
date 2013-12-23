<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<legend>Login</legend>
	
		 <form name="loginForm" action="login.action" method="post">
		 	userName:<input type="text" name="userName"/><br/>
		 	password:<input type="password" name="password"/><br/>
		 	<input type="submit"/>
		 </form>
	</fieldset>
</body>
</html>