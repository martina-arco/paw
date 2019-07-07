define(['footballManager', 'services/PlayerService', 'services/MatchService', 'services/TeamService'], function (footballManager) {

    footballManager.controller("HomeCtl", function ($scope, $window, $location, PlayerService, MatchService, TeamService) {
        TeamService.getTeam().then(function (response) {
            $scope.team = response.data;
        }, function (reason) {
          if (reason.status == 404) {
            $location.url('chooseTeam');
          }
        });

        MatchService.getUpcomingMatch().then(function (response) {
            $scope.upcomingMatch = response.data;
        });

        PlayerService.getPlayers().then(function (response) {
            $scope.players = response.data;
        });

        if($scope.players != null)
            $scope.player = $scope.players[0];

        $scope.labels = [
          $scope.PLAYERFINISHING,
          $scope.PLAYERDEFENDING,
          $scope.PLAYERPASSING,
          $scope.PLAYERGOALKEEPING
        ];

        if($scope.player != null) {
            $scope.data = [
              $scope.player.finishing,
              $scope.player.defense,
              $scope.player.passing,
              $scope.player.goalkeeping
            ];
        }

        $scope.changePlayer = function (id) {
            $scope.players.forEach(function (player) {
                if(player.id === id) {
                  $scope.player = player;
                }
            });

            $scope.data = [
              $scope.player.finishing,
              $scope.player.defense,
              $scope.player.passing,
              $scope.player.goalkeeping
            ];
        };
    });

});
