<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>pic upload</title>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta content="width=device-width, initial-scale=1, maximum-scale=1.0" name="viewport" />
	
	
    <%--
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	--%>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
   	 <link rel="stylesheet" href="resources/css/common.css" />
	 <script type="text/javascript" src="resources/js/jquery-1.9.1.js"></script>
	 <script type="text/javascript">
		$(function(){
			$('#c-btn').bind('click',function(event){
				window.location.href = 'goTo.action?url=question2.jsp?aid=' + $('#aid').val();
			});
		});
		
	 	
	 </script>
	<style type="text/css">
		.m-info {
			width : 100%;
			background:url(resources/image/hdsm-db.png);
			background-size:cover;
			height:280px;
			position: relative;
		}
		
		.m-info img {
			width :260px;
			height : 260px;
			left : 30px;
			top :10px;
			position:absolute;
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
<%
	  String aid=request.getParameter("aid");
	  String descUrl=request.getParameter("descUrl");
	  String type=request.getParameter("type");
 %>
<body>
<input name = "aid" id="aid" type="hidden" value="<%= aid %>"/>
<div id="wrap">
	<div class="main">
		<div class="m-logo">
			<img src="resources/image/logo-small.png"/>
		</div>
	
		<div class="m-info"> 
			<img src="<%= descUrl %>"/>
		</div>
		
		<div class="m-submit">
				<a href="javascript:void(0);" id="c-btn">开始挑战</a>
		</div>
				
	</div>

</div>	
</body>
</html>
