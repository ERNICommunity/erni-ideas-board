(function() {
    'use strict';

    angular
        .module('ideasBoardApp.myIdeas')
        .controller('MyIdeasController', MyIdeasController);
    
    function MyIdeasController (IdeaService) {
        var vm = this;
        vm.predicate = '-id';
        vm.reverse = true;

        //FIXME: only get the ideas of the logged user

        IdeaService.getIdeas(/*user login here*/).$promise.then(function(ideas) {
            vm.ideas = ideas;
        });
        
    }


})();