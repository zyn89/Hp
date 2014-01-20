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
	<title>问答活动</title>
	
	
	<link rel="stylesheet" href="resources/css/common.css" />
	<script src="resources/js/jquery-1.9.1.js"></script>
	<script src="resources/js/question.js"></script>
		
	<script>
	
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
	
		.m-answer ul li:last-child {
			margin-bottom: 10px;
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
			cursor: pointer;
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
		
		.main .m-result {
			width : 100%;
			
		}
		
		.main .m-result .m-resultbox {
			width :50px;
			height : 50px;
			background : url(resources/image/resultbox.png);
			background-size : cover;

			margin : 0 auto;
		}


		.main .m-result .m-resultbox img {
			width:100%;
		}
		.main .m-submit {
			width: 100%;
			margin-top:15px;
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
		
		.m-modelbox {
			display:none;
			position:absolute;
			left : 20px;
			top :200px;
			width : 280px;
			height : 280px;	
			background : url(resources/image/modelboxdb.png);
			background-size : cover;
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
		}
		
	</style>
</head>
<%
	  String aid=request.getParameter("aid");
 %>
<body>
<input type="hidden" name="aid" id="aid" value="<%=aid%>"/>
	<div id="wrap">
		<div class="main">
			<div class="m-logo">
				<img src="resources/image/logo-small.png" alt="">
			</div>
			<div class="m-question">
				<img src="resources/image/list.png" />
			</div>
			
			<div class="m-answer">
				<ul class="u-answer">
					<li>
						<span class="alph">A</span>
						<span class="content"></span>
						<span class="radio"></span>
						
					</li>
					<li>
						<span class="alph">B</span>
						<span class="content"></span>
						<span class="radio"></span>
						
					</li>
					<li>
						<span class="alph">C</span>
						<span class="content"></span>
						<span class="radio"></span>
						
					</li>
				</ul> 
			</div>
			<div class="m-result" style="display:none;">
				<div class="m-resultbox">
					<img src="resources/image/right.png"/>
				</div>
			</div>
			<div class="m-submit">
				<a href="javascript:void(0);" id="j-confirmbtn">提交</a>
			</div>
			<div class="m-modelbox">
				<div class="u-contentbox" >
					weddxgfxgfcgfcgfcg
				</div>
				<a href="javascript:void(0);" id="j-finishedbtn">完成</a>
			</div>
		</div>
	</div>
</body>
</html>

