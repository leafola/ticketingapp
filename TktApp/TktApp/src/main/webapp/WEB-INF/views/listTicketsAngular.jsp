<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>AngularJS $http Example</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">

	<div class="generic-container" ng-controller="TicketController2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Ticket Service </span>
			</div>
		</div>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">A Ticket </span>
			</div>
				<metawidget ng:model="ticket" >
			</div>
		</div>
	


	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/ticket_service.js' />"></script>
	<script src="lib/metawidget/core/metawidget-core.min.js" type="text/javascript"></script>
	<script src="lib/metawidget/angular/metawidget-angular.min.js" type="text/javascript"></script>
	<script src="<c:url value='/static/js/controller/ticket_controller.js' />"></script>
</body>
</html>