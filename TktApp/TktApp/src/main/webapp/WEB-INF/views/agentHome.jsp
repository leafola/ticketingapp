<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/j_spring_security_logout" var="logoutUrl" />

<html>
<head>

<title>Ticketing App Agent Account</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.12.0/ui-bootstrap.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<style>
.label {
font-size: 14;
background: none;
border: 2px solid #ffee9d;
}
</style>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container fill"
		ng-controller="mainController as ctrl">
		<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-default">
					<div class="panel-heading ">
						<div class="row">
							<div class="col-sm-8">
								<h5>
									<span class="label label-default">Agent Account:</span> {{ agent.fname }} {{
									agent.lname }}
								</h5>
							</div>
							<div class="col-sm-4 text-right">
								<button type="button"
									class="btn btn-default btn-sm custom-width1"
									ng-click="showAccountDetailsFunc()">Account</button>
								<button type="button"
									class="btn btn-default btn-sm custom-width1"
									ng-click="showTicketsFunc()">Tickets</button>
								<button type="button"
									class="btn btn-default btn-sm custom-width1"
									ng-click="showArchiveFunc()">Archive</button>
								<a href="http://localhost:8080/j_spring_security_logout"
									class="btn btn-default btn-sm custom-width1" role="button">Logout</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-default" ng-show="showAccountDetails">
					<div class="panel-heading ">
						<span class="glyphicon glyphicon-user"></span>Account Details
					</div>
					<div class="panel-body">
						<div class="read-account" ng-show="!showEdit">
							<h5>Name: {{ agent.fname }} {{ agent.lname }}</h5>
							<button type="button" ng-click="showEditFunc()"
								class="btn-sm btn-success custom-width2"
								style="margin-left: 5px;">Edit</button>
						</div>
						<div class="edit-account" ng-show="showEdit">
							<div style="padding-bottom: 15px;">
								<input type="text" ng-model="agent.fname"
									class="form-control input-sm"
									placeholder="Enter your first name here">
							</div>
							<div style="padding-bottom: 15px;">
								<input type="text" ng-model="agent.lname"
									class="form-control input-sm"
									placeholder="Enter your last name here">
							</div>
							<button type="button"
								ng-click="ctrl.updateAgent();showEditFunc()"
								class="btn-sm btn-success custom-width2"
								style="margin-left: 5px;">Save</button>
						</div>
					</div>
				</div>
				<div class="panel panel-default" ng-show="!showAccountDetails">
					<div class="panel-heading "
						ng-show="!showArchive">
						<span class="glyphicon glyphicon-th-list"></span>Customer Tickets
					</div>
					<div class="panel-body" ng-show="!showArchive">
						<div class="alert alert-info" ng-show="noTickets">
							<span class="glyphicon glyphicon-arrow-up"></span><strong>Welcome!</strong>
							You have no pending support tickets.
						</div>
						<div class="list-group"
							ng-repeat="t in tickets | filter:{closed:false} | orderBy:'dateCreated':true">
							<span class="col-xs-10">
								<button type="button"
									class="btn btn-block btn-primary "
									ng-click="select(t);ctrl.getPosts(t.id, t.title);"
									ng-class="{active: isActive(t)}">
									<span class="glyphicon glyphicon-folder-close"></span> {{
									t.title}}<span class="closed pull-right text-right">{{ checkStatus(t.closed) }}</span>
								</button>
							</span>
							<span class="col-xs-2"><button type="button"
									class="btn-xs btn-success"
									ng-click="t.closed = !t.closed;ctrl.updateTicket(t);">Archive
									
								</button>
							</span>
						</div>
					</div>
					<div class="panel-heading "
						ng-show="showArchive">
						<span class="glyphicon glyphicon-th-list"></span>Archived Customer Tickets
					</div>
					<div class="panel-body" ng-show="showArchive">
						<div class="alert alert-info" ng-show="noTickets">
							<span class="glyphicon glyphicon-arrow-up"></span><strong>Welcome!</strong>
							You have no pending support tickets.
						</div>
						<div class="list-group"
							ng-repeat="t in tickets | filter:{closed:true} | orderBy:'dateCreated':true">
							<span class="col-xs-10">
								<button type="button"
									class="btn btn-block btn-primary"
									ng-click="select(t);ctrl.getPosts(t.id, t.title);"
									ng-class="{active: isActive(t)}">
									<span class="glyphicon glyphicon-folder-close"></span> {{
									t.title}}<span class="closed pull-right text-right">{{
										checkStatus(t.closed) }}</span>
								</button>
							</span> <span class="col-xs-2" style="margin-top: 5px;padding:0px;"> <button type="button"
									class="btn-xs btn-success"
									ng-click="t.closed = !t.closed;ctrl.updateTicket(t);">Open
								</button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="panel panel-default" ng-show="showImage">
					<div class="panel-body">
						<img src="/static/images/support.jpg"
							class="img-rounded img-responsive center-block"
							alt="Technical Support">
					</div>

				</div>
				<div class="panel panel-default" ng-show="showPosts">
					<div class="panel-heading ">
						<div class="row">
							<div class="col-sm-8">
								<span class="glyphicon glyphicon-folder-open"></span>{{
								postTitle }}
							</div>
							<div class="col-sm-4 text-right">
								<a href="#" ng-click="isCollapsed = !isCollapsed"
									class="btn btn-default btn-sm custom-width3"> {{
									newPostMessage }} </a>
								<button ng-click="ctrl.refreshPosts()"
									class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-refresh"></span>
								</button>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="alert alert-info" ng-show="firstEverPost">
							<span class="glyphicon glyphicon-arrow-down"></span><strong>Great!</strong>
							Now attach your first post to this ticket.
						</div>
						<div class="alert alert-info" ng-show="postSent">
							<span class="glyphicon glyphicon-ok"></span> You have responded
							to this customer. Awaiting their reply.
						</div>
						<div collapse="isCollapsed">
							<textarea rows="6" ng-model="post.body"
								class="form-control input-sm" placeholder="Enter new post here"
								style="margin-bottom: 10px;"></textarea>
							<span class="input-group-btn">
								<button type="button"
									ng-click="ctrl.createPost(post.body);ctrl.postSent()"
									class="btn-sm btn-success custom-width2 pull-right"
									style="margin-bottom: 10px;">Post</button>
							</span>
						</div>
						<div class="list-group"
							ng-repeat="p in posts | orderBy:'dateCreated':true">
							<div class="conversation" ng-style="setConversationClass(p.author)">
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
