define(['footballManager', 'services/MatchService'], function (footballManager) {

    footballManager.controller("MatchCtl", function ($scope, MatchService) {
        $scope.matches = MatchService.getMatches();
    });

});
