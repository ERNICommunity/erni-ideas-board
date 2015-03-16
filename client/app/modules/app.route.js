(function() {
    'use strict';

    angular
        .module('ideasBoardApp')
        .config(config);

    config.$inject = ['$urlRouterProvider'];

    function config($urlRouterProvider) {
       $urlRouterProvider.otherwise('/');
    }

})();