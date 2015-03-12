'use strict';

describe('Controller: HomeController', function () {

    // load the controller's module
    beforeEach(module('ideasBoardApp.home'));

    var HomeController,
      scope,
      IdeaService;

    // Initialize the controller and a mock scope
    beforeEach(inject(function ($controller, $rootScope, _IdeaService_) {
        IdeaService = _IdeaService_;
        scope = $rootScope.$new();
        HomeController = $controller('HomeController', {
            $scope: scope
        });
    }));

    //FIXME: implement proper tests! :-)

    it('should attach a list of awesomeThings to the scope', function () {
        
        expect(true).toBe(true);
    
    });
});