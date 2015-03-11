'use strict';

describe('Controller: HomeController', function () {

    // load the controller's module
    beforeEach(module('ideasBoardApp.home'));

    var HomeController,
      scope;

    // Initialize the controller and a mock scope
    beforeEach(inject(function ($controller, $rootScope) {
        scope = $rootScope.$new();
        HomeController = $controller('HomeController', {
            $scope: scope
        });
    }));

    it('should attach a list of awesomeThings to the scope', function () {
        
        expect(scope.awesomeThings.length).toBe(3);
    
    });
});