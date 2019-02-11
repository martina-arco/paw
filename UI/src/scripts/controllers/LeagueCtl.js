define(['footballManager', 'services/LeagueService', 'services/TeamService', 'services/MatchService'], function (footballManager) {

    footballManager.controller('LeagueCtl', function ($scope, LeagueService, TeamService, MatchService) {
        TeamService.getTeams().then(function (response) {
          $scope.teams = response.data;
        });

        MatchService.getUpcomingMatches().then(function (response) {
          $scope.upcomingMatches = response.data;
        });

        LeagueService.getLeagueInformation().then(function (response) {
          $scope.matchesToPlay = response.data.matchesToPlay;
          $scope.matchesPlayed = response.data.matchesPlayed;
          $scope.league = response.data.league;
        });
    });

});
