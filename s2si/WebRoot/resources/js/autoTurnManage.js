$(function(){
	//add new
	$(".btn.add").bind("click", function() {
		$("#atm-add").modal("show");
	});
	//refresh iframe
	$(".btn.refresh").bind("click", function() {
		location.reload();
	});
	$("#atm-add .cancel").bind("click", function() {
		console.log("cancel");
		$("#atm-add").modal("hide");
	});
	//save new atm
	$("#atm-add .save").bind("click", function() {
		console.log("save");
		var fd = new FormData(document.getElementById("atm-add-form"));
		console.log($("#atm-add-form"));
		$.ajax({
			url: 'AddCarouselInfo.action',
			type: 'post',
			processData: false,
   			contentType: false,
			data: fd
		})
		.done(function(data) {
			console.log("done");
//			$("#atm-add").modal("hide");
			location.reload();
		})
		.fail(function() {
			alert("上传失败");
			console.log("error");
		});
	});
	//oper action
	$('#atm-table').delegate('.atm-oper', 'click', function(){
		console.log($(this).closest('tr').find('td.id').text());
		$("#atm-oper").data('currentId', $(this).closest('tr').find('td.id').text());
		$("#atm-oper").modal("show");
	});
	
	//oper change action
	$("#atm-oper").delegate('.change', 'click', function() {
		$('#atm-oper-form').show();
	});
	//oper form cancel action
	$("#atm-oper-form .cancel").bind("click", function() {
		$('#atm-oper-form').hide();
	});
	//oper form delete action
	$("#atm-oper .delete").bind("click", function() {
		var curId = $("#atm-oper").data('currentId');
		console.log("delete id" + curId);
		$.ajax({
			url:'DeleteCarouselInfo.action' ,
			type: 'post',
   			data: {
				cid: curId
			}
		}).done(function(data) {
			location.reload();
		}).fail(function() {
			alert("删除失败");
		});
	});
	
	//oper form save action
	$("#atm-oper-form .save").bind("click", function() {
		console.log("save");
		var curId = $("#atm-oper").data('currentId');
		console.log("delete id" + curId);
		var fd = new FormData(document.getElementById("atm-oper-form"));
		fd.append('cid', curId);
		$.ajax({
			url: 'UpdateCarouselInfo.action',
			type: 'post',
			processData: false,
   			contentType: false,
			data: fd
		})
		.done(function(data) {
			console.log("done");
			location.reload();
		})
		.fail(function() {
			alert("上传失败");
			console.log("error");
		});
	});
});