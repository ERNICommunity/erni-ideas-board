(function() {
    'use strict';

    angular
        .module('ideasBoardApp.createIdea')
        .controller('CreateIdeaController', CreateIdeaController);
    
    function CreateIdeaController ($scope) {

    	// TODO please complete

    	$scope.publish = function() {
    		window.alert('publish');
    	};

    	$scope.saveAsDraft = function() {
    		window.alert('saveAsDraft');
    	};
    }


})();