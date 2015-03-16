(function() {
		'use strict';

		angular
				.module('ideasBoardApp.idea')
				.service('IdeaService', IdeaService);
		
		function IdeaService (/*$rootScope, $window, halClient*/) {
			
			/* TODO:  
			 * after figuring out how to CRUD the backend,
			   here should be defined all service methods regarding ideas service
			   and not on the controller
			*/

			/*function getIdeas () {
				return ...
			}

			function createIdea (newIdea) {
				return ...
			}

			return {
				getIdeas: getIdeas,
				createIdea: createIdea
			}*/
		}


})();