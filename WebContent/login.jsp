<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
 String path=request.getContextPath();
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BookStoreLogin</title>
<style type="text/css">
.box{background:url("<%=path%>/image/login.jpg") no-repeat; margin:100px auto;height:500px;width:500px;background-size:100% 100%;position:relative;}
.account{height:6%;width:50%;text-align:center;margin:20% 25% 1% 25%;;position:absolute;}
.password{height:6%;width:50%;text-align:center;margin:26% 25% 1% 25%;position:absolute;}
.button{height:6%;width:25%;text-align:center;margin:35%  37.5% 1% 37.5%;position:absolute;border:0px}
</style>
</head>
<body>
<div class="box" >
<form class="message" action="<%=path%>/loginCheck" method="post" name="login">
<input type="text" class="account" name="account" placeholder="账号"id="account" ></br>
<input type="password" class="password" name="password" placeholder="密码" id="password">
<input type="submit" class="button" value="登录" onclick="return check()" >
</form>
</div>
<script type="text/javascript">

function check() {
	var account = document.login.account.value;
	var password = document.login.password.value;
	if(account==null||password==null||account==""||password==""){
		alert("对不起，您的输入有误！");
		document.login.account.focus();
		return false;
	}
	return true;
	}
</script>
</body>
</html>