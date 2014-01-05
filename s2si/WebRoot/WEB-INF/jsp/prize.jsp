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
	
	<script src="resources/js/jquery-1.9.1.js"></script>
	<script src="resources/js/wScratchPad.js"></script>
	<style>
		.u-ggk {
			margin-top: 20px;
		}	
	</style>


	
	<script>
		jQuery(function($){




			//start ggk part
				var state = { status : 0 ,m : [1,1,1]};


				function _callback(e,percentage,state,index,_this) {
					if(percentage > 80 && state.m[index]) {
						state.status += 1;
						_this.clear();
						if(state.status == 3) {
							alert($('#lottery_result').text());
						}
						state.m[index] = 0;
					}
					console.log('p:' + percentage);
					console.log('s:' + state.status);
				}



				function initGGK(options) {
					var defaultOpt = {
						width : 210,
						height : 100,
						color : '#999',
						size : 20
					},
					opts = $.extend(defaultOpt,options || {});
					opts.container.wScratchPad({
					    width           : opts.width,                
					    height          : opts.height,                
					    image           : opts.imgUrl,  
					    image2          : null,              
					    color           : opts.color,     
					    overlay         : 'none',    
					    size            : opts.size,               
					    realtimePercent : true,             
					    scratchDown     : function(e,percent){
					    						opts.callback(e,percent,opts.state,opts.index,this);
					    				 },               
					    scratchUp       : function(e,percent){
					    						opts.callback(e,percent,opts.state,opts.index,this);
					    				 },                   
					    scratchMove     : function(e,percent){	
					    						opts.callback(e,percent,opts.state,opts.index,this);
					    				 },                   
					    cursor          : '/images/coin.png'        
					});

				}
	



			//end ggk 



			function displayInfo(url,callback,contentDiv) {
				$.ajax({
					url: url,
					type: 'post',
					dataType: 'json'
				})
				.done(function(data) {
					callback(data,contentDiv);
				})
				.fail(function(err) {
					console.log("error");
				});
			}

			
			function _btn1Callback(data,contentDiv) {
				var exchanges = data.exchanges,
					$ul = $('<ul>');
				$.each(exchanges,function(index,value){
					var $li = $('<li>'),
						$a = $('<a>'),
						$img = $('<img>');
					$a.attr({
						href : "interact_prizeInfo.action?eid=" + value.eid + '&descUrl=' + value.descUrl
					});
					$img.attr({
						src : value.exchangeUrl
					});

					$a.append($img).appendTo($li);
					$li.appendTo($ul);
				});
				contentDiv.empty();
				contentDiv.append($ul);
			}

			function _btn2Callback(data,contentDiv) {
				var lottery = (data.lotterys)[0],
					lotteryCount = data.lotteryCount,
					credit = data.credit,
					choiceItems = lottery.choiceItems,
					$ggks = $('.u-ggk').empty();

				$('.m-credit p',contentDiv).text('剩余次数:' + lotteryCount);
				state.m = [1,1,1];
				state.status = 0;
				$('.m-pic img',contentDiv).attr({
					src :　lottery.lotteryUrl
				});

				if(choiceItems[0] == choiceItems[1] && choiceItems[1] == choiceItems[2]) {
					$('#lottery_result').text('获奖');
				} else {
					$('#lottery_result').text('遗憾没获奖');
				}
				$.each(choiceItems,function(index,value){
					initGGK({
						'imgUrl' : value,
						callback : _callback,
						state : state,
						index : index,
						container : $ggks.eq(index)
					});

				});
			}

			function _btn3Callback(data,contentDiv) {
				var lotterys = data.lotterys;
				contentDiv.empty();
				if(!lotterys || lotterys.length == 0) {
					contentDiv.html('<p>没有获得奖品</p>');
				} else {
					$.each(lotterys,function(index,value){
						var $a = $('<a>'),
							$img = $('<img>');

						$a.attr({
							href : 'javascript:void(0);'
						});
						$img.attr({
							src : value.prizeUrl
						});
						$a.append($img).appendTo(contentDiv);
					});
				}
			}

			$('.m-tabs-btns ul button').bind('click',function(event){
				var _this = $(this),
				    btnId = _this.attr('id'),
					contentId = btnId.substring(0, btnId.length-1);
				$('.tab-content').hide();
				$('#' + contentId).show();

				switch(contentId) {
					case "one" : 
						displayInfo("GetExchanges.action",_btn1Callback,$('#' + contentId));
						break;
					case "two" :
						displayInfo("LotteryList.action",_btn2Callback,$('#' + contentId));
						break;
					case "three" :
						displayInfo("QueryLottery.action",_btn3Callback,$('#' + contentId));
						break;
				}

			}); 
			
			$('.m-tabs-btns ul button:eq(0)').trigger('click');
			
		




		});
	</script>
	<style>
	
	</style>
</head>

<body>
	<div class="main">
		<div class="m-tabs">
			<div class="m-tabs-btns">
				<ul>
					<li><button id="one#">兑奖</button></li>
					<li><button id="two#">抽奖</button></li>
					<li><button id="three#">奖品</button></li>
				</ul>
			</div>
			<div class="m-tabs-content">
				<div id="one" style="display:none;" class="tab-content">
					<ul>
						<li><a href=""><img src="" alt=""></a></li>
						<li><a href=""><img src="" alt=""></a></li>
						<li><a href=""><img src="" alt=""></a></li>
					</ul>
				</div>
				<div id="two" style="display:none;" class="tab-content">
					<div class="m-credit" >
						<p></p>
					</div>
					<div class="m-pic">
						<a href=""><img src="" alt=""></a>
					</div>
					<div class="m-ggk">
						 <div class="u-ggk"></div>
						 <div class="u-ggk"></div>
						 <div class="u-ggk"></div> 
						 <div id="lottery_result" style="display:none;">
						 </div>
					</div>
				</div>
				<div id="three" style="display:none;" class="tab-content">
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>

