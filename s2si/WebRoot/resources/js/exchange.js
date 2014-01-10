(function(window,$){
	$(function(){
		
//{"eid":4,"exchangeUrl":"D:\\ExchangePic\\exchange19546027_1389339858470_2740.jpeg","descUrl":"D:\\ExchangePic\\exchangeDesc19546027_1389339859193_3097.jpeg","credit":12,"prizeUrl":"D:\\ExchangePic\\prizeDesc19546027_1389339854042_1210.jpeg"}]}



		function fillInExchangeTable(data) {
			var $tbody = $('tbody','#j-exchangetable'),
				records = data.exchanges,
				operateBtns = '<button style="margin-right:10px;" class="btn btn-primary j-c-delete" type="button">删除</button>'
							  +  '<button style="margin-right:10px;" class="btn btn-primary j-c-modify" type="button">修改</button>'
							  + '<button style="margin-right:10px;" class="btn btn-primary j-c-statics" type="button">统计<span class="caret"></span></button>';
				
			console.log(records);
			$tbody.empty();
			
			$.each(records,function(index,record){

				var $tr =  $('<tr>').attr({
					'data-eid' : record.eid
				});
				$('<td>').text(index+1).appendTo($tr);
				$('<td>').css({
					'text-align' : 'center'
				}).attr({
					'data-imgUrl' : record.exchangeUrl
				}).html('<button type="button" class="btn btn-primary j-imgpreview">查看</button>')
				  .appendTo($tr);
				$('<td>').css({
					'text-align' : 'center'
				}).attr({
					'data-imgUrl' : record.descUrl
				}).html('<button type="button" class="btn btn-primary j-imgpreview">查看</button>')
				  .appendTo($tr);
				$('<td>').css({
					'text-align' : 'center'
				}).attr({
					'data-imgUrl' : record.prizeUrl
				}).html('<button type="button" class="btn btn-primary j-imgpreview">查看</button>')
				  .appendTo($tr);
				$('<td>').css({
					'text-align' : 'center'
				}).attr({
					'data-imgUrl' : record.prizeDescUrl
				}).html('<button type="button" class="btn btn-primary j-imgpreview">查看</button>')
				  .appendTo($tr);
				$('<td>').css({
					'text-align' : 'center'
				}).text(record.credit)
				  .appendTo($tr);
				$('<td>').css({
					'text-align' : 'center'
				}).html(operateBtns).appendTo($tr);

				$tr.appendTo($tbody);
			});
		}

		function uploadFile(formId,url,callback) {
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
				callback($.parseJSON(data));
				bindEventAfterDataLoaded();
			})
			.fail(function(err) {
			
			});
		}

		function bindEvent() {

			//弹出增加模态框
			$('#j-addexchange').bind('click.foradd',function(){
				$('#j-addmodel').modal('show');
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

				$input_hidden.val(value.substring(value.lastIndexOf('\\') + 1));
			});

			//保存增加兑奖活动
			$('#j-saveexchange').bind('click.foradd',function(event){
				uploadFile('j-addexchangeform','AddExchange.action',fillInExchangeTable);
				$('#j-addmodel').modal('hide');
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


		}

	
		bindEvent(); 
		  
		$.ajax({
			url: 'GetExchanges.action',
			type: 'post',
			dataType: 'json',
		})
		.done(function(data) {
			fillInExchangeTable(data);
			bindEventAfterDataLoaded();
		})
		.fail(function(err) {
			console.log(err);
		});

	});
})(window,jQuery);