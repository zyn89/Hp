jQuery(function($){
		var sid = $('#sid').val(),
			questions,curIndex = 1,answered = 0,score=0,count=0;

		function makeQuestion(questionData) {
				var num2alph = {1 : 'A', 2 : 'B','3' : 'C' ,4 : 'D'},
					qid = questionData.qid,
					done = questionData.done,
					questionUrl = questionData.imageUrl,
					choiceItems = questionData.choiceItems,
					rate = questionData.rate,
					questionAnswerArea =$('.m-answer') .find('>fieldset'),
					questionPicArea = $('.m-question').find('>img');

					if(done) {
						count++;
						answered = 1;
						curIndex++;
						$('.m-submit a').text("已答! 下一题");
						$('.m-result').text(rate?rate[0] + " " + rate[1] + " " + rate[2] : "" ).show();
					} else {
						answered = 0;
						$('.m-submit a').text("提交");
					}
						
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

		$('.m-submit a').bind('click',function(event){

			var _this = $(this),
				choiceResult,
				$resultDiv = $('.m-result');
			if(answered) {
				//下一题
				if(curIndex>questions.length) {
					if(count == questions.length) {
						alert('已经参与过此次调研');
					} else {
						alert("感谢你的参与调研！恭喜获得10个积分！");
					}
				} else {
					$resultDiv.hide();
					makeQuestion(questions[curIndex-1]);
				}
			} else {
				choiceResult = getCheckedValue();
				answered = 1;
				curIndex;
				
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
					console.log(data);
					console.log((data.rate)[0]);
					
					$resultDiv.text("p1:" + (data.rate)[0] + " p2:" + (data.rate)[1] + " p3:" + (data.rate)[2]);
					$resultDiv.show();
				})
				.fail(function(err) {
					console.log(err);
				});
				
				curIndex++;
				
				_this.text('下一题');
				
			}
		});	

});