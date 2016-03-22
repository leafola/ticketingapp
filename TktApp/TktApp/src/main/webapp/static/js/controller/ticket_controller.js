'use strict';
 
App.controller('TicketController', ['$scope', 'TicketService', function($scope, TicketService) {
          var self = this;
          self.ticket={id:null,title:'',dateCreated:null,customerId:null,agentId:null,closed:''};
          self.tickets=[];
               
          self.fetchAllTickets = function(){
              TicketService.fetchAllTickets()
                  .then(
                               function(d) {
                                    self.tickets = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching Tickets');
                                }
                       );
          };
 
          self.fetchAllTickets();
 
      }]);

App.controller('TicketController2', ['$scope', 'TicketService', function($scope, TicketService) {
	
	var self = this;
         
    self.fetchTicket = function(){
        TicketService.fetchTicket()
            .then(
                         function(d) {
                              $scope.ticket = d;
                         },
                          function(errResponse){
                              console.error('Error while fetching Ticket');
                          }
                 );
    };

    self.fetchTicket();

}]);