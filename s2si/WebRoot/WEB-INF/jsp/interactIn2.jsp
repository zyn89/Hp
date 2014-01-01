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
				thumbnailWidth : 40,
				thumbnailHeight : 40,
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

			/*function handleFileSelect(evt) {
				var files = evt.target.files;
				var f = files[0];
				var reader = new FileReader();

				reader.onload = (function(theFile) {
					return function(e) {
						var innerHtml = ['<img src="', e.target.result, '" title="', theFile.name, '" width="50" />'].join('');
						$(".p_preview") .html(innerHtml);
					};
				})(f);

				reader.readAsDataURL(f);
			}

		 	if (window.FileReader) {
				$('.j-file').bind('change.pupload',function(event){
					handleFileSelect(event);
				});
			} else {
				  alert('This browser does not support FileReader');
			}*/




			 $('.c_btn').click(function(event) {
				 
					var myDropzone = Dropzone.forElement("form#simpleUploadDropzone");
					myDropzone.processQueue();
			 });

			
		 });
	 	
	 </script>
	<style type="text/css">
		body{
			background-color: #ddd;
		}
		.main {
			width:100%;
			margin: 0px auto;
			border: 1px solid red;
			overflow: hidden;
			zoom:1;
		}
		.main .a_info {	
			margin: 0 auto;
			text-align: center;

		}
		.main .p_upload {
			margin: 5px;
			text-align: center;
			height: 200px;
			border: 1px solid #000;

		}
		.dropzone .dz-preview {
			margin:10px;
		}
		.dropzone .dz-filename {
			display: none;
		}
		.dropzone .dz-size {
			display: none;
		}
		.dropzone .dz-preview  .dz-details {
			width: 40px;
			height: 40px;
			margin-bottom: 5px;
		}
		.dropzone a.dz-remove {
			font-size: 12px;
			margin-top: 0px;
		}

		.dropzone .dz-preview .dz-details img {
			width: 40px;
			height: 40px;
		}
		.dropzone .dz-default.dz-message {
			background-image:none;
		}
		
		.dropzone .dz-preview .dz-error-message {
			display:none;
		}
		
		.c_btn {
			display: block;
			margin:0 auto;
			text-align: center;
			height: 40px;
			width: 100px;
		}
	</style>
</head>
<%
	  String aid=request.getParameter("aid");
	  String descUrl=request.getParameter("descUrl");
	  String type=request.getParameter("type");
 %>
<body>
	<div class="main">
		<div class="a_info"> 
			<img src="<%= descUrl%>"/>
		</div>
		
		<%if(type.equals("pic_word") ){ %>
			<textarea width="100%"  rows="3" value="输入活动内容"></textarea>
		<% } %>
		
		<% if(type.equals("pic") || type.equals("pic_word")) {%>
		<form id="simpleUploadDropzone" action="/s2si/UplordPic.action" name="pics" class="dropzone" enctype="multipart/form-data">
			<input id="j-aid" type="hidden" name="aid" value="<%= aid %>"/>
			<input id="j-content" type="hidden" name="content"/>
		</form>
		<% } %>
		
		<button class="c_btn" >确定</button>
	</div>
</body>
</html>
