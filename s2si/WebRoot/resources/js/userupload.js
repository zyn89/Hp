;$(function(){	
	var pic=function (pageNo, pageSize) {
		//console.log(pageSize+"asd");
		$(".btn.fresh").addClass("disabled");
		var firstIndex = (pageNo - 1) * pageSize,
		lastIndex = firstIndex+pageSize-1;
		var aid=$(".section_header").data("aid");
		$.ajax({
				url : "QueryUplord.action",
				type : "POST",
				data : {"aid":aid,
				"start":firstIndex,
				"end":lastIndex,
				},
				success : function(data) {
					console.log(data);
					var data = JSON.parse(data);
					arr=data.uplord || [];
					console.log(arr);
					var pics=["picUrl1","picUrl2","picUrl3","picUrl4","picUrl5","picUrl6"];
					var tds=["pic1","pic2","pic3","pic4","pic5","pic6"];
					$("table.pics").find("tr:not(.hide):not(.header)").remove();
					for ( var i = 0; i < arr.length; i++) {
						var $tr = $("table.pics").find("tr.temple").clone();
						$tr.data("data", arr[i]);
						$tr.removeClass("hide");
						$tr.removeClass("temple");
						$tr.find(".id").text(arr[i].id);
						$tr.find(".name").text(arr[i]["name"]);
						for(var j=0;j<6;j++){
							console.log(tds[j]);
							if(arr[i][pics[j]]==null){
								$tr.find("."+tds[j]).find(".btn").removeClass("btn-link");
								console.log($tr.find("."+tds[j]));
								$tr.find("."+tds[j]).find(".btn").addClass("disabled");
							}else{
								$tr.find("."+tds[j]).data("data",arr[i][pics[j]]);
								$tr.find("."+tds[j]).find(".btn").bind("click",function(){
									$(".modal.show").find("h3").text("图片");
									$(".modal.show").find("p").text($(this).parent().data("data"));
									$(".modal.show").modal("show");
								});
							}
						}
						$tr.find(".words").data("data",arr[i]["content"]);
						//console.log($tr.find(".words").data("data"));
						var str=arr[i]["content"];
						$tr.find(".words").find(".btn").bind("click",function(){
								//console.log($(this).parent());
								$(".modal.show").find("p").text(str);
								$(".modal.show").find("h3").text("文字");
								$(".modal.show").modal("show");
						});
						//$tr.find(".pic1").data("data",arr[i]["picUrl1"]);
						$tr.appendTo($("table.pics"));					
					}
					$(".btn.load").button("reset");
					$(".btn.load").addClass("disabled");
					//console.log($(".btn.fresh"));
				},
		});
	}
	$("table.pics").data("callback",pic);
});