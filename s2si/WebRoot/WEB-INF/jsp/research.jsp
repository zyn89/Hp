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
	


	
	<script>
		jQuery(function($){

			function displayData(data) {
				$('.m-infopic img').attr({
					src : data.imageUrl
				});

				$('#sid').val(data.sid);
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
				window.location.href="goTo.action?url=researchDetail.jsp&sid=" + $('#sid').val();
			});
		});
	</script>
	<style>
	
	</style>
</head>

<body>
	<div class="main">
		<input type="hidden" id="sid" >
		<div class="m-infopic">
			<img src=""/>
		</div>
		<div class="m-submit">
			<button id="c-btn" type="button">确定</button>
		</div>
	</div>
</body>
</html>

