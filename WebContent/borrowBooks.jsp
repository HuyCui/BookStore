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
<title>书籍显示页</title>
<style type="text/css">
a{text-decoration: none}
.box{margin:100px auto;width:400px;border:1px solid #000000;background:#F3D6A6;}
</style>
</head>
<body>
<c:forEach var = "book" items="${bookList}">
<div class="box"><a href="<%=basePath%>bookServlet?book_Id=${book.id}">
<img src="${pageContext.request.contextPath}/image/${book.image}" width="400px" height="500px">
<h5 align="center">书名：${book.name}</br>描述：${book.description}</h5>
</a></div>
</c:forEach>
</body>
</html>