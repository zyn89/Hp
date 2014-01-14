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
	 <script type="text/javascript" src="resources/js/jquery-1.9.1.js"></script>
	 <script type="text/javascript" src="resources/js/dropzone.js"></script>
	 <script type="text/javascript">

		 jQuery(function($){



			 Dropzone.options.simpleUploadDropzone = {
				maxFilesize: 2,
				thumbnailWidth : 20,
				thumbnailHeight : 20,
				autoProcessQueue : false,
				parallelUploads: 6,
				uploadMultiple:true,
				paramName:'pics',
				addRemoveLinks : true,
				maxFiles : 6,
	      		acceptedFiles: 'image/*',
				init: function() {

				    this.on("maxfilesexceeded", function(file){
				        alert("最多只能添加六个图片");
				    });

				    this.on("removedfile",function(file){
				    		// console.log("a file " + file.name + "is removed");
				    		if(uploaded[file.name]&&uploaded[file.name].length !=0&&isPost==0) {
				    			var fname = uploaded[file.name].shift();
		    					$.ajax({
										url: '/deleteImg',
										type: 'post',
										dataType: 'json',
										data: {
											filename: fname
										}
								}).done(function(data){

								}).fail(function(err){

								});
				    		}
				    });

				    this.on("success",function(file,data){
				    	isPost = 0;
				    	var filename = file.name;
				    	// console.log("file:" + file.name);
				    	// console.log('已上传:' + this.getAcceptedFiles().length);
				    	// console.log("总共可以上传几个文件:" + Dropzone.options.simpleUploadDropzone['maxFiles']);
				    	if(!uploaded[filename]) {
				    		uploaded[filename] = [];
				    	} 
				    	uploaded[filename].push(data);
				    });
			  	}
			};

			




			 $('.c_btn').click(function(event) {
					var myDropzone = Dropzone.forElement("form#simpleUploadDropzone");
					myDropzone.processQueue();
			 });


		 });
	 	
	 </script>
	<style type="text/css">

		html,body {
			height:100%;
			width : 100%;
			font-family:"黑体","微软雅黑";
			font-size: 14px;
		}

		body,ul {
			margin:0px;
			padding:0px;
		}
		
		ul {
			list-style: none;
		}

		button ,a {
			outline: none !important;
		}
		a {
			text-decoration: none;
			color: #fff;
		}

		#wrap {
			width:100%;
			min-height: 100%;
			background: url(resources/image/背景.png) repeat-y fixed;	
			background-size:cover;
		}

		.main {
			width : 320px;
			min-height: 100%;
			margin : 0px auto;
			overflow:hidden;
			zoom:1;
			text-align:center;
		}
	
		
		.main .m-logo {
			 height: 135px;
			 border: 1px solid red;
			 width : 100%;
			 margin-bottom:10px;
			 position: relative;
		}

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
			height: 52px;
			background: url(resources/image/upload_td.png);
			background-size:cover;
			margin-top: 20px;
			margin-left: 16px;
			position: relative;
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
		}

		.main .m-upload .u-upload li a {
			display: block;
			width: 32px;
			height:32px;
			background:url(resources/image/upload_plus.png);
			background-size:cover;
			position: absolute;
			top : 10px;
			left : 14px;
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

<body>
	<div id="wrap">

	
		<div class="main">
			<div class="m-logo">

			</div>

			<div class="m-info"> 
				<img src="resources/image/list.png"/>
			</div>
			<div class="m-content"> 
				<input class="u-content" type="text" name="content" autocomplete="off" placeholder="请输入"/>
			</div>
			
			<div class="m-upload">
				<form name="" id="" class="">
					<ul class="u-upload">
						<li class="title">上传图片</li>
						<li class="first">
							<a href="javacript:void(0);"></a>
							<input type="file" name="pics">
							<img src="" class="u-thumbnail" />
						</li>
						<li>
							<a href="javacript:void(0);"><img src="" alt=""></a>
							<input type="file" name="pics">
						</li>
						<li>
							<input type="file" name="pics">
						</li>
						<li class="first">
							<input type="file" name="pics">
						</li>
						<li>
							<input type="file" name="pics">
						</li>
						<li>
							<input type="file" name="pics">
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
