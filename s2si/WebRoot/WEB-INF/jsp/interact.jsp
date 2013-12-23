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
<title>微互动</title>
   <meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport"/>
<link type="text/css" rel="stylesheet" href="resources/css/interact.css"/>
<script type="text/javascript" defer>

/*function cDiv(act){
//var link=document.createElement('a');
//link.setAttribute('href', url) ;

var oDiv=document.createElement("div");
oDiv.onclick="goIn()";
var oSpan=document.createElement("span");
oSpan.innerHTML= act;
oDiv.appendChild(oSpan);
//link.appendChild(oDiv)
document.getElementById("showDiv").appendChild(oDiv);
}*/

function cDiv(act){
//var link=document.createElement('a');
//link.setAttribute('href', url) ;

var button=document.createElement("input");
button.type="button";
button.value= act;
button.onclick=function go(){
  window.location.href="goTo.action?url=interactIn.jsp?act="+act;
 
}

//link.appendChild(oDiv)
document.getElementById("showDiv").appendChild(button);
}
function all(){
 for(j = 1;j <5; j++) {
    cDiv("活动"+j);
 }
}


</script >

</head>
<body onload="all()">
	<div id="showDiv">
        
	</div>
	
</body>
</html>