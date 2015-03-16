(function () {
    'use strict';

    angular
        .module('ideasBoardApp.overview')
        .controller('OverviewController', OverviewController);

    OverviewController.$inject = ['IdeaService'];

    function OverviewController(IdeaService) {
        var vm = this;
        vm.predicate = '-id';
        vm.reverse = true;
        
        IdeaService.getIdeas().$promise.then(function(ideas) {
            vm.ideas = ideas;
        });
        
    }
})();