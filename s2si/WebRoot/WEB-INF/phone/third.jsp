<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id=request.getParameter("hid");
%>
<!DOCTYPE>
<html>
  <head>
    <title>微网站</title>
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
		<style>
    .container {
      margin: 0 auto
    }

    /* For tablets & smart phones */
    @media (max-width: 767px) {
      body {
        padding-left: 20px;
        padding-right: 20px;
      }
      .container {
        width: auto
      }
    }

    /* For smartphones */
    @media (max-width: 480px) {
      .container {
        width: auto
      }
    }

    /* For smaller displays like laptops */
    @media (min-width: 768px) and (max-width: 979px) {
      .container {
        width: 724px
      }
    }

    /* For larger displays */
    @media (min-width: 1200px) {
      .container {
        width: 1170px
      }
    }
  </style>
  </head>
  <script>
  	$(function(){
  		var flag=true;
  		$("tr").bind("click",function(e){
  			//$('#myModal').modal("show");
  			//e.stopPropagation();
  		});
  		$("body").bind("click",function(){
  		    $('#myModal').modal("hide");
  		    flag=true;
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
					var $tr=$("table.third").find("tr.temple2").clone();
					console.log($tr[0]);
					$tr.removeClass("hide");
					$tr.removeClass("temple2");
					$tr.data("id",data[i].id);
					$tr.find("td.one").find("div").css("background-image","url("+data[i].imageUrl+")");
					var str=data[i].content;
					if(str.length>9){
						str=str.substr(0,9);
						str=str+"...";
					}
					$tr.find(".two span").text(str);
					
					if(data[i].url != ""){
						$($tr).find("a").attr("href", data[i].url);
						$($tr).bind("click", function(e) {
							e.preventDefault();
							location.href = $(this).find("a").attr("href");
						});
					}else{
						$($tr).bind("click",function(e){
							$("#myModal").data("data",($(this)).data("id"));
							if(!flag){
				  				return;
				  			}
				  			e.stopPropagation();
				  			e.preventDefault();
				  			$('#myModal').modal({
				  				backdrop:true,
				  			});
				  			flag=false;
						});
					}
					//if(data[i].isFinal==1){
					
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
				$ul=$('#four-level-ul');
				$ul.empty();
				for(var i=0;i<data.length;i++){
					$li=$('#myModal').find("li.temple").clone();
					$li.removeClass("hide");
					$li.removeClass("temple");
					$li.find("span").text(data[i].content);
					$li.data("url", data[i].url);
					$li.bind("click",function(){
						location.href=$(this).data("url");
					});
					$li.appendTo($ul);
				}	
				},
		 	});
  			
  		});
  	});
  </script>
  <body style="padding:5px;background-image:url(resources/image/bac.png);
background-size:cover;">
  <div class="container">
  	<div style="width:auto;">
  	<img src="resources/image/logobig.png" style="width:128px;height:128px;display:block;margin:0px auto"></img>
  	</div>
    <table class="table third" style="margin-top:5px;cellspacing:0;cellpadding:0;font-size: 16px;">
		<tbody>
			<tr class="hide temple2" style="
			">
				<td style="vertical-align:middle;padding-left:1px;width:25%" class="one">
				<div style="float:left;;height:80px;width:80px;
				background-size:100% 100%;
				"></div>
				</td>
			  	<td style="text-align:left;vertical-align:middle;padding-left:2px" class="two">
				  	<a href="" style="text-decoration:none;">			 	
						<span style="color: white">指导价...</span>
               		</a>
            	</td>
	            	<td style="vertical-align:middle;text-align:right;padding-right:20px">
	            	<img src="resources/image/onearrow.png" style="width:auto; height:30px;"></img>
            	</td>
			</tr>
		</tbody>
	</table>
	</div>
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	style="background-image:url(resources/image/bac3.png);
	background-size:100% 100%;height:250px;background-color:transparent;box-shadow:none;border:none;
	top:35%;overflow-y:scroll";
	>
	  <div class="modal-body">
	    	<ul class="hide"><li class="hide temple" style="
	    		background: url(resources/image/bac6.png);
	    		background-size:100% 100%;
				height: 45px;
				width: 100%;
				left : 60px;
				line-height: 36px;
				color: #fff;
				font-size: 16px;
				margin-bottom:10px;
	    		"><span style="margin-top:3px;margin-left:10px;display:inline-block;">活动题目</span><img style="display:inline-block;height:60%;float:right;margin-top:8px;margin-right:10px" src="resources/image/twoarrow.png"/></li>
	    	</ul>
	    	<ul id="four-level-ul"style="list-style: none;width:95%;margin:0px auto">
	    		</ul>
	  </div>
	</div>
  </body>
</html>

