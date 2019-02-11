define(['footballManager', 'services/FormationService', 'services/PlayerService'], function (footballManager) {

    footballManager.controller("FormationCtl", function ($scope, FormationService, PlayerService) {
        // $scope.formation = {
        //         gk: {name:'hola', id:1, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //         lb: {name:'como', id:2, position:1, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //         lcb:           {name:'como', id:2, position:1, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //         cb:           {name:'como', id:2, position:1, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //         rcb: {id: 0, name: ''},
        //         rb: {id: 0, name: ''},
        //         lm: {id: 0, name: ''},
        //         lcm: {id: 0, name: ''},
        //         cdm: {id: 0, name: ''},
        //         cam: {id: 0, name: ''},
        //         rcm: {id: 0, name: ''},
        //         rm: {id: 0, name: ''},
        //         lw: {id: 0, name: ''},
        //         lf: {id: 0, name: ''},
        //         st: {id: 0, name: ''},
        //         rf: {id: 0, name: ''},
        //         rw: {id: 0, name: ''},
        //
        // };
        //
        // $scope.players = [
        //   {name:'hola', id:1, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3},
        //   {name:'como', id:2, position:1, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   {name:'estas', id:3, position:2, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   {name:'lala', id:4, position:3, fitness: 0, skillLevel:2, goalKeeping:0, finishing:0, defending:2, passing:3},
        //   {name:'bien', id:5, position:0, fitness: 0, skillLevel:2, goalKeeping:10, finishing:0, defending:2, passing:3}
        // ];

        FormationService.getFormation().then(function (response) {
            $scope.formation = response.data;
        });

        PlayerService.getPlayers().then(function (response) {
            $scope.players = response.data;
        });

        $scope.goalKeepers = [];
        $scope.backPlayers = [];
        $scope.wingPlayers = [];
        $scope.frontPlayers = [];

        FormationService.fillPositionArrays($scope.players, $scope.goalKeepers, $scope.backPlayers,
          $scope.wingPlayers, $scope.frontPlayers);

    });

});
