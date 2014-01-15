$(function(){
	function filldata_one(data){
		for(var i=0;i<data.length;i++){
			var $tr=$("table.one").find("tr.temple").clone();
			$tr.data("data",data[i]);
			$tr.removeClass("hide");
			$tr.removeClass("temple");
			$tr.find(".id").text((i+1)+"");
			$tr.find(".pic").data("pic",data[i].imageUrl);
			if(parseInt(data[i].isStatic)==0){
				$tr.find(".static").text("是")
				$tr.find(".url").find(".btn-link").addClass("disabled");
				$tr.find(".url").find(".btn-link").text("查看");
			}else{
				$tr.find(".url").data("url",data[i].url);
				console.log(data[i].url);
				$tr.find(".static").text("否");
			}
			$tr.appendTo($("table.one"));
		}
		bindevent_one();
	}
	function bindevent_one(){
		$("table.one .pic .btn-link").bind("click",function(){
			var tmp=$(this).parent().data("pic");
			$(".modal.show").find("h3").text("图片预览");
			$(".modal.show").find("img").attr("src",tmp);
			$(".modal.show").modal("show");
		});
		$("table.one").find(".url").find(".btn-link").bind("click",function(){
			var tmp=$(this).parent().data("url");
			console.log(tmp);
			$(".modal.show").find("h3").text("显示链接");
			$(".modal.show").find("p").text(tmp);
			$(".modal.show").modal("show");
		});
		$("table.one").find(".btn.clean").bind("click",function(){
			var id=$(this).parent().parent().data("data").id;
			if(confirm("确定删除？")){
				
			}else{
				return;
			}
			del(id,1);
		});
		$("table.one").find(".btn.change").bind("click",function(){
			var id=$(this).parent().parent().data("data").id;
			var tmp=$(this).parent().parent().data("data").isStatic;
			var url=$(this).parent().parent().data("data").url;
			if(parseInt(tmp)==0){
				$(".modal.oper").find("select").val("0");
				$(".modal.oper").find("input[name='url']").attr("readonly",true);
			}else{
				$(".modal.oper").find("select").val("1");
				$(".modal.oper").find("input[name='url']").text(usrl);
			}
			$(".modal.oper").modal("show");
		});
		$(".modal.oper").find("select").bind("change",function(){
			alert();
			var tmp=$(".modal.oper").find("select").val();
			if(parseInt(tmp)==0){
				$(".modal.oper").find("input[name='url']").attr("readonly",true);
			}else{
				$(".modal.oper").find("input[name='url']").attr("readonly",null);
			}
		});
	}
	$(".modal.show").find(".btn.save").bind("click",function(){
		var form=$(".modal.show").find("form")[0];
		var formData = new FormData(form);
	});
	$(".modal.show").bind("hide",function(){
		$(this).find("p").text("");
		$(".modal.show").find("img").attr("src","");
	})
	function save(id,level){
		console.log('save...')
		var curId = $('#img-oper').data('currentId');
		var fd = new FormData(document.getElementById("img-oper-form"));
		fd.append('id', curId);
		urls = ['UpdateOneLevel', 'UpdateTwoLevel', 'UpdateThreeLevel', 'UpdateFourLevel']
		$.ajax({
			url: urls[CLevel-1]+'.action',
			type: 'post',
			processData: false,
   			contentType: false,
   			data: fd
		}).done(function(data) {
			//refresh
			loadImgList(CLevel, CID);
			console.log('change success');
			$('#img-oper').modal('hide');			
		});
	}
	function del(id,level) {
		var curId = curId;
		console.log('ajax delete' + curId);
		urls = ['DeleteOneLevel', 'DeleteTwoLevel', 'DeleteThreeLevel', 'DeleteFourLevel'];
		$.ajax({
			url: urls[level-1]+'.action',
			type: 'post',
   			data: {
				id: curId
			}
		}).done(function(data) {
			//location.reload();
			//loadImgList(CLevel, CID);
			//console.log('delete success');
			//$('#img-oper').modal('hide');
			loadImgList(level);
		});
		}
	function loadImgList(level, id) {
		//update current level and id
		CLevel = level;
		CID = id;
		//load data 
		var url="";
		var data = {};
		if (level === 1) {
			url = "GetWebs";
		} else if (level ===2){
			url = "GetTwoLevel";
			data = {tid: id};
		} else if (level ===3){
			url = "GetThreeLevel";
			data = {hid: id};
		} else if (level ===4){
			url = "GetFourLevel";
			data = {fid: id};
		}
		$.ajax({
			url: url + ".action",
			type: 'post',
			dataType: 'json',
			data: data
		})
		.done(function(data) {
			console.log(data);
			if(level==1){
				filldata_one(data.one_level);
			}
			//createTable(level, id, data)
			//create table
		})
	}
	loadImgList(1);
});