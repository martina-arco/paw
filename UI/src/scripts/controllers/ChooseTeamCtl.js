define(['footballManager', 'services/TeamService'], function (footballManager) {

    footballManager.controller("ChooseTeamCtl", function ($scope, $location, TeamService) {
        TeamService.getTeams().then(function (response) {
            $scope.teams = response.data;
        });

        $scope.submit = function () {
            TeamService.chooseTeam($scope.teamSelected).then(
              function (response) {
                $location.url("home");
              }
            );
        }

    });

});
