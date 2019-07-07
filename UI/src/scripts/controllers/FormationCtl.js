define(['footballManager', 'services/FormationService', 'services/PlayerService'], function (footballManager) {

    footballManager.controller("FormationCtl", function ($scope, ngDialog, FormationService, PlayerService) {

        $scope.saveFormation = function () {
          // $scope.error = FormationService.saveFormation($scope.formation);
          // $scope.error = true;
          if($scope.error)
            $scope.openFormationChangeErrorModal();
          else
            $scope.openFormationChangeSuccessModal();
        };

        $scope.openFormationChangeSuccessModal = function() {
            ngDialog.open({
                templateUrl: 'views/formationChangeSuccessModal.html',
                className: 'ngdialog-theme-default',
                scope: $scope
            });
        };

        $scope.openFormationChangeErrorModal = function() {
            ngDialog.open({
                templateUrl: 'views/formationChangeErrorModal.html',
                className: 'ngdialog-theme-default',
                scope: $scope
            });
        };

        FormationService.getFormation().then(function (response) {
            $scope.formation = response.data;

            $scope.occupiedPlayers = [];
            $scope.occupiedPlayers.push($scope.formation.cam);
            $scope.occupiedPlayers.push($scope.formation.cb);
            $scope.occupiedPlayers.push($scope.formation.cdm);
            $scope.occupiedPlayers.push($scope.formation.gk);
            $scope.occupiedPlayers.push($scope.formation.lb);
            $scope.occupiedPlayers.push($scope.formation.lcb);
            $scope.occupiedPlayers.push($scope.formation.lcm);
            $scope.occupiedPlayers.push($scope.formation.lf);
            $scope.occupiedPlayers.push($scope.formation.lm);
            $scope.occupiedPlayers.push($scope.formation.lw);
            $scope.occupiedPlayers.push($scope.formation.rb);
            $scope.occupiedPlayers.push($scope.formation.rcb);
            $scope.occupiedPlayers.push($scope.formation.rcm);
            $scope.occupiedPlayers.push($scope.formation.rf);
            $scope.occupiedPlayers.push($scope.formation.rm);
            $scope.occupiedPlayers.push($scope.formation.rw);
            $scope.occupiedPlayers.push($scope.formation.st);
        });

        PlayerService.getPlayers().then(function (response) {
            $scope.goalKeepers = [];
            $scope.backPlayers = [];
            $scope.wingPlayers = [];
            $scope.frontPlayers = [];
            $scope.players = response.data;

            FormationService.fillPositionArrays($scope.players, $scope.goalKeepers, $scope.backPlayers,
              $scope.wingPlayers, $scope.frontPlayers);
        });

        $scope.error = false;
        $scope.substitutesIsClosed = true;
        $scope.rolesIsClosed = true;
    });

});
