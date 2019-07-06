define(['footballManager', 'services/MatchService'], function (footballManager) {

    footballManager.controller("MatchEndCtl", function ($scope, MatchService) {
        MatchService.getUpcomingMatches().then(function (response) {
            $scope.matches = response.data;
        });

        MatchService.getCurrentMatch().then(function (response) {
            $scope.match = response.data;
        });
    });

});
