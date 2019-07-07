function validateFormation(formation, scheme) {
    var success = true;

    if(formation.lcm == null || formation.rcm == null || formation.lcb == null || formation.rcb == null || formation.gk == null)
        success = false;

    switch(scheme) {
        case 343:
            if(formation.lw == null || formation.st == null || formation.rw == null || formation.lm == null ||
               formation.rm == null || formation.cb == null)
                success = false;

            if(formation.lf != null || formation.rf != null || formation.cam != null || formation.cdm != null ||
               formation.lb != null || formation.rb != null)
                success = false;
            break;

        case 352 :
            if(formation.lf == null || formation.rf == null || formation.cam == null || formation.lm == null ||
              formation.rm == null || formation.cb == null)
              success = false;

            if(formation.lw != null || formation.st != null || formation.rw != null || formation.cdm != null ||
              formation.lb != null || formation.rb != null)
              success = false;
            break;

        case 433 :
            if(formation.lw == null || formation.st == null || formation.rw == null || formation.cdm == null ||
               formation.lb == null || formation.rb == null)
                success = false;

            if(formation.lf != null || formation.rf != null || formation.cam != null || formation.lm != null ||
               formation.rm != null || formation.cb != null)
                success = false;
            break;

        case 442 :
            if(formation.lf == null || formation.rf == null || formation.lm == null || formation.rm == null ||
              formation.lb == null || formation.rb == null)
                success = false;

            if(formation.lw != null || formation.st != null || formation.rw != null || formation.cam != null ||
              formation.cdm != null || formation.cb != null)
                success = false;
            break;

        case 451 :
            if(formation.st == null || formation.cam == null || formation.lm == null || formation.rm == null ||
              formation.lb == null || formation.rb == null)
                success = false;

            if(formation.lw != null || formation.lf != null || formation.rf != null || formation.rw != null ||
              formation.cdm != null || formation.cb != null)
                success = false;
            break;

        case 523 :
            if(formation.lw == null || formation.st == null || formation.rw == null || formation.cb == null ||
              formation.lb == null || formation.rb == null)
                success = false;

            if(formation.lf != null || formation.rf != null || formation.cam != null || formation.lm != null ||
              formation.rm != null || formation.cdm != null)
                success = false;
            break;

        case 532 :
            if(formation.lf == null || formation.rf == null || formation.cam == null || formation.cb == null ||
              formation.lb == null || formation.rb == null)
                success = false;

            if(formation.lw != null || formation.st != null || formation.rw != null || formation.lm != null ||
              formation.rm != null || formation.cdm != null)
                success = false;
            break;

        case 541 :
            if(formation.st == null || formation.lm == null || formation.rm == null || formation.cb == null ||
              formation.lb == null || formation.rb == null)
                success = false;

            if(formation.lw != null || formation.lf != null || formation.rf != null || formation.rw != null ||
              formation.cam != null || formation.cdm != null)
                success = false;
            break;
    }

    return success;
}

define(['footballManager', 'services/FormationService', 'services/PlayerService'], function (footballManager) {

    footballManager.controller("FormationCtl", function ($scope, ngDialog, FormationService, PlayerService) {

        $scope.updateOccupiedPlayers = function () {
            $scope.occupiedPlayers = [];
            if($scope.formation.cam != null)
                $scope.occupiedPlayers.push($scope.formation.cam);
            if($scope.formation.cb != null)
              $scope.occupiedPlayers.push($scope.formation.cb);
            if($scope.formation.cdm != null)
              $scope.occupiedPlayers.push($scope.formation.cdm);
            if($scope.formation.gk != null)
              $scope.occupiedPlayers.push($scope.formation.gk);
            if($scope.formation.lb != null)
              $scope.occupiedPlayers.push($scope.formation.lb);
            if($scope.formation.lcb != null)
              $scope.occupiedPlayers.push($scope.formation.lcb);
            if($scope.formation.lcm != null)
              $scope.occupiedPlayers.push($scope.formation.lcm);
            if($scope.formation.lf != null)
              $scope.occupiedPlayers.push($scope.formation.lf);
            if($scope.formation.lm != null)
              $scope.occupiedPlayers.push($scope.formation.lm);
            if($scope.formation.lw != null)
              $scope.occupiedPlayers.push($scope.formation.lw);
            if($scope.formation.rb != null)
              $scope.occupiedPlayers.push($scope.formation.rb);
            if($scope.formation.rcb != null)
              $scope.occupiedPlayers.push($scope.formation.rcb);
            if($scope.formation.rcm != null)
              $scope.occupiedPlayers.push($scope.formation.rcm);
            if($scope.formation.rf != null)
              $scope.occupiedPlayers.push($scope.formation.rf);
            if($scope.formation.rm != null)
              $scope.occupiedPlayers.push($scope.formation.rm);
            if($scope.formation.rw != null)
              $scope.occupiedPlayers.push($scope.formation.rw);
            if($scope.formation.st != null)
              $scope.occupiedPlayers.push($scope.formation.st);
        };

        $scope.saveFormation = function () {
          if(!validateFormation($scope.formation, $scope.formation.formation)) {
              $scope.openFormationChangeErrorModal();
              return;
          }

          FormationService.saveFormation($scope.formation)
            .then(function (response) {
              $scope.openFormationChangeSuccessModal();
            }, function(response) {
              $scope.openFormationChangeErrorModal();
          });
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

            $scope.formation.options.forEach(function (option) {
                if(validateFormation($scope.formation, option))
                    $scope.formation.formation = option;
            });

            $scope.updateOccupiedPlayers();
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

        $scope.updateFormationScheme = function() {

            switch ($scope.formation.formation) {
                case 343:
                    $scope.formation.lf = null;
                    $scope.formation.rf = null;
                    $scope.formation.cam = null;
                    $scope.formation.cdm = null;
                    $scope.formation.lb = null;
                    $scope.formation.rb = null;
                    break;

                case 352:
                    $scope.formation.lw = null;
                    $scope.formation.st = null;
                    $scope.formation.rw = null;
                    $scope.formation.cdm = null;
                    $scope.formation.lb = null;
                    $scope.formation.rb = null;
                    break;

                case 433:
                    $scope.formation.lf = null;
                    $scope.formation.rf = null;
                    $scope.formation.cam = null;
                    $scope.formation.lm = null;
                    $scope.formation.rm = null;
                    $scope.formation.cb = null;
                    break;

                case 442:
                    $scope.formation.lw = null;
                    $scope.formation.st = null;
                    $scope.formation.rw = null;
                    $scope.formation.cam = null;
                    $scope.formation.cdm = null;
                    $scope.formation.cb = null;
                    break;

                case 451:
                  $scope.formation.lw = null;
                  $scope.formation.lf = null;
                  $scope.formation.rf = null;
                  $scope.formation.rw = null;
                  $scope.formation.cdm = null;
                  $scope.formation.cb = null;
                  break;

                case 523:
                  $scope.formation.lf = null;
                  $scope.formation.rf = null;
                  $scope.formation.cam = null;
                  $scope.formation.lm = null;
                  $scope.formation.rm = null;
                  $scope.formation.cdm = null;
                  break;

                case 532:
                  $scope.formation.lw = null;
                  $scope.formation.st = null;
                  $scope.formation.rw = null;
                  $scope.formation.lm = null;
                  $scope.formation.rm = null;
                  $scope.formation.cdm = null;
                  break;

                case 541:
                  $scope.formation.lw = null;
                  $scope.formation.lf = null;
                  $scope.formation.rf = null;
                  $scope.formation.rw = null;
                  $scope.formation.cam = null;
                  $scope.formation.cdm = null;
                  break;
            }
        };

        $scope.error = false;
        $scope.substitutesIsClosed = true;
        $scope.rolesIsClosed = true;
    });

});
