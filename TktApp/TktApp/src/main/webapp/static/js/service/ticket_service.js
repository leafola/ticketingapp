'use strict';
 
App.factory('TicketService', ['$http', '$q', function($http, $q){
 
    return {
         
            fetchAllTickets: function() {
                    return $http.get('http://localhost:8080/rest/ticketservice/tickets')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching tickets');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
    
		    fetchTicket: function() {
		    	
		        return $http.get('http://localhost:8080/rest/ticketservice/tickets/1')
		                .then(
		                        function(response){
		                            return response.data;
		                        }, 
		                        function(errResponse){
		                            console.error('Error while fetching tickets');
		                            return $q.reject(errResponse);
		                        }
		                );
		}
    
    		
         
    };
 
}]);