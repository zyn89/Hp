(function($,window){
	$(function(){
		
		function fillDataInTable(surveys) {
			var $tbody = $('#j-wdytable tbody'),
				operateBtns = '<button class="btn btn-primary j-modify" type="button">修改</button>'
							  +  '<button class="btn btn-primary j-delete" type="button">删除</button>'
							  +  '<button class="btn btn-primary j-mqestion" type="button">管理调研问题</button>';
				$tbody.empty();
				$.each(surveys,function(index,value){
					var $tr = $('<tr>')
							  .attr({
							  	"data-sid" : value.sid
							  });
					$('<td>').text(index+1).appendTo($tr);
					$('<td>').html('<button class="btn btn-primary j-preview" type="button">查看</button>')
							 .appendTo($tr);
					$('<td>').text(value.credit).appendTo($tr);
					$('<td>').html(operateBtns)
							 .appendTo($tr);
					$tr.appendTo($tbody);
				});
			
				$(".j-delete").on('click.del',function(event){
				
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
		}

		
		function uploadFile() {
			var form = document.getElementById("j-addwdyform"),
				formData = new FormData(form);
			$.ajax({
				url: 'AddSurvey.action',
				type: 'post',
				processData: false,
       			contentType: false,
				data: formData
			})
			.done(function(data) {
				console.log(data);
			})
			.fail(function() {
				console.log("error");
			});
		}
		
		function bindEvent() {
			
			
			
			$('#j-addwdy').bind('click.foradd',function(){
				$('#j-addmodel').modal('show');
			});
			
			
			document.getElementById('j-uploadfile').onchange = function () {
				  $("#j-filename").text(this.value);
				  
			};
			
			/*$('#j-uploadfile').bind('change',function(){
				var file = $(this)[0].files[0];
				console.log(file);
					
			});*/
			
			$('#j-savewdy').bind('click.forsave',function(event){
				uploadFile();
				$('#j-addmodel').modal('hide');
			});
			
			$('#j-abtn').bind('click.forupload',function(){
				$('#j-uploadfile').trigger('click');
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
			fillDataInTable(surveys)
			
	


		})
		.fail(function() {
			console.log("error");
		})

		
	});
})(jQuery,window);