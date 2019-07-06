define(['footballManager', 'services/MatchService'], function (footballManager) {

    footballManager.controller("MatchCtl", function ($scope, $location, MatchService) {

        $scope.matches = [];
        $scope.time = 0;

        MatchService.playMatch().then(function (response) {
            var matchesWithEvents = response.data;

            matchesWithEvents.forEach(function (match) {
                var matchToAdd = {id: match.id, stadium: match.stadium, homeTeam: match.homeTeam,
                  awayTeam: match.awayTeam, homeScore: 0, awayScore: 0, event:''};
                $scope.matches.push(matchToAdd);
            })
        });

        function iterate(){

          if($scope.time > 90){
            $location.url('/matchEnd');
            return;
          }

          $scope.matches.forEach(function (match) {
            match.events.forEach(function (event) {
              if(event.minute === minute){
                updateEvents(match, event);
              }
            });
          });

          // jQuery('.progress-bar').css('width', (minute * 1.1) + '%');

          $scope.time++;
          setTimeout(iterate, 150, matches, time);
        }

        function updateEvents(match, event){
          var eventString;

          switch(event.type) {
            case "HOMESCORE":
              eventString = event.player1 + " - " + "goal scored" + "( " + event.minute + "' )";
              match.homeScore++;
              break;
            case "AWAYSCORE":
              eventString = event.player1 + " - " + "goal scored" + "( " + event.minute + "' )";
              match.awayScore++;
              break;
            case "YELLOW CARD":
              eventString = event.player1 + " - " + "yellow card" + " ( " + event.minute + " )";
              break;
            case "RED CARD":
              eventString = event.player1 + " - " + "red card" + " ( " + event.minute + " )";
              break;
            // case "SUBSTITUTE":

            //     type = json[match][event].p1.name + " " + document.getElementById("goalScored").value + " " + json[match][event].p2.name + "( " + json[match][event].minute + " )<br>";
            //     break;
            default:
              break;
          }

          match.event = eventString;

        }

        iterate();


    });

});
