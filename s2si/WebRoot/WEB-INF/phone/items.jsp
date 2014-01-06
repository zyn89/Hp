<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id=request.getParameter("id");
%>
<!DOCTYPE>
<html>
  <head>
    <title>二级页面</title>
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache"> 
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="expires" content="0">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width">
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <script language="javascript" type="text/javascript" src="resources/js/jquery-1.9.1.js"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  </head>
  <script>
  	$(function(){
  		$("body").bind("show",function(){
  			$.ajax({
			url: "GetTwoLevel.action?tid="+<%=id%>,
			type: "POST",
			data: {},
			success:function(d){
				var data=JSON.parse(d);
				console.log(data);
				data=data.two_level || [];
				for(var i=0;i<data.length;i++){
					var $tr=$("table").find("tr.temple").clone();
					console.log($tr[0]);
					$tr.removeClass("hide");
					$tr.removeClass("temple");
					$tr.find("a").attr("href","third.action?hid="+data[i].id);
					$tr.find("td").bind("click",function(){
							var $urls = $(this).find("a").attr("href");
							console.log($urls);
							if($urls){
								location.href=$urls;
							}
					});	
					$tr.appendTo($("table"));
					var str=data[i].content;
					if(str.length>10){
						str=str.substr(0,9);
						console.log(str);
						str=str+"...";
					}
					$tr.find("td").find("span").text(str);	
				}	
				},
		 	});
  		});
  		$("body").trigger("show");
  	});
  </script>
  
  <body style="padding:0px">
  <div>
    <table class="table table-hover table-striped" style="margin-top:5px">
		<tbody>
			<!--tr>
			  <td>
			  	<a href="mobile.html" style="text-decoration:none;">
			 	<div style="margin-right:5px;float:left;background-image:url(resources/image/car.png);height:48px;width:48px"></div>
			 	<div class="weimob-list-item-line">
					<div class="" style="font-family: 'Microsoft YaHei', 宋体;color: rgb(102, 102, 102);font-size: 16px;">指导价：25.2万-35.8万    
						发动机1.6T / 2.0T            
						变速箱：7速双离合
					</div>
               </div>
               </a>
            </td>
			</tr>
			<tr>
			  <td>
			  	<a href="#" style="text-decoration:none;" class="test">
			 	<div style="margin-right:5px;float:left;background-image:url(resources/image/car.png);height:48px;width:48px"></div>
				<div class="" style="font-family: 'Microsoft YaHei', 宋体;color: rgb(102, 102, 102);
					font-size: 16px;
					display:block;
					margin-top:autp 0px;
					vertical-align:middle;
					margin-top:12px;
					">指导价：25.2万-35.8万    
					</div>
   				</a>
            </td>
			</tr-->
			<tr class="hide temple" style="">
			  
				  <td style="vertical-align:middle;
				  font-family: 'Microsoft YaHei', 宋体;color: rgb(102, 102, 102);
				  ">
				  <a href="#" style="text-decoration:none;cursor:default;color:rgb(102, 102, 102)">
				  <img src="resources/image/car.png"/>
							<span>指导价：25.2万-35.8万    
							发动机1.6T / 2.0T            
							变速箱：7速双离合</span>
				   </a>
	           	 </td>
			</tr>
		</tbody>
	</table>
	</div>
  </body>
</html>
