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
<title>FINANCIAL</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet">
<script src="<c:url value="/js/ie-emulation-modes-warning.js"/>"></script>
<link href="<c:url value="/css/fin-ui.css"/>" rel="stylesheet">
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<div class="jumbotron-f">
				<div class="introduce">
					<img src="<c:url value="/img/ws.png"/>">
					<h1>FINANCIAL COMMENTARY</h1>
					<p>A SEARCH WEB FOR FINANCIAL INFOMATION.</p>
				</div>
				<div class="row visible-md visible-lg download">
					<div class="col-md-2">
						<p></p>
					</div>
					<form method="GET" action="<st:url value="search/s"/>">
						<input type="hidden" name="needfacet" value=1>
						<div class="form-group col-md-7">
							<input name="q" class="form-control"
								style="background-color: transparent; color: white; border: 2px solid white"
								placeholder="Search.." value="${q}">
						</div>
						<input type="submit"  class="btn-download col-sm-2" value="SEARCH"></input>
					</form>

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
</body>
</html>