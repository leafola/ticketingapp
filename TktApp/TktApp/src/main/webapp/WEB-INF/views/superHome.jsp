<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/j_spring_security_logout" var="logoutUrl" />

<html>
<head>

<title>Ticketing App Admin Account</title>

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
	border: 2px solid #9989cf;
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
									<span class="label label-default">Admin Account:</span> {{
									agent.fname }} {{ agent.lname }}
								</h5>
							</div>
							<div class="col-sm-4 text-right">
								<button type="button"
									class="btn btn-default btn-sm custom-width1"
									ng-click="showAccountDetailsFunc()">Accounts</button>
								<button type="button"
									class="btn btn-default btn-sm custom-width1"
									ng-click="showAllTicketsFunc()">Tickets</button>
								<a href="http://localhost:8080/j_spring_security_logout"
									class="btn btn-default btn-sm custom-width1" role="button">Logout</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12" ng-show="!showAccountDetails">
				<div class="panel panel-default">
					<div class="panel-heading ">
						<div class="row">
							<div class="col-sm-8">
								<span class="glyphicon glyphicon-folder-open"></span>Tickets
							</div>
						</div>
					</div>
					<div class="panel-body">
						<label>Search Tickets: <input ng-model="searchText"></label>
						<table table="rowCollection" class="table table-striped">
							<thead>
								<tr>
									<th>Id</th>
									<th>Title</th>
									<th>Customer Id</th>
									<th>Agent Id</th>
									<th>Closed</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="t in tickets | filter:searchText">
									<td>{{t.id}}</td>
									<td>{{t.title}}</td>
									<td>{{t.customerId}}</td>
									<td>{{t.agentId}}</td>
									<td>{{t.closed}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-2" ng-show="showAccountDetails">
				<div class="panel panel-default">
					<div class="panel-heading ">
						<span class="glyphicon glyphicon-user"></span>Create Account
					</div>
					<div class="panel-body">
						<div style="padding-bottom: 15px;">
							<label for="sel1">Username:</label> <input type="text"
								ng-model="account.username" class="form-control input-sm"
								placeholder="Enter new ticket question here">
						</div>
						<div style="padding-bottom: 15px;">
							<label for="sel1">Password:</label> <input type="text"
								ng-model="account.password" class="form-control input-sm"
								placeholder="Enter new ticket question here">
						</div>
						<div style="padding-bottom: 15px;">
							<label for="sel1">Account Type:</label> <select
								class="form-control" id="sel1" ng-model="account.role">
								<option value="ROLE_CUSTOMER">Customer</option>
								<option value="ROLE_AGENT">Agent</option>
								<option value="ROLE_SUPER">Super</option>
							</select>
						</div>
						<span class="input-group-btn">
							<button type="button"
								ng-click="ctrl.createAccount(account.username, account.password, account.role)"
								class="btn-sm btn-success custom-width2"
								style="margin-left: 5px;">Create</button>
						</span>

					</div>
				</div>

			</div>
			<div class="col-sm-10" ng-show="showAccountDetails">
				<div class="panel panel-default">
					<div class="panel-heading ">
						<div class="row">
							<div class="col-sm-8">
								<span class="glyphicon glyphicon-folder-open"></span>Accounts
							</div>
						</div>
					</div>
					<div class="panel-body">
						<label>Search Accounts: <input ng-model="searchText"></label>
						<table table="rowCollection" class="table table-striped">
							<thead>
								<tr>
									<th>Id</th>
									<th>Username</th>
									<th>Password</th>
									<th>Role</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="a in accounts | filter:searchText">
									<td>{{a.id}}</td>
									<td>{{a.username}}</td>
									<td>{{a.password}}</td>
									<td>{{a.role}}</td>
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
