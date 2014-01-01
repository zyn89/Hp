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
	<script src="resources/js/jquery-1.9.1.js"></script>
	<script>
		jQuery(function($){

			function diplayActs(data) {
				var $ul = $('<ul>');
				
				$.each(data,function(index,value){
					var aid = value.aid,
						activityUrl = value.activityUrl,
						credit = value.credit,
						descUrl = value.descUrl,
						done = value.done,
						score = value.score,
						type = value.type,
						$li = $('<li>'),
						$a = $('<a>'),
						$img = $('<img>');

					$li.attr({
						'data-aid' : aid,
						'data-credit' : credit,
						'data-type' : type,
						'data-score' : score
					});

					$a.attr({
						href:'interact.action?aid=' + aid + '&type=' + type + '&descUrl=' + descUrl
					});

					if(done) {
						//设置图片是活动完成的图片
					} else {
						$img.attr({
							src : activityUrl
						});
					}

					$a.append($img).appendTo($li);
					$li.appendTo($ul);
				});

				$('.m-interacts').empty().append($ul);
			}

			
			$.ajax({
				url: '/s2si/ActivityList.action',
				type: 'post',
				dataType: 'json',
			})
			.done(function(data) {
				diplayActs(data.activities);
			})
			.fail(function(error) {
				console.log("error");
			});
		});
	</script>
	<style> 
		body {
			padding: 0px;
			margin: 0px;
		}
		.main {
			margin:0px auto;
			width:100%;
			overflow: hidden;
			zoom : 1;
			text-align: center;
		}
		.m-interacts ul {
			list-style: none;
		}
	</style>
  </head>
  
  <body>
  <% String pram = (String)request.getAttribute("name");%>
 	<div class="main">
 	<span><%= pram %></span>
		<div class="m-interacts">
			<ul>
				<li><a href=""><img src="" alt=""></a></li>
				<li><a href=""><img src="" alt=""></a></li>
				<li><a href=""><img src="" alt=""></a></li>
			</ul>
		</div>
	</div>
  </body>
</html>
