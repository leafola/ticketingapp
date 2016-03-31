var myApp = angular.module('myApp', [ 'ui.bootstrap' ]);

myApp
		.factory(
				'getUser',
				function($http) {
					return {
						getLoggedInUser : function() {
							var url = "http://localhost:8080/rest/accountservice/accounts/account/loggedin";
							return $http.get(url);
						}
					};
				});

myApp.controller('mainController', function($scope, $http, getUser) {

	$scope.isCollapsed = true;
	$scope.newPostMessage = 'Add New Post';
	
	$scope.changeIsCollapsed = function(){
		$scope.isCollapsed = false;	
	};
	
	$scope.$watch(function(scope) {
		return scope.isCollapsed
	}, function(newValue) {
		if (newValue) {
			$scope.newPostMessage = 'Add New Post';
		} else {
			$scope.newPostMessage = 'Hide Post Area';
		}
	});
	
	 $scope.select= function(item) {
	        $scope.selected = item; 
	 };

	 $scope.isActive = function(item) {
	        return $scope.selected === item;
	 };
	 
	 

	var self = this;

	self.ticket = {
		id : null,
		title : '',
		dateCreated : '',
		customerId : '',
		agentId : '',
		closed : 0
	};

	self.post = {
		id : null,
		body : '',
		author : null,
		authorName : null,
		dateCreated : '',
		ticketId : null
	};

	self.account = {
		account_id : null,
		username : 'not set',
		password : '',
		role : ''
	};

	// Get Logged In User
	self.getLoggedInUser = function() {
		getUser.getLoggedInUser().success(function(account) {
			$scope.account = account;
			console.log($scope.account.id);

			self.getTickets();
			self.getCustomer();

		});
	};

	self.getLoggedInUser();

	// Get Tickets
	self.getTickets = function() {
		$http.get(
				'http://localhost:8080/rest/ticketservice/tickets/customer/username/'
						+ $scope.account.username).success(function(d) {
			$scope.tickets = d;
			$scope.newTicket = $scope.tickets[$scope.tickets.length - 1];
			self.getPosts($scope.newTicket.id, $scope.newTicket.title);
		}).error(function(data, status) {

			console.log(data);

		});
	};

	// Get Customer
	self.getCustomer = function() {
		$http.get(
				'http://localhost:8080/rest/customerservice/customers/account/'
						+ $scope.account.id).success(function(d) {
			$scope.customer = d;
		}).error(function(data, status) {

			console.log(data);

		});
	};

	// Get Posts
	self.getPosts = function(id, title) {
		$scope.postTitle = title;
		self.post.ticketId = id;
		$http.get('http://localhost:8080/rest/postservice/posts/ticket/' + id)
				.success(function(d) {
					$scope.posts = d;
				}).error(function(data, status) {

					console.log(data);

				});
	};
	
	$scope.makeUrl = function(id){
		return $scope.url = "#" + id;
	}

	// Test Function
	self.deleteTicket = function(id) {

		// ['delete'] in square brackets because eclipse was giving a syntax
		// error - an eclipse bug
		$http['delete'](
				'http://localhost:8080/rest/ticketservice/tickets/' + id)
				.success(function(response) {
					self.getTickets();
					return response.data;
				}).error(function(data, status) {

					console.log(data);

				});
	};

	// Create Ticket
	self.createTicket = function(title) {
		self.ticket.title = title;
		self.ticket.customerId = $scope.customer.id;
		$http.post('http://localhost:8080/rest/ticketservice/tickets/ticket/',
				self.ticket).success(function(response) {
			self.getTickets();
			$scope.changeIsCollapsed();
			return response.data;
			
		}).error(function(data, status) {

			console.log(data);

		});
	};

	// Create Post
	self.createPost = function(body) {
		self.post.body = body;
		self.post.author = $scope.account.id;
		self.post.authorName = $scope.customer.fname + ' ' + $scope.customer.lname;
		$http.post('http://localhost:8080/rest/postservice/posts/post/',
				self.post).success(function(response) {
			self.getPosts(self.post.ticketId);
			return response.data;
		}).error(function(data, status) {

			console.log(data);

		});
	};

	self.logout = function() {
		$http.post('http://localhost:8080/j_spring_security_logout').success(
				function(response) {
					return response.data;
				}).error(function(data, status) {

			console.log(data);

		});
	};

	self.getAuthor = function(authorId) {
		$http.get(
				'http://localhost:8080/rest/customerservice/customers/account/'
						+ authorId).success(function(d) {
			$scope.author = d;
			return $scope.author.fname;
		}).error(function(data, status) {

			console.log(data);

		});
	};

	$scope.setConversationClass = function(authorId) {
		if (authorId == $scope.account.id) {
			return {
				"padding" : "20px",
				"margin-bottom" : "10px",
				"margin-right" : "50px",
				"border-radius" : "25px",
				"background" : "#73AD21"
			}
		} else {
			return {
				"padding" : "20px",
				"margin-bottom" : "10px",
				"margin-left" : "50px",
				"border-radius" : "25px",
				"background" : "#4887c1"
			}
		}
	};

	self.register = function(username, password) {
		self.account.username = username;
		self.account.role = 'ROLE_CUSTOMER';
		self.account.password = password;
		$http.post('http://localhost:8080/accountservice/accounts/account',
				self.account).success(function(response) {
			return response.data;
		}).error(function(data, status) {

			console.log(data);

		});
	};

	self.register2 = function() {
		$scope.test = 'worked';
	};

	$scope.testFunction = function() {
		return "worked";
	};

});