<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=gbk">
    <meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport"/>
    <title>�ι���</title>
    <style type="text/css">
        #main {border: 1px solid #666;cursor: pointer;}
        img.output { border: 1px solid #666; }
    </style>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js "></script>
<script type="text/javascript">
$('document').ready(function(){
    var backimage = { 'url':'http://y2.ifengimg.com/2013/04/07/08384565.gif', 'img': null };
    var canvas = {'temp': null, 'draw': null}; 
    var mouseDown = false;    
    // canvas �ϳ�    
    function recompositeCanvases() {
        var main = document.getElementById('main');
        var tempctx = canvas.temp.getContext('2d');
        var mainctx = main.getContext('2d');
        // clear the temp
        canvas.temp.width = canvas.temp.width;
        // �� canvas.draw ���w�� drawImage ��
        tempctx.drawImage(canvas.draw, 0, 0);
        // �� source-atop �ķ�ʽ�� backimage ���� tempctx ��
        tempctx.globalCompositeOperation = 'source-atop';
        tempctx.drawImage(backimage.img, 0, 0);
        // mainctx => ��ɫǰ�� (߀�]�йε��ĵط�)
        mainctx.fillStyle = "#888";
        mainctx.fillRect(0, 0, backimage.img.width, backimage.img.height);
        // ����� canvas.temp ���w�� mainctx ��
        mainctx.drawImage(canvas.temp, 0, 0);
    }
    // ����
    function scratch(canv, x, y, fresh) {
        var ctx = canv.getContext('2d');        
        // ���P��С, �Π�...
        ctx.lineWidth = 20;
        ctx.lineCap = ctx.lineJoin = 'round';
        if (fresh) {
            ctx.beginPath();
            // ����ģ�M���ӣ������� x ���� 0.01����Ȼ�cһ�²����a������
            ctx.moveTo(x+0.01, y);
        }
        ctx.lineTo(x, y);
        ctx.stroke();
    }
    function setupCanvases() {
        var c = document.getElementById('main');
        // ȡ�ÈDƬ�L��
        c.width = backimage.img.width;
        c.height = backimage.img.height;
        canvas.temp = document.createElement('canvas');
        canvas.draw = document.createElement('canvas');
        canvas.temp.width = canvas.draw.width = c.width;
        canvas.temp.height = canvas.draw.height = c.height;
        recompositeCanvases();
        function mousedown_handler(e) {
            var local = getLocalCoords(c, e);
            mouseDown = true;
            scratch(canvas.draw, local.x, local.y, true);
            recompositeCanvases();
            if (e.cancelable) { e.preventDefault(); } 
            return false;
        };
        function mousemove_handler(e) {
            if (!mouseDown) { return true; }
            var local = getLocalCoords(c, e);
            scratch(canvas.draw, local.x, local.y, false);
            recompositeCanvases();
            if (e.cancelable) { e.preventDefault(); } 
            return false;
        };
        function mouseup_handler(e) {
            if (mouseDown) {
                mouseDown = false;
                if (e.cancelable) { e.preventDefault(); } 
                return false;
            }
            return true;
        };
        // mouse handlers
        c.addEventListener('mousedown', mousedown_handler, false);
        c.addEventListener('touchstart', mousedown_handler, false);
        window.addEventListener('mousemove', mousemove_handler, false);
        window.addEventListener('touchmove', mousemove_handler, false);
        window.addEventListener('mouseup', mouseup_handler, false);
        window.addEventListener('touchend', mouseup_handler, false);
    }
    // ȡ������
    function getLocalCoords(elem, ev) {
        var ox = 0, oy = 0;
        var first;
        var pageX, pageY;
        while (elem != null) {
            ox += elem.offsetLeft;
            oy += elem.offsetTop;
            elem = elem.offsetParent;
        }
        if (ev.hasOwnProperty('changedTouches')) {
            first = ev.changedTouches[0];
            pageX = first.pageX;
            pageY = first.pageY;
        } else {
            pageX = ev.pageX;
            pageY = ev.pageY;
        }
        return { 'x': pageX - ox, 'y': pageY - oy };
    }
    // �ӑB��һ�� img ����
    backimage.img = document.createElement('img'); 
    backimage.img.addEventListener('load', setupCanvases, false);
    backimage.img.src = backimage.url;
    // reset
    $('#resetbutton').bind('click', function() {
        canvas.draw.width = canvas.draw.width;
        recompositeCanvases();
        return false;
    });    
});
</script>
</head>
<body>
    <canvas id="main"></canvas> <br />
    <input id="resetbutton" type="button" value="Reset" />
</body>
</html>