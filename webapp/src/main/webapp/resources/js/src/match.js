var  json;

$(document).ready(function(){
    fetchData();

    // setTimeout(function () {
    //     window.location.href= '/matchEnd';
    //
    // },30000);

    var i = 0;

    var counterBack = setInterval(function () {

        if (i <= 90) {

            $('.progress-bar').css('width', i + '%');
            document.getElementById("time").innerHTML = i.toString() + "'";

        } else
            clearInterval(counterBack);

        i++;
    }, 333);

    // let timer = setInterval(fetchData, 250);
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
                            type = json[match][event].p1 + " - gol ( " + json[match][event].minute + " )<br>";
                            break;
                        case "YELLOW_CARD":
                            type = json[match][event].p1 + " - tarjeta amarilla ( " + json[match][event].minute + " )<br>";
                            break;
                        case "RED_CARD":
                            type = json[match][event].p1 + "- tarjeta roja ( " + json[match][event].minute + " )<br>";
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
    });
}




