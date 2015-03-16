(function() {
    'use strict';

    angular
        .module('ideasBoardApp.myIdeas')
        .config(config);

    config.$inject = ['$stateProvider'];

    function config($stateProvider) {
        $stateProvider
            .state('my-ideas', {
                url: '/my-ideas',
                templateUrl: 'modules/my-ideas/my-ideas.tpl.html',
                controller: 'MyIdeasController',
                controllerAs: 'myIdeas'
        });

    }

})();