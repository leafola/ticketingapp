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
    //$scope.ticket={id:null,title:'',dateCreated:null,customerId:null,agentId:null,closed:''};
         
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

App.controller('TicketController3', ['$scope', 'TicketService', function($scope, TicketService) {
	var self = this;
    //$scope.ticket={id:null,title:'',dateCreated:null,customerId:null,agentId:null,closed:''};
         
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

App.controller('RestTestController', ['$scope', '$http','TicketService', function( $scope, $http, TicketService ) {
	var self = this;
    //$scope.ticket={id:null,title:'',dateCreated:null,customerId:null,agentId:null,closed:''};
         
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

	'use strict';

	$scope.numberOfRestCalls = 0;

	$scope.restTest = {
		"save": function() {

			$scope.readOnly = true;
		}
	};

	$scope.metawidgetConfig = {

		inspectionResultProcessors: [ function( inspectionResult, mw, toInspect, type, names ) {

			// Shouldn't get called again when we reset 'readOnly'

			$http.get( 'http://localhost:8080/rest/swagger.json' ).then( function( result ) {

				metawidget.util.combineInspectionResults( inspectionResult, result.data );
				$scope.numberOfRestCalls++;
				mw.buildWidgets( inspectionResult );
			} );
		} ]
	};
}]);