define(['footballManager', 'services/LeagueService'], function (footballManager) {

    footballManager.controller('LeagueCtl', function ($scope, LeagueService) {

        LeagueService.getLeagueInformation().then(function (response) {
          var teams = response.data.teams;

          $scope.league = response.data;
          $scope.teams = [];

          for(var prop in teams) {
              for(var i in teams[prop]) {
                  $scope.teams.push({name: i, points: teams[prop][i]});
              }
          }

        });
    });

});
