<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%
 String path=request.getContextPath();
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>书籍显示</title>
<style type="text/css">
.box{margin:100px auto;width:400px;border:1px solid #000000;}
#button{width:100%;height:40px;border:0px;background:#CF6253;cursor:pointer;}
</style>
</head>
<body>
<div class="box">
<form action="<%=basePath%>borrowServlet" method="post">
<img src="${pageContext.request.contextPath}/image/${book.image}" width="400px" height="500px">
<input type="hidden" name="book" value="${book.id}+${book.save}">
<h5 align="center">书名：${book.name} </br>描述：${book.description}</h5>
<input type="submit" name ="button" id="button">
</form>
</div>
</body>
</html>