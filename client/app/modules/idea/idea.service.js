(function() {
		'use strict';

		angular
				.module('ideasBoardApp.idea')
				.service('IdeaService', IdeaService);
		
		function IdeaService ($q, IdeaResource) {
			
			/* TODO:  
			 * after figuring out how to CRUD the backend,
			   here should be defined all service methods regarding ideas service
			   and not on the controller
			*/

			function getIdeas () {
				return IdeaResource.query();
			}

			function createIdea (newIdea, successHandler, errorHandler) {
				return IdeaResource.save(newIdea, successHandler, errorHandler);
			}

			return {
				getIdeas: getIdeas,
				createIdea: createIdea
			};
		}


})();