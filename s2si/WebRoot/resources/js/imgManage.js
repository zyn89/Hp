$(function() {
	var CLevel = -1;
	var CID = -1;
	var L1, L2;
	function updateLevelAndId(level, id) {
		CLevel = level;
		CID = id;
		if (CLevel == 2) {
			L1 = id;
		} else if(CLevel == 3) {
			L2 = id;
		}
		var titles = ['微网站管理', '<a class="prev-level" href="javascript:void(0);">返回上一级</a>', '<a class="prev-level" href="javascript:void(0);">返回上一级</a>', '<a class="prev-level" href="javascript:void(0);">返回上一级</a>']
		$('#autoManageTitle').html(titles[CLevel-1]);
	}
	
	$('#autoManageTitle').delegate('a.prev-level', 'click', function(event) {
		if (CLevel == 2) {
			loadImgList(1, 0);
		} else if (CLevel == 3) {
			loadImgList(2, L1);
		} else if(CLevel == 4) {
			loadImgList(3, L2);
		}
			
	});
	
	//load data
	function loadImgList(level, id) {
		//update current level and id
//		CLevel = level;
//		CID = id;
		updateLevelAndId(level, id);
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
			createTable(level, id, data)
			//create table
		})
		.fail(function() {
			alert("读取数据失败");
			console.log("error");
		});
	}
	
	function createTable(level, id, data) {
		var arr = null;
		var table = '';
		if (level === 1) {
			arr = data["one_level"];
			table = tmplTable(arr);
		} else if (level ===2){
			arr = data["two_level"];
			table = tmplTable(arr);
		} else if (level ===3){
			arr = data["three_level"];
			table = tmplTable(arr);
		} else if (level ===4){
			arr = data["four_level"];
			table = tmplTable(arr);
		}		
		
		$("#img-table-container").html(table);
	}
	
	function tmplTable(arr) {
		return  _.template($('#table-template-level' + CLevel).html(),{datas: arr})
	}
	
//	function otherTable(arr) {
		//return  _.template($('#one-table-template').html(),{datas: arr})
//	}
	
	//crud


	//show img
	$('#img-table-container').delegate('a.show-img', 'click', function(event) {
		$('#img-tag').attr('src', $(this).attr('imgURL'));
		$('#img-modal-container').modal('show');
	});
	//oper img
	$('#img-table-container').delegate('button.oper', 'click', function(event) {
		//todo save id
		var id = $(this).closest('tr').find('td.id').text();
		console.log('img id is: ' + id);
		var formData = $(this).closest('tr').find('td.form-data').text();
		$('#img-oper-level' + CLevel).data('currentId', id);
		$('#img-oper-level' + CLevel).data('currentFormData', formData);
		$('#img-oper-form-level' + CLevel).hide()[0].reset();
		$('#img-oper-level' + CLevel).modal('show');
	});
	
	//form action
	$('.img-oper').delegate('.delete', 'click', function(event) {
		var curId = $('#img-oper-level' + CLevel).data('currentId');
		//todo ajax delete
		console.log('ajax delete' + curId);
		//todo ajax delete
		urls = ['DeleteOneLevel', 'DeleteTwoLevel', 'DeleteThreeLevel', 'DeleteFourLevel']
		$.ajax({
			url: urls[CLevel-1]+'.action',
			type: 'post',
   			data: {
				id: curId
			}
		}).done(function(data) {
			//location.reload();
			loadImgList(CLevel, CID);
			console.log('delete success');
			$('#img-oper-level' + CLevel).modal('hide');
		}).fail(function() {
			alert("删除失败");
		});
		
	});
	$('.img-oper').delegate('.change', 'click', function(event) {
		var curId = $('#img-oper-level' + CLevel).data('currentId');
		var formData = $('#img-oper-level' + CLevel).data('currentFormData');
		$('#img-oper-form-level' + CLevel)[0].reset();
		$('#img-oper-form-level' + CLevel).loadJSON(JSON.parse(formData));
		$('#img-oper-form-level' + CLevel).show();
	});
	
	//cancel 
	$('.img-oper-form').delegate('.cancel', 'click', function(event) {
		$("#img-oper-form-level" + CLevel).hide();
	});
	//save 
	$('.img-oper-form').delegate('.save', 'click', function(event) {
		//todo ajax save
		console.log('save...')
		var curId = $('#img-oper-level' + CLevel).data('currentId');
		var fd = new FormData(document.getElementById("img-oper-form-level" + CLevel));
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
			$('#img-oper-form-level' + CLevel).hide()[0].reset();
			$('#img-oper-level' + CLevel).modal('hide');
		}).fail(function() {
			alert("修改失败");
		});

	});
	
	//add img action
	$('#add-img-btn').click(function() {
		//todo add by level
		$('#img-add-form-level' + CLevel)[0].reset();
		$('#img-add-level' + CLevel).modal('show');
		//$('#img-add').modal('show');
	});

	//add cancel 
	$('.img-add-form').delegate('.cancel', 'click', function(event) {
		var sl = 'img-add-level' + CLevel;
		$('#' + sl).modal('hide');
		//$('#img-add').modal('hide');
	});
	//save 
	$('.img-add-form').delegate('.save', 'click', function(event) {
		//ajax save
		console.log('save...')
		var fd = new FormData(document.getElementById("img-add-form-level" + CLevel));
		var idName = ['', '', 'tid', 'hid', 'fid'][CLevel];
		if (idName) {
			fd.append(idName, CID);
		}		
		//todo ajax delete
		urls = ['AddOneLevel', 'AddTwoLevel', 'AddThreeLevel', 'AddFourLevel']
		$.ajax({
			url: urls[CLevel-1]+'.action',
			type: 'post',
			processData: false,
   			contentType: false,
   			data: fd
		}).done(function(data) {
			//refresh
			loadImgList(CLevel, CID);
			console.log('add success');
			$("#img-add-level" + CLevel).modal('hide');			
		}).fail(function() {
			alert("添加失败");
		});

	});
	
	$('#refresh-btn').click(function(){
		loadImgList(CLevel, CID);
	});
	
	//nextlevel
	$('#img-table-container').delegate('.nextlevel', 'click', function(event) {
		CLevel++;
		CID = $(this).closest('tr').find('td.id').text();
		loadImgList(CLevel, CID);
	});
	//init
	loadImgList(1, 0);
})