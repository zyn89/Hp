$(function() {
	var CLevel = -1;
	var CID = -1;
	//load data
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
			table = oneTable(arr);
		} else if (level ===2){
			arr = data["two_level"];
			table = otherTable(arr);
		} else if (level ===3){
			arr = data["three_level"];
			table = otherTable(arr);
		} else if (level ===4){
			arr = data["four_level"];
			table = otherTable(arr);
		}		
		
		//$("#img-table-container").html(table);
	}
	
	function oneTable(arr) {
		var table = '';
		
		table = $("#img-table-container").loadTemplate("#one-table-template", {imageUrl: 'iU'});		
		//return table;
	}
	
	function otherTable(arr) {
		var table = arr;
		
		return table;
	}
	
	//crud

	//init
	loadImgList(1, 0);
})