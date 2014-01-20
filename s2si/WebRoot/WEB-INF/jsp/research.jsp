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
	<script src="resources/js/jquery-1.9.1.js"></script>
	


	
	<script>
		jQuery(function($){

			function displayData(data) {
				$('.m-infopic img').attr({
					src : data.imageUrl
				});

				$('#sid').val(data.sid);
				$('#credit').val(data.credit);
			}

			$.ajax({
				url: 'GetSurveyList.action',
				type: 'post',
				dataType: 'json'
			})
			.done(function(data) {
				console.log(data);
				displayData((data.surveys)[0]);
			})
			.fail(function(err) {
				console.log(err);
			});
				

			$('#c-btn').bind('click',function(event){
				window.location.href="goTo.action?url=researchDetail.jsp&sid=" + $('#sid').val() + "&credit=" + $('#credit').val();
			});
		});
	</script>
	<style>
		
		
		.m-infopic {
			width:100%;
		}
		
		.m-infopic .m-dyinfo {
			background:url(resources/image/dyinfo-db.png);
			background-size : cover;
			width: 272px;
			height: 272px;
			margin: 0 auto;	
		}
	
		.m-infopic .m-dyinfo img {
			width:100%;
			height:100%
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
			background:url(resources/image/btn-green.png);
			background-size:cover;
			letter-spacing: 3px;
		}
		
	</style>
</head>

<body>
	<div id="wrap">
		<div class="main">
			<input type="hidden" id="sid" >
			<input type="hidden" id="credit" >
			<div class="m-logo">
				<img src="resources/image/logo-small.png"/>
			</div>
			<div class="m-infopic">
				<div class="m-dyinfo">
					<img src=""/>
				</div>
				
			</div>
			<div class="m-submit">
				<a href="javascript:void(0);" id="c-btn">开始</a>
			</div>
		</div>
	</div>
</body>
</html>

