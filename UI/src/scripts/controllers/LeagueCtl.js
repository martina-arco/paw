define(['footballManager', 'services/LeagueService'], function (footballManager) {

    footballManager.controller('LeagueCtl', function ($scope, LeagueService) {

        LeagueService.getLeagueInformation().then(function (response) {
          var teams = response.data.teams;

          $scope.matchesToPlay = response.data.matchesToPlay;
          $scope.matchesPlayed = response.data.matchesPlayed;
          $scope.league = response.data.league;
          $scope.upcomingMatches = response.data.upcomingMatches;
          $scope.teams = [];

          for(var prop in teams) {
              for(var i in teams[prop]) {
                  $scope.teams.push({name: i, points: teams[prop][i]});
              }
          }

        });
    });

});
