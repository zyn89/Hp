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
	<link rel="stylesheet" href="resources/tools/jQuery-mobile-1.4/jquery.mobile-1.4.0.min.css">
	<script src="resources/js/jquery-1.9.1.js"></script>
	
	<script src="resources/tools/jQuery-mobile-1.4/jquery.mobile-1.4.0.min.js"></script>
	<script src="resources/js/research.js"></script>
	
	<script>
	
	</script>
	<style>
		.m-question {
			
		}
		.m-question ,.m-result{
		 	margin:0 auto;
		 	width: 100%;
		 	text-align:center;
		}
		.m-result {
			margin-top: 10px;
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
	
	<div id="page1" data-role="page">
		
			<div data-role="content">
				<div class="m-question">
					<img src="" />
				</div>
				<div class="m-result" style="display:none;">

				</div>
				<div class="m-answer">
					<fieldset data-role="controlgroup" data-iconpos="right">
					   		
					</fieldset>
				</div>
				<div class="m-submit">
					<a data-role="button">提交</a>
				</div>
			</div>

	</div>
</body>
</html>

