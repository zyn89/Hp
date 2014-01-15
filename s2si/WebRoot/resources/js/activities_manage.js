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
				/*
				$li.bind("getinfo", function() {
					$.ajax({
						url : "ActivityList.action",
						type : "POST",
						data : {},
						success : function(data) {
							var data = JSON.parse(data);
							$li.data("data", data);
							$("#tmp_1").data("data", data);
							$(".content").load("all.action", function() {
								$("#tmp_1").trigger("table");
							});
						},
					});
				});*/
				var dict={"pic":"图片活动",
				"qa":"问答活动",
				"pic_word":"图片文字活动",
				};
				$(".btn.fresh").bind("fresh",function() {
							$("table.all").find("tr:not(.hide):not(.header)").remove();
							var data = $("#j-content").data("data");
							//console.log(data);
							var arr = data["activities"] || [];
							for ( var i = 0; i < arr.length; i++) {
								var $tr = $("table.all").find("tr.temple").clone();
								$tr.data("data", arr[i]);
								$tr.removeClass("hide");
								$tr.removeClass("temple");
								$tr.find(".id").text(i+1);
								$tr.find(".type").text(dict[arr[i]["type"]]);
								$tr.find(".desc").data("data",arr[i]["descUrl"]);
								$tr.find("a.descURL").bind("click",function() {
											var $tmp = $(this).parent();
											console.log($tmp.data("data"));
											$(".modal.show").find(".modal-body").find("p").text($tmp.data("data"));
											$(".modal.show").find(".modal-body").find("img").attr("src",$tmp.data("data"));
											$(".modal.show").modal("show");
										});
								$tr.find(".activity").data("data",arr[i]["activityUrl"]);
								$tr.find("a.activityURL").bind("click",function() {
											var $tmp = $(this).parent();
											str=$tmp.data("data");
											console.log(str);
											//str=str.substr(2);
											//str="resources\\image"+str;
											//console.log(str);
											//console.log($tmp.data("data"));
											//$(".modal.show").find(".modal-body").find("p").text($tmp.data("data"));
											$(".modal.show").find(".modal-body").find("img").attr("src",
												str);
											$(".modal.show").find(".modal-body").find("p").text(str);
											$(".modal.show").modal("show");
										});
								$tr.find(".score").text(arr[i]["score"]);
								$tr.find(".credit").text(arr[i]["credit"]);
								$tr.find(".btn.oper").bind("click",function() {
											$(".modal.oper").data("data",$(this).parent().parent().data("data"));
											$(".modal.oper").modal("show");
											//$(".mynav").data("data",$(".modal.oper"));
										});
								if(arr[i]["type"]=="qa"){									
									$tr.find(".btn.detail").text(dict[arr[i]["type"]]+"管理");
								}else{
									$tr.find(".btn.detail").remove();
								}
								$tr.find(".btn.detail").bind("click",function(){
									var aid=$(this).parent().parent().data("data")["aid"];
									//console.log($(this).parent().parent());
									//console.log(aid);
									var date=new Date();
									$("#j-content").load("all_questions.action?liu="+date.getTime(), function() {
										$btn=$("#j-content").find(".btn.fresh");
										$btn.data("aid",aid);
										$btn.trigger("click");
									});
									/*$.ajax({
										url : "GetQuestionList.action",
										type : "POST",
										data : {"aid":aid},
										success : function(data) {
											var data = JSON.parse(data);
											//console.log("question");
											console.log(data);
											$("#j-content").data("data", data);
											//$("#tmp_1").data("data", data);
											var date=new Date();
											$("#j-content").load("all_questions.action?liu="+date.getTime(), function() {
												$btn=$("#j-content").find(".btn.fresh");
												$btn.trigger("click");
											});
										},
									});*/
								});
								$tr.find(".btn.all").bind("click",function(){
									console.log($(this).parent().parent().data("data")["type"]);
									var type=$(this).parent().parent().data("data")["type"];
									var aid=$(this).parent().parent().data("data")["aid"];
									$(".section_header").data("aid",aid);
									$(".section_header").find(".btn-group > .fresh").addClass("hide");
									$(".section_header").find(".btn-group > .load").removeClass("hide");
									if(type=="qa"){
										$(".section_header").find("p").text("返回上一级");
										$(".pagination").removeClass("hide");
										$("table.all").fadeOut("fast",function(){
											$("table.res").removeClass("hide");
											$("table.pics").addClass("hide");
											$("table.res").fadeIn();
										});
										$(".btn.load").button("loading");
										$.ajax({
											url : "QueryUserScore.action",
											type : "POST",
											data : {"aid":aid,
											"start":0,
											"end":1,
											},
											success : function(data) {
												//console.log(data);
												data=JSON.parse(data);
												//$("body").data("data",data);
												var length=data.count;
												console.log(length);
												$(".pagination").BTPagination(data.count, {
													getItemsAjax: $("table.res").data("callback"),
													prev_text: '上一页',
													next_text: '下一页',
													num_display_pageno: 8,
													items_per_page :5,
												});
											}
										});
									}else{
										$(".section_header").find("p").text("返回上一级");
										$(".pagination").removeClass("hide");
										$("table.all").fadeOut("fast",function(){
											$("table.pics").removeClass("hide");
											$("table.res").addClass("hide");
											$("table.pics").fadeIn();
										});
										$(".btn.load").button("loading");
										$.ajax({
											url : "QueryUplord.action",
											type : "POST",
											data : {"aid":aid,
											"start":0,
											"end":1,
											},
											success : function(data) {
												//console.log(data);
												data=JSON.parse(data);
												//$("body").data("data",data);
												//var length=data.count;
												//console.log(length);
												$(".pagination").BTPagination(data.count, {
													getItemsAjax: $("table.pics").data("callback"),
													prev_text: '上一页',
													next_text: '下一页',
													num_display_pageno: 8,
													items_per_page :5,
												});
											}
										});
									}
									//$(".modal.res").data("data",type);
									//$(".modal.res").modal("show");
								});
								$tr.appendTo($("table.all"));
							}
							var callback=$("table.all").data("callback");
							callback();
							$(".btn.fresh").button("reset");
						});
				$(".btn.fresh").bind("click",function(){
					$(this).button("loading");
					$.ajax({
						url : "ActivityList_Web.action",
						type : "POST",
						data : {},
						success : function(data) {
							try{
								 var data=JSON.parse(data);
							 }catch(exception){				
							     alert("an error processed");
							     return;
							 }
							//var data = JSON.parse(data);
							//console.log(data);
							$("#j-content").data("data", data);
							$(".btn.fresh").trigger("fresh");
						},
					});
				});
				//返回上一级的链接事件处理
				$(".section_header").find("a").bind("click",function(){
					$(".pagination").addClass("hide");
					$(".section_header").find(".btn-group > .fresh").removeClass("hide");
					$(".section_header").find(".btn-group > .load").addClass("hide");
					var str=$(".section_header").find("p").text();
					if(str=="全部活动"){
						
					}else{
						$("table.res").addClass("hide");
						$("table.pics").addClass("hide");
						$("table.all").fadeIn();
						$(".section_header").find("p").text("全部活动");
					}
				});
				//修改操作中的保存操作
				$(".modal.oper").find(".btn.save").bind("click", function() {
								var type = $(".oper .controls.type").find("input").val();
								var flag=true
								var credit = $(".oper .controls.credit").find("input").val();
								var score = $(".oper .controls.score").find("input").val();
								var fileobj1 = $("input.uploaddesc")[0].files[0];
								var fileobj2 = $("input.uploadactivity")[0].files[0];
								// FormData 对象
								//console.log(fileobj1);
								var arr = [];
								var filename1="";
								var filename2="";
								var index=0;
								if(fileobj1){
									filename1=index;
									index++;
									arr.push(fileobj1.name);
								}else{
									filename1="-1";
								}
								if(fileobj2){
									filename2=index;
									index++;
									arr.push(fileobj2.name);
								}else{
									filename2="-1";
								}
								var form = new FormData($("#pictures")[0]);
								form.append("aid", $(".modal.oper").data("data")["aid"]);
								form.append("credit", parseInt(credit));
								form.append("score", parseInt(score));
								form.append("descIndex", filename1);
								form.append("activityIndex", filename2);
								form.append("picsName", arr);
								//form.append("pics", fileobj1);
								//form.append("pics", fileobj2);                          
								// XMLHttpRequest 对象
								var xhr = new XMLHttpRequest();
								xhr.open("post", "UpdateActivity.action", true);
								xhr.onload = function() {
									alert("上传完成!");
									$(".modal.oper").modal("hide");
									$(".btn.fresh").trigger("click");
								};
								//xhr.send(form);
								$.ajax({
									url : "UpdateActivity.action",
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
										//alert("上传完成");
										$(".modal.oper").modal("hide");
										$(".btn.fresh").trigger("click");
									},
								});
								//console.log("上传");
			});
			//关闭对话框的时候清空表单
			$(".modal.add").bind("hide",function(){
				$("form#pictures")[0].reset();
			});
			$(".modal.add").find(".btn.save").bind("click", function() {
								var type = $(".add .controls.type").find("select").val();
								var credit = $(".add .controls.credit").find("input").val();
								var score = $(".add .controls.score").find("input").val();
								console.log($("input.uploaddesc")[0].files);
								console.log($("input.uploadactivity")[0].files);
								var fileobj1 = $("input.uploaddesc")[0].files[0];
								var fileobj2 = $("input.uploadactivity")[0].files[0];
								var flag=true
								if(!credit || credit.trim()==""){
									$(".modal.add").find("p.credit").css("display","inline-block");
									flag=false;
								}else{
									$(".modal.add").find("p.credit").css("display","none");
								}
								if(score.trim()==""){
									//alert("");
									$(".modal.add").find("p.score").css("display","inline-block");
									flag=false;
								}else{
									$(".modal.add").find("p.score").css("display","none");
								}
								if(!fileobj1){
									$(".modal.add").find("p.file1").css("display","inline-block");
									flag=false;
								}else{
									$(".modal.add").find("p.file1").css("display","none");
								}
								if(!fileobj2){
									$(".modal.add").find("p.file2").css("display","inline-block");
									flag=false;
								}else{
									$(".modal.add").find("p.file2").css("display","none");
								}
								if(flag==false){
									return;
								}
								// FormData 对象
								//console.log(fileobj1);
								var arr = [];
								var arr = [];
								var filename1="";
								var filename2="";
								if(fileobj1 && fileobj2){
										arr.push(fileobj1.name);
										filename1=fileobj1.name;
										arr.push(fileobj2.name);
										filename2=fileobj2.name;
								}
								var form = new FormData($("#pictures")[0]);
								//form.append("aid", $(".modal.oper").data("data")["aid"]);\
								form.append("type", type);
								form.append("credit", parseInt(credit));
								form.append("score", parseInt(score));
								form.append("descIndex", 0);
								form.append("activityIndex", 1);
								form.append("picsName", arr);
								//form.append("pics", fileobj1);
								//form.append("pics", fileobj2);                          
								// XMLHttpRequest 对象
								//console.log(form);
								//var xhr = new XMLHttpRequest();
								//xhr.open("post", "AddActivity.action", true);
								//xhr.onload = function() {
								//	alert("上传完成!");
								//	$(".btn.fresh").trigger("click");
								//};
								//xhr.send(form);
								//console.log("上传");
								$.ajax({
									url : "AddActivity.action",
									type : "POST",
									data : form,
									contentType: false,
									processData: false,
									success : function(data) {
										//console.log(data);
										//alert("上传完成");
										try{
											if(typeof(data)!="object"){					
												JSON.parse(data);
											} 
										 }catch(exception){				
										     alert("an error processed");
										 }
										$(".modal.add").modal("hide");
										$(".btn.fresh").trigger("click");
									},
									error:function(data){
										console.log(data);
										//alert("出错");
									}
								});
			});
			//查看上传图片时的事件处理
			$(".modal.show").bind("show",function(){
				  $(this).css({width:'auto'});
			      $('.modal-body',this).css({width:'auto',height:'auto', 'max-height':'100%'});
			});
			//点击打开文件上传框
			$(".modal").find(".btn-link.desc").bind("click", function() {
				$(".uploaddesc").trigger("click");
			});
			$(".modal").find(".btn-link.activity").bind("click", function() {
				$(".uploadactivity").trigger("click");
				//alert("");
			});
			//文件选择框的change事件
			$(".uploaddesc").bind("change",function(){
				var $modal=$("#j-content").data("modal");
				var $a=$(".modal."+$modal).find("a.desc ~ a.temple").clone();
				console.log($(this)[0].files[0].name);
				$(".modal."+$modal).find("a.desc ~ a.already").remove();
				$(".modal."+$modal).find("a.desc ~ p.text-error").css("display","none");
				$a.text($(this)[0].files[0].name);
				$a.removeClass("hide");
				$a.removeClass("temple");
				$a.addClass("already");
				$a.insertAfter($(".modal."+$modal).find("a.desc ~ a.temple"));
			});
			$(".uploadactivity").bind("change",function(){
				var $modal=$("#j-content").data("modal");
				var $a=$(".modal."+$modal).find("a.activity ~ a.temple").clone();
				console.log($(this)[0].files[0].name);
				$(".modal."+$modal).find("a.activity ~ a.already").remove();
				$(".modal."+$modal).find("a.activity ~ p.text-error").css("display","none");
				$a.text($(this)[0].files[0].name);
				$a.removeClass("hide");
				$a.removeClass("temple");
				$a.addClass("already");
				$a.insertAfter($(".modal."+$modal).find("a.activity ~ a.temple"));
			});
			//关闭操作对话框的时候关闭修改用的列表
			$(".modal.oper").bind("hide", function() {
				//$(".oper").find("form").addClass("hide");
				$(".modal.oper").find("a.activity ~ a.already").remove();
				$(".modal.oper").find("a.desc ~ a.already").remove();
				$("#pictures")[0].reset();
			});
			$(".modal.add").bind("hide", function() {
				//$(".oper").find("form").addClass("hide");
				$(".modal.add").find("a.activity ~ a.already").remove();
				$(".modal.add").find("a.desc ~ a.already").remove();
				$("#pictures")[0].reset();
			});
			//点击取消按钮的时候关闭对话框
			$(".modal-footer").find(".btn.shut").bind("click", function() {
				$(".modal.oper").modal("hide");
				$(".modal.add").modal("hide");
				$(".modal.res").modal("hide");
			});			
			function callback(){
				//点击修改按钮，出现修改用的列表，并给输入框填上数据
				$("table.all").find(".btn.change").bind("click", function() {
					//console.log($(".modal.oper").data("data"));
					$(".modal.oper").modal("show");
					$(".oper").find("form").removeClass("hide");
					//var obj = $(".modal.oper").data("data");
					var obj = $(this).parent().parent().data("data");
					$(".modal.oper").data("data",obj);
					console.log(obj);
					console.log(dict[obj["type"]]);
					$(".oper .controls.type").find("input").val(dict[obj["type"]]);
					$(".oper .controls.credit").find("input").val(obj["credit"]);
					$(".oper .controls.score").find("input").val(obj["score"]);
				});
				//删除用按钮的相关处理
				$("table.all").find(".btn.clean").bind("click", function() {
					//var $modal=$(".mynav").data("data");
					$(".modal.oper").data("data");
					var obj = $(this).parent().parent().data("data");
					//$(".modal.oper").data("data");
					if(confirm("确定删除吗？") == true){     //如果用户单击了确定按钮 
							 console.log("删除");
							 $.ajax({
								url : "DeleteActivity.action",
								type : "POST",
								data : {
										"aid" : obj.aid,
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
										//$(".modal.oper").modal("hide");
										$(".btn.fresh").trigger("click");
								},
							});
					} 
					else{ 
							console.log("不删除");
					} 
				});
			}
			$("table.all").data("callback",callback);
			//点击新增按钮，弹出新增操作用到的对话框
			$(".btn.add").bind("click", function() {
				$(".modal.add").modal("show");
				//$(".mynav").data("data", $(".modal.add"));
			});
			//让页面知道弹出的是修改框还是新增框
			$(".modal.oper").bind("show", function() {
				$(this).find("p.oper").css("display","none");
				$("#j-content").data("modal","oper");
			});
			$(".modal.add").bind("show", function() {
				$(this).find("p.add").css("display","none");
				$("#j-content").data("modal","add");
				$(".add .controls.type").find("input").val("");
				$(".add .controls.credit").find("input").val("");
				$(".add .controls.score").find("input").val("");
				$("a.already").remove();
			});
});