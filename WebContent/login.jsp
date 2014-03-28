<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Iogin in please</title>
</head>
<body>
<s:debug></s:debug>
	<div style="color: red;">
		${fieldErrors['uname'][0]}
	</div>
	<%=request.getHeader("User-Agent") %>
	<form action="home">
		<div>
			请输入用户名:<input type="text" name="uname" value="${uname}" />
			请输入用户名2:<input type="text" name="user.name" value="${user.name}" />
		</div>
		<div>
			pwd:<input type="text" name="upwd" value="${upwd}" />
			pwd2:<input type="text" name="user.password" value="${user.password}" />
		</div>
		<div>
			<input type="submit" value="submit" />
		</div>
	</form>
</body>
</html>