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
	<script src="resources/js/question.js"></script>
	
	<script>
	
	</script>
	<style>
		.m-question ,.m-result{
		 	margin:0 auto;
		 	width: 100%;
		 	height: 50px;
		 	border: 1px solid red;
		}
		.m-result {
			margin-top: 10px;
		}
	</style>
</head>
<%
	  String aid=request.getParameter("aid");
 %>
<body>
	<input id="aid" type="hidden" value="<%= aid %>"/>
	<div id="page1" data-role="page">
		
			<div data-role="content">
				<div class="m-question">
					<img src="" />
				</div>
				<div class="m-result" style="display:none;">

				</div>
				<div class="m-answer">
					<fieldset data-role="controlgroup" data-iconpos="right">
					   		
					        <!-- <input type="radio" name="radio-choice-1" id="radio-choice-1" value="choice-1" checked="checked">
					        <label for="radio-choice-1">A. Cat</label>
					        <input type="radio" name="radio-choice-1" id="radio-choice-2" value="choice-2">
					        <label for="radio-choice-2">B. Dog</label>
					        <input type="radio" name="radio-choice-1" id="radio-choice-3" value="choice-3">
					        <label for="radio-choice-3">C. Hamster</label>
					        <input type="radio" name="radio-choice-1" id="radio-choice-4" value="choice-4">
					        <label for="radio-choice-4">D. Lizard</label> -->
					</fieldset>
				</div>
				<div class="m-submit">
					<a data-role="button">提交</a>
				</div>
			</div>

	</div>
</body>
</html>

