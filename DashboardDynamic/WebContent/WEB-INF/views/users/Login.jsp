<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<c:set var="pageTitle" value="Login"/>
<c:import url="/WEB-INF/views/commons/Top.jsp" charEncoding="utf-8" />

	<div class="container">
		<form action="${base}/login" class="col" method="post">
			<div class="row center ">
				<h5 class="header col s12 light"></h5>
				<input type="text" name="login" placeholder="login">
			</div>
			<div class="row center">
				<h5 class="header col s12 light"></h5>
				<input type="password" name="password" placeholder="password">
			</div>
			<div class="row center">
				<h5 class="header col s12 light"></h5>
				<button
					class="btn col s12 waves-effect waves-light blue-grey darken-3"
					type="submit" name="ok" value="Sign In">
					<i class="material-icons center">Submit</i>
				</button>
			</div>
			<div class="row center">
				<i class="material-icons center"> <a href="${base}/usercreate"
					id="download-button"
					class="btn-large col s12 waves-effect waves-light blue-grey darken-3">Register</a>
				</i>
			</div>
		</form>
	</div>
	<c:import url="/WEB-INF/views/commons/Footer.jsp" charEncoding="utf-8" />