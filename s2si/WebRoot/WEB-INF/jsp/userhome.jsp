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
			window.location.href="goTo.action?url=login.jsp";
		});

		$('#reg-btn').bind('click',function(event){
			window.location.href="goTo.action?url=register.jsp";
		});

		//单击签到图片 ajax异步请求 {"isCheck":1,"credit":51}  
		$('#logout-btn').bind('click',function(event){
			
		});
  

		$('.u-activities a').bind('click',function(event){
			var _this = $(this);
			_this.siblings().add(_this).removeClass();
			_this.addClass('dark');
		});
		
	});
		
	
	</script>
	<style> 	
	
		.main .m-logo {
			height : 155px;
			
		}
		.main .m-logo img {
			top : 42.5px;
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
			
		.m-btns,.m-info {
			width: 100%;
		}

		.m-btns {
			margin-top : 20px;
			margin-bottom : 40px;
			overflow : hidden;
			zoom : 1;
		}
			
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
		
		
		
		
		.m-acts {
			width : 100%;
		}
		
		.m-acts ul.u-activities {
			
			margin:0 auto;
			width: 100%;
			height: 240px;
			background: url(resources/image/zjmdb.png);
			background-size: cover;		
			background-position: 0 -898px;
			
		}
		
		.m-acts ul.u-activities li {
			display:block;
			float:left;
			width:156px;
			height:116px;
			position:relative;
		}
		
		.m-acts ul.u-activities li a {
			width : 60px;
			height : 86px;
			position:absolute;
			left:48px;
			top:15px;
			
			
		}
		.m-acts ul.u-activities li.yhzx {
			
		} 
		.m-acts ul.u-activities li.whd {
			margin-left:3px;
		} 
		.m-acts ul.u-activities li.wdy {
			margin-top:3px;
		} 
		.m-acts ul.u-activities li.lpzx {
			margin-left:3px;
			margin-top:3px;
		} 
		
		.m-acts ul.u-activities li.yhzx a{
			background:url(resources/image/yhzx-light.png);
			background-size:cover;
		}
		.m-acts ul.u-activities li.whd a{
			
			background:url(resources/image/whd-light.png);
			background-size:cover;
		}
		.m-acts ul.u-activities li.wdy a{
			width:60px;
			height : 96px;
			background:url(resources/image/wdy-light.png);
			background-size:cover;
		}
		.m-acts ul.u-activities li.lpzx a{
			background:url(resources/image/lpzx-light.png);
			background-size:cover;
		}
		
		.m-acts ul.u-activities li.yhzx a.dark{
			background:url(resources/image/yhzx-dark.png);
			background-size:cover;
		}
		.m-acts ul.u-activities li.whd a.dark{
			background:url(resources/image/whd-dark.png);
			background-size:cover;
		}
		.m-acts ul.u-activities li.wdy a.dark{
			background:url(resources/image/wdy-dark.png);
			background-size:cover;
		}
		.m-acts ul.u-activities li.lpzx a.dark{
			background:url(resources/image/lpzx-dark.png);
			background-size:cover;
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
				<img src="resources/image/logo-small.png"/>
			</div>
			<div class="m-btns">
			<% if(name==null) { %>
				<a href="goTo.action?url=login.jsp" class="btn btn-large btn-up" id="login-btn">登录</a>
				<a href="javasrcipt:void(0);" class="btn btn-large btn-bottom" id="reg-btn">注册</a>
			<% } else { %>
				<a href="javasrcipt:void(0);" class="btn-left" id="login-btn">注册</a>
				<a href="javasrcipt:void(0);" class="btn-right" id="reg-btn">退出</a>
			<% } %> 
			</div>
			
			<div class="m-info">
				<p>用户名/积分:123456</p>
			</div>
			
			<div class="m-check">
				<p class="checked">已签到</p>
			</div>
			
			<div class="m-acts">
				<%-- 
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
				--%>
				
				<ul class="u-activities">
					<li class="yhzx"><a href=""></a></li>
					<li class="whd"><a href="goTo.action?url=interact.jsp"></a></li>
					<li class="wdy"><a href="goTo.action?url=research.jsp"></a></li>
					<li class="lpzx"><a href="goTo.action?url=prize.jsp"></a></li>
				</ul>
			</div>
		</div>
  	</div>
 
  </body>
</html>
