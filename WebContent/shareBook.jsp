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
<title>Insert title here</title>
<link rel="stylesheet" href="css/shareBook.css" type="text/css" />
</head>
<body>
<h1 align="center">图书漂流 --- 让知识流动起来</h1>
<div class="box">
<input type="file" name="im" id="im" class="im" style="opacity: 0" accept="image/*" onchange="document.getElementById('image').value=this.value" form="in" border="0"></br>
<input type="text"  id="image" name="image" placeholder="请选择图书封面" class="filetext"/></br><button class="xiugaibtn" style="background-color:#B2E0F8">选择提交的图片</button></br>
<form enctype="multipart/form-data" action="<%=basePath%>sharedServlet" id="in" method="post">
<input type="text" name="name" id="name" placeholder="请输入图书名称"></br>
<input type="text" name="id" id="id" placeholder="请输入图书条形码"></br>
<textarea  name="description" id="description" form="in" placeholder="请输入图书简介"></textarea></br>
<input type="submit" name="submit" id="submit" style="background-color:#70C2F1" value="确认提交">
</form>
</div>
<script src="js/jquery-1.8.2.min.js">
</script>
<script type="text/javascript">
$(".xiugaibtn").click(function () {
    $(".im").click();
});

</script>
</body>
</html>