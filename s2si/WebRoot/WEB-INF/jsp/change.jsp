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
<meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport"/>
<title>积分兑换</title>

<link type="text/css" rel="stylesheet" href="resources/css/change.css"/>
<script type="text/javascript" defer>


function cDiv(act){

var button=document.createElement("input");
button.type="button";
button.value= act;
button.onclick=function go(){
  // window.location.href="changeIn.jsp?prize="+act;
   //var encodeparam = encodeURI(act)
   
  window.location.href="goTo.action?url=changeIn.jsp?prize="+act;
  
}

//link.appendChild(oDiv)
document.getElementById("showDiv").appendChild(button);
}
function all(){
 for(j = 1;j <4; j++) {
    cDiv("礼品"+j);
 }
}


</script >
</head>
<body onload="all()">
    <div id="showDiv">
        <p class="title">您的目前积分是:<span>10000</span></p>
        
    </div>
</body>



</html>