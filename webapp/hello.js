angular.module('demo', [])
.controller('Hello', function($scope, $http) {
    console.log('tutaj2');
    
    $http.get('http://localhost:8080').
        then(function(response) {
        console.log(response);
            $scope.greeting = response.data;
        });
});