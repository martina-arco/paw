define(['footballManager', 'services/PlayerService', 'services/MatchService', 'services/TeamService'], function (footballManager) {

    footballManager.controller("HomeCtl", function ($scope, $window, $location, PlayerService, MatchService, TeamService) {
        TeamService.getTeam().then(function (response) {
            $scope.team = response.data;
        });

        MatchService.getUpcomingMatch().then(function (response) {
            $scope.upcomingMatch = response.data;
        });

        PlayerService.getPlayers().then(function (response) {
            $scope.playersList = response.data;
        });

        $scope.player = {name:'marti', age: 18, salary: 1000, value: 10000,
          finishing:1, defending:2, passing:3, goalKeeping:0};

        $scope.labels = [
          $scope.PLAYERFINISHING,
          $scope.PLAYERDEFENDING,
          $scope.PLAYERPASSING,
          $scope.PLAYERGOALKEEPING
        ]
        ;
        $scope.data = [
          $scope.player.finishing,
          $scope.player.defending,
          $scope.player.passing,
          $scope.player.goalKeeping
        ];

        $scope.playMatch = function () {
          $location.url('/match');
          // $location.replace();
        }
    });

});
