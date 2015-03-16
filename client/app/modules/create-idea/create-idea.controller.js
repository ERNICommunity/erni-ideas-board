(function() {
    'use strict';

    angular
        .module('ideasBoardApp.createIdea')
        .controller('CreateIdeaController', CreateIdeaController);
    
    function CreateIdeaController (IdeaService, $state) {
        var vm = this;
    	// TODO please complete

    	vm.publish = function() {
    		window.alert('to be implemented');
    	};
    	
        vm.saveAsDraft = function () {
            if (vm.newIdeaForm.$invalid){
                return;
            }
            
            IdeaService.createIdea(vm.newIdea,
                function (data) { // success
                    console.log('idea created, ', data);
                    $state.go('my-ideas');
                },
                function (error) { // error
                    console.error(error);
                }
            );

        };

    }


})();