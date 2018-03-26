<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}" />

<c:set var="submitButtonTextVar" value="Register"></c:set>

<c:if test="${not empty userDTO}">
<c:set var="nameVar" value="${userDTO.name}"></c:set>
<c:set var="loginVar" value="${userDTO.login}"></c:set>
<c:set var="emailVar" value="${userDTO.email}"></c:set>
<c:set var="submitButtonTextVar" value="Confirm"></c:set>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
</head>
<body>

<h3>WELLCOME ${nameVar} !!!</h3>

<form action="${base}/usercreate" method="post">
	Name: <input type="text" name="name" placeholder = "name" value = "${nameVar}"> <br> <br>
	
	Login: <input type="text" name="login" placeholder = "login" value = "${loginVar}"> <br> <br>
	
	Password: <input type="password" name="password" placeholder = "password"> <br> <br>
	Confirm password: <input type="password" name="confirmpassword" placeholder = "password"> <br> <br>
	Email: <input type="text" name="email" placeholder = "email" value = "${emailVar}"> <br> <br>
	
	<input type="submit" name="register" value="${submitButtonTextVar}" method="post"> <br> <br>
	</form>
	<form action="${base}/usercancel" method="post">
	<input type="submit" name="cancel" value="Cancel" method="post"> <br> <br>
	</form>
	
	<c:if test="${not empty errorMessage}">
		<font color="red">${errorMessage}</font>
	</c:if>

</body>
</html>