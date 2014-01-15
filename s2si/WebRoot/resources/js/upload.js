(function(window,$){
	
	$(function(){

		var count = 0; //count files to be uploaded
		function uploadFile(formId,url,content) {
			var form = document.getElementById(formId),
				formData = new FormData(form);

			if(content) {
				formData.append('content',content);
			}

			$.ajax({
				url: url,
				type: 'post',
				processData: false,
       			contentType: false,
				data: formData
			})
			.done(function(data) {
				$('.m-confirmbtn a').text('上传完成');
				//跳转的活动首页
				/*if(){

				}*/
			})
			.fail(function() {
				console.log("error");
			});

		}

		//显示缩略图
		function handleFileSelect(evt,_this) {
				var files = evt.target.files;
				var f = files[0];
				var reader = new FileReader();

				reader.onload = (function(theFile) {
					return function(e) {
						var innerHtml = ['<img src="', e.target.result, '" title="', theFile.name, ' width="50" />'].join('');
						_this.next('.thumbnail').html(innerHtml);
					};
				})(f);

				reader.readAsDataURL(f);
		}


		//绑定事件
		$('.u-upload').on('click.a','a',function(event){
			var _target = $(event.target),
				$input = _target.next('input');
			$input.trigger('click');
			event.preventDefault();
		});

		$('input','.u-upload').bind('change',function(event){
			var _this = $(this),
				$nextLi = _this.parent('li').next();
			_this.prev().hide();
			if($nextLi){
				$nextLi.find('a').show();
			}
			count++;
			handleFileSelect(event,$(this));
		});

		$('.m-confirmbtn a').bind('click',function(event){
			var _this = $(this),
				$contentInput = $('.m-content .u-content'),
				content;
			if($contentInput.size() > 0) {
				content = $contentInput.val();
				$contentInput.val("");
			}
			//验证图片是否至少传了一张
			if(count==0) {
				alert("至少上传一张图片");
			} else {
				uploadFile('picform','UplordPic.action',content);
				_this.text('正在上传...');
			}

			
			//需不需要清空看是否是刷新过来的

		}); 

	});//jQuery
	
	
	
})(window,jQuery);