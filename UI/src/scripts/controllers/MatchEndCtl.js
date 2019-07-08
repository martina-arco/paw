define(['footballManager', 'services/MatchService', 'services/UserService'], function (footballManager) {

    footballManager.controller("MatchEndCtl", function ($scope, MatchService, UserService) {
        UserService.advanceDate().then(function (response) {});

        MatchService.getCurrentMatches().then(function (response) {
            $scope.matches = response.data;
        });

        MatchService.getMatch().then(function (response) {
            $scope.match = response.data;

            $scope.homeEvents = [];
            $scope.awayEvents = [];
            $scope.match.events.forEach(function (event) {
                switch(event.type) {
                  case 'HOMESCORE':
                      $scope.homeEvents.push(event.player1 + event.player2 + ' ' +  $scope.EVENTHOMESCORE
                        + ' - ' + event.minute + "'");
                      break;
                  case 'AWAYSCORE':
                      $scope.awayEvents.push(event.player1 + event.player2 + ' ' +  $scope.EVENTHOMESCORE
                        + ' - ' + event.minute + "'");
                      break;
                  // case 'YELLOW_CARD':
                  // case 'RED_CARD':
                }
            })
        });
    });

});
