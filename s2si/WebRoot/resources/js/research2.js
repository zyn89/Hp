/**
 * 
 */

(function($,window){
		$(function(){
			var sid = $('#sid').val(),
				questions, //存放所有问题的相关数据
				curIndex = 1, //当前问题索引
				answered = 0, //当前问题是否已经回答 用来判断是否需要改变按钮文字
				score=0, 
				count=0; //用来判断是否所有调研问题都已经回答过 初始时置0
			var credit_get="";
			$.ajax({
				url: 'GetSurveyList.action',
				type: 'post',
				dataType: 'json',
				//data: {sid: sid},
			})
			.done(function(data) {
				count = 0;
				arr = data.surveys;
				if(data.status){
					$(".modal").find(".res").html("出错了");
					$(".modal").fadeIn("fast");
					return;
				}
				for(var i=0;i<arr.length;i++){
					//console.log(arr[i].sid==sid);
					if(arr[i].sid==sid){
						credit_get=arr[i].credit
					}
				}
			})
			.fail(function(err) {
				console.log(err);
			});	
			if(!answered){				
				$(".tip",".u-answer").hide();
			}
			$.ajax({
				url: 'GetSurveyQuestion.action',
				type: 'post',
				dataType: 'json',
				data: {sid: sid},
			})
			.done(function(data) {
				count = 0;
				questions = data.questions;
				makeQuestion(questions[curIndex-1]);
			})
			.fail(function(err) {
				console.log(err);
			});	
			
			
			function eventHandlerForRadio(event,$radios) {
				var _target = $(event.target),
					$parentLi = _target.parents('li'),
					$siblingLis = $parentLi.siblings();
				
				$radios.removeClass('radio-light');
				_target.addClass('radio-light');
			}
			
			function makeQuestion(questionData) {
				console.log(questionData);
				var qid = questionData.qid,
					done = questionData.done,
					questionUrl = questionData.imageUrl,
					choiceItems = questionData.choiceItems,
					rate = questionData.rate,
					aIndex = questionData.aIndex,
					$tips = $(".tip",".u-answer").hide(),
					$radios = $('.radio','.u-answer'),
					$ul = $('.u-answer');
					
				//添加选项里面的文本内容
				$("span.content").each(function(index,e){
					$(e).text(choiceItems[index]);
				});
				//判断此问题是否已经回答过 可以固定选项 后面的radio 不可单击
				//unbind radio上的事件 或者撤销 ul 上的事件(如果事件绑定在ul上)
				if(done) {
					count+=1;
					answered = 1;
					curIndex+=1;
					$.each($tips,function(index,tip){
						$(tip).text(rate[index])
							  .show();
					});
					$('.m-submit a').css({
						'letter-spacing' : '1px' 
					}).text("下一题目");

					$radios.removeClass('radio-light')
					.eq(aIndex-1).addClass('radio-light')
					.end().css({
						cursor : 'text'
					});

					$ul.unbind('click.radio');

				} else {
					answered = 0;
					//绑定事件
					
					//
					$('.m-submit a').css({
						'letter-spacing' : '3px' 
					}).text("提交");
					
					$radios.removeClass('radio-light')
					.eq(0).addClass('radio-light')
					.end().css({
						cursor : 'pointer'
					});

					$ul.on('click.radio','.radio',{'radios' : $radios},function(event){
						eventHandlerForRadio(event,event.data.radios);
					});


				}
				
			}
			

			function getCheckedValue() {
				var $radios = $('.radio','.u-answer'),
					aIndex = 1;
				$.each($radios,function(index,radio){
					if($(radio).hasClass('radio-light')) {
						aIndex = index-0 + 1;
						return false;
					}
				});
				return aIndex;
			}

			$(".modal").find(".btn").bind("click",function(e){
				e.stopPropagation();
				location.href="goTo.action?url=userhome.jsp";
			});
			//提交/下一题按钮事件
			//应该避免重复提交
			$('.m-submit #j-confirmbtn').bind('click',function(event){
					var _this = $(this);
					var	choiceResult="";
					if(answered) {
						if(!questions[curIndex-1]) {
							//如果是最后一题
							console.log("last");
							$(".modal").find(".res span").text(credit_get);
							$(".modal").fadeIn("fast");
							return;
						}
						//如果调研问题已经回答过 那么直接下一题
						makeQuestion(questions[curIndex-1]);
						/*if(curIndex>questions.length) {
							if(count == questions.length) {
								alert('已经参与过此次调研');
							} else {
								alert("感谢你的参与调研！恭喜获得10个积分！");
							}
						} else {
							$resultDiv.hide();
							
						}*/
					} else {

						//如果调研问题没有回答过 提交答案 判断是否是最后一题 如果是
						//弹出获得积分对话框 如果不是 currentIndex++ 改变按钮 显示统计结果

						choiceResult = getCheckedValue();
						answered = 1; //设置问题是否已答
						//curIndex;
						
						$.ajax({
							url: 'DoneSurveyQuestion.action',
							type: 'post',
							dataType: 'json',
							data : {
								sid : sid,
								qid : questions[curIndex-1].qid,
								isFinal : curIndex+1 > questions.length ? 1 : 0,
								aIndex : choiceResult
							}
						})
						.done(function(data) {
							//console.log(data);
							//回显百分比 如果是最后一次 弹出获得积分对话框
							if(data.status){
								$(".modal").find(".res").html(data.message);
								$(".modal").fadeIn("fast");
								return;
							}
							var credit =data.credit,
								$tips = $('.tip','.u-answer'),
								rate = data.rate;
							$.each($tips,function(index,tip){
								$(tip).text(rate[index])
									  .show();
							});
							if(curIndex+1 > questions.length && credit != null) {
								//如果是最后一题	
								$(".modal").find(".res span").text(credit_get);
								$(".modal").fadeIn("fast");
							}
							curIndex++;
							_this.text('下一题');
						})
						.fail(function(err) {
							console.log(err);
						});		
					}
			});	

		});//jQuery
})(jQuery,window);