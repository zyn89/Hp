<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
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
		$(".m-modelbox").hide();
		$("#modelbox").bind("click",function(){
			location.href="goTo.action?url=login_0121.jsp";
		});
		$("a.login").bind("click",function(){
			var username = $('input.phone').val(),
					upass = $('input.pwd').val();
			if(!username || !username.trim() || !upass || !upass.trim()) {
					return false;
			}
			//$("input[type='submit']").trigger("click");
			var data={};
				data.phone=$('input.phone').val();
				data.pw=$("input.pwd").val();
				//console.log(data);
				$.ajax({
					url: 'Login.action',
					type: 'post',
					data: data,
				})
				.done(function(data) {
					var data=JSON.parse(data);
					console.log(data["status"]);
					if(!data.status){					
						location.href="goTo.action?url=userhome.jsp";
					}else{
						$(".m-modelbox").fadeIn();
					}
				});
		});
		
	});
		
	
	</script>
	<style> 	
		img{
			border:none;
		}
		.main .m-logo {
			height : 155px;
			
		}
		.m-logo{
			border:none;
		}
		.main .m-logo img {
			top : 42.5px;
			border:none;
			background-color: transparent;
			box-shadow:none;
			z-index:100;
			border:0px solid red;
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
		.main .m-submit a.login{
			display:block;
			width: 260px;
			height:40px;
			line-height:40px;
			margin: 0 auto;
			background:url(resources/image/btn-green.png);
			background-size:cover;
			letter-spacing: 3px;
		}
		.main .info div.username{
			display:block;
			width: 260px;
			height:40px;
			line-height:40px;
			margin: 0 auto;
			background:url(resources/image/input1.png);
			background-size:cover;
			letter-spacing: 3px;
		}
		.main .info div.password{
			display:block;
			width: 260px;
			height:40px;
			line-height:40px;
			margin: 0 auto;
			background:url(resources/image/input1.png);
			background-size:cover;
			letter-spacing: 3px;
		}
		.main .m-submit a.reg{
			display:block;
			width: 260px;
			height:40px;
			line-height:40px;
			margin: 0 auto;
			background:url(resources/image/btn-blue.png);
			background-size:cover;
			letter-spacing: 3px;
		}
		.main .m-submit a.pwd{
			display:block;
			width: 125px;
			height:40px;
			line-height:40px;
			margin: 0 auto;
			background:url(resources/image/findpwd.png);
			background-size:cover;
			letter-spacing: 3px;
		}
		.main .username .logo{
			display:inline-block;
			width: 20px;
			height:20px;
			line-height:20px;
			margin: 10px auto;
			background:url(resources/image/small_username.png);
			background-size:cover;
			letter-spacing: 3px;
			float:left;
		}
		.main .password .logo{
			display:inline-block;
			width: 20px;
			height:20px;
			line-height:20px;
			margin: 10px auto;
			background:url(resources/image/small_pwd.png);
			background-size:cover;
			letter-spacing: 3px;
			float:left;
		}
		.main .info input{
			width : 200px;
			height: 30px;
			line-height : 20px;
			display: inline-block;
			border: 0px;
			margin:0px auto;
			outline: none;
			text-indent: 15px;
			font-size: 14px;
			font-weight: bold;
			color: #999;
			padding-right:15px;
			padding:0px;
			background-color: transparent;
			/*background: url(resources/image/input1.png);
			background-size:cover;*/
		}
		.bottom{
		position:absolute;
		bottom:0;
		width:100%;
		margin-top:100px;
		margin-left:auto;
		margin-right:auto;
		height:86px;
		background: url(resources/image/login_bottom.png);
		background-size:cover;
		background-color: transparent;
		}
		.main .val{
		color:white;
		font-size:16px;
		background-color: transparent;
		margin:0px auto;
		text-align:center;
		}
		.m-modelbox {
			display:block;
			position:absolute;
			left : 20px;
			top :100px;
			width : 280px;
			height : 280px;	
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
			cursor:pointer;
		}				
	</style>
  </head>
  <%
  	  Object name = session.getAttribute("name");
	 
	  Object credit = session.getAttribute("credit");
  	  Object isCheck = session.getAttribute("isCheck");
	 
 %>
  <body>
  	<div id="wrap" style="position:relative;scroll:auto">

	  	<div class="main" style="position:relative;z-index:1000">
	  		<div class="m-logo">
	  			<a href="phone.action" class="m-wwz" id="j-wwz"></a>
				<img src="resources/image/logo-small.png"/>
			</div>
			<div class="val" style=""></div>
			<form action="Login.action" method="post">
			<div class="info" style="margin-bottom:30px;margin-top:20px">
				<div class="username">
					<div class="logo" style="margin-left:15px"></div>
					<input class="phone" type="text" name="phone" autocomplete="off" 
					style="margin-top:5px;margin-left:1px"
					placeholder="请输入用户名"/>
				</div>
				<div class="password" style="margin-top:20px">
					<div class="logo" style="margin-left:15px"></div>
					<input class="pwd" type="password" name="pw" autocomplete="off" 
					style="margin-top:5px;margin-left:1px"
					placeholder="请输入密码"/>
				</div>
				<input type="submit" style="display:none"/>
			</div>
			</form>
			<div class="m-submit">
				<a href="#" onclick="return false;" class="login" style="z-index:1000">登录</a>
				<a href="goTo.action?url=register_0121.jsp" class="reg" style="margin-top:20px;z-index:1000">注册</a>
				<a href="goTo.action?url=findpwd.jsp" class="pwd" style="margin-top:20px;z-index:1000">找回密码</a>
			</div>
			<div class="m-modelbox" style="">
					<div class="u-contentbox" >
						<br/>
						<br/>
						登录失败！
					</div>
					<a id="modelbox">确定</a>
			</div>
		</div>
	<div class="bottom">
	</div>
  	</div>
  </body>
</html>
