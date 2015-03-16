(function () {
    'use strict';

    angular
        .module('ideasBoardApp', [
            'ideasBoardApp.home',
            'ideasBoardApp.myIdeas',
            'ideasBoardApp.discussVote',
            'ideasBoardApp.idea',
			'ideasBoardApp.account'
        ]);

})();