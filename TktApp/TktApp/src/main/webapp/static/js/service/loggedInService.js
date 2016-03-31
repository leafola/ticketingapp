App.factory('LoggedInService', function($http) {
    return {
         getUserName: function() {
             return $http.get('http://localhost:8080/rest/accountservice/accounts/account/loggedin');
         }
     };
});