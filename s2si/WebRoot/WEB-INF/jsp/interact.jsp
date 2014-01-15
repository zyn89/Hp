<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>微互动</title>
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
	<script src="resources/js/interact.js"></script>

	<script>
		// jQuery(function($){

		// 	function diplayActs(data) {
		// 		var $ul = $('<ul>');
				
		// 		$.each(data,function(index,value){
		// 			var aid = value.aid,
		// 				activityUrl = value.activityUrl,
		// 				credit = value.credit,
		// 				descUrl = value.descUrl,
		// 				done = value.done,
		// 				score = value.score,
		// 				type = value.type,
		// 				$li = $('<li>'),
		// 				$a = $('<a>'),
		// 				$img = $('<img>');

		// 			$li.attr({
		// 				'data-aid' : aid,
		// 				'data-credit' : credit,
		// 				'data-type' : type,
		// 				'data-score' : score
		// 			});

		// 			$a.attr({
		// 				href:'interact_beginActivity.action?aid=' + aid + '&type=' + type + '&descUrl=' + descUrl
		// 			});

		// 			if(done) {
		// 				//设置图片是活动完成的图片
		// 			} else {
		// 				$img.attr({
		// 					src : activityUrl
		// 				});
		// 			}

		// 			$a.append($img).appendTo($li);
		// 			$li.appendTo($ul);
		// 		});

		// 		$('.m-interacts').empty().append($ul);
		// 	}

			
		// 	$.ajax({
		// 		url: '/s2si/ActivityList.action',
		// 		type: 'post',
		// 		dataType: 'json',
		// 	})
		// 	.done(function(data) {
		// 		diplayActs(data.activities);
		// 	})
		// 	.fail(function(error) {
		// 		console.log("error");
		// 	});
		// });


		
	</script>
	<style> 
		
		.m-pagination {
			width : 100%;
		}
		
		.m-pagination .u-circles {
			width: 100px;
			margin: 0 auto;
			height: 10px;
			margin-top: 10px;
			margin-bottom: 20px;
		}

		.m-pagination .u-circles li {
			width:10px;
			height: 10px;
			background: url(resources/image/circle.png);
			background-size: cover;
			display:block;
			float : left;
			cursor: pointer;
			margin-left : 15px;
		}

		.m-interacts  {
			width : 100%;
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

		.finished {
			display: none;
			position: absolute;
			right: 15px;
			top: 26px;
			height: 40px;
			line-height: 40px;
			color: #fff;
			font-weight: bold;
		}
	</style>
  </head>
  <% String pram = (String)request.getAttribute("name");%>
  <body>
	  <div id="wrap">
	  	<div class="main">
	  		<div class="m-logo"></div>
	  		<div class="m-pagination">
	  			<ul class="u-circles">
	  				<li class="circle"></li>
	  				<li></li>
	  				<li></li>
	  			</ul>
	  		</div>
			<div class="m-interacts">
					<ul id="exchange-items">
						
						<li class="u-pitem">	
								<img class="u-prize" src="resources/image/list.png" alt="">
								<img class="u-arrow" src="resources/image/arrow.png" alt="">
								<span class="finished">已完成</span>
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
		</div>
	  </div>
	 <li id="j-clone" class="u-pitem" style="display:none;">	
			<img class="u-prize" src="resources/image/list.png" alt="">			
			<img class="u-arrow" src="resources/image/arrow.png" alt="">
	</li>
  </body>
</html>
