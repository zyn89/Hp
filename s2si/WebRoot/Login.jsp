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
<title>登录</title>
  <meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport"/>
  
<link type="text/css" rel="stylesheet" href="resources/css/login.css"/>
	<script type="text/javascript">
function loadXMLDoc()
{
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","/Hp/Ping_GamerInteractiveAction",true);
xmlhttp.send();
}

function remind(){
    window.location.href="register.jsp";
    }
</script >

</head>
<body>
	
	<div id="login" class="clearfix">
    <h1>请先登录</h1>
    <form class="form-horizontal" id="signupForm" method="post" action="login.action" >
      <fieldset>
        <div class="control-group">
					<input id="loginName" name="loginName"  type="text"  required="required" placeholder="手机号">
        </div>
        <div class="control-group">
					<input id="password" name="password" type="password"  required="required" placeholder="密码">
        </div>        
        <div class="form-actions">
					<button class="btn btn-primary submit" type="submit" id="signup">登录</button>
					<button class="btn" type="button" onclick="remind();" id="pwd-btn">注册</button>
        </div>
      </fieldset>
    </form>
  </div>
		<h2>AJAX</h2>
<button type="button" onclick="loadXMLDoc()">请求数据</button>
<div id="myDiv"></div>	
</body>
</html>