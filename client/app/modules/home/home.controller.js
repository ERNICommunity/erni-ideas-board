(function() {
    'use strict';

    angular
        .module('ideasBoardApp.home')
        .controller('HomeController', HomeController);
    
    function HomeController ($scope/*, IdeaService*/) {
        
    	// I'm trying to use this example, but don't seem to work..
    	// https://github.com/LuvDaSun/angular-hal/blob/master/demo/src/app.js

		$scope.submitNewIdea = function () {
			if ($scope.newIdeaForm.$invalid){
				return;
			}
			return $scope.apiRoot.then(function (apiRoot) {
				return apiRoot.$post('ideas', null,  $scope.newIdea);
			});//.then(load);
		};

	}


})();