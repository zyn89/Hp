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

		//单击签到图片 ajax异步请求 {"isCheck":1,"credit":51}  
		$('#logout-btn').bind('click',function(event){
			
		});
		
	});
		
	
	</script>
	<style> 
		html,body {
			height:100%;
			width : 100%;
			font-family:"黑体","微软雅黑";
			font-size: 14px;
		}

		body,ul {
			margin:0px;
			padding:0px;
		}
		
		ul {
			list-style: none;
		}

		button ,a {
			outline: none !important;
		}
		a {
			text-decoration: none;
			color: #fff;
		}

		#wrap {
			width:100%;
			min-height: 100%;
			background: url(resources/image/背景.png) repeat-y fixed;	
			background-size:cover;
		}

		.main {
			width : 320px;
			min-height: 100%;
			margin : 0px auto;
			overflow:hidden;
			zoom:1;
			text-align:center;
		}
	

		.main .m-logo {
			 height: 135px;
			 border: 1px solid red;
			 width : 100%;
			 margin-bottom:10px;
			 position: relative;
		}

		.main .m-logo .m-wwz {
			background: url(resources/image/wwz.png);
			background-size: cover;
			width: 72px;
			height: 52px;
			position: absolute;
			left: 5px;
			top: 5px;
		}

		.m-btns .btn-left,
		.m-btns .btn-right {
			display: block;
			width: 112px;
			height: 34px;
			line-height: 34px;
			display: block;
			font-weight: bold;
		}

		.m-btns .btn-left {
			background: url(resources/image/btnsm-blue.png);
			background-size:cover;
			float : left;
			margin-left: 5px;
		}
		
		.m-btns .btn-right {
			background: url(resources/image/btnsm-logout.png);
			background-size:cover;	
			float : right;
			margin-right:5px;
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
			width : 100%;
			margin-right: auto;
			margin-left: auto;
			margin-bottom : 5px;
			margin-top : 15px;
		}
		
		.m-info p {
			display: block;
			width: 226px;
			height: 34px;
			margin: 0 auto;
			line-height : 34px;
			background: url(resources/image/input1.png);
			background-size: cover;
			color:#999;
		}
		
		.m-check {
			width:100%;
			height:56px;
			margin-bottom: 10px;
		}

		.m-check p {
			display: block;
			margin:0 auto;
			width:118px;
			height:56px;
			line-height: 72px;
			color: #fff;
			font-size: 14px;
			background: url(resources/image/checkIn.png);
			background-size: cover;
			cursor : pointer;
		}

		.m-check p.checked {
			background: url(resources/image/checked.png);
			background-size: cover;
			cursor : text;
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
				<a href="javasrcipt:void(0);" class="m-wwz" id="j-wwz"></a>
			</div>
			<div class="m-btns">
				<a href="javasrcipt:void(0);" class="btn-left" id="login-btn">登录</a>
				<a href="javasrcipt:void(0);" class="btn-right" id="reg-btn">退出</a>
			</div>
			<div class="m-info">
				<p>用户名/积分:123456</p>
			</div>
			
			<div class="m-check">
				<p class="checked">已签到</p>
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
