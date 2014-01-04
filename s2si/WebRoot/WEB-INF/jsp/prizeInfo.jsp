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
		jQuery(function($){


			
		
		});
	</script>
	<style>
	
	</style>
</head>
<%
	  String eid=request.getParameter("eid");
	  String descUrl=request.getParameter("descUrl");
 %>
<body>
	<div class="main">
		<div class="m-infopic">
			<img src="<%= descUrl %>"/>
		</div>
		<div class="m-submit">
			<button type="button">确定</button>
		</div>
	</div>
</body>
</html>

