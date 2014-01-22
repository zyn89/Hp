<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setHeader("Cache-Control","no-cache"); 
%>
<%
  	  Object name = session.getAttribute("name");
	 
	  Object credit = session.getAttribute("credit");
  	  Object isCheck = session.getAttribute("isCheck");
  	  int val=0;
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
		<script src="resources/js/jquery.cookie.js"></script>
		<script type="text/javascript">

	jQuery(function($){
		var str="<%=name%>";
		var qqq="<%=isCheck%>";
		console.log(str);
		console.log(qqq);
		if(str=="null"){
			$(".main .m-user div").hide();
			$(".main .check p").hide();
		}else{
			$(".main .m-user div").show();
			$(".main .check p").show();
		}
		if(str!="null" && <%=isCheck%>==0){
			console.log(<%=isCheck%>);
			$(".main .check p").text("签到");
		}else if(str!="null"){
			console.log(<%=isCheck%>);
			$(".main .check p").text("已签到");	
		}
		$('#j-whd').bind('click',function(event){
			window.location.href="goTo.action?url=interact.jsp";
		});
		$(".main .check p").bind("click",function(){
			$(".m-modelbox").fadeIn("fast");
			$.ajax({
					url: 'CheckIn.action',
					type: 'post',
				})
				.done(function(data) {
					location.href="goTo.action?url=userhome.jsp";
				})
				.fail(function(error) {
					console.log("error");
			 });
		});
		$(".entrance .wwz").bind("click",function(){
			location.href="phone.action";
		});

		//单击签到图片 ajax异步请求 {"isCheck":1,"credit":51}  
		$('#logout-btn').bind('click',function(event){
			 $.removeCookie('yhzx');
			 $.removeCookie('whd');
			 $.removeCookie('wdy');
			 $.removeCookie('lpzx');
			 $.ajax({
					url: 'Logout.action',
					type: 'post',
				})
				.done(function(data) {
					location.href="goTo.action?url=userhome.jsp";
				})
				.fail(function(error) {
					console.log("error");
			 });
		});
		/*
							<li class="whd"><a href="goTo.action?url=interact.jsp"></a></li>
					<li class="wdy"><a href="goTo.action?url=research.jsp"></a></li>
					<li class="lpzx"><a href="goTo.action?url=prize.jsp"></a></li>
		*/
		$("li.whd").data("url","goTo.action?url=interact.jsp");
		$("li.wdy").data("url","goTo.action?url=research.jsp");
		$("li.lpzx").data("url","goTo.action?url=prize.jsp");
		$("li.yhzx").data("url","goTo.action?url=userinfo_0121.jsp");
		$(".u-activities li").each(function(index,e){
			var name=$(this).attr("class");
			console.log($.cookie(name));
			if($.cookie(name)=="dark"){
				$(this).find("a").addClass("dark");
			}
		});
		$('.u-activities a').bind('click',function(event){
			var _this = $(this);
			_this.addClass('dark');
			var name=$(this).parent().attr("class");
			$.cookie(name, "dark", { expires: 1 });
			//location.href="goTo.action?url=interact.jsp";
			//return false;
			if(str=="null"){
				$(this).attr("href",null);
				return;
			}else{
				var url=$(this).parent().data("url");
				$(this).attr("href",url);
				location.href=url;
			}
		});
		$(".m-modelbox").hide();
		$("#modelbox").bind("click",function(){
			$(".m-modelbox").fadeOut("fast");
		});
	});
		
	
	</script>
		<style>
.main .m-logo {
	height: 120px;
	width: 106px;
	float: left;
}
.main{
	height:100%
}
.main .m-user {
	height: 120px;
	width: 150px;
	margin-left: 0px;
	float: left;
}

.main .check {
	height: 120px;
	width: 60px;
	float: left;
}

.main .check p {
	display: block;
	margin: 0 auto;
	width: 60px;
	height: 26px;
	line-height: 26px;
	color: #000;
	font-size: 12px;
	background: url(resources/image/userhome/checkin.png);
	background-size: cover;
	cursor: pointer;
	margin-top: 25px;
	float: right;
	margin-right: 5px;
}

.main .m-logo img { /*top : 42.5px;*/
	position: static;
	margin-top: 20px;
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

.main .entrance .wwz {
	background: url(resources/image/userhome/wwz.png);
	background-size: cover;
	width: 45px;
	height: 45px;
	float: right;
	cursor: pointer;
}

.m-info {
	width: 100%;
	margin-right: auto;
	margin-left: auto;
	margin-bottom: 5px;
	margin-top: 15px;
}

.m-info p {
	display: block;
	width: 226px;
	height: 34px;
	margin: 0 auto;
	line-height: 34px;
	background: url(resources/image/input1.png);
	background-size: cover;
	color: #999;
}

.m-check {
	width: 100%;
	height: 56px;
	margin-bottom: 10px;
}

.m-check p {
	display: block;
	margin: 0 auto;
	width: 118px;
	height: 56px;
	line-height: 72px;
	color: #fff;
	font-size: 14px;
	background: url(resources/image/checkIn.png);
	background-size: cover;
	cursor: pointer;
}

.m-check p.checked {
	background: url(resources/image/checked.png);
	background-size: cover;
	cursor: text;
}

.m-btns,.m-info {
	width: 100%;
}

.m-btns {
	margin-top: 20px;
	margin-bottom: 10px;
	overflow: hidden;
	zoom: 1;
}

.m-btns .btn-up,.m-btns .btn-bottom {
	display: block;
	width: 238px;
	height: 36px;
	line-height: 36px;
	display: block;
	margin: 0 auto;
	margin-bottom: 20px;
	font-weight: bold;
}

.m-btns .btn-up {
	background: url(resources/image/btn-green.png);
	background-size: cover;
}

.m-btns .btn-bottom {
	background: url(resources/image/btn-blue.png);
	background-size: cover;
}

.m-btns .btn-left,.m-btns .btn-right {
	display: block;
	width: 137px;
	height: 36px;
	line-height: 36px;
	display: block;
	font-weight: bold;
	font-size: 14px;
}

.m-btns .btn-left {
	background: url(resources/image/userhome/smallgreen.png);
	background-size: cover;
	float: left;
	margin-left: 5px;
}

.m-btns .btn-right {
	background: url(resources/image/userhome/smallorange.png);
	background-size: cover;
	float: right;
	margin-right: 5px;
}

.m-acts {
	width: 100%;
}

.m-acts ul.u-activities { /*margin:0 auto;*/
	width: 320px;
	height: 240px;
	background: url(resources/image/zjmdb.png);
	background-size: cover;
	background-position: 0 -898px;
}

.m-acts ul.u-activities li {
	display: block;
	float: left;
	width: 156px;
	height: 116px;
	position: relative;
}

.m-acts ul.u-activities li a {
	width: 60px;
	height: 86px;
	position: absolute;
	left: 48px;
	top: 15px;
}

.m-acts ul.u-activities li.yhzx {
	
}

.m-acts ul.u-activities li.whd {
	margin-left: 3px;
}

.m-acts ul.u-activities li.wdy {
	margin-top: 3px;
}

.m-acts ul.u-activities li.lpzx {
	margin-left: 3px;
	margin-top: 3px;
}

.m-acts ul.u-activities li.yhzx a {
	background: url(resources/image/userhome/yhzx-light.png);
	background-size: cover;
}

.m-acts ul.u-activities li.whd a {
	background: url(resources/image/userhome/whd-light.png);
	background-size: cover;
}

.m-acts ul.u-activities li.wdy a {
	width: 60px;
	height: 86px;
	background: url(resources/image/userhome/wdy-light.png);
	background-size: cover;
}

.m-acts ul.u-activities li.lpzx a {
	background: url(resources/image/userhome/lpzx-light.png);
	background-size: cover;
}

.m-acts ul.u-activities li.yhzx a.dark {
	background: url(resources/image/userhome/yhzx-dark.png);
	background-size: cover;
}

.m-acts ul.u-activities li.whd a.dark {
	background: url(resources/image/userhome/whd-dark.png);
	background-size: cover;
}

.m-acts ul.u-activities li.wdy a.dark {
	background: url(resources/image/userhome/wdy-dark.png);
	background-size: cover;
}

.m-acts ul.u-activities li.lpzx a.dark {
	background: url(resources/image/userhome/lpzx-dark.png);
	background-size: cover;
}
/*.m-modelbox {
			display:block;
			position:absolute;
			left : 60px;
			top :20px;
			width : 200px;
			height : 200px;	
			background : url(resources/image/modelboxdb.png);
			background-size : cover;
			z-index:1000
		}
.m-modelbox .u-contentbox {
			background : url(resources/image/boxcontent.png);
			background-size : cover;
			width : 140px;
			height : 93px;
			position : absolute;
			left :30px;
			top : 15px;
			display: table-cell;
			color: #fff;
			font-size: 14px;
			text-align: center;
			/*padding-top:20px
		}	
		
.m-modelbox a {
			display:block;
			width: 140px;
			height:22px;
			line-height:22px;
			margin: 0 auto;
			background:url(resources/image/btn-green.png);
			background-size:cover;
			letter-spacing: 3px;
			position:absolute;
			left : 30px;
			bottom : 20px;
		}*/
		.m-modelbox {
			display:block;
			width : 280px;
			height : 280px;	
			margin-left:20px;
			background : url(resources/image/modelboxdb.png);
			background-size : cover;
			z-index:1000;
		}
		
		.m-modelbox .u-contentbox {
			background : url(resources/image/boxcontent.png);
			background-size : cover;
			width : 240px;
			height : 160px;
			position : absolute;
			left :20px;
			top : 15px;
			display: table-cell;
			color: #fff;
			font-size: 18px;
			text-align: center;
			vertical-align: middle;
		}	
		
		.m-modelbox a {
			display:block;
			width: 240px;
			height:38px;
			line-height:40px;
			margin: 0 auto;
			background:url(resources/image/btn-green.png);
			background-size:cover;
			letter-spacing: 3px;
			position:absolute;
			left : 20px;
			bottom : 20px;
			cursor:pointer
		}
</style>
	</head>
	<body>
		<div id="wrap" style="position: relative">

			<div class="main" style="position: relative">
				<div class="top">
					<div class="m-logo">
						<img src="resources/image/logo-small.png"
							style="width: 90px; height: 90px" />
					</div>
					<div class="m-user" style="">
						<div
							style="text-align: start; color: white; margin-left: auto; margin-right: auto; font-size: 24px; margin-top: 25px;"><%=name%></div>
						<div
							style="text-align: start; color: white; margin-left: auto; margin-right: auto; font-size: 16px; margin-top: 5px; color: #bbb;">
							积分:<%=credit%></div>
					</div>
					<div class="check" style="">
						<p>
							已签到
						</p>
					</div>
				</div>
				<div class="m-btns">
					<% if(name==null) { %>
					<a href="goTo.action?url=login_0121.jsp" class="btn-left"
						id="login-btn">登录</a>
					<a href="goTo.action?url=register_0121.jsp" class="btn-right"
						id="logout-btn">注册</a>
					<% } else { %>
					<a href="goTo.action?url=register_0121.jsp" class="btn-left" id="">注册</a>
					<a href="#" onclick="return false;" class="btn-right"
						id="logout-btn">退出</a>
					<% } %>
				</div>

				<div class="entrance" style="height: 45px">
					<div class="wwz"></div>
				</div>
				<div class="m-acts" style="position: fixed; bottom: 0 ;z-index:1">
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
						<li class="yhzx">
							<a class="" onclick="return false;"
								href="goTo.action?url=userinfo_0121.jsp"></a>
						</li>
						<li class="whd">
							<a class="" onclick="return false;"
								href="goTo.action?url=interact.jsp"></a>
						</li>
						<li class="wdy">
							<a class="" onclick="return false;"
								href="goTo.action?url=research.jsp"></a>
						</li>
						<li class="lpzx">
							<a class="" onclick="return false;"
								href="goTo.action?url=prize.jsp"></a>
						</li>
					</ul>
				</div>
				<div class="m-modelbox" style="position: fixed; top: 20px">
					<div class="u-contentbox" >
						<br/>
						<br/>
						签到成功！
					</div>
					<a id="modelbox">确定</a>
				</div>
			</div>
		</div>

	</body>
</html>
