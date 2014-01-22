<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
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
			location.href="goTo.action?url=register_0121.jsp";
		});
		$("form input").focus(function(){
				$(this).parent().removeClass("error");
				if($(this).data("normal")){
					$(this).attr("placeholder",$(this).data("normal"));
				}
		});
		$("a.reg").bind("click",function(){
			var phone = $('input.phone').val(),
				pw	  = $('input.pw').val(),
				pw2   = $('input.pw2').val(),
				name = $('input.name').val(),
				shopName=$('input.shopName').val();
			console.log(phone);
			var flag=false;
			
			$("form input").each(function(index,e){
				if(index>3){
					return;
				}
				var tmp=$(this).attr("placeholder");
				$(this).data("normal",tmp);
				var str=$(this).val()
				if(str==""){
					$(this).parent().addClass("error");
					$(this).val("");
					$(this).attr("placeholder","该选项不能为空");
					flag=true;
				}
			});
			if(flag==false){
				if(pw.length<6 || pw.length>12){
					$('input.pw').parent().addClass("error");
					$('input.pw').val("");
					$('input.pw').attr("placeholder","密码为6-12位");
					flag=true;
				}
				if(pw!=pw2){
					$('input.pw2').parent().addClass("error");
					$('input.pw2').val("");
					$('input.pw2').attr("placeholder","密码前后不符合");
					flag=true;
				}
				if(phone.length!=11){
					$('input.phone').parent().addClass("error");
					$('input.phone').val("");
					$('input.phone').attr("placeholder","手机号码为11位");
					flag=true;
				}
			}
			if(flag==true){
				return;
			}else{
				//$("form input[type='submit']").trigger("click");
				var data={};
				data.phone=$('input.phone').val();
				data.name=$('input.name').val();
				data.shopName=$('input.shopName').val();
				data.uid=$('input.uid').val();
				data.pw=$("input.pw").val();
				console.log(data);
				$.ajax({
					url: 'register.action',
					type: 'post',
					data: data,
				})
				.done(function(data) {
					if(typeof(data)!="object"){
						data=JSON.parse(data);
					}
					if(!data.status){					
						location.href="goTo.action?url=userhome.jsp";
					}else{
						$(".m-modelbox").fadeIn();
					}
				})
				.fail(function(error) {
					console.log("error");
			 	});
			}
			/*if(!username || !username.trim() || !upass || !upass.trim()) {
					return false;
			} 
			return true;*/
			//location.href="Login.action";
		})
		
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
			z-index:1000;
		}
		.main .info input{
			width : 220px;
			height: 30px;
			line-height : normal;
			display: inline-block;
			border: 0px;
			margin:0px auto;
			outline: none;
			text-indent: 15px;
			font-size: 14px;
			font-weight: bold;
			color: #999;
			padding:0px;
			background-color: transparent;
			text-align:center;
			/*background: url(resources/image/input1.png);
			background-size:cover;*/
		}
		.bottom{
		position:absolute;
		bottom:0;
		width:100%;
		margin-left:auto;
		margin-right:auto;
		height:86px;
		background: url(resources/image/login_bottom.png);
		background-size:cover;
		background-color: transparent;
		z-index:0;
		}
		.main .val{
		color:white;
		font-size:16px;
		background-color: transparent;
		margin:0px auto;
		text-align:center;
		}
		.error input::-webkit-input-placeholder { color:#b94a48; }
		.error input:-moz-placeholder { color: #b94a48; }
		.main .info div{
			display:block;
			width: 260px;
			height:40px;
			line-height:40px;
			margin: 0 auto;
			background:url(resources/image/input1.png);
			background-size:cover;
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
  	<div id="wrap" style="position:relative;z-index:1">

	  	<div class="main" style="position:relative;z-index:100">
	  		<div class="m-logo">
				<img src="resources/image/logo-small.png"/>
			</div>
			<div class="val" style=""></div>
			<form action="register.action" method="post">
			<div class="info" style="margin-bottom:30px;margin-top:20px">
				<div class="">
					<input class="phone" type="text" name="phone" autocomplete="off" 
					style="margin-top:5px;"
					placeholder="请输入手机号码（必填）"/>
				</div>
				<div class="" style="margin-top:10px">
					<input class="name" type="text" name="name" autocomplete="off" 
					style="margin-top:5px;"
					placeholder="请输入姓名（必填）"/>
				</div>
				<div class="" style="margin-top:10px">
					<input class="pw" type="password" name="pw" autocomplete="off" 
					style="margin-top:5px;"
					placeholder="请输入密码（必填）"/>
				</div>
				<div class="" style="margin-top:10px">
					<input class="pw2" type="password" name="pw2" autocomplete="off" 
					style="margin-top:5px;"
					placeholder="请确认密码（必填）"/>
				</div>
				<div class="" style="margin-top:10px">
					<input class="shopName" type="text" name="shopName" autocomplete="off" 
					style="margin-top:5px;"
					placeholder="请输入门店（选填）"/>
				</div>
				<input type="submit" style="display:none"/>
			</div>
			</form>
			<div class="m-submit" style="z-index:1000">
				<a href="#" onclick="return false;" class="reg" style="margin-top:20px;z-index:2000">注册</a>
			</div>
			<div class="m-modelbox" style="">
					<div class="u-contentbox" >
						<br/>
						<br/>
						注册失败！
					</div>
					<a id="modelbox">确定</a>
			</div>
		</div>
	<div class="bottom"></div>
  	</div>
  </body>
</html>
