(function() {
    'use strict';

    angular
        .module('ideasBoardApp.overview')
        .config(config);

    config.$inject = ['$stateProvider'];

    function config($stateProvider) {
        $stateProvider
            .state('overview', {
                url: '/overview',
                templateUrl: 'modules/overview/overview.tpl.html',
                controller: 'OverviewController'
        });
    }

})();