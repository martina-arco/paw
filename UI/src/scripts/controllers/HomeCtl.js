define(['../app'], function (footballManager) {

    footballManager.controller("chooseTeamCtl", function ($scope) {
        $scope.team = "team"; //getTeam
        $scope.match = {team1: "team1", team2: "team2", stadium: "stadium"}; //getUpcomingMatch
        $scope.players = "players"; //getPlayers
        $scope.player = "player"; //getCurrentPlayer
    });

});
