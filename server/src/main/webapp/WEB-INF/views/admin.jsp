<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fin.info.server.jstl.tag.*, fin.info.server.model.*"%>
<%@ taglib prefix="fin" uri="/WEB-INF/finPage_taglib.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html lang="en">
<head>
<base href="<%=basePath%>">
<title>admin</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet">
<script src="<c:url value="/js/ie-emulation-modes-warning.js"/>"></script>
<link href="<c:url value="/css/fin-ui.css"/>" rel="stylesheet">
</head>
<body>
	<div class="row">
		<div class="container">
			<div class="col-md-12">
				<h4>Search User</h4>
				<nav class="navbar navbar-f">
				<div class="navbar-header">
					<a href="#" class="navbar-brand">HOME</a>
				</div>

				<div class="collapse navbar-collapse" id="navbar2">

					<form method="GET" action="<st:url value="admin/query"/>"
						role="search" class="navbar-form navbar-left">
						<div class="form-group">
							<div class="input-group">
								<input type="search" style="width: 500px; color: white;"
									name="q" class="form-control" placeholder="Search user..">
								<div class="input-group-btn">
									<button class="btn" type="submie">SEARCH</button>
								</div>
							</div>
						</div>
					</form>

				</div>
				</nav>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="container">
			<div class="box" style="opacity: 0.9;">
				<div class="row">
					<h4>
						<div class="col-md-2">ID</div>
						<div class="col-md-2">NAME</div>
						<div class="col-md-2">JOININGDATE</div>
						<div class="col-md-6" style="text-align: center;">AUTHORITIES</div>
					</h4>
				</div>
				<c:forEach var="user" items="${pb.items}">
					<hr>
					<div class="row">
						<div class="col-md-2 show-popup" id="lookuserr"
							style="overflow: hidden;" data-toggle="modal"
							data-target="#lookuser">${user.id }</div>
						<div class="col-md-2">${user.name }</div>
						<div class="col-md-2">${user.joiningDate }</div>
						<div class="col-md-6">
							<c:forEach var="auth" items="${user.authorities}">
								<button class="btn btn-default">${auth.name }</button>
							</c:forEach>
							<button class="btn btn-default">+</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@ include file="lookuser.html" %>
	<div class="input-f">
		<div class="container">
			<div class="row">
				<div class="col-md-6" style="float: right;">
					<fin:pager pageBean="${pb }" path="admin/query" q="${q}" />
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<c:url value="/js/jquery-2.2.1.min.js"/>"></script>
	<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="<c:url value="/js/holder.min.js"/>"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
	<script src="<c:url value="/js/f-js.js"/>"></script>
	<script src="<c:url value="/js/adminPageInit.js"/>"></script>
</body>
</html>