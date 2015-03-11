(function() {
    'use strict';

    angular
        .module('ideasBoardApp.home')
        .controller('HomeController', HomeController);
    
    function HomeController ($scope) {
        $scope.awesomeThings = [
          'HTML5 Boilerplate',
          'AngularJS',
          'Karma'
        ];
    }


})();