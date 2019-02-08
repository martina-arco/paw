define(['footballManager', 'services/TeamService'], function (footballManager) {

    footballManager.controller("ChooseTeamCtl", function ($scope, TeamService) {
        TeamService.getTeams().then(function (response) {
          $scope.teams = response.data;
        });
    });

});

