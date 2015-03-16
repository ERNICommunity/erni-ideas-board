(function () {
    'use strict';

    angular
        .module('ideasBoardApp', [
            'ideasBoardApp.home',
            'ideasBoardApp.createIdea',
            'ideasBoardApp.myIdeas',
            'ideasBoardApp.discussVote',
            'ideasBoardApp.idea'
        ]);

})();