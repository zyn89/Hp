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
<title>积分兑换</title>
  <meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport"/>
  
<link type="text/css" rel="stylesheet" href="resources/css/changeIn.css"/>
<script type="text/javascript">


function change(){
    alert("change!");
    }
</script >
<%request.setCharacterEncoding("gbk");
String prize=request.getParameter("prize");%>

</head>
<body onload="all()">
<center>
    <div id="showDiv">
        <div class="info"><span><%=prize%></span>礼品说明。。。。</div>
        <div><input type="button" value="兑换" onclick="change()"></div>
    </div>
    </center>
</body>



</html>