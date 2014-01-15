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
	<link rel="stylesheet" href="resources/css/common.css" />
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

		//单击签到图片 ajax异步请求 {"isCheck":1,"credit":51}  
		$('#logout-btn').bind('click',function(event){
			
		});
		
	});
		
	
	</script>
	<style> 		
		.m-btns .btn-up,
		.m-btns .btn-bottom {
			display: block;
			width: 238px;
			height: 36px;
			line-height: 36px;
			display: block;
			margin: 0 auto;
			margin-bottom:20px;
			font-weight: bold;
		}

		.m-btns .btn-up {
			background: url(resources/image/btn-green.png);
			background-size:cover;
		}
		
		.m-btns .btn-bottom {
			background: url(resources/image/btn-blue.png);
			background-size:cover;	
		}
		
		.m-btns,.m-info {
			width: 100%;
		}

		.m-btns {
			margin-top : 20px;
			overflow : hidden;
			zoom : 1;
		}
		
		.m-info {
			clear: both;
			height: 30px;
		
			border-radius : 5px;
			width : 80%;
			margin-right: auto;
			margin-left: auto;
			margin-bottom : 5px;
		}
		
		.m-info p {
			line-height : 30px;
			font-size : 16px;
			border : 1px solid #999;
		}
		
		.m-acts {
	
		}
		
		.m-acts .m-acts-l {
			width: 60%;
			float: left;
		}
		.m-acts .m-acts-r {
			width: 40%;
			float:right;
		}

		.m-acts .m-acts-l ul,
		.m-acts .m-acts-r ul {
			list-style: none;
			
		}
		
		.m-acts .m-acts-l ul li {
			display : block;
			margin-top : 15px;
		}
		
		.m-acts .m-acts-l ul>li:first-child {
			margin-top : 0px;
		}
		
		.m-acts .m-acts-r ul {
			margin-top : 50px;
		}
		
		.m-acts .m-acts-r ul>li {
			margin-top : 20px;
		}
		
		.m-acts .m-acts-r ul>li:first-child {
			margin-top : 0px;
		}
		
	</style>
  </head>
  <%
  	  Object name = session.getAttribute("name");
	 
	  Object credit = session.getAttribute("credit");
  	  Object isCheck = session.getAttribute("isCheck");
	 
 %>
  <body>
  	<div id="wrap">

	  	<div class="main">
	  		<div class="m-logo">

			</div>
			<div class="m-btns">
			<% if(name==null) { %>
				<a href="javasrcipt:void(0);" class="btn btn-large btn-up" id="login-btn">登录</a>
				<a href="javasrcipt:void(0);" class="btn btn-large btn-bottom" id="reg-btn">注册</a>
			<% } else { %>
				<a href="javasrcipt:void(0);" class="btn btn-large btn-up" id="logout-btn">退出</a>
				<a href="javasrcipt:void(0);" class="btn btn-large btn-bottom" id="reg-btn">注册</a>
			<% } %> 
			</div>
			<div class="m-info">
			<% if(name!=null && credit!=null) { %>
				<p><%= name %>/<%= credit %></p>
			<% } %>
			</div>
			<div class="m-acts">
				<div class="m-acts-l">
					<ul>
						<li><a href="goTo.action?url=userInfo2.jsp"><img src="resources/image/yhzx.jpg" alt=""></a></li>
						<li><a id="j-whd" href="javascript:void(0);"><img src="resources/image/whd.jpg" alt=""></a></li>
						<li><a href="goTo.action?url=research.jsp"><img src="resources/image/wdy.jpg" alt=""></a></li>
					</ul>
				</div>
				<div class="m-acts-r">
					<ul>

					<% if(isCheck != null && (Integer)isCheck == 1){ %>
						
					<% } else{ %>
						<li><a href="javascript:void(0);"><img src="resources/image/qd.jpg" alt=""></a></li>
					<% } %>
						
						<li><a href="goTo.action?url=prize.jsp"><img src="resources/image/lp.jpg" alt=""></a></li>
					</ul>
				</div>
			</div>
		</div>
  	</div>
 
  </body>
</html>
