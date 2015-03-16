(function () {
    'use strict';

    angular
        .module('ideasBoardApp.formatting')
        .filter('truncate', TruncateFilter);

    function TruncateFilter() {

        return function (input, charCount) {
            var output = input;
            if (output.length > charCount) {
                output = output.substr(0, charCount) + '...';
            }
            return output;
        };

    }


})();