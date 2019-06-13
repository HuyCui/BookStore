<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <% 
  String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>main.jsp</title>
<link rel="stylesheet" href="<%=basePath%>css/select.css" type="text/css" media="screen">
<style type="text/css">
body {  font-family: Arial; background-color: #FFFFFF; }
</style>
</head>
<body>
				<div class="clean-button">
					<a href="<%=basePath%>borrowServlet"><span class="text"> 借书</a></span></a></br></br>
					<form action="<%=basePath%>backServlet" id="in" method="post">
					<a  onclick="document.getElementById('in').submit();"><span class="text1">还书</span></a>
					</form></br></br>
					<a href="<%=path%>/shareBook.jsp" decoration="none" style="color:red;font-size:20px"><点此开始共享你的图书></a>
				</div>
</body>
</html>