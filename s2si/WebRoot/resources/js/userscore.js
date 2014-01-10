;$(function(){	
	var qa=function (pageNo, pageSize) {
		var firstIndex = (pageNo - 1) * pageSize,
		lastIndex = firstIndex+pageSize-1;
		var aid=$(".section_header").data("aid");
		$.ajax({
				url : "QueryUserScore.action",
				type : "POST",
				data : {"aid":aid,
				"start":firstIndex,
				"end":lastIndex,
				},
				success : function(data) {
					var data = JSON.parse(data);
					arr=data.scores || [];
					$("table.res").find("tr:not(.hide):not(.header)").remove();
					for ( var i = 0; i < arr.length; i++) {
						//console.log(arr[i]);
						var $tr = $("table.res").find("tr.temple").clone();
						$tr.data("data", arr[i]);
						$tr.removeClass("hide");
						$tr.removeClass("temple");
						$tr.find(".id").text(i+1);
						$tr.find(".name").text(arr[i]["name"]);
						$tr.find(".score").data("data",arr[i]["score"]);
						$tr.appendTo($("table.res"));
					}
					$(".btn.load").button("reset");
					$(".btn.load").addClass("disabled");
				},
		});
	}
	$("table.res").data("callback",qa);
});