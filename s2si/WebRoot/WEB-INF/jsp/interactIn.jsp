<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
  <meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport"/>
<base href="<%=basePath%>">
<title>微互动内容</title>

<link type="text/css" rel="stylesheet" href="resources/css/interactIn.css"/>
<script  LANGUAGE="JavaScript">   
  <!--   
  function   showimg()   
  {   
                    document.Myform.Myimg.src=document.Myform.filename.value;   
                    document.Myform.Myimg.width=100;   
                    document.Myform.Myimg.height=150;   
                     
  } 
  //--> 
  function jump(){
    alert("显示已有内容");
  }  
  </script>
  <%request.setCharacterEncoding("gbk");
String act=request.getParameter("act");%>
</head>
<body>
 <div class="main">
    <div class="header">
        <p>活动主题:<%=act%></p>
        <input type="button" id="more" value="已上传内容" onclick="jump()"> 
    </div>
 
    <div class="context">
        <form id="form"  name=Myform   method=POST   action="">  
            <p><span>在下面输入内容：</span>
            <p><textarea id="context" ></textarea>   
            <p><input   name=filename  type="file"   onchange=setTimeout('showimg()',500)>   
            <p><img   name=Myimg   src="" >   
            <p><input class="submit" type="submit" value="确定">
         </form>
   </div>
 </div>
</body>



</html>