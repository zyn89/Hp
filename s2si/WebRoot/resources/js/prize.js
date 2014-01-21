jQuery(function($){




			//start ggk part
				var state = { status : 0 ,m : [1,1,1],r : 0};
				
				
				

				function _callback(e,percentage,state,index,_this,lid) {
					console.log('lid:' + lid);
					if(percentage > 80 && state.m[index]) {
						state.status += 1;
						_this.clear();
						if(state.status == 3) {
							if(state.r){
								//中奖后
								$.ajax({
									url: 'DrawLottery.action',
									type: 'post',
									dataType: 'json',
									data : {
										lid : lid,
										isAward : 1
									}
								})
								.done(function(data) {
									window.location.reload();
									$('.m-tabs-btns ul li').eq(1).trigger('click');
								})
								.fail(function(err) {
									console.log("error");
									
								});
							} else {
								//没有中奖
								$.ajax({
									url: 'DrawLottery.action',
									type: 'post',
									dataType: 'json',
									data : {
										lid : lid,
										isAward : 0
									}
								})
								.done(function(data) {
									window.location.reload();
									$('.m-tabs-btns ul li').eq(1).trigger('click');
								})
								.fail(function(err) {
									console.log("error");
								});
							}
							alert($('#lottery_result').text());
						}
						state.m[index] = 0;
					}
					
				}



				function initGGK(options) {
					var defaultOpt = {
						width : 210,
						height : 100,
						color : '#999',
						size : 20,
						image2  : null,
						lid :0
					},
					opts = $.extend(defaultOpt,options || {}),
					sp = opts.container.wScratchPad({
					    width           : opts.width,                
					    height          : opts.height,                
					    image           : opts.imgUrl,  
					    image2          : opts.image2,              	
					    size            : opts.size,               
					    realtimePercent : true,             
					    scratchDown     : function(e,percent){
					    						opts.callback(e,percent,opts.state,opts.index,this,opts.lid);
					    				 },               
					    scratchUp       : function(e,percent){
					    						opts.callback(e,percent,opts.state,opts.index,this,opts.lid);
					    				 },                   
					    scratchMove     : function(e,percent){	
					    						opts.callback(e,percent,opts.state,opts.index,this,opts.lid);
					    				 },                   
					    cursor          : '/images/coin.png'        
					});
				/*	sp.wScratchPad('image2', './images/scratch-to-win.png');
					sp.wScratchPad('reset');*/
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
					$ul = $('#exchange-items');
				$ul.empty();
				$.each(exchanges,function(index,value){
					var $li = $('#u-pitem-clone').clone().attr({id:'exchange-'+ index}),
						$img = $('img.u-prize',$li);
					
					$li.attr({
						'data-href' : "interact_prizeInfo.action?eid=" + value.eid + '&descUrl=' + value.descUrl + '&prizeUrl=' + value.prizeUrl
					});
					
					$img.attr({
						src : value.exchangeUrl
					});
					$li.show().appendTo($ul);
				});
			}
			

			function _btn2Callback(data,contentDiv) {
				var lottery = (data.lotterys)[0],
					lotteryCount = data.lotteryCount,
					lid = lottery.lid,
					credit = data.credit,
					choiceItems = lottery.choiceItems,
					$ggks = $('.u-ggk').empty();
				
				console.log('lid_in_callback:' + lid );
				$('.m-credit p',contentDiv).text('剩余次数:' + lotteryCount);
				state.m = [1,1,1];
				state.status = 0;
				state.r = 0;
				$('img.m-pic',contentDiv).attr({
					src :　lottery.lotteryUrl
				});

				if(choiceItems[0] == choiceItems[1] && choiceItems[1] == choiceItems[2]) {
					$('#lottery_result').text('获奖');
					state.r = 1;
				} else {
					$('#lottery_result').text('遗憾没获奖');
					state.r = 0;
				}
				$.each(choiceItems,function(index,value){
					initGGK({
						'imgUrl' : value,
						image2 : 'resources/image/cardsm.png',
						width:98,
						height:98,
						callback : _callback,
						state : state,
						index : index,
						container : $ggks.eq(index),
						lid : lid
					});

				});
			}

			function _btn3Callback(data,contentDiv) {
				var lotterys = data.lotterys;
					$ul = $('#prize-items');
					$ul.empty();
				/*if(!lotterys || lotterys.length == 0) {
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
				}*/
				
				
				
				$.each(lotterys,function(index,value){
					var $li = $('#u-pitem-clone').clone().attr({id:'prize-'+ index}),
						$img = $('img.u-prize',$li);
					
					$li.attr({
						'data-href' : "interact_prize.action?descUrl=" + value.descUrl
					});
					
					$img.attr({
						src : value.prizeUrl
					});
					$li.show().appendTo($ul);
				});	
				
			}

			$('.m-tabs-btns ul li').bind('click',function(event){
				var _this = $(this),
				    btnId = _this.attr('id'),
					contentId = btnId.substring(0, btnId.length-1);
				_this.siblings().removeClass('tab-light').addClass('tab-dark')
				.end().removeClass('tab-dark').addClass('tab-light');
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
			
			$('.m-tabs-btns ul li:eq(0)').trigger('click');
			$('#exchange-items,#prize-items').on("click.for.exchange","li",function(event){
				var _target = $(event.target),
					href = _target.data('href');
				window.location.href = href;
			});	
			
			$('#exchange-items,#prize-items').on('click.img','img',function(event){
				var _target = $(event.target),
					parentLi = _target.parents('li');
				parentLi.trigger('click');
				return false;
			});


		});