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
	String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		    + "/";
%>
<html lang="en">
<head>
<base href="<%=basePath%>">
<title>FINANCIAL SEARCH</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/css/font-awesome.min.css"/>" rel="stylesheet">
<script src="<c:url value="/js/ie-emulation-modes-warning.js"/>"></script>
<link href="<c:url value="/css/fin-ui.css"/>" rel="stylesheet">
<link href="<c:url value="/css/custom.css"/>" rel="stylesheet">
<!-- time -->
<link href="<c:url value="/css/bootstrapDatepickr-1.0.0.css"/>"
	rel="stylesheet">

</head>
<body>
	<div class="row" style="margin: 10px; overflow: hidden;">
		<ul class="nav navbar-nav">
			<li style="overflow: hidden;"><img onclick="location='/server/'"
				src="<c:url value="/img/rws.png"/>"
				style="width: 50px; height: 50px; margin-top: 3px; overflow: hidden; cursor: pointer" /></li>
		</ul>
		<form method="get" action="<st:url value="search/s"/>"
			class="navbar-form navbar-left" style="overflow: hidden;">
			<input type="hidden" name="needfacet" value=1> <input
				name='start' type='hidden' value="1" /> <input name="q"
				class="form-control" value="${q}"
				style="float: left; width: 500px; position: absolute; z-index: 0; height: 40px; overflow: hidden; background: transparent; border-radius: 0px;"
				placeholder="Search.." id="searchInput" /> <input type="text"
				id="autocomplete-ajax-x" disabled="disabled" class="form-control"
				style="color: #CCC; float: left; width: 500px; position: absolute; height: 40px; overflow: hidden; background: transparent; border-radius: 0px; z-index: -1;" />
			<input type="submit" style="float: left; margin-left: 500px"
				class="btn-ccc" value="SEARCH"></input>
		</form>
		<a class="advadm show-popup" style="overflow: hidden;" data-toggle="modal" data-target="#login">admin</a>
		<a class="advadm show-popup" style="overflow: hidden;" data-toggle="modal" data-target="#myPopup">advance</a>
	</div>
	<div id="result">searched ${pb.totalrows } items, time: ${qtime } ms</div>
	<div id="content_right" class="cr-offset">
		<ul>
			<li style="list-style-type: none;"><h3>AUTHOR:</h3></li>
			<c:forEach items="${frs}" var="fr">
				<li style="list-style-type: none;"><a
					href="<c:url value="/search/s?start=1&q=author:${fr.name }"/>">${fr.name}(${fr.count })</a></li>
			</c:forEach>
		</ul>
	</div>
	<div id="content_left">
		<c:forEach var="item" items="${pb.items}">

			<div class="result c-container ">
				<h3 class="t">
					<a href="${item.uri} "> ${item.title} </a>
				</h3>
				<div class="row c-gap-top-small">
					<div class="general_image_pic c-span6"
						style="display: inline-block; float: left; margin: 10px">
						<a class="c-img6" style="height: 75px" href="${serverPath }/${item.firstimage }" target="_blank"><img
							class="c-img c-img6" alt="${item.title}" src="${serverPath }/${item.firstimage }"
							style="height: 75px;"></a>
					</div>
					<div class="c-span18 c-span-last">
						<div class="bbs f13">
							<span>by: <b>${item.author}</b></span>&nbsp;&nbsp;&nbsp;
							<fmt:timeZone value='<%=java.util.TimeZone.getTimeZone("UTC")%>'>
								<fmt:formatDate value="${item.time}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</fmt:timeZone>
						</div>
						<div class="c-abstract">${item.description }</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="footer">
		<div class="col-md-6" style="margin-left: 100px">
			<fin:pager pageBean="${pb }" path="${path }" q="${paramq}" />
		</div>
	</div>
	<div class="clear"></div>
	<%@ include file="popup.html" %>
	<%@ include file="loginAdmin.html" %>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- jquery -->
	<script src="<c:url value="/js/jquery-2.2.1.min.js"/>"></script>
	<!-- time -->
	<script src="<c:url value="/js/bootstrapDatepickr-1.0.0.min.js"/>"></script>
	<!-- autocomplete -->
	<script type='text/javascript'
		src="<c:url value='/js/jquery.autocomplete.js'/>"></script>
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="<c:url value="/js/holder.min.js"/>"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
	<script src="<c:url value="/js/f-js.js"/>"></script>
	<script src="<c:url value="/js/searchPageInit.js"/>"></script>
</body>
</html>