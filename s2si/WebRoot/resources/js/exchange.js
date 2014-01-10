(function(window,$){
	$(function(){
		

		function uploadFile(formId,url) {
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
				console.log(data);
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
				uploadFile('j-addexchangeform','AddExchange.action');
				$('#j-addmodel').modal('hide');
			});
		}
		
		bindEvent();
		
		
	});
})(window,jQuery);