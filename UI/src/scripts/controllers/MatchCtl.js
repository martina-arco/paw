define(['footballManager', 'services/MatchService'], function (footballManager) {

    footballManager.controller("MatchCtl", function ($scope, MatchService) {
        MatchService.getMatches().then(function (response) {
            $scope.matches = response.data;
        });
    });

});
