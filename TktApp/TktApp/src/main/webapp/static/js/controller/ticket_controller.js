'use strict';

App.controller('TicketController', [ '$scope', 'TicketService',
		function ($scope, TicketService) {
        var self = this;
        self.ticket = {
            id : null,
            title : '',
            dateCreated : null,
            customerId : null,
            agentId : null,
            closed : ''
        };
        self.tickets = [];

        self.fetchAllTickets = function () {
            TicketService.fetchAllTickets().then(function (d) {
				self.tickets = d;
            }, function (errResponse) {
				console.error('Error while fetching Tickets');
            });
        };

        self.fetchAllTickets();

    } ]);

App.controller('TicketController2', [ '$scope', 'TicketService',
		function ($scope, TicketService) {

        var self = this;

        self.fetchTicket = function () {
            TicketService.fetchTicket().then(function (d) {
				$scope.ticket = d;
            }, function (errResponse) {
				console.error('Error while fetching Ticket');
            });
        };

        self.fetchTicket();

    } ]);

App.controller('TicketController1', [ '$scope', 'TicketService', 'LoggedInService','$http',
		function ($scope, TicketService, LoggedInService, $http) {

    	var self = this;    
	
       self.account = { 
        		id : 0, 
        		username : 'agent1', 
        		password : null, 
        		role : null
        		};
        
        $scope.username = self.account.username;
        $scope.$watch('username', function (newValue, oldValue) { 
        	
        	console.info('Changed!');
        	console.log('Old: ' + oldValue);
        	console.log('New: ' + newValue);
        })
        
        $http.get('http://localhost:8080/rest/accountservice/accounts/account/loggedin')
        	.success(function (result){
        		self.account = result;
        	});
        
        $scope.username = self.account.username;
        
        self.ticket = {
            id : null,
            title : '',
            dateCreated : null,
            customerId : null,
            agentId : null,
            closed : ''
        };
        self.tickets = [];

        self.fetchCustomerTickets = function (username) {
            TicketService.fetchCustomerTickets(username).then(function (d) {
				self.tickets = d;
            }, function (errResponse) {
				console.error('Error while fetching Tickets');
            });
        };

        self.fetchCustomerTickets($scope.username);
    } ]);