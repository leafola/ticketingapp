<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AngularJS $http Example</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<!--  <script src="<c:url value='/static/js/service/ticket_service.js' />"></script> -->
<!-- <script src="<c:url value='/static/js/service/loggedInService.js' />"></script> -->
<!--  <script src="<c:url value='/static/js/controller/ticket_controller.js' />"></script> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="mainController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="row">
					<div class="col-sm-10">
						<span class="lead">Hello {{ account.username }} </span>
					</div>
					<div class="col-sm-2 text-right">
						<form:form method="post" action="${logoutUrl}">
							<input type="submit" value="Logout" />
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<span class="lead">Your Tickets </span>
					</div>
					<div class="tablecontainer">
						<table class="table table-hover">
							<form class="form-horizontal">
								<tr>
									<td><input type="text" ng-model="ticket.title"
										class="form-control input-sm"
										placeholder="Enter new ticket question here" required
										ng-minlength="3"></td>
									<td>
										<button type="button"
											ng-click="ctrl.createTicket(ticket.title)"
											class="btn btn-success custom-width">Create</button>
									</td>
								</tr>
							</form>
						</table>
					</div>

					<div class="tablecontainer">

						<div ng-repeat="t in tickets">
							<div class="row">
								<div class="col-sm-4">
									<span ng-bind="t.dateCreated"></span>
								</div>
								<div class="col-sm-4">
									<span ng-bind="t.closed"></span>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-8">
									<span ng-bind="t.title"></span>
								</div>
								<div class="col-sm-2">
									<button type="button" ng-click="ctrl.getPosts(t.id, t.title)"
										class="btn-xs btn-success">Read</button>
									<button type="button" ng-click="ctrl.deleteTicket(t.id)"
										class="btn-xs btn-danger ">Delete</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<span class="lead"> Posts: {{ postTitle }}</span>
					</div>
					<div class="tablecontainer">
						<table class="table table-hover">
							<thead>
								<tr>
									<th width="20%">Author</th>
									<th>Body</th>
									<th width="20%"></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="p in posts">
									<td><small><span ng-bind="p.author"></span><br />
											<span ng-bind="p.dateCreated"></span></small></td>
									<td><span ng-bind="p.body"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
