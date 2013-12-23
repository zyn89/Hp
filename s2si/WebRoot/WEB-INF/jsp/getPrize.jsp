<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
  <meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport"/>
<title>圆盘抽奖</title>
<style>
*.{margin:0;padding:0;}
#elm1{height:40px;background-color:#a00;}
#elm2{height:50px;background-color:#0a0;}
#wrap{position:relative;height:400px;}
#bg{position:absolute;left:0;top:0;}
#cell{position:absolute;left:0;top:0;}
#control{position:absolute;left:430px;top:30px;}
</style>
</head>
<body>
<div id='elm1'></div>
<div id='wrap'>
<canvas id='bg'></canvas>
<canvas id='cell'></canvas>
<div id='control'>
<div id='start'>start</div>
<div id='end'>end</div>
<div id='show'>show</div>
</div>
</div>
<div id='elm2'></div>
<script type="text/javascript">
var field = [[0,30],[30,60],[60,90],[90,120],[120,150],[150,180],[180,210],[210,240],[240,270],[270,300],[300,330],[330,360]]
    ,fillStyle = ['#a00','#0a0','#00a','aa0','#a16','#0aa','#a5a','f31','#784','#a43','#34a','#786'] 
	,width = 400
	,height = 400
	,c_x = 200
	,c_y =200
	,radius = 200
	,dom_bg = document.getElementById('bg')
	,dom_cell = document.getElementById('cell')
	,index =0
	,timer = null
	,stop = false
	,running = false
	,speed = 300
	,flag = false
	,stepall
	,stepfast = 7
	,stepslow
	,stepping =0
	,selected
	,pos;
function setup(){
	drawBg();
}
function drawBg(){
	var deg = Math.PI/180;
	var tmp_1 = 0;
	var tmp_2 = 30;
	dom_bg.height = height;
	dom_bg.width = width;
	var ctx=dom_bg.getContext('2d');
	for(var i=0;i<12;i++){
		ctx.beginPath();
		ctx.fillStyle = fillStyle[i];
		ctx.moveTo(c_x,c_y);
		ctx.arc(c_x,c_y,radius,deg*tmp_1,deg*tmp_2,false);
		ctx.fill();
		ctx.fillStyle = '#000';
		ctx.fillText(tmp_1,200+Math.cos(deg*(tmp_1+15))*130,200+Math.sin(deg*(tmp_1+15))*130);
		tmp_1 +=30;
		tmp_2 +=30;
	}
}
function drawBgText(){	
}
function rotate(){
	if(!flag && stepping>7){
		clearTimer();
		speed = 100;
		timer = setInterval(rotate,speed);
	}
	if(flag && stepping > stepslow && stepping <stepall){
		clearTimer();
		speed = 300;
		timer = setInterval(rotate,speed);
	}
	if(index ==12){
		index = 0;
	}
	if(flag && stepping == stepall){
		clearTimer();
		happy();
	}
	drawCell();
	index++;
	stepping++;
}
function drawCell(){
	var deg = Math.PI/180;
	var tmp_1 = field[index][0];
	var tmp_2 = field[index][1];
	dom_cell.height=height;
	dom_cell.width =width;
	var ctx=dom_cell.getContext('2d');
	ctx.beginPath();
	ctx.fillStyle = '#eee';
	ctx.moveTo(c_x,c_y);
	ctx.arc(c_x,c_y,radius,deg*tmp_1,deg*tmp_2,false);
	ctx.fill();
}
function createStop(){
	selected = Math.floor(Math.random() * 11);         //从0-11个数字中选   想zb在这控制一下。
	var circle = Math.floor(Math.random()*3) + 2;
	var c_step = circle *12;
	var   actualIndex = stepping%12;
	stepall =  selected - actualIndex + c_step;
	flag = true;
	stepslow = stepall - 7;
	stepping = 0;
}
function happy(){
	document.getElementById("show").innerText = selected + '选上了';
}
function startClick(){
	if(running){
		return;
	}
	running= true;
	timer = setInterval(rotate,speed);
}
function endClick(){
	if(stop){
		return;
	}
	stop = true;
	clearTimer();
	createStop();
	timer = setInterval(rotate,speed);
}
function clearTimer(){
	clearInterval(timer);
	timer = null;
}
setup();
document.getElementById('start').addEventListener('click',startClick,false);
document.getElementById('end').addEventListener('click',endClick,false);
</script>
</body>
</html>