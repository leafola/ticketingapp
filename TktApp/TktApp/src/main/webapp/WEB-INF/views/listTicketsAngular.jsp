<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>AngularJS $http Example</title>  
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
 
      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
 
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="TicketController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Tickets Service </span></div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Tickets </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>Ticket ID</th>
                              <th>Title</th>
                              <th>Date Created</th>
                              <th>Customer Id</th>
                              <th>Agent Id</th>
                              <th>Closed</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="t in ctrl.tickets">
                              <td><span ng-bind="t.id"></span></td>
                              <td><span ng-bind="t.title"></span></td>
                              <td><span ng-bind="t.dateCreated"></span></td>
                              <td><span ng-bind="t.customerId"></span></td>
                              <td><span ng-bind="t.agentId"></span></td>
                              <td><span ng-bind="t.closed"></span></td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
       
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/ticket_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/ticket_controller.js' />"></script>
  </body>
</html>