define(['../app', 'services/MatchEndService'], function (footballManager) {

    footballManager.controller("matchEndCtl", function ($scope, MatchEndService) {
        $scope.matches = MatchEndService.getMatches();
        $scope.match = MatchEndService.getMatch();
    });

});
