var eventMinutes = 10;
var minute = 0;
$(document).ready(function(){
    fetchData();
});

function fetchData() {
    $.getJSON('/data', function(json) {
        var leave = false;
        console.log(json);
        setInterval(iterate, 100, json);
        window.location.href = "../matchEnd";
    });
}

function iterate(json){

    if(minute == 91){
        return;
    }
    var eventContainer;
    var homeScoreContainer;
    var awayScoreContainer;

    for(var index in json){
        var matchDTO = json[index];
        console.log(matchDTO);
        var matchId = matchDTO.matchId;
        console.log(matchId);
        eventContainer = document.getElementById(matchId + "event");
        homeScoreContainer = document.getElementById(matchId + "homeScore");
        awayScoreContainer = document.getElementById(matchId + "awayScore");
        homeScoreContainer.innerHTML = matchDTO.homeScore;
        awayScoreContainer.innerHTML = matchDTO.awayScore;

        for(var eventDTO in matchDTO.events){
            if(eventDTO.minute == minute){
                updateEvents(minute, eventContainer, eventDTO);
            }
        }
    }

    $('.progress-bar').css('width', (minute * 1.1) + '%');
    document.getElementById("time").innerHTML = minute + "'";

    minute++;
}

function updateEvents(currentMinute, eventContainer, event){
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
        case "SCORE":
            type.innerHTML = event.player1 + " - " + document.getElementById("goalScored").value + " ( " + event.minute + "' )";
            break;
        case "YELLOW CARD":
            type.innerHTML = event.player1 + " - " + document.getElementById("yellowCard").value + " ( " + event.minute + " )";
            break;
        case "RED CARD":
            type.innerHTML = event.player1 + " - " + document.getElementById("redCard").value + " ( " + event.minute + " )";
            break;
        // case "SUBSTITUTE":

        //     type = json[match][event].p1.name + " " + document.getElementById("goalScored").value + " " + json[match][event].p2.name + "( " + json[match][event].minute + " )<br>";
        //     break;
        default:
            break;
    }

    eventContainer.appendChild(type);
}


