(function () {
    'use strict';

    angular
        .module('ideasBoardApp.overview')
        .controller('OverviewController', OverviewController);

    OverviewController.$inject = ['$scope'];

    function OverviewController($scope) {

        $scope.ideas = [{
                id: 0,
                name: 'John',
                description: 'fooooo!',
                tags: '#bar, #great'
            },
            {
                id: 1,
                name: 'Bill',
                description: 'fooooo!',
                tags: '#bar, #great'
            },
            {
                id: 2,
                name: 'Klaus',
                description: 'fooooo!',
                tags: '#bar, #great'
            }];
    }
})();