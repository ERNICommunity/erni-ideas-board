(function() {
    'use strict';

    angular
        .module('ideasBoardApp.discussVote')
        .config(config);

    config.$inject = ['$stateProvider'];

    function config($stateProvider) {
        $stateProvider
            .state('discuss-vote', {
                url: '/discuss-vote',
                templateUrl: 'modules/discuss-vote/discuss-vote.tpl.html',
                controller: 'DiscussVoteController'
        });
    }
})();