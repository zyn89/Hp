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
	<title>调研</title>
	<link rel="stylesheet" href="resources/css/common.css" />
	<script src="resources/js/jquery-1.9.1.js"></script>
	<script src="resources/js/research2.js"></script>
	<script>	
		jQuery(function($){
					
		});
	</script>
	<style>
		.m-question {
			width:100%;
			margin-bottom: 20px;
		}
		
		.m-question img {
			width: 280px;
			height: 112px;
			margin: 0 auto;
		}

		.m-answer {
			width: 100%;
		}

		.m-answer ul{
			width: 310px;
			margin: 0 auto;
		}

		.m-answer ul li {
			display: block;
			height : 40px;
			zoom:1;
			position: relative;
			height: 50px;
			margin-bottom: 20px;
		}

		.m-answer ul li span {
			display: block;
			float: left;
			position: absolute;
			top : 3px;
		}

		.m-answer ul li span.alph {
			background: url(resources/image/bubble.png);
			background-size: cover;
			height: 40px;
			width: 56px;
			line-height: 36px;
			text-align: left;
			text-indent: 15px;
			color: #fff;
			font-size: 20px;
			left:0px;
		}

		.m-answer ul li span.content {
			background: url(resources/image/content.png);
			background-size: cover;
			height: 36px;
			width: 200px;
			left : 60px;
			line-height: 36px;
			color: #fff;
			font-size: 16px;
		}
		
		.m-answer ul li span.radio {
			width: 47px;
			height: 50px;
			background: url(resources/image/radio-dark.png);
			background-size: cover;
			top:-3px;
			right : 0px;
		}
		
		.m-answer ul li span.tip {
			width: 82px;
			height: 30px;
			background: url(resources/image/rate.png);
			background-size: cover;
			top:40px;
			right : 20px;
			text-align: left;
			line-height: 28px;
			color: #fff;
			text-indent:50px;
		}

		.m-answer ul li span.radio-light {
			background: url(resources/image/radio-light.png);
			background-size: cover;
		}

		.main .m-submit {
			width: 100%;
			margin-top:35px;
		}

		.main .m-submit a {
			display:block;
			width: 260px;
			height:40px;
			line-height:40px;
			margin: 0 auto;
			background:url(resources/image/btn-blue.png);
			background-size:cover;
			letter-spacing: 3px;
		}
		.main .modal{
			background-image:url(resources/image/bac3.png);
			background-size:100% 100%;
			height:250px;
			width:300px;
			background-color:transparent;
			box-shadow:none;
			border:none;
			top:50%;overflow-y:auto;
			position:absolute;
			margin-left:10px;
			z-index:1;
			display:none;
		}
		.main .modal-body{
			background-image:url(resources/image/modal_content.png);
			background-size:100% 100%;
			position:absolute;
			top:5px;
			height:150px;
			width:260px;
			margin-left:20px;
			/*background-color:transparent;*/
			box-shadow:none;
			border:none;
		}
		.main .res{
			margin-left:25px;
			width:210px;
			height:150px;
			font-size:16px;
			color:white;
			top:40%;
			position:absolute;
			z-index:2;		
		}
		.modal .btn{
			display:block;
			width: 260px;
			height:40px;
			line-height:40px;
			margin: 0 auto;
			background:url(resources/image/btn-green.png);
			background-size:cover;
			letter-spacing: 3px;
			position:absolute;
			bottom:5%;
			margin-left:20px;
		}
	</style>
</head>
<%
	  String sid=request.getParameter("sid");
	  String credit=request.getParameter("credit");
 %>
<body>
	<input id="sid" type="hidden" value="<%= sid %>"/>
	<input id="credit" type="hidden"  value="<%= credit %>"/>
	

	<div id="wrap">
		    <div class="main">
			<div class="m-logo">
				<!--<img src="resources/image/logo.png" alt="">-->
			</div>
			<div class="m-question">
				<img src="resources/image/list.png" />
			</div>
				<div id="myModal" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-body">
				    	<div class="res">感谢您参与答题<br>恭喜您获得了<span>12</span>积分</div>
				  </div>
				  <a href="javascript:void(0);" class="btn">确定</a>
				</div>
			<div class="m-result" style="display:none;">

			</div>
			<div class="m-answer">
				<ul class="u-answer">
					<li>
						<span class="alph">A</span>
						<span class="content">调研内容</span>
						<span class="radio"></span>
						<span class="tip">30%</span>
					</li>
					<li>
						<span class="alph">B</span>
						<span class="content">调研内容</span>
						<span class="radio"></span>
						<span class="tip">30%</span>
					</li>
					<li>
						<span class="alph">C</span>
						<span class="content">ksjdfsdjkfhjsdhfjh</span>
						<span class="radio"></span>
						<span class="tip">30%</span>
					</li>
				</ul>
			</div>
			<div class="m-submit">
				<a href="javascript:void(0);" id="j-confirmbtn">提交</a>
			</div>
		</div>
	</div>
</body>
</html>

