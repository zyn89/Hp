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
<title>��ҳ</title>
   <meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport"/>
<link type="text/css" rel="stylesheet" href="resources/css/main.css"/>

</head>
<body>
	<div id="mainDiv">
        <div id="childDiv">�� ӭ!
        </div>
       	<table id="mainTable">
			<tr>
				<td><a href="goTo.action?url=userInfo.jsp"><p>��������</p></a>
				</td>
				<td><a href="goTo.action?url=checkIn.jsp"><p>ǩ��</p></a>
				</td>
			</tr>
			<tr>
				<td><a href="goTo.action?url=interact.jsp"><p>΢����</p></a>
				</td>
				<td><a href="goTo.action?url=questions.jsp"><p>΢����</p></a>
				</td>
			</tr>
			<tr>
				<td><a href="goTo.action?url=change.jsp"><p>��Ʒ�һ�</p></a>
				</td>
				<td><a href="goTo.action?url=getPrize.jsp"><p>���ֳ齱</p></a>
				</td>
			</tr>
		</table>
	</div>
	
</body>
</html>