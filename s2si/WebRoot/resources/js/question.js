jQuery(function($){
		var aid = $('#aid').val(),
			questions,curIndex = 1,answered = 0,score=0;

		function makeQuestion(questionData) {
			
				var qid = questionData.qid,
					done = questionData.done,
					questionUrl = questionData.imageUrl,
					choiceItems = questionData.choiceitems,
					$radios = $('.radio','.u-answer'),
					questionPicArea = $('.m-question img'),
					$contentAreas = $('.m-answer .content'),
					$ul = $('.u-answer'),
					$result = $('.m-result');
				
					$result.hide();
					$radios.removeClass('radio-light');
					$radios.eq(0).addClass('radio-light');
					questionPicArea.attr({
						src : questionUrl
					});
					
					
					$.each(choiceItems,function(index,value){
						$contentAreas.eq(index).text(value);
					});

		}


		
		function eventHandlerForRadio(event,$radios) {
			var _target = $(event.target),
				$parentLi = _target.parents('li'),
				$siblingLis = $parentLi.siblings();
			
			$radios.removeClass('radio-light');
			_target.addClass('radio-light');
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
		
		$.ajax({
			url: 'GetQuestionList.action',
			type: 'post',
			dataType: 'json',
			data: {aid: aid},
		})
		.done(function(data) {
			questions = data.questions;
			makeQuestion(questions[curIndex-1]);
		})
		.fail(function(err) {
			console.log(err);
		});	
		
		
		$('.u-answer').on('click.radio','.radio',{'radios' : $('.radio','.u-answer')},function(event){
			eventHandlerForRadio(event,event.data.radios);
		});
		
		$('#j-finishedbtn').bind('click',function(event){
			window.location.href = "goTo.action?url=interact.jsp";
		});
		
		$('.m-submit a').bind('click',function(event){

			var _this = $(this),
				resultBox = $('.m-resultbox img'),
				choiceResult;
				
			if(answered) {
				
				makeQuestion(questions[curIndex-1]);
				answered = 0;
				_this.text('提交');
			} else {
				choiceResult = getCheckedValue();

				if(choiceResult == questions[curIndex-1].aIndex) {
					resultBox.attr({
						src : 'resources/image/right.png'
					});
					$('.m-result').show();
					score += questions[curIndex-1].score;
				} else {
					resultBox.attr({
						src : 'resources/image/wrong.png'
					});
					$('.m-result').show();
					
				}
				
				answered = 1;
				curIndex++;
				if(curIndex > questions.length) {
					
					//弹出提示框
					$.ajax({
						url: 'DoneQuestion.action',
						type: 'post',
						dataType: 'json',
						data: {
							aid: aid,
							score : score
						},
					})
					.done(function(data) {
						$('.u-answer').unbind('click.radio');
						$('.m-submit a').unbind('click');
						$('.u-contentbox','.m-modelbox').text('感谢您参与答题,恭喜获得' + score + '积分！');
						$('.m-modelbox').show();
					})
					.fail(function(err) {
						console.log(err);
					});	
					
				} else {
					_this.text('下一题目');
				}
			}
		});	

});