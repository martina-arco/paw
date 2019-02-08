define(['footballManager', 'services/MatchEndService'], function (footballManager) {

    footballManager.controller("MatchEndCtl", function ($scope, MatchEndService) {
        $scope.matches = MatchEndService.getMatches();
        $scope.match = MatchEndService.getMatch();
    });

});
