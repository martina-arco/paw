var  json;

// var request = new XMLHttpRequest();
//
// request.open('GET', '/json');
//
// request.onload = function() {
//     var match = JSON.parse(request.responseText);
//     eventContainer.insertAdjacentHTML('beforeennd', match.event);
//     homeScoreContainer.insertAdjacentHTML('beforeend', match.homeScore);
//     awayScoreContainer.insertAdjacentHTML('beforeend', match.awayScore);
// };
//
// request.onerror = function() {
//
// };
//
//
// function requests() {
//     request.send();
// }

$(document).ready(function(){
    fetchData();

    let timer = setInterval(fetchData, 250);
});

function fetchData() {
    $.getJSON('/data', function(json) {
        var eventContainer;
        var homeScoreContainer;
        var awayScoreContainer;
        console.log(JSON.stringify(json));
        console.log(json);

            console.log( json["1"][0].type );

            for(var match in json) {

                var events = new String();

                for(var event in json[match]) {

                    console.log(json[match]);

                    var type = new String();

                    switch(json[match][event].type) {
                        case "SCORE":
                            type = json[match][event].p1 + " metio gol en minuto " + json[match][event].minute + "<br>";
                            break;
                        case "YELLOW_CARD":
                            type = "A " + json[match][event].p1 + " le sacaron tarjeta amarilla en minuto " + json[match][event].minute + "<br>";
                            break;
                        case "RED_CARD":
                            type = "A " + json[match][event].p1 + " le sacaron tarjeta roja en minuto " + json[match][event].minute + "<br>";
                            break;
                        default:break;
                    }


                    events += type;

                }

                eventContainer = document.getElementById(match + "event");
                homeScoreContainer = document.getElementById(match + "homeScore");
                awayScoreContainer = document.getElementById(match + "awayScore");

                eventContainer.innerHTML = events;

            }

        // eventContainer.insertAdjacentHTML('beforeennd', match.event);
        // homeScoreContainer.insertAdjacentHTML('beforeend', match.homeScore);
        // awayScoreContainer.insertAdjacentHTML('beforeend', match.awayScore);
    });
}




