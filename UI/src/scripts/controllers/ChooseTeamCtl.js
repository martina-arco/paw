define(['footballManager', 'services/TeamService'], function (footballManager) {

    footballManager.controller("ChooseTeamCtl", function ($scope, ngDialog, $location, TeamService) {
        TeamService.getTeams().then(function (response) {
          $scope.teams = response.data;
        });

        $scope.model = {};
        $scope.model.selected = 0;

        $scope.submit = function () {
          console.log(Number($scope.model.selected));
          TeamService.chooseTeam(Number($scope.model.selected))
            .then(function (response) {
              $location.url("home");
            }, function(response) {
              $scope.openChooseTeamErrorModal();
            });
        };

      $scope.openChooseTeamErrorModal = function () {
        ngDialog.open({
          templateUrl: 'views/chooseTeamErrorModal.html',
          className: 'ngdialog-theme-default',
          scope: $scope
        });
      }
    });
});
