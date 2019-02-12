define(['footballManager', 'services/MatchService'], function (footballManager) {

    footballManager.controller("MatchEndCtl", function ($scope, MatchService) {
        MatchService.getMatches().then(function (response) {
            $scope.matches = response.data;
        });

        MatchService.getMatch().then(function (response) {
            $scope.match = response.data;
        });
    });

});
