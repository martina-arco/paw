function compare(event1, event2) {
    return event1.minute - event2.minute;
}

define(['footballManager', 'services/MatchService', 'services/UserService'], function (footballManager) {

    footballManager.controller("MatchEndCtl", function ($scope, MatchService) {

        MatchService.getPlayedMatches().then(function (response) {
            $scope.matches = response.data;
        });

        MatchService.getUserPlayedMatch().then(function (response) {
            $scope.match = response.data;

            $scope.events = [];
            $scope.match.events.forEach(function (event) {
                switch(event.type) {
                  case 'HOMESCORE':
                      $scope.events.push({type:'home',
                        description: event.player1 + event.player2 + ' ' +  $scope.GOALSCORED
                        + ' - ' + event.minute + "'"});
                      break;
                  case 'AWAYSCORE':
                      $scope.events.push({type:'away',
                      description: event.player1 + event.player2 + ' ' +  $scope.GOALSCORED
                        + ' - ' + event.minute + "'"});
                      break;
                  // case 'YELLOW_CARD':
                  // case 'RED_CARD':
                }
            });

            $scope.events.sort(compare);
        });
    });

});
