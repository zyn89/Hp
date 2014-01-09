(function($,window){
	$(function(){
		
		function uploadFile(formId,url,sid,qid) {
			var form = document.getElementById(formId),
				formData = new FormData(form);
			
			if(sid) {
				formData.append('sid',sid);
			} 
			if(qid) {
				formData.append('qid',qid);
			}
			$.ajax({
				url: url,
				type: 'post',
				processData: false,
       			contentType: false,
				data: formData
			})
			.done(function(data) {
				console.log(data);
				if(sid) {
					$('#j-wdytable tbody').find('tr[data-sid=' + sid + ']').find('.j-mqestion')
																		   .trigger('click.manager.question');
				}
			})
			.fail(function() {
				console.log("error");
			});
		}
		


		function fillDataInWdyTable(surveys) {
			var $tbody = $('#j-wdytable tbody'),
				operateBtns = '<button style="margin-right:10px;" class="btn btn-primary j-delete" type="button">删除</button>'
							  +  '<button style="margin-right:10px;" class="btn btn-primary j-mqestion" type="button">管理调研问题</button>';
				$tbody.empty();
				$.each(surveys,function(index,value){
					var $tr = $('<tr>')
							  .attr({
							  	"data-sid" : value.sid,
							  	"data-imgUrl" : value.imageUrl
							  });
					$('<td>').text(index+1).appendTo($tr);
					$('<td>').css({
								'text-align' : 'center'
							  })
							 .html('<button class="btn btn-primary j-preview" type="button">查看</button>')
							 .appendTo($tr);
					$('<td>').css({
								'text-align' : 'center'
							  })
							 .text(value.credit).appendTo($tr);
					$('<td>').css({
								'text-align' : 'center'
							  })
							 .html(operateBtns)
							 .appendTo($tr);
					$tr.appendTo($tbody);
				});
			
		}

		
		function loadSurveyQuestions(sid) {
			$.ajax({
				url: 'GetSurveyQuestion.action',
				type: 'post',
				dataType: 'json',
				data: {sid: sid},
			})
			.done(function(data) {
				fillDataInWdyqTable(data.questions);
				bindEventAfterQuestionsLoaded();
			})
			.fail(function(err) {
				console.log("error");
			});
			
		}

		function fillDataInWdyqTable(questions) {
			var $tbody = $('tbody','#j-wdyqtable'),
				operateBtns = '<button style="margin-right:10px;" class="btn btn-primary j-q-delete" type="button">删除</button>'
							  +  '<button style="margin-right:10px;" class="btn btn-primary j-q-modify" type="button">修改</button>'
							  + '<button style="margin-right:10px;" class="btn btn-primary j-q-statics" type="button">统计<span class="caret"></span></button>';
			$tbody.empty();
			$.each(questions,function(index,value){
				var $tr = $('<tr>');
				$tr.attr({
					'data-qid' : value.qid,
					'data-imgUrl' : value.imageUrl
				});
				$('<td>').text(index+1).appendTo($tr);
				$('<td>').css({
								'text-align' : 'center'
							  })
							 .html('<button class="btn btn-primary j-q-preview" type="button">查看</button>')
							 .appendTo($tr);
				$.each(value.choiceItems,function(i,val){
					$('<td>').css({
								'text-align' : 'center'
							  })
							 .text(val).appendTo($tr);
				});

				$('<td>').css({
								'text-align' : 'center'
							  })
							 .html(operateBtns)
							 .appendTo($tr);
				$tr.appendTo($tbody);
			});
		}

		
		function bindEventAfterQuestionsLoaded(){
			//关于问题的一些事件
			//1 图片预览
			$('.j-q-preview').bind('click.preview.img',function(event){
					var _this = $(this),
					imgUrl = _this.parents('tr').attr('data-imgUrl');
				$(".modal-body img",'#j-imgpreview').attr({
					src : imgUrl
				});
				$('#j-imgpreview').modal('show');
			});
			
			//2 问题删除
			$('.j-q-delete').bind('click.for.del',function(event){
				var _this = $(this),
					qid = _this.parents('tr').attr('data-qid'),
					sid = _this.parents('#j-wdyqtable').attr('data-sid');
				$.ajax({
					url: 'DeleteSurveyQuestion.action',
					type: 'post',
					dataType: 'json',
					data: {
						qid : qid,
						sid : sid
					},
				})
				.done(function(data) {
					_this.parents('tr').remove();
				})
				.fail(function() {
					console.log("error");
				});
			});
			//3 问题修改

			$('.j-q-modify').bind('click.for.modify',function(event){
				var _this = $(this),
					$tr = _this.parents('tr'),
					qid = $tr.attr('data-qid'),
					imgUrl = $tr.attr('data-imgUrl'),
					a1 = $tr.find('td').eq(2).text(),
					a2 = $tr.find('td').eq(3).text(),
					a3 = $tr.find('td').eq(4).text();
				$('.modal-header h3','#j-add-qustion-modal').text('修改调研问题');
				$('#j-oldimg img','#j-add-qustion-modal').attr({src:imgUrl});
				$('#qid','#j-add-qustion-modal').val(qid);
				$('#j-oldimg','#j-add-qustion-modal').show();
				$('#j-q-filename','#j-add-qustion-modal').text(imgUrl);//应该显示图片

				$('#j-q-a1','#j-add-qustion-modal').val(a1);
				$('#j-q-a2','#j-add-qustion-modal').val(a2);
				$('#j-q-a3','#j-add-qustion-modal').val(a3);

				$('#j-add-qustion-modal').addClass('s-mark').modal('show');
				

			});


			//3 问题统计
			$('.j-q-statics').bind('click.for.statics',function(event){


				var _this = $(this),
					$curTr = _this.parents('tr'),
					$nextTr = $curTr.next(),
					qid = $curTr.attr('data-qid'),
					sid = _this.parents('#j-wdyqtable').attr('data-sid'),
					$tr,$td,$newDiv;

				function callback(pageNo,pageSize) {
					var start = (pageNo-1)*pageSize,
						end = (start+pageSize)-1;
					
						$.ajax({
							url: 'QueryUserSurvey.action',
							type: 'post',
							dataType: 'json',
							data: {
								sid: sid,
								qid: qid,
								start: start,
								end : end
							}
						}).done(function(data) {
					
							var $tbody = $('tbody','#qid_'+qid),
								userSurvey = data.userSurvey;
							$tbody.empty();

							$.each(userSurvey,function(index,value){
								var $tr = $('<tr>');
								$('<td>').text(value.name)
								  .appendTo($tr);
								$('<td>').css({
									'text-align' : 'center'
								}).text(value.answer)
								  .appendTo($tr);
								$tr.appendTo($tbody);
							});

						})
						.fail(function(err) {
							console.log(err);
						});
				}

				if(!$nextTr.hasClass('s-mark')) {


					

					$('.j-common').parents('tr').hide().removeClass('s-active');
					$tr = $('<tr>').addClass('s-mark s-active');
					$td = $('<td>').attr({
								colspan : 6
							}).appendTo($tr);
		
					$newDiv = $('#j-clone').clone().attr({id:'qid_'+qid})
									 .show()
									 .appendTo($td);

					$.ajax({
						url: 'QueryUserSurvey.action',
						type: 'post',
						dataType: 'json',
						data: {
							sid: sid,
							qid: qid,
							start: 0,
							end : 1
						}
					})
					.done(function(data) {
						$('.pagination',$newDiv).BTPagination(data.count,{
							items_per_page : 10 ,
							num_display_pageno : 10 ,
							prev_text : 'Prev' ,
							next_text : 'Next' ,
							getItemsAjax : callback
						});
						$curTr.after($tr);
					});
				} else {
					if($nextTr.hasClass('s-active')) {
						$nextTr.hide().removeClass('s-active');
					} else {
						$('.j-common').parents('tr').hide().removeClass('s-active');
						$nextTr.show().addClass('s-active');
					}
				}	


				//加载用户信息 初始化分页


			});
			
		}
		
		function bindEventAfterDataLoaded() {
			$('.j-delete').on('click.del',function(event){
					var _this = $(this),
						sid = _this.parents('tr').attr('data-sid');
					
					$.ajax({
						url: 'DeleteSurvey.action',
						type: 'post',
						dataType: 'json',
						data: {sid: sid},
					})
					.done(function(data) {	
						console.log(data);
						_this.parents('tr').remove();
					})
					.fail(function(err) {
					});
			});

			$('.j-preview').bind('click.preview.img',function(event){
				var _this = $(this),
					imgUrl = _this.parents('tr').attr('data-imgUrl');
				$(".modal-body img",'#j-imgpreview').attr({
					src : imgUrl
				});
				$('#j-imgpreview').modal('show');
			});

			$('.j-mqestion').bind('click.manager.question',function(event){
				var _this = $(this),
					sid = _this.parents('tr').attr('data-sid');
				$('#j-wdytable').fadeOut('slow',function(){
					$('#j-bread').text('<<返回').addClass('s-mark')
								 .css({
									  cursor : 'pointer'
								 })
								 .mouseover(function(event){
									 $(this).css({
										 'text-decoration' : 'underline',
										  color : '#0088cc'
									 });
								 }).mouseout(function(event){
										 $(this).css({
											 'text-decoration' : 'none',
											  color : '#000'
										 });
								 });
					$('#j-wdyqtable').fadeIn()
									.attr({
										'data-sid' :  sid
									});
				});
				
				
				
				//获取数据填充表格
				loadSurveyQuestions(sid);
				
			});
		}

		
		
		function bindEvent() {
			
			$('#j-imgpreview').on('show', function () {
				  $(this).css({width:'auto'});
			      $('.modal-body',this).css({width:'auto',height:'auto', 'max-height':'100%'});
			});
			
			//增加微调研弹出模态框
			$('#j-addwdy').bind('click.foradd',function(){
				if($('#j-bread').hasClass('s-mark')) {
					$('.modal-header h3','#j-add-qustion-modal').text('增加调研问题');
					$('#j-oldimg','#j-add-qustion-modal').hide();

					$('#j-q-filename','#j-add-qustion-modal').text("");//应该显示图片

					$('#j-q-a1','#j-add-qustion-modal').val("");
					$('#j-q-a2','#j-add-qustion-modal').val("");
					$('#j-q-a3','#j-add-qustion-modal').val("");
					$('#j-add-qustion-modal').removeClass('s-mark').modal('show');
				} else {
					$('#j-addmodel').modal('show');
				}
			});
			
			//获取上传图片的名称
			document.getElementById('j-uploadfile').onchange = function () {
				  $("#j-filename").text(this.value);
			};
			document.getElementById('j-q-uploadfile').onchange = function () {
				  $("#j-q-filename").text(this.value);
			};
			
			//保存上传wdy信息
			$('#j-savewdy').bind('click.forsave',function(event){
				uploadFile('j-addwdyform','AddSurvey.action');
				$('#j-addmodel').modal('hide');
			});

			$('#j-q-savewdy').bind('click.forsave',function(event){
				if($(this).parents('#j-add-qustion-modal').hasClass('s-mark')) {
					uploadFile('j-addwdyqform','UpdateSurveyQuestion.action',$('#j-wdyqtable').attr('data-sid'));
				} else {
					uploadFile('j-addwdyqform','AddSurveyQuestion.action',$('#j-wdyqtable').attr('data-sid'));
				}
				$('#j-add-qustion-modal').modal('hide');
			});
			
			//上传图片触发事件
			$('#j-abtn').bind('click.forupload',function(){
				$('#j-uploadfile').trigger('click');
			});

			$('#j-q-abtn').bind('click.forupload',function(){
				$('#j-q-uploadfile').trigger('click');
			});
			
			$('#j-bread').bind('click.forback',function(event){
				var _this = $(this);
				if(_this.hasClass('s-mark')) {
					$('#j-wdyqtable').fadeOut('slow',function(){
						$('#j-bread').text('调研活动').removeClass('s-mark')
									 .unbind('mouseover').unbind('mouseout')
									 .css({
										 'text-decoration' : 'none',
										  color : '#000'
									 });
						$('#j-wdytable').fadeIn();
					});
				}
			});
				

		}
		
				
		
		bindEvent();
		$.ajax({
			url: 'GetSurveyList.action',
			type: 'post',
			dataType: 'json',
		})
		.done(function(data) {
			var surveys = data.surveys;
			fillDataInWdyTable(surveys);
			bindEventAfterDataLoaded();
		})
		.fail(function() {
			console.log("error");
		});

		

	});
})(jQuery,window);