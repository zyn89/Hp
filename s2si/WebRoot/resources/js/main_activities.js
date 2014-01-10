;$(function(){
		$li=$("#activities");
		$li.bind("click", function() {
							var date=new Date();
							//$("#tmp_1").data("data", data);
							$("#j-content").data("obj",$(this));
							$("#j-content").load("all_execute.action?liu="+date.getTime(), function() {
								$btn=$("#j-content").find(".btn.fresh");
								$btn.trigger("click");
							});
				});
});