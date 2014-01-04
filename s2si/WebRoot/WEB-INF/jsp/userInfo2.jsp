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
			var oldData;
			function displayData(data) {
				$.each(data,function(key,value){
					$('#u-' + key).text(value);
				});
			}

			$.ajax({
				url: 'MotifyInfo.action',
				type: 'post',
				dataType: 'json',
			})
			.done(function(data) {
				oldData = data;
				displayData(data);
			})
			.fail(function(err) {
				console.log(err);
			});

			$('#modify-btn').bind('click',function(event){
				$.each(oldData,function(){
					
				});
			});

		});	
	</script>
	<style>
		
	</style>
</head>

<body>
	<div class="main">
		<div class="m-uinfo">
			<ul>
				<li id="u-phone"></li>
				<li id="u-name"></li>
				<li id="u-credit"></li>
				<li id="u-shopName"></li>
			</ul>
			<button id="modify-btn" type="button">修改</button>
			<button id="save-btn" type="button">保存</button>
		</div>
	</div>
</body>
</html>

