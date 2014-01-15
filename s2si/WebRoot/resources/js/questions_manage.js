;$(function(){
	$( document ).ajaxError(function(data,xhr) {
			//$(".btn.refresh").button("reset");
			console.log(xhr);
			var str=xhr.getAllResponseHeaders();
			var reg=/.*errorType:.*[\r\n|\n]/;
			var res=str.match(reg);
			console.log(res[0]);
			var tmp=res[0].replace(/[\r\n|\n]/,"");
			console.log(tmp);
			var type=tmp.substr(11);
			console.log(type.length);
			console.log(type=="exception");
			if(type=="exception"){
				location.href="all_Error.action";
			}else if(type=="adminLogin"){
				location.href="all_Login.action";
			}
		});
	//载入数据
	$(".btn.fresh").bind("click",function(){
		$(this).button("loading");
		var aid=$(this).data("aid");
		$.ajax({
			url : "GetQuestionList.action",
			type : "POST",
			data : {"aid":aid},
			success : function(data) {
				//console.log("question");
				 try{
					 if(typeof(data)!="object"){					
							var data=JSON.parse(data);
						} 
				 }catch(exception){				
				     alert("an error processed");
				     return;
				 }
				$("#j-content").data("data", data);
				//$("#tmp_1").data("data", data);
				$(".btn.fresh").trigger("fresh");
			},
		});
	});
	//让显示图片的对话框自适应大小
	$(".modal.show").bind("show",function(){
		  $(this).css({width:'auto'});
	      $('.modal-body',this).css({width:'auto',height:'auto', 'max-height':'100%'});
	});
	$(".btn.fresh").bind("fresh",function() {
							$("table").find("tr:not(.hide):not(.header)").remove();
							$(this).button("loading");
							var data = $("#j-content").data("data");
							var arr = data["questions"] || [];
							var options=["option_one","option_two","option_three"];
							for ( var i = 0; i < arr.length; i++) {
								var $tr = $("table").find("tr.temple").clone();
								$tr.data("data", arr[i]);
								$tr.removeClass("hide");
								$tr.removeClass("temple");
								$tr.find(".id").text(i+1);
								$tr.find(".aIndex").text(arr[i]["aIndex"]);
								for(var j=0;j<3;j++){
									$tr.find("."+options[j]).text(arr[i]["choiceitems"][j]);
								}
								$tr.find(".url").data("data",arr[i]["questionUrl"]);
								$tr.find("a.questionUrl").bind("click",function() {
											var $tmp = $(this).parent();
											//console.log($tmp.data("data"));
											$(".modal.show").find(".modal-body").find("p").text($tmp.data("data"));
											$(".modal.show").find(".modal-body").find("img").attr("src",$tmp.data("data"));
											$(".modal.show").modal("show");
										});
								$tr.find(".score").text(arr[i]["score"]);
								$tr.find(".oper > .delete").bind("click",function() {
											var obj=$(".modal.oper").data("data",$(this).parent().parent().data("data"));
											var qid=$(".modal.oper").data("data")["qid"];											var aid=$("")

											var aid=$(".btn.fresh").data("aid");
											if(confirm("确定删除吗？") == true){     //如果用户单击了确定按钮 
									          	 console.log("删除");
									          	 $.ajax({
													url : "deleteQuestion.action",
													type : "POST",
													data : {
														"aid" : aid,
														"qid" : qid,
													},
													success : function(data) {
														console.log("delete!");
														try{
															if(typeof(data)!="object"){					
																JSON.parse(data);
															} 
														 }catch(exception){				
														     alert("an error processed");
														     return;
														 }
														$(".btn.fresh").trigger("click");
													},
												});
									          } 
									          else{ 
									             console.log("不删除");
									         } 
											//console.log("删除");
										});
								$tr.find(".oper > .change").bind("click",function() {
											$(".modal.oper").data("data",$(this).parent().parent().data("data"));
											$("#j-content").data("oper","change");
											console.log("更改");
											$(".modal.oper").modal("show");
										});
								$tr.appendTo($("table"));
							}
							$(".btn.fresh").button("reset");
						});
			//新增按钮事件
			$(".btn.add").bind("click", function() {
				$("#j-content").data("oper","add");
				console.log("新增");
				$(".modal.oper").modal("show");
				//$(".mynav").data("data", $(".modal.add"));
			});
			$("a.back").bind("click",function(){
				$("#activities").trigger("click");
			});
			//新增和修改两种不同的显示模式
			$(".modal.oper").bind("show", function() {
				var str=$("#j-content").data("oper");
				//console.log(str);
				$(this).find("p").css("display","none");
				if(str=="change"){
					var obj = $(".modal.oper").data("data");
					//console.log(obj);
					$(".oper .controls.aIndex").find("input").val(obj["aIndex"]);
					$(".oper .controls.score").find("input").val(obj["score"]);
					$(".oper .controls.option_one").find("input").val(obj["choiceitems"][0]);
					$(".oper .controls.option_two").find("input").val(obj["choiceitems"][1]);
					$(".oper .controls.option_three").find("input").val(obj["choiceitems"][2]);
				}else{
					$(".modal.oper").find("a.desc ~ a.already").remove();
					$(".oper .controls.aIndex").find("input").val("");
					$(".oper .controls.score").find("input").val("");
					$(".oper .controls.option_one").find("input").val("");
					$(".oper .controls.option_two").find("input").val("");
					$(".oper .controls.option_three").find("input").val("");
				}
			});
			//点击关闭按钮关闭对话框
			$(".modal.oper").find(".btn.shutdown").bind("click",function(){
				$(".modal.oper").modal("hide");
			});
			//点击上传文件
			$(".modal").find(".btn-link.desc").bind("click", function() {
				$(".uploaddesc").trigger("click");
			});
			//文件对话框的change事件
			$(".uploaddesc").bind("change",function(){
				//var $modal=$("#j-content").data("modal");
				console.log($(this)[0].files);
				var arr=$(this)[0].files || [];
				$(".modal.oper").find("a.desc ~ a.already").remove();
				for(var i=0;i<arr.length;i++){
					var $a=$(".modal.oper").find("a.desc ~ a.temple").clone();
					$a.text($(this)[0].files[i].name);
					$(".modal.oper").find("a.desc ~ p.text-error").css("display","none");
					$a.removeClass("hide");
					$a.removeClass("temple");
					$a.addClass("already");
					$a.insertAfter($(".modal.oper").find("a.desc ~ a.temple"));
				}
			});
			$(".modal.oper").bind("hide",function(){
				$(".modal.oper").find("a.desc ~ a.already").remove();
				$("form#pictures")[0].reset();
			});
			//保存按钮事件
			$(".modal.oper").find(".btn.save").bind("click", function() {
								var oper=$("#j-content").data("oper");
								var aIndex = $(".oper .controls.aIndex").find("input").val();
								var score = $(".oper .controls.score").find("input").val();
								var option_one = $(".oper .controls.option_one").find("input").val();
								var option_two= $(".oper .controls.option_two").find("input").val();
								var option_three = $(".oper .controls.option_three").find("input").val();
								var options=[];
								var flag=true;
								if((!aIndex || aIndex.trim()=="") && oper=="add"){
									$(".modal.oper").find("p.aIndex").css("display","inline-block");
									flag=false;
								}else{
									$(".modal.oper").find("p.aIndex").css("display","none");
								}
								if((!score || score.trim()=="") && oper=="add"){
									$(".modal.oper").find("p.score").css("display","inline-block");
									flag=false;
								}else{
									$(".modal.oper").find("p.score").css("display","none");
								}
								if((!option_one || option_one=="") && oper=="add"){
									$(".modal.oper").find("p.option_one").css("display","inline-block");
									flag=false;
								}else{
									$(".modal.oper").find("p.option_one").css("display","none");
								}
								if((!option_two || option_two=="") && oper=="add"){
									$(".modal.oper").find("p.option_two").css("display","inline-block");
									flag=false;
								}else{
									$(".modal.oper").find("p.option_two").css("display","none");
								}
								if((!option_three || option_three=="") && oper=="add"){
									$(".modal.oper").find("p.option_three").css("display","inline-block");
									flag=false;
								}else{
									$(".modal.oper").find("p.option_three").css("display","none");
								}
								if($(".modal.oper").data("data")){									
									var qid=$(".modal.oper").data("data")["qid"];
								}
								console.log("qid="+qid);
								options.push(option_one);
								options.push(option_two);
								options.push(option_three);
								var files = $("input.uploaddesc")[0].files;
								if((!files || files.length==0) && oper=="add"){
									$(".modal.oper").find("p.desc").css("display","inline-block");
									flag=false;
								}else{
									$(".modal.oper").find("p.desc").css("display","none");
								}
								if(flag==false){
									return;
								}
								// FormData 对象
								var arr = [];
								for(var i=0;i<files.length;i++){
									arr.push(files[i].name);
								}
								var form = new FormData($("#pictures")[0]);
								//form.append("aid", $(".modal.oper").data("data")["aid"]);
								form.append("aIndex", aIndex);
								form.append("score", parseInt(score));
								form.append("a1",option_one);
								form.append("a2",option_two);
								form.append("a3",option_three);
								var aid=$(".btn.fresh").data("aid");
								form.append("aid",aid);
								// XMLHttpRequest 对象
								var xhr = new XMLHttpRequest();
								var str=$("#j-content").data("oper");
								var actionName="";
								if(str=="change"){
									actionName="UpdateQuestion.action";
									form.append("qid",qid);
									form.append("picsName", arr);
								}else{
									actionName="AddQuestion.action";
								}
								console.log(actionName);
								xhr.open("post", actionName, true);
								xhr.onload = function() {
									alert("上传完成!");
									$(".modal.oper").modal("hide");
									$(".btn.fresh").trigger("click");
								};
								//xhr.send(form);
								$.ajax({
									url : actionName,
									type : "POST",
									data : form,
									contentType: false,
									processData: false,
									success : function(data) {
										//console.log(data);
										 try{
											 if(typeof(data)!="object"){					
													JSON.parse(data);
												} 
										 }catch(exception){				
										     alert("an error processed");
										 }
										$(".modal.oper").modal("hide");
										$(".btn.fresh").trigger("click");
									},
								});
								//console.log("上传");
			});
});