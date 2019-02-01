function initializeUpcomingMatches($scope, upcomingMatches) {
    $scope.upcomingMatches = upcomingMatches;
}

function initializeTeams($scope, teams) {
    $scope.teams = teams;
}

function initializeLeague($scope, leagueInformation) {
    $scope.matchesToPlay = leagueInformation.matchesToPlay;
    $scope.matchesPlayed = leagueInformation.matchesPlayed;
    $scope.league = leagueInformation.league;
}

define(['../app', 'services/LeagueService'], function (footballManager) {

    footballManager.controller("leagueCtl", function ($scope, LeagueService) {
        initializeTeams($scope, LeagueService.getTeams());
        initializeUpcomingMatches($scope, LeagueService.getUpcomingMatches());
        initializeLeague($scope, LeagueService.getLeagueInformation());
    });

});
