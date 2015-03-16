(function() {
    'use strict';

    angular
        .module('ideasBoardApp.account.login')
        .config(config);

    config.$inject = ['$stateProvider'];

    function config($stateProvider) {
        $stateProvider
            .state('login', {
                url: '/login',
                templateUrl: 'modules/account/login/login.tpl.html',
                controller: 'LoginController'
        });

    }

})();