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
	
	<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
	<script src="resources/js/jquery-1.9.1.js"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	


	
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
		body,ul {
			padding: 0px;
			margin: 0px;
		}
		
		button,a {
			outline: none !important;
		}

		.main {
			margin:0px auto;
			width:100%;
			overflow:hidden;
			zoom:1;
			text-align:center;
		}
		
		.m-infopic {
			margin:20px auto;
		}
	
		
	</style>
</head>

<body>
	<div class="main">
		<input type="hidden" id="sid" >
		<input type="hidden" id="credit" >
		<div class="m-infopic">
			<img src=""/>
		</div>
		<div class="m-submit">
			<button id="c-btn" type="button" style="width:200px;" class="btn btn-large btn-primary">确定</button>
		</div>
	</div>
</body>
</html>

