//pageSize = 4
(function(window,$){
			

			$(function(){
				/*var acts; //save activities retrieved from server 

				

				function initPagination(acts,pageSize) {
					var len = acts.length,
						$paginationContainer = $('ul.u-circles'),
						totalPageNum = Math.ceil(len/pageSize),
						i;

					for(i = 0; i <= totalPageNum; i+=1) {
						$('<li>').attr({
							'data-pageno' : i
						})
						.appendTo($paginationContainer);
					}

				}

				function paginationEventHandler(event,pageSize) {
					var _target = $(event.target);
						pageno = _target.data('pageno'),
						start = pageno*pageSize,
						end = (start + pageSize) - 1;
					diplayActs(start,end);
				}

				function diplayActs(start,end) {
					var i,$newLi,curAct
						$actContainer = $('#exchange-items');
					for(i = start; i <= end; i+=1) {
						curAct = acts[i];
						$newLi = $('#j-clone').clone().attr({
							id:'activity' + i,
							'data-href' : 'interact_beginActivity.action?aid=' + curAct.aid + '&type=' + curAct.type + '&descUrl=' + curAct.descUrl
						});

						$('.u-prize',$newLi).attr({
							src : curAct.activityUrl
						});
						$newLi.appendTo($actContainer);
					}
				}


				$.ajax({
					url: '/s2si/ActivityList.action',
					type: 'post',
					dataType: 'json',
				})
				.done(function(data) {
					acts = data.activities;
					// diplayActs(data.activities);
				})
				.fail(function(error) {
					console.log("error");
				});

				
				initPagination(acts,4);
				//bind event handle pagination 
				$('ul.u-circles').on('click.pagination',{'pageSize':4},'li',function(event){
					paginationEventHandler(event,eventd.data.pageSize);
				});

				//事件处理中还应更改样式 计算分页点的宽度 调节总体宽度*/
				
				
				function diplayActs(data) {
				 	var i,$newLi,
						$actContainer = $('#exchange-items');
				 	$actContainer.empty();	
					
					$.each(data,function(index,value){
						var aid = value.aid,
			 				activityUrl = value.activityUrl,
							credit = value.credit,
							descUrl = value.descUrl,
							done = value.done,
			 				score = value.score,
			 				type = value.type;

						$newLi = $('#j-clone').clone().attr({
							id:'activity' + index,
							'data-href' : 'interact_beginActivity.action?aid=' + aid + '&type=' + type + '&descUrl=' + descUrl
						});

						$('.u-prize',$newLi).attr({
							src :activityUrl
						});
						
						
					
			 			

			 			if(done) {
			 				//设置图片是活动完成的图片
			 				$('.u-arrow',$newLi).hide();
			 				$('<span>').addClass('finished').text('已完成').appendTo($newLi);
			 				$newLi.attr({
				 				'data-done' : 1
				 			});
			 			} 
		
			 			$newLi.show().appendTo($actContainer);
			 		});
			 	}
				
				
				
				$.ajax({
			 		url: '/s2si/ActivityList.action',
			 		type: 'post',
			 		dataType: 'json'
			 	})
			 	.done(function(data) {
			 		diplayActs(data.activities);
			 	})
			 	.fail(function(error) {
			 		console.log("error");
			 	});


			 	$('#exchange-items').on('click','li',function(event){
				 	var _target = $(event.target),
				 		done = _target.data('done'),
				 		href = _target.data('href');
			 			
			 		
				 	if(!done){
					 	window.location.href = href;
				 	}
			 	});
				
				
			});
})(window,jQuery);