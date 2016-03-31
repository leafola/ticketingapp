<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AngularJS $http Example</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.12.0/ui-bootstrap.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<style>
html {
	overflow-y: scroll;
}

.list-group {
	margin-bottom: 0px;
}

.list-group-item {
	padding: 5px 10px;
}

a.list-group-item:hover {
	text-decoration: none;
	color: #555555;
	background-color: #FFAD21;
}

a.list-group-item:focus {
	text-decoration: none;
	color: #555555;
	background-color: #73AD21;
}

.active {
	color: red;
}
</style>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="mainController as ctrl">
		<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-sm-8">
								<h5>Account: {{ customer.fname }} {{ customer.lname }}</h5>
							</div>
							<div class="col-sm-4 text-right">
								<a href="http://localhost:8080/j_spring_security_logout"
									class="btn btn-default btn-sm" role="button">Logout</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-default">
					<div class="panel-heading">My Tickets</div>
					<div class="panel-body">
						<div class="input-group" style="padding-bottom: 15px;">
							<input type="text" ng-model="ticket.title"
								class="form-control input-sm"
								placeholder="Enter new ticket question here"> <span
								class="input-group-btn">
								<button type="button" ng-click="ctrl.createTicket(ticket.title)"
									class="btn-xs btn-success" style="margin-left: 5px;">Create</button>
							</span>
						</div>

						<div class="list-group"
							ng-repeat="t in tickets | orderBy:'dateCreated':true">
							<button type="button" class="btn btn-default btn-block"
								ng-click="select(t);ctrl.getPosts(t.id, t.title);"
								ng-class="{active: isActive(t)}">{{ t.title }}</button>
						</div>

					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-sm-8">
								<h4>{{ postTitle }}</h4>
							</div>
							<div class="col-sm-4 text-right">
								<a href="#" ng-click="isCollapsed = !isCollapsed"
									class="btn btn-default btn-sm"> {{ newPostMessage }} </a>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div collapse="isCollapsed">
							<textarea rows="6" ng-model="post.body"
								class="form-control input-sm" placeholder="Enter new post here"
								style="margin-bottom: 10px;"></textarea>
							<span class="input-group-btn">
								<button type="button" ng-click="ctrl.createPost(post.body)"
									class="btn-xs btn-success pull-right"
									style="margin-bottom: 10px;">Post</button>
							</span>
						</div>
						<div class="list-group"
							ng-repeat="p in posts | orderBy:'dateCreated':true">
							<div ng-style="setConversationClass(p.author)">
								<p class="list-group-item-text"
									ng-bind="p.authorName + ' wrote on ' + p.dateCreated"></p>
								<h5 class="list-group-item-heading" ng-bind="p.body"></h5>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
