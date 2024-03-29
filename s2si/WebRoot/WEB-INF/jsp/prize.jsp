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
	
	<link rel="stylesheet" href="resources/css/common.css" />
	<link rel="stylesheet" href="resources/js/ExceptionDisplayBox/css/exceptionMsgBox.css" />
	<script src="resources/js/jquery-1.9.1.js"></script>
	<script src="resources/js/wScratchPad.js"></script>
	<script src="resources/js/ExceptionDisplayBox/js/exceptionMsgBox.js"></script>
	<script type="text/javascript" src="resources/js/prize.js"></script>
	<style>
	
	

		.main .m-tabs {
			
			width : 100%;
		}
		.main .m-tabs .m-tabs-btns {


		}

		.main .m-tabs .m-tabs-btns ul {
			overflow: hidden;
			zoom: 1;
		}

		.main .m-tabs .m-tabs-btns ul li {
			display: block;
			height: 32px;
			width: 80px;
			text-align: center;
			line-height: 30px;
			float:left;
			cursor : pointer;
		}
		.main .m-tabs .m-tabs-btns ul li.tab-light {
			background:url('resources/image/tab-light.png') no-repeat;
			background-size: cover;
			color: #fff;
			
		}

		.main .m-tabs .m-tabs-btns ul li.tab-dark {
			background:url('resources/image/tab-dark.png') no-repeat;
			background-size: cover;
			color: #999;
		}
		.main .m-tabs .m-tabs-btns ul li a {
			text-align: center;
			text-decoration: none;
			color:inherit;
			font-weight:bold;
			font-size:12px;
		}

		.u-pitem {
			width: 100%;
			height: 92px;
			background: url('resources/image/listdb.png') no-repeat;
			background-size:cover;
			vertical-align: middle;
			text-align: center;
			position: relative;
			display: block;
			margin-bottom: 2px;
			cursor: pointer;
		}

		.u-pitem .u-prize{
			display: inline-block;
			height: 80%;
			position: absolute;
			left:6%;
			top:10%;
			width: 220px;
		}

		.u-pitem .u-arrow {
			display: inline-block;
			height:30%;
			position: absolute;
			right:7%;
			top:39%;
		}
		
		.m-tabs-content {
			margin:0 auto;
			width : 100%;
		}
	
		.m-tabs-content .tab-content {
			margin:0 auto;
			width : 100%;
		}

		.m-tabs-content .tab-content .m-credit {
			background:url(resources/image/lefttimes.png) no-repeat;
			background-size: contain;
			height: 34px;
			width: 134px;
			margin:0 auto;
			line-height:32px;
			color : #fff;
			font-weight : bold;
			font-size:13px;
		}
		
		.m-tabs-content .tab-content .m-pic {
			width: 80%;
			margin: 0 auto;
			display: block;
			margin-top: 15px;
		}
		
		.m-tabs-content .tab-content .m-ggk {
			margin-top: 20px;
			width: 100%;
			margin-left: auto;
			margin-right: auto;
			height :98px;
		}
		
		.m-tabs-content .tab-content .u-ggk {
			width: 98px;
			height: 98px;
			display: inline-block;
		}
		.m-tabs-content .tab-content .u-ggk:first-child {
			margin-left:1%;
		}
		
		.m-tabs-content .tab-content .u-ggk:last-child {
			margin-left:1%;
		}
	</style>


	
	<script >
		
	</script>
	<style>
	
	</style>
</head>

<body>
	<input id="lid" type="hidden" name="lid" />
	<div id="wrap">
			<div class="main">
			
				<div class="m-logo">
					<img src="resources/image/logo-small.png"/>
				</div>
				<div class="m-tabs">
					<div class="m-tabs-btns">
						<ul>
							<li id="one#" class="tab-dark"><a href="javascript:void(0);">兑奖</a></li>
							<li id="two#" class="tab-dark"><a href="javascript:void(0);">抽奖</a></li>
							<li id="three#" class="tab-dark"><a href="javascript:void(0);">奖品</a></li>
						</ul>
					</div>
					<div class="m-tabs-content">
						<div id="one" style="display:none;" class="tab-content">
							<ul id="exchange-items">
							
							</ul>
						</div>
						<div id="two" style="" class="tab-content">
							<div class="m-credit" >
								<p >剩余次数：5</p>
							</div>
							<img class="m-pic" src="resources/image/prizedb.png" alt=""></a>
							<div class="m-ggk">
								 <div class="u-ggk"></div>
								 <div class="u-ggk"></div>
								 <div class="u-ggk"></div> 
								 <div id="lottery_result" style="display:none;">
								 </div>
							</div>
						</div>
						<div id="three" style="display:none;" class="tab-content">
							<ul id="prize-items">
							
							</ul>
						</div>
					</div>
				</div>
			</div>
	</div>
	<li id="u-pitem-clone" class="u-pitem" style="display:none;">	
		<img class="u-prize" src="resources/image/list.png" alt="">						
		<img class="u-arrow" src="resources/image/arrow.png" alt="">
	</li>
</body>
</html>

