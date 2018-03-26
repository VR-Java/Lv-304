<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}" />

<c:if test="${!empty itemDTO}">
<c:set var="titleVar" value="${itemDTO.title}"></c:set>
<c:set var="descriptionVar" value="${itemDTO.description}"></c:set>
<c:set var="statusVar" value="${itemDTO.status}"></c:set>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item profile</title>
</head>
<body>

<h3>ADD NEW ITEM</h3>

<form action="${base}/itemcreate" method="post">
	Title: <input type="text" name="title" placeholder = "title" value = "${titleVar}"> <br> <br>
	Description: <input type="text" name="description" placeholder = "description" value = "${descriptionVar}"> <br> <br>
	Status: <input type="text" name="status" placeholder = "status" value = "${statusVar}"> <br> <br>
	
	<input type="submit" name="submit" value="Submit" method="post"> <br> <br>
	</form>
	<form action="${base}/itemcancel" method="post">
	<input type="submit" name="cancel" value="Cancel" method="post"> <br> <br>
	</form>
	<c:if test="${not empty errorMessage}">
		<font color="red">${errorMessage}</font>
	</c:if>

</body>
</html>