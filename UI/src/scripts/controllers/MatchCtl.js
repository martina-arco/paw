define(['../app', 'services/MatchService'], function (footballManager) {

    footballManager.controller("matchCtl", function ($scope, MatchService) {
        $scope.matches = MatchService.getMatches();
    });

});
