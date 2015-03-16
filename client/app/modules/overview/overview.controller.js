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
                description: 'a fooooo!',
                tags: '#bar, #great'
            },
            {
                id: 1,
                name: 'Bill',
                description: 'b fooooo!',
                tags: '#great'
            },
            {
                id: 2,
                name: 'Klaus',
                description: 'c This is a very long description. Well, long enough to test the truncation.',
                tags: '#bar'
            }];
        $scope.predicate = '-id';
        $scope.reverse = true;
    }
})();