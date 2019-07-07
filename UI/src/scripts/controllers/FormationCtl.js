define(['footballManager', 'services/FormationService', 'services/PlayerService'], function (footballManager) {

    footballManager.controller("FormationCtl", function ($scope, ngDialog, FormationService, PlayerService) {

        // $scope.goalKeepers = [];
        // $scope.backPlayers = [];
        // $scope.wingPlayers = [];
        // $scope.frontPlayers = [];
        //
        // FormationService.fillPositionArrays($scope.players, $scope.goalKeepers, $scope.backPlayers,
        //   $scope.wingPlayers, $scope.frontPlayers);

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
        });

        PlayerService.getPlayers().then(function (response) {
            $scope.players = response.data;
        });

        $scope.error = false;
        $scope.substitutesIsClosed = true;
        $scope.rolesIsClosed = true;

        // $scope.formation = {
        //   gk: {name:'gk', id:1, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   lb: {name:'lb', id:2, position:1, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   lcb:{name:'lcb', id:3, position:1, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   cb: {name:'cb', id:4, position:1, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   rcb: {name:'rcb', id:5, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   rb: {name:'rb', id:6, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   lm: {name:'lm', id:7, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   lcm: {name:'lcm', id:8, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   cdm: {name:'cdm', id:9, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   cam: {name:'cam', id:10, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   rcm: {name:'rcm', id:11, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   rm: {name:'rm', id:12, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   lw: {name:'lw', id:13, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   lf: {name:'lf', id:14, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   st: {name:'st', id:15, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   rf: {name:'rf', id:16, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   rw: {name:'rw', id:17, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   options: [343, 352, 433, 442, 451, 523, 532, 541],
        //   formation: 433
        //
        // };
        //
        // players = [
        //   {name:'hola', id:1, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   {name:'como', id:2, position:1, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   {name:'estas', id:3, position:2, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   {name:'lala', id:4, position:3, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   {name:'bien', id:5, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3}
        // ];
        //
        // $scope.frontPlayers = players;
        // $scope.players = players;
        // $scope.substitutes = $scope.players;

    });

});
