jQuery(function($){
		var aid = $('#aid').val(),
			questions,curIndex = 1,answered = 0,score=0;

		function makeQuestion(questionData) {
				var num2alph = {1 : 'A', 2 : 'B','3' : 'C' ,4 : 'D'},
					qid = questionData.qid,
					questionUrl = questionData.questionUrl,
					choiceItems = questionData.choiceitems,
					questionAnswerArea =$('.m-answer') .find('>fieldset'),
					questionPicArea = $('.m-question').find('>img');

					questionPicArea.attr({
						src : questionUrl
					});
					questionAnswerArea.empty();

					$.each(choiceItems,function(index,value){
						var $input = $('<input>'),
							$label = $('<label>'),
							iIndex = index-0+1,
							idOrname = 'radio-choice-' + iIndex;
							

						$input.attr({
							type : 'radio',
							name : 'radio-choice',
							id : idOrname,
							value : iIndex
						});
						if(index == 0) {
							$input.attr({checked : "checked"});
						}
						$label.attr({
							for : idOrname,
						}).text(num2alph[iIndex] + '. ' + value);

						questionAnswerArea.append($input).append($label);
						questionAnswerArea.trigger('create');
					});

		}


		function getCheckedValue() {
			return $('.m-answer input:checked').val();
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

		$('.m-submit a').bind('click',function(event){

			var _this = $(this),
				choiceResult,
				$resultDiv = $('.m-result');
			if(answered) {
				//下一题
				$resultDiv.hide();
				makeQuestion(questions[curIndex-1]);
				answered = 0;
				_this.text('提交');
			} else {
				choiceResult = getCheckedValue();

				if(choiceResult == questions[curIndex-1].aIndex) {
					$resultDiv.text('正确');
					score += questions[curIndex-1].score;
				} else {
					$resultDiv.text('错误')
				}
				$resultDiv.show();
				answered = 1;
				curIndex++;
				if(curIndex > questions.length) {
					alert('题目已经答完,所获积分' + score);
				} else {
					_this.text('下一题');
				}
			}
		});	

});