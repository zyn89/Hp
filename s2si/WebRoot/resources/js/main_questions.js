;$(function(){
		$a=$("#activities").parent().find("a.questions");
		$a.bind("click", function(e) {
					e.stopPropagation();
					//e[0].returnValue=false;
					var aid=$a.data("aid");
					console.log(aid);
					$.ajax({
						url : "GetQuestionList.action",
						type : "POST",
						data : {"aid":4},
						success : function(data) {
							var data = JSON.parse(data);
							//console.log("question");
							//console.log(data);
							$("#j-content").data("data", data);
							//$("#tmp_1").data("data", data);
							var date=new Date();
							$("#j-content").load("all_questions.action?liu="+date.getTime(), function() {
								$btn=$("#j-content").find(".btn.fresh");
								$btn.trigger("click");
							});
						},
					});
				});
});