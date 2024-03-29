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
	<title>兑换说明</title>
	<link rel="stylesheet" href="resources/css/common.css" />
	<link rel="stylesheet" href="resources/js/ExceptionDisplayBox/css/exceptionMsgBox.css" />
	<script src="resources/js/jquery-1.9.1.js"></script>
	<script src="resources/js/ExceptionDisplayBox/js/exceptionMsgBox.js"></script>
	


	
	<script>
		jQuery(function($){
				
			$('#c-btn').bind('click',function(event){


				$.ajax({
			 		url: 'DoneExchange.action',
			 		type: 'post',
			 		dataType: 'json',
			 		data : {
			 			eid : $('#eid').val()
					}
			 	})
			 	.done(function(data) {
			 		if(data.status == 400) {
				 		$('.main').exceptionMsgBox({
				 			msg : data.message,
							btnClickHandler : function(event){
				 				window.location.href = "goTo.action?url=prize.jsp";
							},
							objForEventCancel : $('#c-btn'),
							eventNameForCancel : 'click',
							btnText : '完成'
					 	});
			 		} else {
			 			window.location.href="goTo.action?url=exchange.jsp&eid=" + $('#eid').val() + "&prizeUrl=" + $('#prizeUrl').val();
			 		}
			 	})
			 	.fail(function(error) {
			 		console.log("error");
			 	});
			});
		});
	</script>
	<style>
		
		
		.m-infopic {
			width:100%;
		}
		
		.m-infopic .m-exchangeinfo {
			background:url(resources/image/dyinfo-db.png);
			background-size : cover;
			width: 272px;
			height: 272px;
			margin: 0 auto;	
		}
	
		.m-infopic .m-exchangeinfo img {
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
<%
	  String eid=request.getParameter("eid");
	  String descUrl=request.getParameter("descUrl");
	  String prizeUrl=request.getParameter("prizeUrl");
 %>
<body>
	<div id="wrap">
		<div class="main">
			<input type="hidden" id="eid" name="eid"  value="<%= eid%>" />
			<input type="hidden" id="prizeUrl" name="prizeUrl" value="<%= prizeUrl %>" />
			<div class="m-logo">
				<img src="resources/image/logo-small.png"/>
			</div>
			<div class="m-infopic">
				<div class="m-exchangeinfo">
					<img src="<%= descUrl %>"/>
				</div>
				
			</div>
			<div class="m-submit">
				<a href="javascript:void(0);" id="c-btn">确定兑换</a>
			</div>
		</div>
	</div>
</body>
</html>

