<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id=request.getParameter("tid");
%>
<!DOCTYPE>
<html style=""> 
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
  		$("body").bind("sec",function(){
  			$.ajax({
			url: "GetTwoLevel.action?tid="+<%=id%>,
			type: "POST",
			data: {},
			success:function(d){
				var data=JSON.parse(d);
				console.log(data);
				data=data.two_level || [];
				for(var i=0;i<data.length;i++){
					var $tr=$("table").find("tr.temple2").clone();
					//console.log($tr[0]);
					$tr.removeClass("hide");
					$tr.removeClass("temple2");
					$tr.find("a").attr("href","third.action?hid="+data[i].id);
					$tr.bind("click",function(){
							var $urls = $(this).find("a").attr("href");
							console.log($urls);
							if($urls){
								location.href=$urls;
							}
					});	
					$tr.find("td.one").find("div").css("background-image","url("+data[i].imageUrl+")");
					$tr.appendTo($("table"));
					var str=data[i].content;
					if(str.length>10){
						str=str.substr(0,9);
						console.log(str);
						str=str+"...";
					}
					$tr.find("td.two").find("span").text(str);	
				}	
				},
		 	});
  		});
  		$("body").trigger("sec");
  	});
  </script>
  
  <body style="padding:5px;background-image:url(resources/image/bac.png);
background-size:cover;">
  <div class="container">
  	<div style="width:auto;">
  	<img src="resources/image/logobig.png" style="width:128px;height:128px;display:block;margin:0px auto"></img>
  	</div>
    <table class="table" cellspacing="0" cellpadding="0" style="margin-top:5px;
    ">
		<tbody>
			<tr class="hide temple2" style="
			">
				<td style="vertical-align:middle;padding-left:1px;width:25%" class="one">
				<div style="float:left;background-image:url(resources/image/smallbac.png);height:80px;width:80px;
				background-size:100% 100%;
				"></div>
				</td>
			  	<td style="text-align:left;vertical-align:middle;padding-left:2px" class="two">
				  	<a href="" style="text-decoration:none;">			 	
						<span style="color: white">指导价...</span>
	               </a>
            	</td>
            	<td style="vertical-align:middle;text-align:right;padding-right:20px">
            	<img src="resources/image/twoarrow.png" style=""></img>
            	</td>
			</tr>
		</tbody>
	</table>
	</div>
  </body>
</html>
