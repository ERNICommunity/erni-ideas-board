(function() {
    'use strict';

    angular
        .module('ideasBoardApp.createIdea')
        .config(config);

    config.$inject = ['$stateProvider'];

    function config($stateProvider) {
        $stateProvider
            .state('create-idea', {
                url: '/create-idea',
                templateUrl: 'modules/my-ideas/create-idea.tpl.html',
                controller: 'CreateIdeaController'
        });

    }

})();