(function($,window){
	

	var state = { status : 0 ,m : [1,1,1]};


	function _callback(e,percentage,state,index,_this) {
		if(percentage > 80 && state.m[index]) {
			state.status += 1;
			_this.clear();
			if(state.status == 3) {
				alert("win");
			}
			state.m[index] = 0;
		}
		console.log('p:' + percentage);
		console.log('s:' + state.status);
	}



	function initGGK(options) {
		var defaultOpt = {
			width : 210,
			height : 100,
			color : '#999',
			size : 20
		},
		opts = $.extend(defaultOpt,options || {});
		opts.container.wScratchPad({
		    width           : opts.width,                
		    height          : opts.height,                
		    image           : opts.imgUrl,  
		    image2          : null,              
		    color           : opts.color,     
		    overlay         : 'none',    
		    size            : opts.size,               
		    realtimePercent : true,             
		    scratchDown     : function(e,percent){
		    						opts.callback(e,percent,opts.state,opts.index,this);
		    				 },               
		    scratchUp       : function(e,percent){
		    						opts.callback(e,percent,opts.state,opts.index,this);
		    				 },                   
		    scratchMove     : function(e,percent){	
		    						opts.callback(e,percent,opts.state,opts.index,this);
		    				 },                   
		    cursor          : '/images/coin.png'        
		});

	}
	
	initGGK({
		imgUrl : '/images/slide1.jpg',
		callback : _callback,
		state : state,
		index : 0,
		container : $('#ggk01')
	});
	initGGK({
		imgUrl : '/images/slide2.jpg',
		callback : _callback,
		state : state,
		index : 1,
		container : $('#ggk02')
	});
	initGGK({
		imgUrl : '/images/slide3.jpg',
		callback : _callback,
		state : state,
		index : 2,
		container : $('#ggk03')
	});
	
	
})(jQuery,window);
		

