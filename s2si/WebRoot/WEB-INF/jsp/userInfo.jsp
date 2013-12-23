<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
 <meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport"/>
<link type="text/css" rel="stylesheet" href="resources/css/userInfo.css"/>

<title>个人中心</title>
<script type="text/javascript">

 	function returnMain(){
 		window.location.href="goTo.action?url=main.jsp";
 	}
 
</script>
</head>
<body>

	<p id="title">个人信息</p>
	

	<center>
		<form id="info" name="fom1" action="UpdateUserSelf.jsp">
			<input type="hidden" name="action" value="update">
			<p>
				<span>姓名：</span> <input type="text" name="username" required="required" value="test">
			<p>
				<span>电话：</span>  <input type="text" name="tel" required="required" value="1231">
			
			<p>
				<span>门店：</span>  <input type="text" name="depart" value="北京">
			<p>
				<span>积分：</span>  <input type="text" name="score"  readonly value="1000" >
				 
			<p> <input class="button" type="submit" value="修改" />
			<input class="button" type="reset" value="重置" />
		</form>
		<!--  <form name="fom2" action="goTo.action">
			<input class="return" type="submit" value="返回" />
		</form>-->
		<input class="return" type="button" value="返回" onclick="returnMain()"/>
	</center>


</body>
</html>