define(['footballManager', 'services/PlayerService', 'services/MatchService', 'services/TeamService'], function (footballManager) {

    footballManager.controller("HomeCtl", function ($scope, $window, $location, PlayerService, MatchService, TeamService) {
        TeamService.getTeam().then(function (response) {
            $scope.team = response.data;

            if($scope.team == null) {
                $location.url('chooseTeam');
            }
        });

        MatchService.getUpcomingMatch().then(function (response) {
            $scope.upcomingMatch = response.data;
        });

        PlayerService.getPlayers().then(function (response) {
            $scope.players = response.data;
        });

        // $scope.players = [
        //   {name:'hola', id:1, age:20, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   {name:'como', id:2, age:18, position:1, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   {name:'estas', id:3, age:23, position:2, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   {name:'lala', id:4, age:24,position:3, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   {name:'bien', id:5, age:22,position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3}
        // ];

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
