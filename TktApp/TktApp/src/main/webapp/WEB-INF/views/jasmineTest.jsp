<html>
  <head>
    <!-- Jasmine References -->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jasmine/2.3.3/jasmine.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jasmine/2.3.3/jasmine.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jasmine/2.3.3/jasmine-html.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jasmine/2.3.3/boot.min.js"></script>
    
    <!-- Angular and Angular Mock references -->
    <script type="text/javascript" src="https://code.angularjs.org/1.4.0-rc.2/angular.min.js"></script>
    <script type="text/javascript" src="https://code.angularjs.org/1.4.0-rc.2/angular-mocks.js"></script>
  </head>
  <body></body>
  <script type="text/javascript">

  var myApp = angular.module('myApp', []);


  myApp
  		.controller(
  				'mainController',
  				function mainController($scope, $http, getUser, $log) {
  					
  					// Global variable for $scope reference within other functions
  					var self = this;
  					
  					
  					$scope.newPostMessage = 'Add New Post';
  					$scope.disabled = false;

  					
  					
  					$scope.disable = function() {
  						$scope.disabled = !scope.disabled;
  						return $scope.disabled;
  					};
  					
  					// *****************************************************
  					// Show and Hide Functionality *************************
  					// *****************************************************
  					
  					// Boolean Show and Hide Variables
  					// *******************************
  					
  					$scope.isCollapsed = true;
  					
  					$scope.showAccountDetails = true;
  					$scope.showEdit = false;
  					$scope.showImage = true;
  					$scope.showPosts = false;
  					$scope.showArchive = false;
  					$scope.showAdminTickets = false;
  					$scope.showAdminAccounts = true;
  					$scope.showAdminMeta = false;
  					
  					
  					// Show and Hide Functions
  					// *******************************
  					
  					//NAV BUTTONS
  					
  					// Show account details section
  					$scope.showAccountDetailsFunc = function() {
  						////$log.debug("Calling showAccountDetailsFunc");
  						
  						$scope.showAccountDetails = true;
  						$scope.showImage = true;
  						$scope.showPosts = false;
  					};
  					
  					// Show the tickets section
  					$scope.showTicketsFunc = function() {
  						////$log.debug("Calling showTicketsFunc");
  						$scope.showAccountDetails = false;
  						$scope.checkForTickets();
  						$scope.showArchive = false;
  						$scope.selectTopTicket(false);
  					};
  					
  					// Show all tickets function
  					$scope.showAllTicketsFunc = function() {
  						//$log.debug("Calling showAllTicketsFunc");
  						$scope.showAccountDetails = false;
  						self.getAllTickets();
  					};
  					
  					// Show Archive section
  					$scope.showArchiveFunc = function() {
  						//$log.debug("Calling showArchiveFunc");
  						$scope.showAccountDetails = false;
  						$scope.checkForTickets();
  						$scope.showArchive = true;
  						$scope.selectTopTicket(true);
  					};
  					
  					// Show MetaWidget
  					$scope.showMetaWidgetFunc = function() {
  						//$log.debug("Calling ShowMetaWidgetFunc");
  						$scope.showAdminTickets = false;
  						$scope.showAdminAccounts = false;
  						$scope.showAdminMeta = true;
  						
  					}
  					
  					$scope.showAdminAccountFunc = function() {
  						//$log.debug("Calling ShowAdminAccountFunc");
  						$scope.showAdminTickets = false;
  						$scope.showAdminAccounts = true;
  						$scope.showAdminMeta = false;
  						
  					}
  					
  					$scope.showAdminTicketsFunc = function() {
  						//$log.debug("Calling ShowAdminTicketsFunc");
  						$scope.showAdminTickets = true;
  						$scope.showAdminAccounts = false;
  						$scope.showAdminMeta = false;
  					}
  					
  					// Select top ticket so that it is highlighted with correct colour
  					$scope.selectTopTicket = function(answer) {
  						//$log.debug("Calling selectTopTicket()");
  						for (i=0;i<$scope.tickets.length;i++){
  							if ($scope.tickets[i].closed === answer){
  								$scope.selected = $scope.tickets[i];
  								self.ticket = $scope.tickets[i]
  							}
  						}
  						self.getPosts(self.ticket.id, self.ticket.title);
  					};
  					
  					
  					// Show the edit account details section
  					$scope.showEditFunc = function() {
  						//$log.debug("Calling showEditFunc");
  						$scope.showEdit = !$scope.showEdit;
  					};
  					
  					
  					// Check if ticket status is open or closed
  					$scope.checkStatus = function(status) {
  						//$log.debug("Calling checkStatus()");
  						if (status) {
  							return 'closed';
  						} else {
  							return 'open';
  						}
  							
  					};
  					
  					// Check if this is users first ever post
  					$scope.checkIfFirstEverPost = function() {
  						//$log.debug("Calling checkIfFirstEverPost");
  						if ($scope.tickets.length === 1
  								&& $scope.posts.length === 0) {
  							$scope.firstEverPost = true;
  						} else {
  							$scope.firstEverPost = false;
  						}
  					};
  					
  					// Check if user has submitted a new post
  					$scope.postSentFunc = function() {
  						//$log.debug("Calling postSentFunc");
  						if ($scope.posts.length >= 1
  								&& $scope.posts[$scope.posts.length - 1].author === $scope.account.id) {
  							console.log("Account id = " + $scope.account.id);
  							console
  									.log("Last Post Account id = "
  											+ $scope.posts[$scope.posts.length - 1].author);
  							$scope.postSent = true;
  						} else {
  							$scope.postSent = false;
  						}
  					};
  					
  					// Check if the user has any tickets created yet. Show and Hide depending on result
  					$scope.checkForTickets = function() {
  						//$log.debug("Calling checkForTickets()");
  						if ($scope.tickets.length === 0) {
  							$scope.noTickets = true;
  							$scope.showImage = true;
  							$scope.showPosts = false;
  						} else if ($scope.tickets.length > 0
  								&& $scope.showAccountDetails === true) {
  							$scope.noTickets = false;
  							$scope.showImage = true;
  							$scope.showPosts = false;
  						} else {
  							$scope.noTickets = false;
  							$scope.showImage = false;
  							$scope.showPosts = true;
  						}
  						console.log("Tickets array size"
  								+ $scope.tickets.length);
  					};
  					
  					
  					// Check if the user has any posts yet. Show/Hide text entry box based on result
  					$scope.checkForPosts = function() {
  						//$log.debug("Calling checkForPosts");
  						if ($scope.posts.length === 0) {
  							$scope.isCollapsed = false;
  						} else {
  							$scope.isCollapsed = true;
  						}
  						console.log("Posts array size" + $scope.posts.length);
  					};
  					
  					// Function to change isCollapsed value to false
  					$scope.changeIsCollapsed = function() {
  						//$log.debug("Calling changeIsCollapsed()");
  						$scope.isCollapsed = false;
  					};  					  					
  				});
    
    /* Test Code */
    describe('my app', function () {
    	var $controller;
    	
      beforeEach(module('myApp'));

		
      beforeEach(inject(function(_$controller_){
    	  $controller = _$controller_;
    	}));
      

      describe('showAccountDetails', function () {
        it('$scope booleans should be true, true, false', function () {
          var $scope = {};
          var $http = {};
          var getUser = {};
          var $log = {};
          var controller = $controller('mainController', { $scope : $scope, $http : $http, getUser: getUser, $log: $log });
          $scope.showAccountDetails = false;
          $scope.showImage = false;
          $scope.showPosts = true;
          $scope.showAccountDetailsFunc();
          expect($scope.showAccountDetails).toBe(true);
          expect($scope.showImage).toBe(true);
          expect($scope.showPosts).toBe(false);
        }); 
      });

    });

  </script>
</html>
