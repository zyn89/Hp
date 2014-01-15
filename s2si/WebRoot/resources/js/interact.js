//pageSize = 4
(function(window,$){
			

			$(function(){
				var acts; //save activities retrieved from server 

				

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

				//事件处理中还应更改样式 计算分页点的宽度 调节总体宽度
				
			});
})(window,jQuery);