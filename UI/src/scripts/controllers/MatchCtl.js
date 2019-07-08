define(['footballManager', 'services/MatchService'], function (footballManager) {

    footballManager.controller("MatchCtl", function ($scope, $location, $timeout, MatchService) {

        $scope.matches = [];
        $scope.time = 0;
        $scope.matchesWithEvents = [];

        MatchService.playMatch().then(function (response) {
            $scope.matchesWithEvents = response.data;

            $scope.matchesWithEvents.forEach(function (match) {
                var matchToAdd = {id: match.id, stadium: match.stadium, home: match.home,
                  away: match.away, homeScore: 0, awayScore: 0, event:''};

                $scope.matches.push(matchToAdd);
            })
        });

        var iterate = function() {

            if($scope.time > 90){
                $location.url('/matchEnd');
                return;
            }

            for (var i = 0; i < $scope.matchesWithEvents.length; i++)  {
                var match = $scope.matchesWithEvents[i];
                var oldMatch = $scope.matches[i];

                match.events.forEach(function (event) {
                    if(event.minute === $scope.time){
                        updateEvents(oldMatch, event);
                    }
                });
            }

            // jQuery('.progress-bar').css('width', (minute * 1.1) + '%');

            $scope.time++;
            $timeout(iterate, 150);
        };

        var updateEvents = function(match, event){

          switch(event.type) {
            case "HOMESCORE":
              match.event = event.player1 + " - " + "goal scored" + "( " + event.minute + "' )";
              match.homeScore++;
              break;
            case "AWAYSCORE":
              match.event = event.player1 + " - " + "goal scored" + "( " + event.minute + "' )";
              match.awayScore++;
              break;
            case "YELLOW CARD":
              match.event = event.player1 + " - " + "yellow card" + " ( " + event.minute + " )";
              break;
            case "RED CARD":
              match.event = event.player1 + " - " + "red card" + " ( " + event.minute + " )";
              break;
            // case "SUBSTITUTE":

            //     type = json[match][event].p1.name + " " + document.getElementById("goalScored").value + " " + json[match][event].p2.name + "( " + json[match][event].minute + " )<br>";
            //     break;
            default:
              break;
          }

          return match;
        };

        $timeout(iterate, 150);

    });

});
