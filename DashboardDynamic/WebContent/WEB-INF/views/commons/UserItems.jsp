<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="base" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Items</title>
</head>
<body>
<h1>${userDTO.name} items:</h1>

	<br> <br> User: <a href="${base}/usercreate">${userItemsDTO.userLogin}</a>
	<br> <a href="${base}/logout">Logout</a>
	<br> <br><a href="${base}/itemcreate">Add Item</a>
	
    <br> <br>
    	<table>
			<tr>
				<th>Position</th>
				<th>Title</th>
				<th>Description</th>
				<th>Status</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			
    <c:if test="${!empty userItemsDTO.items}">
			<c:forEach items="${userItemsDTO.items}" var="item" varStatus="count">
				<tr>
					<td>${count.count}</td>
					<td>${item.title}</td>
					<td>${item.description}</td>
					<td>${item.status}</td>
					<td><a href="${base}/itemedit?idItem=${item.idItem}">edit</a></td>
					<td><a href="${base}/itemdelete?idItem=${item.idItem}">delete</a></td>
				</tr>
			</c:forEach>
		</table>
    </c:if>




</body>
</html>