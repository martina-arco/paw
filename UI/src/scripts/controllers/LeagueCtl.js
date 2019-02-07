define(['footballManager', 'services/LeagueService'], function (footballManager) {

    footballManager.controller("leagueCtl", function ($scope, LeagueService) {
        LeagueService.getTeams().then(function (response) {
          $scope.teams = response.data;
        });

        LeagueService.getUpcomingMatches().then(function (response) {
          $scope.upcomingMatches = response.data;
        });

        LeagueService.getLeagueInformation().then(function (response) {
          $scope.matchesToPlay = response.data.matchesToPlay;
          $scope.matchesPlayed = response.data.matchesPlayed;
          $scope.league = response.data.league;
        });
    });

});
