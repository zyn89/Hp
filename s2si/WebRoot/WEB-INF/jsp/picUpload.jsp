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
	
   	 <link rel="stylesheet" href="resources/css/dropzone.css" />
   	  <link rel="stylesheet" href="resources/css/common.css" />
	 <script type="text/javascript" src="resources/js/jquery-1.9.1.js"></script>
	 <script type="text/javascript" src="resources/js/upload.js"></script>
	 <script type="text/javascript">

		
	 	
	 </script>
	<style type="text/css">

		.main .m-info {	
			margin: 0 auto;
			text-align: center;
			width: 100%;
			margin-bottom : 20px;	
		}

		.main .m-info img {
			width : 260px;
			height:83px;
			margin: 0 auto;
		}

		.main .m-content {
			width: 100%;
			margin-bottom: 10px;
		}

		.main .m-content input.u-content {
			width : 254px;
			height: 39px;
			line-height : 39px;
			display: block;
			border: 0px;
			margin:0 auto;
			outline: none;
			text-indent: 15px;
			font-size: 14px;
			font-weight: bold;
			color: #999;
			padding-right:15px;
			background: url(resources/image/input1.png);
			background-size:cover;

		}
		.main .m-upload {
			width: 100%;
			text-align: center;
		}

		.main .m-upload .u-upload {
			width: 260px;
			height: 217px;
			margin: 0 auto;
			background: url(resources/image/uploaddb.png);
			background-size:cover;
		}

		.main .m-upload .u-upload input{
			display: none;
		}

		.main .m-upload .u-upload li {
			display: block;
			float : left;
			width: 60px;
			height: 51px;
			background: url(resources/image/upload_td.png);
			background-size:cover;
			margin-bottom: 10px;
			margin-left: 16px;
			position: relative;
			margin-top:10px;
			z-index: 100;
		}

		.main .m-upload .u-upload li.first {
			margin-left: 24px;
		}

		.main .m-upload .u-upload li.title {
			width: 100%;
			text-align: center;
			color: #fff;
			font-size: 16px;
			height: 28px;
			line-height: 28px;
			margin-top: 10px;
			background:none;
			margin-left: 0;
			margin-bottom : 10px;
		}

		.main .m-upload .u-upload li a {
			display: block;
			width: 32px;
			height:32px;
			background:url(resources/image/upload_plus.png);
			background-size:cover;
			position: absolute;
			top : 9.5px;
			left : 14px;
		}
		
		   
		 .main .m-upload .u-upload li .thumbnail {
			   	display: table-cell;
				vertical-align: middle;
				text-align: center;
				width : 60px;
				height : 51px;
		 }
		
		.main .m-upload .u-upload li .thumbnail img{
			/*width : 58px;
			height: 49px;*/
			width : 56px;
			height: 47px;
		}

		.main .m-confirmbtn {
			width: 100%;
			margin-top:10px;
		}

		.main .m-confirmbtn a {
			display:block;
			width: 260px;
			height:40px;
			line-height:40px;
			margin: 0 auto;
			background:url(resources/image/btn-blue.png);
			background-size:cover;
			letter-spacing: 1.8;
		}

	</style>
</head>
<%
 	  String aid=request.getParameter("aid");
	  String descUrl=request.getParameter("descUrl");
	  String type=request.getParameter("type");
%>
<body>
	<div id="wrap">

	
		<div class="main">
			<div class="m-logo">
				<img src="resources/image/logo-small.png"/>
			</div>

			<div class="m-info"> 
				<img src="resources/image/list.png"/>
			</div>
			<% if(type.equals("pic_word")){ %>
				<div class="m-content"> 
					<input class="u-content" type="text" name="content" autocomplete="off" placeholder="请输入"/>
				</div>
			<%} %>
			
			<div class="m-upload">
				<form name="" id="picform" class="">
					<input type="hidden" id="aid" name="aid" value="<%= aid %>"/>
					<ul class="u-upload">
						<li class="title">上传图片</li>
						<li class="first">
							<a href="javacript:void(0);" ></a>
							<input style="display:none;" type="file" name="pics" accept="image/*"  >
							<div class="thumbnail">
							</div>
						</li>
						<li>
							<a style="display:none;"  href="javacript:void(0);"></a>
							<input style="display:none;" type="file" name="pics" accept="image/*" >
							<div class="thumbnail">
							</div>
						</li>
						<li>
							<a style="display:none;"  href="javacript:void(0);"></a>
							<input type="file" name="pics" accept="image/*" >
							<div class="thumbnail">
							</div>
						</li>
						<li class="first">
							<a style="display:none;"  href="javacript:void(0);"></a>
							<input type="file" name="pics" accept="image/*" >
							<div class="thumbnail">
							</div>
						</li>
						<li>
							<a style="display:none;"  href="javacript:void(0);"></a>
							<input type="file" name="pics" accept="image/*" >
							<div class="thumbnail">
							</div>
						</li>
						<li>
							<a style="display:none;" href="javacript:void(0);"></a>
							<input type="file" name="pics" accept="image/*" >
							<div class="thumbnail">
							</div>
						</li>
					</ul>
				</form>
			
			</div>
			
			<div class="m-confirmbtn">
				<a href="javascript:void(0);" id="j-confirmbtn">确定</a>
			</div>
		
			
		</div>
	</div>
</body>
</html>
