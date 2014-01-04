<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>userhome</title>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%--<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	--%>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="resources/js/jquery-1.9.1.js"></script>
	<script type="text/javascript">

	jQuery(function($){
		$('#j-whd').bind('click',function(event){
			window.location.href="goTo.action?url=interact.jsp";
		});



		$('#login-btn').bind('click',function(event){
			window.location.href="goTo.action?url=login1.jsp";
		});

		$('#reg-btn').bind('click',function(event){
			window.location.href="goTo.action?url=register.jsp";
		});
		
	});
		
	
	</script>
	<style> 
		body {
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
		.m-btns,.m-info {
			width: 100%;
		}
		.m-btns {
			height: 80px;

		}
		.m-info {
			height: 42px;
		}
		.m-acts {
	
		}

		.m-acts .m-acts-l ul,.m-acts .m-acts-r ul {
			list-style: none;
		}
		.m-acts .m-acts-l {
			width: 60%;
			float: left;
		}
		.m-acts .m-acts-r {
			width: 40%;
			float:right;
		}
	</style>
  </head>
  <%
	  Object name = request.getAttribute("name");
	  Object credit = request.getAttribute("credit");
	 
 %>
  <body>
 	<div class="main">
		<div class="m-btns">
			<button type="button" id="login-btn">µÇÂ¼</button>
			<button type="button" id="reg-btn">×¢²á</button>
		</div>
		<div class="m-info">
		<% if(name!=null && !((String)name).equals("") && credit!=null) { %>
			<p><%= (String)name %></p>
			<p><%= (Integer)credit %></p>
		<% } %>
		</div>
		<div class="m-acts">
			<div class="m-acts-l">
				<ul>
					<li><a href="goTo.action?url=userInfo2.jsp"><img src="resources/image/a1.jpg" alt=""></a></li>
					<li><a id="j-whd" href="javascript:void(0);"><img src="resources/image/a2.jpg" alt=""></a></li>
					<li><a href="javascript:void(0);"><img src="resources/image/a3.jpg" alt=""></a></li>
				</ul>
			</div>
			<div class="m-acts-r">
				<ul>
					<li><a href="javascript:void(0);"><img src="resources/image/a4.jpg" alt=""></a></li>
					<li><a href="goTo.action?url=prize.jsp"><img src="resources/image/a5.jpg" alt=""></a></li>
				</ul>
			</div>
		</div>
	</div>
  </body>
</html>
