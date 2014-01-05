<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id=request.getParameter("hid");
%>
<!DOCTYPE>
<html>
  <head>
    <title>items.html</title>
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
  		$(".test").bind("click",function(){
  			//$('#myModal').modal("show");
  			$('#myModal').modal({
  				backdrop:false,
  			});
  		});
  		$("body").bind("get",function(){
  			$.ajax({
			url: "GetThreeLevel.action?hid="+<%=id%>,
			type: "POST",
			data: {},
			success:function(d){
				var data=JSON.parse(d);
				console.log(data);
				data=data.three_level || [];
				for(var i=0;i<data.length;i++){
					var $tr=$("table.third").find("tr.temple").clone();
					console.log($tr[0]);
					$tr.removeClass("hide");
					$tr.removeClass("temple");
					$tr.data("id",data[i].id);
					$tr.find("img").attr("src",data[i].imageUrl);
					var str=data[i].content;
					if(str.length>10){
						str=str.substr(0,10);
						str=str+"...";
					}
					$tr.find("span").text(str);
					if(data[i].isFinal==1){
						$tr.find("a").attr("href",data[i].url);
						$tr.find("td").bind("click",function(){
							$tr.find("a").trigger("click");
						});
					}else{
						$tr.find("td").bind("click",function(){
							$("#myModal").data("data",$tr.data("id"));
							$('#myModal').modal({
				  				backdrop:false,
				  			});
						});
					}
					$tr.appendTo($("table.third"));
				}	
				},
		 	});
  		});
  		$("body").trigger("get");
  		$('#myModal').bind("show",function(){
  			var id=$("#myModal").data("data");
  			$.ajax({
			url: "GetFourLevel.action?fid="+id,
			type: "POST",
			data: {},
			success:function(d){
				var data=JSON.parse(d);
				console.log(data);
				data=data.four_level || [];
				for(var i=0;i<data.length;i++){
					var $tr=$("table.other_table").find("tr.other_temple").clone();
					console.log($tr[0]);
					$tr.removeClass("hide");
					$tr.removeClass("other_temple");
					$tr.data("id",data[i].id);
					$tr.find("img").attr("src",data[i].imageUrl);
					var str=data[i].content;
					if(str.length>10){
						str=str.substr(0,10);
						str=str+"...";
					}
					$tr.find("a").attr("href",data[i].url);	
					$tr.find("td").bind("click",function(){
							var $urls = $(this).find("a").attr("href");
							console.log($urls);
							if($urls){
								location.href=$urls;
							}
					});		
					console.log(str);
					$tr.find("span").text(str);
					$tr.appendTo($("table.other_table"));
				}	
				},
		 	});
  			
  		});
  	});
  </script>
  <body style="padding:0px">
  <div>
    <table class="table table-hover table-striped third" style="margin-top:5px">
		<tbody>
			<tr class="hide temple" style="">
			  
				  <td style="vertical-align:middle;
				  font-family: 'Microsoft YaHei', 宋体;color: rgb(102, 102, 102);
				  ">
				  <a href="#" style="text-decoration:none;color:rgb(102, 102, 102)">
				  <img style="" src="resources/image/car.png"/>
							<span>指导价：25.2万-35.8万    
							发动机1.6T / 2.0T            
							变速箱：7速双离合</span>
				   </a>
	           	 </td>
			</tr>
		</tbody>
	</table>
	</div>
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header" style="height:20px;padding-top:0px">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h4 id="myModalLabel">详细信息</h4>
	  </div>
	  <div class="modal-body">
	    <table class="table table-hover table-striped other_table" style="margin-top:5px">
		<tbody>
			<tr class="hide other_temple" style="">			  
				  <td style="vertical-align:middle;
				  font-family: 'Microsoft YaHei', 宋体;color: rgb(102, 102, 102);
				  ">
				  <a href="#" style="text-decoration:none;color:rgb(102, 102, 102)">
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
	  <div class="modal-footer" style="padding-top:10px;padding-bottom:11px">
	    <button class="btn pull-left" data-dismiss="modal" aria-hidden="true">关闭</button>
	    <button class="btn btn-primary" data-dismiss="modal" data-dismiss="modal">确定</button>
	  </div>
	</div>
  </body>
</html>

