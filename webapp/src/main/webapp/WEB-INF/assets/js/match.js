var eventMinutes = 10;
var minute = 0;
$(document).ready(function(){
    fetchData();
});

function fetchData() {
    $.getJSON('/data', function(json) {
        iterate(json);
    });
}

function iterate(json){

    if(minute === 91){
        window.location.href = "../matchEnd";
        return;
    }

    for(var index in json){
        var matchDTO = json[index];
        var matchId = matchDTO.matchId;
        var eventContainer = document.getElementById(matchId + "event");
        var homeScoreContainer = document.getElementById(matchId + "homeScore");
        var awayScoreContainer = document.getElementById(matchId + "awayScore");

        for(var eventIndex in matchDTO.events){
            var eventDTO = matchDTO.events[eventIndex];
            if(eventDTO.minute === minute){
                updateEvents(minute, eventContainer, eventDTO, homeScoreContainer, awayScoreContainer);
            }
        }
    }

    $('.progress-bar').css('width', (minute * 1.1) + '%');
    document.getElementById("time").innerHTML = minute + "'";

    minute++;
    setTimeout(iterate,100,json);
}

function updateEvents(currentMinute, eventContainer, event, homeScoreContainer, awayScoreContainer){
    var oldEvents = eventContainer.children;
    for (var i = oldEvents.length - 1; i >= 0 ; i--) {
        if(oldEvents[i].getAttribute("name") < currentMinute - eventMinutes) {
            eventContainer.removeChild(oldEvents[i]);
        }
    }

    var type = document.createElement("span");
    type.setAttribute("name", event.minute.toString());
    type.style.display = "block";

    switch(event.type) {
        case "HOMESCORE":
            type.innerHTML = event.player1 + " - " + document.getElementById("goalScored").innerHTML + " ( " + event.minute + "' )";
            homeScoreContainer.innerHTML = parseInt(homeScoreContainer.innerHTML) + 1;
            break;
        case "AWAYSCORE":
            type.innerHTML = event.player1 + " - " + document.getElementById("goalScored").innerHTML + " ( " + event.minute + "' )";
            awayScoreContainer.innerHTML = parseInt(homeScoreContainer.innerHTML) + 1;
            break;
        case "YELLOW CARD":
            type.innerHTML = event.player1 + " - " + document.getElementById("yellowCard").innerHTML + " ( " + event.minute + " )";
            break;
        case "RED CARD":
            type.innerHTML = event.player1 + " - " + document.getElementById("redCard").innerHTML + " ( " + event.minute + " )";
            break;
        // case "SUBSTITUTE":

        //     type = json[match][event].p1.name + " " + document.getElementById("goalScored").value + " " + json[match][event].p2.name + "( " + json[match][event].minute + " )<br>";
        //     break;
        default:
            break;
    }

    eventContainer.appendChild(type);
}


