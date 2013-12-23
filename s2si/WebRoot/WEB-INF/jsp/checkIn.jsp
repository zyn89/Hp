<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>签到</title>

<link type="text/css" rel="stylesheet" href="resources/css/check.css"/>
 <META HTTP-EQUIV=REFRESH CONTENT="4;URL=main.html">
 <meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport"/>
本页面将在<span id=yu>3</span><a href=javascript:countDown></a>秒后自动返回...........<a href="main.html">或点击这里跳转</a>....

<script>

function countDown(secs){

yu.innerText=secs;

 if(--secs>0){

setTimeout("countDown("+secs+")",1000);

 }

}

countDown(3);

</script>
 

</head>
<body>
	<div class="main">
     <span> 今日签到成功,签到次数+1<span>
	</div>
	
</body>
</html>