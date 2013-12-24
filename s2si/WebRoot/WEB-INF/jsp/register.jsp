<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>注册</title>
  <meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" name="viewport"/>
  
<link type="text/css" rel="stylesheet" href="resources/css/register.css"/>
	

</head>
<body>
	
	<div id="login" class="clearfix">
    <h2>请填写注册内容</h2>
    <form class="form-horizontal" id="signupForm" method="post" action="register.action" >
      <fieldset>
        <div class="control-group">
					<label  for="loginName">手机号*:</label> <input id="phone" name="phone"  type="text"  required="required" >
        </div>
        <div class="control-group">
					<label> 姓名   :</label> <input id="loginName" name="name"  type="text"  required="required">
        </div>
        <div class="control-group">
					<label>  密码*:</label> <input id="password" name="pww" type="password"  required="required">
        </div> 
        <div class="control-group">
					<label>确认密码*</label> <input id="password2" name="pw" type="password"  required="required">
        </div>          
        
        <div class="control-group">
					<label> 门店*:</label> <input id="shopName" name="shopName"  type="text"  required="required" >
        </div>
        <div class="form-actions">
					<button class="btn btn-primary submit" type="submit" id="signup">注册</button>
					<button class="btn" type="button" type="reset"  id="pwd-btn">重置</button>
        </div>
      </fieldset>
    </form>
  </div>
			
</body>
</html>