define(['footballManager', 'services/PlayerService'], function (footballManager) {

  footballManager.controller("TransferCtl", function ($scope, PlayerService) {
    $scope.players = [
      {name:'hola', id:1, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
      {name:'como', id:2, position:1, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
      {name:'estas', id:3, position:2, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
      {name:'lala', id:4, position:3, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
      {name:'bien', id:5, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3}
    ];

    PlayerService.getPlayers().then(function (response) {
      $scope.players = response.data;
    });


    $scope.criteriaTypes = ["AGE","VALUE", "SALARY", "DEFENSE","GOALKEEPING","PASS","FINISH","SKILL","POTENTIAL"];

    $scope.criteriaNumberSelected = new Array($scope.criteriaTypes.length);
    $scope.criteriaTypeSelected = [];

    for (var i = 0; i < $scope.criteriaTypes.length; i++) {
      $scope.criteriaTypeSelected.push('ANY');
    }

    $scope.submitFilter = function () {

    }
  });

});
