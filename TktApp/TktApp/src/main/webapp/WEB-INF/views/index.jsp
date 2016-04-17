<!DOCTYPE html >
<html ng-app="testApp">
	<head>
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <title>My AngularJS App</title>
	  <meta name="description" content="">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-route.js"></script>
	  <script src="/static/js/controller/app.js"></script>
	  <script src="/static/js/controller/view1.js"></script>
	  <script src="/static/js/controller/view2.js"></script>
	</head>
	<body>
	  <ul class="menu">
	    <li><a href="#/view1">view1</a></li>
	    <li><a href="#/view2">view2</a></li>
	  </ul>
	
	  <div ng-view></div>
	
	</body>
</html>
