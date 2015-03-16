(function(){
	'use strict';

    angular
        .module('ideasBoardApp.account.login')
        .controller('LoginController', LoginController);
    
    function LoginController ($scope, $rootScope, db, $routeParams, $http, $cookies, Global) {

            $scope.setUserProfileInViewsModel = function () {
                $scope.profile = angular.fromJson($cookies.UserCredential);
            };

            /*set defaults based on user credentials cookie*/
            if (angular.isUndefined($cookies.UserCredential) == false) {
//                $scope.setUserProfileInViewsModel();
                $rootScope.loggedUser = $cookies.UserCredential;
            } else {
                $rootScope.loggedUser = null;
                $scope.profile = null;
            }

            $scope.logout = function () {
                $scope.profile = undefined;
                $cookies.UserCredential = undefined;
                $rootScope.loggedUser = null;
                changeLocation('/', false);
            };

            $scope.showUserName = function () {
                if ($rootScope.loggedUser) {
                    var loggedUser = JSON.parse($rootScope.loggedUser);
                    return loggedUser.username;
                } else {
                    return 'Login';
                }
            };

            //be sure to inject $scope and $location
            changeLocation = function (url, forceReload) {
                $scope = $scope || angular.element(document).scope();
                if (forceReload || $scope.$$phase) {
                    window.location.href = url;
                }
                else {
                    //only use this if you want to replace the history stack
                    //$location.path(url).replace();

                    //this this if you want to change the URL and add it to the history stack
                    $location.path(url);
                    $scope.$apply();
                }
            };

            $scope.login = function () {


                $http({ method: 'GET', url: 'http://moodyrest.azurewebsites.net/users/' + $scope.credentials.username + '/' + $scope.credentials.password })
                    .success(function (data) {
                        $cookies.UserCredential = JSON.stringify(data);
                        $scope.setUserProfileInViewsModel();
                        var millisecondsToWait = 500;
                        setTimeout(function () {
                            window.location.href = '/';
                        }, millisecondsToWait);

                    })
                    .error(function (data) {
                        Global.showMessage('login error');
//
                    });
            };

            $scope.register = function () {
                if ($scope.details.password !== $scope.details.confirmPassword) {
//
                    Global.showMessage("Passwords do not match.");
                    return;
                }
                Notifier.success('Creating user...');
                $http({
                    method: 'POST',
                    url: 'http://moodyrest.azurewebsites.net/users',
                    headers: {'Content-Type': 'application/json'},
                    data: JSON.stringify($scope.details)})
                    .success(function (data) {
                        Notifier.success('Registration Complete.');
                        $cookies.UserCredential = JSON.stringify(data);
                        $scope.setUserProfileInViewsModel();
                        window.location.href = '/';
                        Notifier.success('Registration Complete.');

                    })
                    .error(function (data) {
                        Global.showMessage(data.message);
                    });
            };
        }

})();