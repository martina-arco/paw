define('footballManager', function (footballManager) {

    footballManager.controller("formationCtl", function ($scope) {
        $scope.mainTeam = {
                gk: {id: 0, name: ''},
                lb: {id: 0, name: ''},
                lcb: {id: 0, name: ''},
                cb: {id: 0, name: ''},
                rcb: {id: 0, name: ''},
                rb: {id: 0, name: ''},
                lm: {id: 0, name: ''},
                lcm: {id: 0, name: ''},
                cdm: {id: 0, name: ''},
                cam: {id: 0, name: ''},
                rcm: {id: 0, name: ''},
                rm: {id: 0, name: ''},
                lw: {id: 0, name: ''},
                lf: {id: 0, name: ''},
                st: {id: 0, name: ''},
                rf: {id: 0, name: ''},
                rw: {id: 0, name: ''}
        };
        $scope.substitutes = [

        ];
        $scope.roles= {
            captain:{id: 0, name: ''},
            freeKickTaker:{id: 0, name: ''},
            penaltyTaker:{id: 0, name: ''}
        };

        $scope.pressure = 0;
        $scope.possiblePressures = {
            low: 0,
            medium: 0,
            high: 0
        };
        $scope.attitude = 0;
        $scope.possibleAttitudes = {
            low: 0,
            medium: 0,
            high: 0
        };
        $scope.formationPositions = '323';
        $scope.possibleFormations = [];

    });

});