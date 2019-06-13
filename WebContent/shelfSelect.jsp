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
<title>借书书柜</title>
<style type="text/css">
.box{margin:150px auto;width:100%;height:100px;}
.ll{margin:5px 35%;width:100%;}
.shelf{width:30%;height:30px;text-align:center;}
.button{border:0;width:10%;height:30px;cursor: pointer;background:#EB4D34;margin:0 45%;}
</style>
</head>
<body>
<div class="box">
<form action="<%=path%>/shelfCheckServlet" method="post" name="select" >
 <div class="ll">书柜号：<input type="text" value="${id}" class="shelf" placeholder="需要手动输入" name="id"></div></br></br>
<input type="submit" class="button" onclick="return check()">
</form>
<script type="text/javascript">

function check() {
	var account = document.select.id.value;
	if(id==null||id==""){
		alert("对不起，您的输入有误！");
		document.select.id.focus();
		return false;
	}
	return true;
	}
</script>
</div>
</body>
</html>