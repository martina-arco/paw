var formatFilter = function(criteriaTypesNames, criteriaTypeSelected, criteriaNumberSelected) {
    var criteriaNumbers = [];
    var criteriaTypes = [];
    var criteriaNames = [];
    var json = {};

    for (var i = 0; i < criteriaTypeSelected.length; i++) {
          for(var j = 0; j < criteriaTypeSelected[i].length; j++) {
            criteriaTypes.push(criteriaTypeSelected[i][j].toUpperCase());
            criteriaNames.push(criteriaTypesNames[i][j].type.toUpperCase());
            if (criteriaNumberSelected[i][j] != null)
              criteriaNumbers.push(criteriaNumberSelected[i][j]);
            else
              criteriaNumbers.push('None');
          }
      }

    for (i = 0; i < criteriaTypes.length; i++) {
      json[criteriaNames[i]] = criteriaNames[i] + ';' + criteriaTypes[i] + ';' + criteriaNames[i] + '=' +
        criteriaNumbers[i];
    }

    return json;
};

define(['footballManager', 'services/PlayerService'], function (footballManager) {

  footballManager.controller("TransferCtl", function ($scope, ngDialog, PlayerService) {

      var updatePlayers = function() {
          PlayerService.filterSearch($scope.criterias).then(function (response) {
            $scope.players = response.data;
            $scope.playersAvailable = $scope.players.length;
            $scope.playerCount--;
          });
      };

      PlayerService.filterSearch($scope.criterias).then(function (response) {
        $scope.players = response.data;
        $scope.playersAvailable = $scope.players.length;
        $scope.playerCount = $scope.players.length;
      });

      $scope.criteriaTypes = {
        0: [{type: "AGE", name:$scope.CRITERIAAGE}, {type: "VALUE", name:$scope.CRITERIAVALUE}, {type: "SALARY", name:$scope.CRITERIASALARY}],
        1: [{type: "DEFENSE", name:$scope.CRITERIADEFENSE}, {type: "GOALKEEPING", name:$scope.CRITERIAGOALKEEPING}, {type: "PASS", name:$scope.CRITERIAPASS}],
        2: [{type: "FINISH", name:$scope.CRITERIAFINISH}, {type: "SKILL", name:$scope.CRITERIASKILL}, {type: "POTENTIAL", name:$scope.CRITERIAPOTENTIAL}]
      };

      $scope.isClosed = true;
      $scope.error = false;

      $scope.criteriaNumberSelected = new Array(3);
      $scope.criteriaTypeSelected = new Array(3);
      $scope.criterias = null;

      for (var i = 0; i < 3; i++) {
          $scope.criteriaTypeSelected[i] = new Array(3);
          $scope.criteriaNumberSelected[i] = new Array(3);
          for(var j = 0; j < 3; j++)
              $scope.criteriaTypeSelected[i][j] = 'ANY';
      }

      $scope.openTransferSuccessModal = function() {
          ngDialog.open({
              templateUrl: 'views/transferSuccessModal.html',
              className: 'ngdialog-theme-default',
              scope: $scope
          });
      };

      $scope.openTransferErrorModal = function() {
          ngDialog.open({
              templateUrl: 'views/noFundsErrorModal.html',
              className: 'ngdialog-theme-default',
              scope: $scope
          });
      };

      $scope.buyPlayer  = function (playerId) {
          PlayerService.buyPlayer(playerId)
            .then(
              function (response) {
                updatePlayers();
                $scope.openTransferSuccessModal();
              },
              function (response) {
                $scope.openTransferErrorModal();
              });
      };

      $scope.submitFilter = function () {
          $scope.criterias = formatFilter($scope.criteriaTypes, $scope.criteriaTypeSelected, $scope.criteriaNumberSelected);
          PlayerService.filterSearch($scope.criterias).then(function (response) {
            $scope.players = response.data;
            $scope.playersAvailable = $scope.players.length;
          });
      };
  });

});
