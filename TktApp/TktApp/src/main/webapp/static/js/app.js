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

myApp
		.controller(
				'mainController',
				function($scope, $http, getUser, $log) {
					
					$scope.isCollapsed = true;
					$scope.newPostMessage = 'Add New Post';

					$scope.disabled = false;

					$scope.disable = function() {
						$scope.disabled = !scope.disabled;
						return $scope.disabled;
					};
					
					$scope.showAccountDetails = true;
					$scope.showEdit = false;
					$scope.showImage = true;
					$scope.showPosts = false;

					$scope.showAccountDetailsFunc = function() {
						$scope.showAccountDetails = true;
						$scope.showImage = true;
						$scope.showPosts = false;
					};
					
					$scope.checkIfFirstEverPost = function() {
						if ($scope.tickets.length === 1 && $scope.posts.length === 0) {
							$scope.firstEverPost = true;
						} else {
							$scope.firstEverPost = false;
						}
					};
					
					$scope.postSentFunc = function() {
						if ($scope.posts.length >= 1 && 
								$scope.posts[$scope.posts.length - 1].author === $scope.account.id ){
							console.log("Account id = " + $scope.account.id);
							console.log("Last Post Account id = " + $scope.posts[$scope.posts.length - 1].author);
							$scope.postSent = true;
						} else {
							$scope.postSent = false;
						}
					};
					
					$scope.showEditFunc = function() {
						$scope.showEdit = !$scope.showEdit;
					};

					$scope.showTicketsFunc = function() {
						$scope.showAccountDetails = false;
						$scope.checkForTickets();
					};
					
					$scope.checkIfFirstEverPost = function() {
						if ($scope.tickets.length === 1 && $scope.posts.length === 0) {
							$scope.firstEverPost = true;
						} else {
							$scope.firstEverPost = false;
						}
					};

					$scope.checkForTickets = function() {
						if ($scope.tickets.length === 0) {
							$scope.noTickets = true;
							$scope.showImage = true;
							$scope.showPosts = false;
						} else if ($scope.tickets.length > 0 && $scope.showAccountDetails === true){
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
					
					
					
					$scope.checkForPosts = function() {
						if ($scope.posts.length === 0) {
							$scope.isCollapsed = false;
						} else {
							$scope.isCollapsed = true;
						}
						console.log("Posts array size"
								+ $scope.posts.length);
					};
					
					$scope.changeIsCollapsed = function() {
						$scope.isCollapsed = false;
					};

					$scope.$watch(function(scope) {
						return scope.isCollapsed
					}, function(newValue) {
						if (newValue===true) {
							$scope.newPostMessage = 'Add New Post';
						} else {
							$scope.newPostMessage = 'Hide Post Area';
						}
					});

					$scope.select = function(item) {
						$scope.selected = item;
						console.log($scope.selected);
					};

					$scope.isActive = function(item) {
						return $scope.selected === item;
					};
					
					$log.debug("showEdit: " + $scope.showEdit);
					$log.info("showAccountDetails: " + $scope.showAccountDetails);
					$log.warn("showImage: " + $scope.showImage);
					$log.error("showPosts: " + $scope.showPosts);
					
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
							self.getAgent();

						});
					};

					self.getLoggedInUser();

					// Get Tickets
					self.getTickets = function() {
						if ($scope.account.role === "ROLE_USER"){
							$scope.getCustomerTickets();
						} else {
							$scope.getAgentTickets();
						}
					}
					
					$scope.getCustomerTickets = function(){
						$http.get('http://localhost:8080/rest/ticketservice/tickets/customer/username/'
												+ $scope.account.username)
								.success(
										function(d) {
											$scope.tickets = d;
											$scope.checkForTickets();

											$scope.selected = $scope.tickets[$scope.tickets.length - 1];
											$scope.newTicket = $scope.tickets[$scope.tickets.length - 1];
											if ($scope.noTickets===false){
											self.getPosts($scope.newTicket.id,
													$scope.newTicket.title);
											}
										}).error(function(data, status) {

									console.log(data);

								});
					};
					
					$scope.getAgentTickets = function(){
						$http.get('http://localhost:8080/rest/ticketservice/tickets/agent/username/'
												+ $scope.account.username)
								.success(
										function(d) {
											$scope.tickets = d;
											$scope.checkForTickets();

											$scope.selected = $scope.tickets[$scope.tickets.length - 1];
											$scope.newTicket = $scope.tickets[$scope.tickets.length - 1];
											if ($scope.noTickets===false){
											self.getPosts($scope.newTicket.id,
													$scope.newTicket.title);
											}
										}).error(function(data, status) {

									console.log(data);

								});
					};

					// Create Ticket
					self.createTicket = function(title) {
						self.ticket.title = title;
						self.ticket.customerId = $scope.customer.id;
						$http
								.post(
										'http://localhost:8080/rest/ticketservice/tickets/ticket/',
										self.ticket).success(
										function(response) {
											$scope.ticket.title = null;
											self.getTickets();
											return response.data;

										}).error(function(data, status) {

									console.log(data);

								});
					};

					// Get Customer
					self.getCustomer = function() {
						$http.get(
								'http://localhost:8080/rest/customerservice/customers/account/'
										+ $scope.account.id).success(
								function(d) {
									$scope.customer = d;
								}).error(function(data, status) {

							console.log(data);

						});
					};
					
					// Get Agent
					self.getAgent = function() {
						$http.get(
								'http://localhost:8080/rest/agentservice/agents/account/'
										+ $scope.account.id).success(
								function(d) {
									$scope.agent = d;
								}).error(function(data, status) {

							console.log(data);

						});
					};

					// Update Customer
					self.updateCustomer = function() {
						$http
								.put(
										'http://localhost:8080/rest/customerservice/customers/customer/',
										$scope.customer).success(function(d) {
								}).error(function(data, status) {

									console.log(data);

								});
					};
					
					// Update Agent
					self.updateAgent = function() {
						$http
								.put(
										'http://localhost:8080/rest/agentservice/agents/agent/',
										$scope.agent).success(function(d) {
								}).error(function(data, status) {

									console.log(data);

								});
					};

					// Refresh Posts
					self.refreshPosts = function() {
						$http.get(
								'http://localhost:8080/rest/postservice/posts/ticket/'
										+ $scope.post.ticketId).success(
								function(d) {
									$scope.posts = d;
								}).error(function(data, status) {

							console.log(data);

						});
					};

					// Get Posts
					self.getPosts = function(id, title) {
						$scope.postTitle = title;
						self.post.ticketId = id;
						$http.get(
								'http://localhost:8080/rest/postservice/posts/ticket/'
										+ id).success(function(d) {
							$scope.posts = d;
							$scope.checkForPosts();
							$scope.checkIfFirstEverPost();
							$scope.postSentFunc();
							
						}).error(function(data, status) {

							console.log(data);

						});
					};

					$scope.makeUrl = function(id) {
						return $scope.url = "#" + id;
					}

					// Delete Ticket
					self.deleteTicket = function(id) {

						// ['delete'] in square brackets because eclipse was
						// giving a syntax
						// error - an eclipse bug
						$http['delete'](
								'http://localhost:8080/rest/ticketservice/tickets/'
										+ id).success(function(response) {
							self.getTickets();
							return response.data;
						}).error(function(data, status) {

							console.log(data);

						});
					};

					// Create Post
					self.createPost = function(body) {
						self.post.body = body;
						self.post.author = $scope.account.id;
						
						if ($scope.account.role === "ROLE_USER"){
						self.post.authorName = $scope.customer.fname + ' ' + $scope.customer.lname;
						} else {
							self.post.authorName = $scope.agent.fname + ' ' + $scope.agent.lname;	
						};
						
						$http
								.post(
										'http://localhost:8080/rest/postservice/posts/post/',
										self.post).success(function(response) {
									$scope.post.body = null;
									self.getPosts(self.post.ticketId);
									return response.data;
								}).error(function(data, status) {

									console.log(data);

								});
					};

					// Logout
					self.logout = function() {
						$http
								.post(
										'http://localhost:8080/j_spring_security_logout')
								.success(function(response) {
									return response.data;
								}).error(function(data, status) {

									console.log(data);

								});
					};
					
					// Get Author Name
					/*self.getAuthor = function(authorId) {
						$http.get('http://localhost:8080/rest/customerservice/customers/account/'
										+ authorId).success(function(d) {
							$scope.author = d;
							return $scope.author.fname;
						}).error(function(data, status) {

							console.log(data);

						});
					};*/
					
					// Function for setting conversation bubble colours
					$scope.setConversationClass = function(authorId) {
						if (authorId == $scope.account.id) {
							return {
								"padding" : "20px",
								"margin-bottom" : "10px",
								"margin-right" : "50px",
								"border-radius" : "25px",
								"background" : "#8b9dc3"
							}
						} else {
							return {
								"padding" : "20px",
								"margin-bottom" : "10px",
								"margin-left" : "50px",
								"border-radius" : "25px",
								"background" : "#f7f7f7",
								"border-color" : "#666666",
								"border-style" : "solid",
								"border-width" : "1px"
							}
						}
					};
					
					
					// Register New Account
					self.register = function(username, password) {
						self.account.username = username;
						self.account.role = 'ROLE_USER';
						self.account.password = password;
						$http
								.post(
										'http://localhost:8080/accountservice/accounts/account',
										self.account).success(
										function(response) {
											return response.data;
										}).error(function(data, status) {

									console.log(data);

								});
					};
					
					
					/*self.register2 = function() {
						$scope.test = 'worked';
					};

					$scope.testFunction = function() {
						return "worked";
					};*/

				});