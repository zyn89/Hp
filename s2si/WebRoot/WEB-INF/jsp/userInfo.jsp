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

<title>��������</title>
<script type="text/javascript">

 	function returnMain(){
 		window.location.href="goTo.action?url=main.jsp";
 	}
 
</script>
</head>
<body>

	<p id="title">������Ϣ</p>
	

	<center>
		<form id="info" name="fom1" action="UpdateUserSelf.jsp">
			<input type="hidden" name="action" value="update">
			<p>
				<span>������</span> <input type="text" name="username" required="required" value="test">
			<p>
				<span>�绰��</span>  <input type="text" name="tel" required="required" value="1231">
			
			<p>
				<span>�ŵ꣺</span>  <input type="text" name="depart" value="����">
			<p>
				<span>���֣�</span>  <input type="text" name="score"  readonly value="1000" >
				 
			<p> <input class="button" type="submit" value="�޸�" />
			<input class="button" type="reset" value="����" />
		</form>
		<!--  <form name="fom2" action="goTo.action">
			<input class="return" type="submit" value="����" />
		</form>-->
		<input class="return" type="button" value="����" onclick="returnMain()"/>
	</center>


</body>
</html>