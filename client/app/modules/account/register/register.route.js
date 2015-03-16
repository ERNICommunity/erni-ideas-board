(function() {
    'use strict';

    angular
        .module('ideasBoardApp.account.register')
        .config(config);

    config.$inject = ['$stateProvider'];

    function config($stateProvider) {
        $stateProvider
            .state('register', {
                url: '/register',
                templateUrl: 'modules/account/register/register.tpl.html',
                controller: 'RegisterController'
        });
    }
})();