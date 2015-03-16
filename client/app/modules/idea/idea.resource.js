(function() {
    'use strict';

    angular
		.module('ideasBoardApp.idea')
		.factory('IdeaResource', IdeaResource);
		
	function IdeaResource ($resource) {
		return $resource('/ideas');
	}
    

})();