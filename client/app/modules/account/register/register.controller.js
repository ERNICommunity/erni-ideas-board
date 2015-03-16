(function(){
	'use strict';

    angular
        .module('ideasBoardApp.account.register')
        .controller('RegisterController', RegisterController);
		
		function RegisterController($scope, $http) {
		$scope.register = function(){
			if ($scope.details.password !== $scope.details.confirmPassword) {
                   // Global.showMessage('Passwords do not match.');
                    alert('Passwords do not match.');
                    return;
                }

                $http({
                    method: 'POST',
                    url: 'http://moodyrest.azurewebsites.net/users',
                    headers: {'Content-Type': 'application/json'},
                    data: JSON.stringify($scope.details)})
                    .success(function (data) {
                        alert('User created successfully');//Global.showMessage('User created successfully');
                    })
                    .error(function (data) {
                       alert(data.message);// Global.showMessage(data.message);
                    });
           
		}
    }
   /*  function RegisterController($scope, db, $routeParams, $http, Global) {

            $scope.searchHint = "search in " + $routeParams.tag;


            $scope.title = $routeParams.tag;
            $scope.register = function () {
                if ($scope.details.password !== $scope.details.confirmPassword) {
                    Global.showMessage('Passwords do not match.');
                    //alert("");
                    return;
                }

                $http({
                    method: 'POST',
                    url: 'http://moodyrest.azurewebsites.net/users',
                    headers: {'Content-Type': 'application/json'},
                    data: JSON.stringify($scope.details)})
                    .success(function (data) {
                        Global.showMessage('User created successfully');
                    })
                    .error(function (data) {
                        Global.showMessage(data.message);
                    });
            };
        } */

})();