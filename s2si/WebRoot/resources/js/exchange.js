(function(window,$){
	$(function(){
		
//{"eid":4,"exchangeUrl":"D:\\ExchangePic\\exchange19546027_1389339858470_2740.jpeg","descUrl":"D:\\ExchangePic\\exchangeDesc19546027_1389339859193_3097.jpeg","credit":12,"prizeUrl":"D:\\ExchangePic\\prizeDesc19546027_1389339854042_1210.jpeg"}]}
		$( document ).ajaxComplete(function(data,xhr) {
			//console.log(xhr);
		});


		function fillInExchangeTable(data) {
			var $tbody = $('tbody','#j-exchangetable'),
				records = data.exchanges,
				operateBtns = '<button style="margin-right:10px;" class="btn btn-primary j-c-delete" type="button">删除</button>'
							  +  '<button style="margin-right:10px;" class="btn btn-primary j-c-modify" type="button">修改</button>'
							  + '<button style="margin-right:10px;" class="btn btn-primary j-c-statics" data-loading-text="正在加载" type="button">统计</button>';
				
			console.log(records);
			$tbody.empty();
			
			$.each(records,function(index,record){

				var $tr =  $('<tr>').attr({
					'data-eid' : record.eid
				});
				$('<td>').css({
					'vertical-align':'middle',
				}).text(index+1).appendTo($tr);
				$('<td>').css({
					'text-align' : 'center'
				}).attr({
					'data-imgUrl' : record.exchangeUrl
				}).html('<button type="button" class="btn btn-link j-imgpreview">查看</button>')
				  .appendTo($tr);
				$('<td>').css({
					'text-align' : 'center'
				}).attr({
					'data-imgUrl' : record.descUrl
				}).html('<button type="button" class="btn btn-link j-imgpreview">查看</button>')
				  .appendTo($tr);
				$('<td>').css({
					'text-align' : 'center'
				}).attr({
					'data-imgUrl' : record.prizeUrl
				}).html('<button type="button" class="btn btn-link j-imgpreview">查看</button>')
				  .appendTo($tr);
				$('<td>').css({
					'text-align' : 'center'
				}).attr({
					'data-imgUrl' : record.prizeDescUrl
				}).html('<button type="button" class="btn btn-link j-imgpreview">查看</button>')
				  .appendTo($tr);
				$('<td>').css({
					'text-align' : 'center',
					'vertical-align':'middle',
				}).text(record.credit)
				  .appendTo($tr);
				$('<td>').css({
					'text-align' : 'center'
				}).html(operateBtns).appendTo($tr);

				$tr.appendTo($tbody);
			});
		}

		function uploadFile(formId,url,callback) {
			var vali=$("body").data("validate");
			var flag=false;
			var index=0;
			$("#"+formId).find('div input[type="hidden"]').each(function(i,e){
				var str=$(e).val();
				console.log(str);
				var name=$(e).attr("name");
				console.log("index="+index);
				if(str==99 || str=='99'){					
					$(e).val(index);
					index=index+1;
					//$("p."+name).css("display","none");
				}else{
					//if(vali){						
					//	$("p."+name).css("display","inline-block");
					//	flag=true;
					//}
					$(e).val(-1)
				}				
			});
			$("#"+formId).find('div input[type="file"]').each(function(i,e){
				var str=$(e).val();
				//console.log(str);
				var name=$(e).parent().find("input[type='hidden']").attr("name");
				console.log(name);
				console.log(e.files);
				if(e.files.length!=0){	
					$("p."+name).css("display","none");
				}else{
					if(vali){
						$("p."+name).css("display","inline-block");
						flag=true;
					}
				}				
			});
			var str=$("#"+formId).find("input[name='credit']").val();
			if((str=="" || !str) && vali==true){
				$("p.credit").css("display","inline-block");
				flag=true;
			}else{
				$("p.credit").css("display","none");
			}
			if(flag==true && vali==true){
				//$("#"+formId)[0].reset();
				return;
			}
			var form = document.getElementById(formId),
				formData = new FormData(form);
			$.ajax({
				url: url,
				type: 'post',
				processData: false,
       			contentType: false,
				data: formData
			})
			.done(function(data) {
				//console.log(data);
				//console.log(data);
				try{
					if(callback) {
						callback($.parseJSON(data));
						bindEventAfterDataLoaded();
					}
					$('#j-addmodel').modal('hide');
				 }catch(exception){				
				     alert("an error processed");
				 }				 
			
			})
			.fail(function(err) {
			
			});
		}

		function bindEvent() {

			//弹出增加模态框
			$('#j-addexchange').bind('click.foradd',function(e){
				console.log(e);
				$('#j-addmodel').modal('show');
				$('#j-addmodel').find("span.j-filename").text("");
				$('#j-addmodel').find("p.text-error").css("display","none");
			});
			
			//点击上传 时间绑定
			$('.j-abtn').bind('click.forupload',function(){
				var _this = $(this),
					$input_file = _this.parents('.controls').find('.j-uploadfile');
				$input_file.trigger('click');
			});

			//获取图片名称 设置标识
			$('.j-uploadfile').change(function(event){
				var _this = $(this),
					value = _this.get(0).value,
					$divParent =  _this.parents('.controls'),
					$fileName =$divParent.find('.j-filename'),
					$input_hidden = $divParent.find('input[type="hidden"]');
				$fileName.text(value);
				$(this).parent().find("p.text-error").css("display","none");
				//$input_hidden.val(value.substring(value.lastIndexOf('\\') + 1));
				//99只是个标记值，没有任何意义
				$input_hidden.val(99);
			});

			//保存增加兑奖活动
			$('#j-saveexchange').bind('click.foradd',function(event){
				$("body").data("validate",true);
				uploadFile('j-addexchangeform','AddExchange.action',fillInExchangeTable);
				//$('#j-addmodel').modal('hide');
			});
			//增加活动对话框关闭时清空form
			$('#j-addmodel').bind('hide',function(){
				$(this).find("form")[0].reset();
			});
			//保存修改
			$('#j-ex-update').bind('click.for.update',function(event){
				uploadFile('j-update-form','updateExchange.action',fillInExchangeTable);
				$('#j-update-exchange-modal').modal('hide');
			});
		}


		function bindEventAfterDataLoaded() {
			//1. 图片预览
			$('.j-imgpreview').bind('click.forpreview',function(event){
				var _this = $(this),
					imgUrl = _this.parents('td').attr('data-imgUrl');
				$(".modal-body img",'#j-imgpreview').attr({
					src : imgUrl
				});
				$('#j-imgpreview').modal('show');
			});

			//2.删除活动
			$('.j-c-delete').bind('click.for.del',function(event){
				if(confirm("确定要删除吗？")){
					
				}else{
					return;
				}
				var _this = $(this),
					eid = _this.parents('tr').attr('data-eid');
					
				$.ajax({
					url: 'DeleteExchange.action',
					type: 'post',
					dataType: 'json',
					data: {
						eid : eid,
					},
				})
				.done(function(data) {
					try{
						if(typeof(data)!="object"){					
							JSON.parse(data);
						} 
					 }catch(exception){	
					     alert("an error processed");
					     return;
					 }
					var $tr = _this.parents('tr');
					//修改序号
					$trs = _this.parents('tbody').find('tr').not($tr);
					$tr.remove();
					$.each($trs,function(index,tr){
						$(tr).find('td:eq(0)').text(index+1);
					});
				})
				.fail(function() {
					console.log("error");
				});
			});

			//3.修改活动
			$('.j-c-modify').bind('click.for.modify',function(event){
				$("body").data("validate",false);
				var _this = $(this);
					$pTr = _this.parents('tr'),
					eid = $pTr.attr('data-eid'),
					$tds = $pTr.find('td:gt(0):lt(4)'),
					credit = $pTr.find('td:eq(5)').text(),
					$imgs = $('#j-oldimg').find('img');
				/*$.each($tds,function(index,tr){
					$imgs.eq(index).attr({
						src : $(tr).attr('data-imgUrl')
					});
				});*/
				$('#eid','#j-update-exchange-modal').val(eid);
				$('#j-u-credit','#j-update-exchange-modal').val(credit);
				$('#j-update-exchange-modal').modal('show');
			});

			//4.统计活动
			$('.j-c-statics').bind('click.for.statics',function(event){
				$(this).button("loading");
				var _this = $(this),
					$curTr = _this.parents('tr'),
					$nextTr = $curTr.next(),
					eid = $curTr.attr('data-eid'),
					$tr,$td,$newDiv;

				function callback(pageNo,pageSize) {
					var start = (pageNo-1)*pageSize,
						end = (start+pageSize)-1;
					
						$.ajax({
							url: 'ExchangerList.action',
							type: 'post',
							dataType: 'json',
							data: {
								eid: eid,
								start: start,
								end : end
							}
						}).done(function(data) {
					
							//var $tbody = $('tbody','#eid_'+eid),
							var $tbody=$('#j-wdyquser').find("tbody");
								users = data.users;
							$tbody.empty();

							$.each(users,function(index,value){
								var $tr = $('<tr>');
								$('<td>').css({
									'text-align' : 'center'
								})
								  .text(value.name)
								  .appendTo($tr);
								$tr.appendTo($tbody);
							});
							$("a.back").text("返回上一级");
							$("a.back").addClass("rear");
							$('.pagination').removeClass("hide");
							$("#j-exchangetable").fadeOut();
							$('#j-wdyquser').removeClass("hide");
							$('#j-wdyquser').fadeIn();
							_this.button("reset");
						})
						.fail(function(err) {
							console.log(err);
						});
				}
				$.ajax({
						url: 'ExchangerList.action',
						type: 'post',
						dataType: 'json',
						data: {
							eid: eid,
							start: 0,
							end : 1
						}
					})
					.done(function(data) {
						if(data.count==0){
							_this.button("reset");
							alert("没有统计数据");
							return;
						}
						$('.pagination',$newDiv).BTPagination(data.count,{
							items_per_page : 10 ,
							num_display_pageno : 10 ,
							prev_text : '上一页' ,
							next_text : '下一页' ,
							getItemsAjax : callback
						});
						//$curTr.after($tr);
					});
				/*
				 * if(!$nextTr.hasClass('s-mark')) {
					

					$('.j-common').parents('tr').hide().removeClass('s-active');
					$tr = $('<tr>').addClass('s-mark s-active');
					$td = $('<td>').attr({
								colspan : 7
							}).appendTo($tr);
		
					$newDiv = $('#j-clone').clone().attr({id:'eid_'+eid})
									 .show()
									 .appendTo($td);

					$.ajax({
						url: 'ExchangerList.action',
						type: 'post',
						dataType: 'json',
						data: {
							eid: eid,
							start: 0,
							end : 1
						}
					})
					.done(function(data) {
						$('.pagination',$newDiv).BTPagination(data.count,{
							items_per_page : 10 ,
							num_display_pageno : 10 ,
							prev_text : '上一页' ,
							next_text : '下一页' ,
							getItemsAjax : callback
						});
						//$curTr.after($tr);
					});
				} else {
					if($nextTr.hasClass('s-active')) {
						$nextTr.hide().removeClass('s-active');
					} else {
						$('.j-common').parents('tr').hide().removeClass('s-active');
						$nextTr.show().addClass('s-active');
					}
				}*/	


				//加载用户信息 初始化分页

			});

		}

		$("a.back").bind("click",function(){
			if($(this).hasClass("rear")){
				$(this).removeClass("rear");
				$("#j-exchangetable").fadeIn();
				$('#j-wdyquser').addClass("hide");
				$('#j-wdyquser').fadeOut();
				$(this).text("兑奖管理");
				$('.pagination').addClass("hide");
			}
		});
		bindEvent(); 
		$(".btn.refresh").button("loading");
		$.ajax({
			url: 'GetExchanges.action',
			type: 'post',
			dataType: 'json',
		})
		.done(function(data) {
			try{
				if(typeof(data)!="object"){					
					JSON.parse(data);
				} 
			 }catch(exception){	
			     alert("an error processed");
			     return;
			 }
			fillInExchangeTable(data);
			bindEventAfterDataLoaded();
			$(".btn.refresh").button("reset");
		})
		.fail(function(err) {
			console.log(err);
		});

	});
})(window,jQuery);