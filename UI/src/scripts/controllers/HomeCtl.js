define(['footballManager', 'services/PlayerService', 'services/MatchService', 'services/TeamService'], function (footballManager) {

    footballManager.controller("HomeCtl", function ($scope, PlayerService, MatchService, TeamService) {
        TeamService.getTeam().then(function (response) {
            $scope.team = response.data;
        });

        MatchService.getUpcomingMatch().then(function (response) {
            $scope.upcomingMatch = response.data;
        });

        PlayerService.getPlayers().then(function (response) {
            $scope.playersList = response.data;
        });

        PlayerService.getPlayerInfo().then(function (response) {
            $scope.currentPlayer = response.data;
        });
    });

});
