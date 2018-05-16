var currentMinute = 0;

$(document).ready(function(){
    fetchData();
    setInterval(fetchData, 500);
});

function fetchData() {
    $.getJSON('/data', function(json) {
        var eventContainer;
        var homeScoreContainer;
        var awayScoreContainer;
        var minute = -1;
        var eventMinutes = 10;

        console.log(json);

        for(var matchId in json) {

            if(minute == -1) {
                minute = json[matchId].minute;
                if (minute > currentMinute)
                    currentMinute = minute;
                else if (minute < currentMinute - eventMinutes)
                    return;
            }

            eventContainer = document.getElementById(matchId + "event");
            homeScoreContainer = document.getElementById(matchId + "homeScore");
            awayScoreContainer = document.getElementById(matchId + "awayScore");

            homeScoreContainer.innerHTML = json[matchId].homeScore;
            awayScoreContainer.innerHTML = json[matchId].awayScore;

            var oldEvents = eventContainer.children;
            for (var i = oldEvents.length - 1; i >= 0 ; i--) {
                if(oldEvents[i].getAttribute("name") < currentMinute - eventMinutes) {
                    eventContainer.removeChild(oldEvents[i]);
                }
            }

            for(var eventId in json[matchId].events) {

                var event = json[matchId].events[eventId];
                var type = document.createElement("span");
                type.setAttribute("name", event.minute.toString());
                type.style.display = "block";

                switch(event.type) {
                    case "SCORE":
                        type.innerHTML = event.p1.name + " - " + document.getElementById("goalScored").value + " ( " + event.minute + "' )";
                        break;
                    case "YELLOW_CARD":
                        type.innerHTML = event.p1.name + " - " + document.getElementById("yellowCard").value + " ( " + event.minute + " )";
                        break;
                    case "RED_CARD":
                        type.innerHTML = event.p1.name + " - " + document.getElementById("redCard").value + " ( " + event.minute + " )";
                        break;
                    // case "SUBSTITUTE":

                    //     type = json[match][event].p1.name + " " + document.getElementById("goalScored").value + " " + json[match][event].p2.name + "( " + json[match][event].minute + " )<br>";
                    //     break;
                    default:
                        break;
                }

                eventContainer.appendChild(type);
            }

        }

        $('.progress-bar').css('width', (currentMinute * 1.1) + '%');
        document.getElementById("time").innerHTML = currentMinute + "'";

        if(currentMinute == 90) {
            window.location.href = "../matchEnd";
        }
    });
}




