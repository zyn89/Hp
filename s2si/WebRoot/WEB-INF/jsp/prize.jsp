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
	
	<script src="resources/js/jquery-1.9.1.js"></script>
	<script src="resources/js/wScratchPad.js"></script>
	<script type="text/javascript" src="resources/js/prize.js"></script>
	<style>
	
		html,body {
			height:100%;
			width : 100%;
		}

		body,ul {
			margin:0px;
			padding:0px;
		}
		
		ul {
			list-style: none;
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
			
		}
		
		.main .m-logo {
			 height: 135px;
			 border: 1px solid red;
			 width : 100%;
			 margin-bottom:10px;
		}

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
			border: 1px solid red;
			
			
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
	<div id="wrap">
			<div class="main">
			
				<div class="m-logo">

				</div>
				<div class="m-tabs">
					<div class="m-tabs-btns">
						<ul>
							<li id="one#" class="tab-light"><a href="javascript:void(0);">兑奖</a></li>
							<li id="two#" class="tab-light"><a href="javascript:void(0);">抽奖</a></li>
							<li id="three#" class="tab-light"><a href="javascript:void(0);">奖品</a></li>
						</ul>
					</div>
					<div class="m-tabs-content">
						<div id="one" style="display:none;" class="tab-content">
							<ul id="exchange-items">
								<li class="u-pitem">	
										<img class="u-prize" src="resources/image/list.png" alt="">						
										<img class="u-arrow" src="resources/image/arrow.png" alt="">
								</li>
									<li class="u-pitem">	
										<img class="u-prize" src="resources/image/list.png" alt="">
								
										<img class="u-arrow" src="resources/image/arrow.png" alt="">
								</li>
								<li class="u-pitem">	
										<img class="u-prize" src="resources/image/list.png" alt="">
								
										<img class="u-arrow" src="resources/image/arrow.png" alt="">
								</li>
								<li class="u-pitem">	
										<img class="u-prize" src="resources/image/list.png" alt="">
								
										<img class="u-arrow" src="resources/image/arrow.png" alt="">
								</li>
							</ul>
						</div>
						<div id="two" style="" class="tab-content">
							<div class="m-credit" >
								<p style="padding-left: 22px;">剩余次数：12</p>
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

