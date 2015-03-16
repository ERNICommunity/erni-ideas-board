(function() {
    'use strict';

    angular
        .module('ideasBoardApp.idea',[
                'angular-hal'
            ])
        .run(function ($rootScope, halClient) {
    		$rootScope.apiRoot = halClient.$get('/ideas/');
		});

})();