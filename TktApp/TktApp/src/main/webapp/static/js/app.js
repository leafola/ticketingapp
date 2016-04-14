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
					
					
					// Show and Hide Functions
					// *******************************
					
					//NAV BUTTONS
					
					// Show account details section
					$scope.showAccountDetailsFunc = function() {
						$log.debug("Calling showAccountDetailsFunc");
						
						$scope.showAccountDetails = true;
						$scope.showImage = true;
						$scope.showPosts = false;
					};
					
					// Show the tickets section
					$scope.showTicketsFunc = function() {
						$log.debug("Calling showTicketsFunc");
						$scope.showAccountDetails = false;
						$scope.checkForTickets();
						$scope.showArchive = false;
						$scope.selectTopTicket(false);
					};
					
					// Show all tickets function
					$scope.showAllTicketsFunc = function() {
						$log.debug("Calling showAllTicketsFunc");
						$scope.showAccountDetails = false;
						self.getAllTickets();
					};
					
					// Show Archive section
					$scope.showArchiveFunc = function() {
						$log.debug("Calling showArchiveFunc");
						$scope.showAccountDetails = false;
						$scope.checkForTickets();
						$scope.showArchive = true;
						$scope.selectTopTicket(true);
					};
					
					// Select top ticket so that it is highlighted with correct colour
					$scope.selectTopTicket = function(answer) {
						$log.debug("Calling selectTopTicket()");
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
						$log.debug("Calling showEditFunc");
						$scope.showEdit = !$scope.showEdit;
					};
					
					
					// Check if ticket status is open or closed
					$scope.checkStatus = function(status) {
						$log.debug("Calling checkStatus()");
						if (status) {
							return 'closed';
						} else {
							return 'open';
						}
							
					};
					
					// Check if this is users first ever post
					$scope.checkIfFirstEverPost = function() {
						$log.debug("Calling checkIfFirstEverPost");
						if ($scope.tickets.length === 1
								&& $scope.posts.length === 0) {
							$scope.firstEverPost = true;
						} else {
							$scope.firstEverPost = false;
						}
					};
					
					// Check if user has submitted a new post
					$scope.postSentFunc = function() {
						$log.debug("Calling postSentFunc");
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
						$log.debug("Calling checkForTickets()");
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
						$log.debug("Calling checkForPosts");
						if ($scope.posts.length === 0) {
							$scope.isCollapsed = false;
						} else {
							$scope.isCollapsed = true;
						}
						console.log("Posts array size" + $scope.posts.length);
					};
					
					// Function to change isCollapsed value to false
					$scope.changeIsCollapsed = function() {
						$log.debug("Calling changeIsCollapsed()");
						$scope.isCollapsed = false;
					};
					
					// Function to watch the isCollapsed value. Change button value on result.
					$scope.$watch(function(scope) {
						return scope.isCollapsed
					}, function(newValue) {
						if (newValue === true) {
							$scope.newPostMessage = 'Add New Post';
						} else {
							$scope.newPostMessage = 'Hide Post Area';
						}
					});
					
					// Function to make ticket item selected on click.
					$scope.select = function(item) {
						$log.debug("Calling select()");
						$scope.selected = item;
						console.log($scope.selected);
					};
					
					// Function to return boolean based on wheter passed item is equal to selected item
					$scope.isActive = function(item) {
						$log.debug("Calling isActive()");
						return $scope.selected === item;
					};
					
					// Function for setting conversation bubble colours
					$scope.setConversationClass = function(authorId) {
						$log.debug("Calling setConversationClass");
							if (authorId == $scope.account.id) {
								return {
									"margin-right": "50px",
									"background" : "#8b9dc3"
								}
							} else {
								return {
									"margin-left": "50px",
									"background" : "#f7f7f7",
									"border-color" : "#666666",
									"border-style" : "solid",
									"border-width" : "1px"
								}
							}
					};
					
					// Console Feedback
					$log.debug("showEdit: " + $scope.showEdit);
					$log.debug("showAccountDetails: "
							+ $scope.showAccountDetails);
					$log.debug("showImage: " + $scope.showImage);
					$log.debug("showPosts: " + $scope.showPosts);
					
					// ***********************************************************
					// Model OBJECTS *********************************************
					// ***********************************************************
					
					// TICKET
					self.ticket = {
						id : null,
						title : '',
						dateCreated : '',
						customerId : '',
						agentId : '',
						closed : 0
					};
					
					// POST
					self.post = {
						id : null,
						body : '',
						author : null,
						authorName : null,
						dateCreated : '',
						ticketId : null
					};
					
					// ACCOUNT
					self.account = {
						id : null,
						username : 'not set',
						password : '',
						role : ''
					};
					
					function account(username, password, role) {
						this.id = null;
					    this.username = username;
					    this.password = password;
					    this.role = role;
					}

					// ***********************************************************
					// HTTP Functions for requesting data from REST API **********
					// ***********************************************************
					
					
					// Get Logged In User (Spring Security Request)
					self.getLoggedInUser = function() {
						$log.debug("Calling getLoggedInUser()");
						getUser.getLoggedInUser().success(function(account) {
							$scope.account = account;
							console.log($scope.account.id);
							
							if($scope.account.role === "ROLE_SUPER"){
								self.getAllAccounts();
								self.getAllTickets();
							} else {
							self.getTickets();
							self.getCustomer();
							self.getAgent();
							}

						});
					};
					
					// Call this method on start-up
					self.getLoggedInUser();
					
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

					
					// TICKETS HTTP FUNCTIONS ******************************************
					
					// Get All Tickets
					self.getAllTickets = function() {
						$log.debug("Calling getAllTickets()");
							$http.get(
									'http://localhost:8080/rest/ticketservice/tickets').success(
									function(d) {
										$scope.tickets = d;
									}).error(function(data, status) {

								console.log(data);

							});
						};
					
					// Get Tickets by Customer or Agent
					self.getTickets = function() {
						$log.debug("Calling getTickets()");
						if ($scope.account.role === "ROLE_USER") {
							$scope.getCustomerTickets();
						} else {
							$scope.getAgentTickets();
						}
					}
					
					// Get Tickets by Customer Username
					$scope.getCustomerTickets = function() {
						$log.debug("Calling getCustomerTickets");
						$http
								.get(
										'http://localhost:8080/rest/ticketservice/tickets/customer/username/'
												+ $scope.account.username)
								.success(
										function(d) {
											$scope.tickets = d;
											$scope.checkForTickets();

											$scope.selected = $scope.tickets[$scope.tickets.length - 1];
											$scope.newTicket = $scope.tickets[$scope.tickets.length - 1];
											if ($scope.noTickets === false) {
												self.getPosts(
														$scope.newTicket.id,
														$scope.newTicket.title);
											}
										}).error(function(data, status) {

									console.log(data);

								});
					};
					
					
					// Get Tickets by Agent username
					$scope.getAgentTickets = function() {
						$log.debug("Calling getAgentTickets()");
						$http
								.get(
										'http://localhost:8080/rest/ticketservice/tickets/agent/username/'
												+ $scope.account.username)
								.success(
										function(d) {
											$scope.tickets = d;
											$scope.checkForTickets();

											$scope.selected = $scope.tickets[$scope.tickets.length - 1];
											$scope.newTicket = $scope.tickets[$scope.tickets.length - 1];
											if ($scope.noTickets === false) {
												self.getPosts(
														$scope.newTicket.id,
														$scope.newTicket.title);
											}
										}).error(function(data, status) {

									console.log(data);

								});
					};

					// Create Ticket
					self.createTicket = function(title) {
						$log.debug("Calling createTicket()");
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
					
					// Update Ticket 
					self.updateTicket = function(t) {
						$log.debug("Calling updateTicket()");
						$scope.selectTopTicket(!t.closed);
						console.log("Ticket: " + t.title);
						$http.put('http://localhost:8080/rest/ticketservice/tickets/ticket/', t)
						.success(
										function(response) {
											//self.ticket.title = null;
											//self.getTickets();
											return response.data;

										}).error(function(data, status) {

									console.log(data);

								});
					};
					
					// Delete Ticket
					self.deleteTicket = function(id) {
						$log.debug("Calling deleteTicket()");

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
					
					// CUSTOMER HTTP FUNCTIONS ***********************************
					
					// Get Customer
					self.getCustomer = function() {
						$log.debug("Calling getCustomer()");
						$http.get(
								'http://localhost:8080/rest/customerservice/customers/account/'
										+ $scope.account.id).success(
								function(d) {
									$scope.customer = d;
								}).error(function(data, status) {

							console.log(data);

						});
					};
					
					// Update Customer
					self.updateCustomer = function() {
						$log.debug("Calling updateCustomer()");
						$http
								.put(
										'http://localhost:8080/rest/customerservice/customers/customer/',
										$scope.customer).success(function(d) {
								}).error(function(data, status) {

									console.log(data);

								});
					};
					
					// AGENT HTTP FUNCTIONS *********************************************
					
					// Get Agent
					self.getAgent = function() {
						$log.debug("Calling getAgent()");
						$http.get(
								'http://localhost:8080/rest/agentservice/agents/account/'
										+ $scope.account.id).success(
								function(d) {
									$scope.agent = d;
								}).error(function(data, status) {

							console.log(data);

						});
					};

					// Update Agent
					self.updateAgent = function() {
						$log.debug("Calling updateAgent()");
						$http
								.put(
										'http://localhost:8080/rest/agentservice/agents/agent/',
										$scope.agent).success(function(d) {
								}).error(function(data, status) {

									console.log(data);

								});
					};
					
					// POSTS HTTP FUNCTIONS ******************************************
					
					// Refresh Posts
					self.refreshPosts = function() {
						$log.debug("Calling refreshPosts()");
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
						$log.debug("Calling getPosts()");
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
					
					
					// Create Post
					self.createPost = function(body) {
						$log.debug("Calling createPost");
						self.post.body = body;
						self.post.author = $scope.account.id;

						if ($scope.account.role === "ROLE_USER") {
							self.post.authorName = $scope.customer.fname + ' '
									+ $scope.customer.lname;
						} else {
							self.post.authorName = $scope.agent.fname + ' '
									+ $scope.agent.lname;
						}
						;

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

					// ACCOUNT HTTP FUNCTIONS *********************************
					
					// Register New Account
					self.register = function(username, password) {
						$log.debug("Calling register()");
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
					
					self.getAllAccounts = function() {
						$log.debug("Calling getAllAccounts()");
						$http
								.get(
										'http://localhost:8080/rest/accountservice/accounts').success(
										function(response) {
											$scope.accounts = response;
											return response.data;
										}).error(function(data, status) {

									console.log(data);

								});
					};
					
					self.createAccount = function(username, password, role) {
						$log.debug("Calling createAccount()");
						tempAcc = new account(username,password,role);
						$http
								.post(
										'http://localhost:8080/rest/accountservice/accounts/account', tempAcc).success(
										function(response) {
											self.getAllAccounts();
											return response.data;
										}).error(function(data, status) {

									console.log(data);

								});
					};
					
					

				});