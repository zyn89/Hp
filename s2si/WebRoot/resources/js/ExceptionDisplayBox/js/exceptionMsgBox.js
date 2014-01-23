(function(window,$){
	
	$.fn.exceptionMsgBox = function(options){
		var opts = $.extend($.fn.exceptionMsgBox.defaults,options || {});
		return this.each(function(){
			var _thisContainer = $(this);
			
			//1 create error msg box 
			/*
			 * <div class="m-modelbox">
				<div class="u-contentbox" >
					weddxgfxgfcgfcgfcg
				</div>
				<a href="javascript:void(0);" id="j-finishedbtn">完成</a>
			</div>
			 */
			function _createMsgBox() {
				var $modelBox = $('<div>').addClass('m-modelbox');
				$('<div>').addClass('u-contentbox').text(opts.msg).appendTo($modelBox);
				$('<a href="javascript:void(0);" id="j-finishedbtn"></a>').text(opts.btnText).appendTo($modelBox);
				return $modelBox;
			}
			
			//2 bind btn event
			function _bindBtnEvent(context) {
				$('#j-finishedbtn',context).bind('click',function(event){
					opts.btnClickHandler(event);
					context.remove();
				});
			}
			//3 unbind some obj event hander
			function _unbindObjEventHandler() {
				opts.objForEventCancel.unbind(opts.eventNameForCancel);
			}
			
			var msgBox = _createMsgBox();
			_bindBtnEvent(msgBox);
			_unbindObjEventHandler();
			msgBox.show().appendTo(_thisContainer);
			
		});
	};
	
	$.fn.exceptionMsgBox.defaults = {
			msg : '',
			btnClickHandler : function(){},
			objForEventCancel : '',
			eventNameForCancel : ''
	};
	
})(window,jQuery);