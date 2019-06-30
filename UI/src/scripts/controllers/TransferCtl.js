define(['footballManager', 'services/PlayerService'], function (footballManager) {

  footballManager.controller("TransferCtl", function ($scope, ngDialog, PlayerService) {
      PlayerService.getPlayers().then(function (response) {
        $scope.players = response.data;
      });

      $scope.players = [
        {name:'hola', id:1, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        {name:'como', id:2, position:1, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        {name:'estas', id:3, position:2, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        {name:'lala', id:4, position:3, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        {name:'bien', id:5, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3}
      ];

      $scope.criteriaTypes = {
        0: ["Age", "Value", "Salary"],
        1: ["Defense", "GoalKeeping", "Pass"],
        2: ["Finish", "Skill", "Potential"]
      };

      $scope.isClosed = true;
      $scope.error = false;

      $scope.criteriaNumberSelected = new Array(3);
      $scope.criteriaTypeSelected = new Array(3);
      $scope.criterias = null;
      $scope.playerCount = $scope.players.length;
      $scope.playersAvailable = $scope.players.length;

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
              templateUrl: 'views/transferErrorModal.html',
              className: 'ngdialog-theme-default',
              scope: $scope
          });
      };

      $scope.buyPlayer  = function (playerId) {
          PlayerService.buyPlayer(playerId).then(function (response) {
              $scope.error = response.data;
          });

          $scope.error = true;

          if($scope.error)
              $scope.openTransferErrorModal();
          else
              $scope.openTransferSuccessModal();
      };

      $scope.sellPlayer  = function (playerId) {
          PlayerService.sellPlayer(playerId).then(function (response) {
              $scope.error = response.data;
          });

          $scope.error = false;

          if($scope.error)
              $scope.openTransferErrorModal();
          else
              $scope.openTransferSuccessModal();
      };

      $scope.submitFilter = function () {
          PlayerService.filterSearch($scope.criterias);
      }
  });

});
