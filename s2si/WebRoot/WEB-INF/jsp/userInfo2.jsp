<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">
<head>
	<base href="<%=basePath%>">  
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Document</title>
	<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="resources/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
	<script src="resources/js/jquery-1.9.1.js"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>


	
	<script>
		jQuery(function($){
			var oldData;
			$('#save-btn').addClass('disabled');
			function displayData(data) {
				$.each(data,function(key,value){
					$('#u-' + key).val(value)
								  .attr({
									  readonly : true
								  });
				});
				$('#u-pass').val('').attr({readonly:true});
			}

			$.ajax({
				url: 'MotifyInfo.action',
				type: 'post',
				dataType: 'json',
			})
			.done(function(data) {
				console.log(data);
				oldData = data;
				displayData(data);
			})
			.fail(function(err) {
				console.log(err);
			});

			$('#modify-btn').bind('click',function(event){
				$.each(oldData,function(key,value){
					$('#u-' + key).attr({readonly:false});
				});
				$('#u-pass').attr({readonly:false});
				$('#u-phone').focus();
				$('#save-btn').removeClass('disabled');
			});

			$(".m-uinfo input").focus(function(event){
				var _this = $(this),
					$errMsgP = _this.next('.text-error');
				$errMsgP.fadeOut('normal',function(){
					_this.css({
						'margin-bottom' : '40px'
					});
				});
			});
			
			$('#save-btn').bind('click',function(event){
					var name = $('#u-name').val(),
						phone = $('#u-phone').val(),
						shopName = $('#u-shopName').val(),
						pw = $('#u-pass').val(),
						phoneRegex = /^(1(([35][0-9])|(47)|[8][0126789]))\d{8}$/,
						m = 1;
					if($(this).hasClass('disabled')) {
						return false;
					}
					if(name == null || name.trim() == "") {
						$('#u-name').css({
										'margin-bottom' : '10px'
									})
									.next('.text-error')
									.text('用户名不能为空')
									.show();
						m = 0;
					} 
					if(shopName == null || shopName.trim() == "") {
						$('#u-shopName').css({
										'margin-bottom' : '10px'
									})
									.next('.text-error')
									.text('店名不能为空')
									.show();
						m = 0;
					} 
					if(pw==null || pw.length > 12 || pw.length < 6) {
						$('#u-pass').css({
										'margin-bottom' : '10px'
									})
									.next('.text-error')
									.text('密码应该为6-12位')
									.show();
						m = 0;
					} 
					if(phone == null || !phoneRegex.test(phone)) {
						$('#u-phone').css({
										'margin-bottom' : '10px'
									})
									.next('.text-error')
									.text('手机号码不合法')
									.show();
						m = 0;
					}
					if(m) {
						$.ajax({
							url: 'MotifyInfo.action',
							type: 'post',
							dataType: 'json',
							data: {
								pw : pw,
								phone : phone,
								shopName : shopName,
								name : name
							},
						})
						.done(function(data) {
							displayData(data);
						})
						.fail(function(err) {
							
						});
					} else {
						return false;
					}
			});
		});	
	</script>
	<style>
		body,ul {
			padding: 0px;
			margin: 0px;
		}
		
		button,a {
			outline: none !important;
		}

		.main {
			margin:0px auto;
			width:100%;
			overflow:hidden;
			zoom:1;
			text-align:center;
		}
		
		.m-uinfo {
			margin-top : 20px;
			margin-left:auto;
			margin-right:auto;
		}
		
		.m-uinfo ul {
			list-style : none;
		}
		
		
		.m-uinfo ul input.input-lg {
			height : 30px;
			font-size : 18px;
			line-height : 30px;
			margin-bottom:40px;
		}
		
		.m-uinfo ul>li .u-errmsg {
			font-size : 14px;
			color : red;
		}
		
		.m-uinfo .btns {
			margin-left:auto;
			margin-right : auto;
			/*width : 220px;*/
			overflow:hidden;
			zoom : 1;
		}
		
	/*	.m-uinfo .btns .btn-left {
			float : left;
		}
		.m-uinfo .btns .btn-right {
			float : right;
		}*/
	</style>
</head>

<body>
	<div class="main">
		<div class="m-uinfo">
			<ul>
				<li>
					<input class="input-lg" id="u-phone" type="tel" placeholder="手机号码" readonly/>
					<p class="text-error" style="display:none;"></p>
				</li>
			
				<li>
					<input class="input-lg" id="u-name" type="text" placeholder="用户名" />
					<p class="text-error" style="display:none;" ></p>
				</li>
				
				<li>
					<input class="input-lg" id="u-pass" type="text" placeholder="修改密码" />
					<p class="text-error" style="display:none;" ></p>
				</li>
			
				<li>
					<input class="input-lg" id="u-shopName" type="text" placeholder="店名" />
					<p class="text-error" style="display:none;" ></p>
				</li>
			
			</ul>
			<div class="btns">
				<button id="modify-btn" type="button" class="btn btn-large btn-left">修改</button>
				<button id="save-btn" type="button" class="btn btn-large btn-right">保存</button>
			</div>
		</div>
	</div>
</body>
</html>

