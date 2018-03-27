<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Login</h1>
<br>
<br>

	<form action="${base}/login" method="post">
		Login: <input type="text" name="login" placeholder="login">
		<br> <br> 
		Password: <input type="password" name="password" placeholder="password"> 
		<br> <br>
		<input type="submit" name="ok" value="Sign In">
		<br> <br>
		<a href="${base}/usercreate"> Register</a>
	</form>

	<br>
	<br>
	<c:if test="${not empty errorMessage}">
		<font color="red">${errorMessage}</font>
	</c:if>

</body>
</html>