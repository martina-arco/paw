var request = new XMLHttpRequest();
var eventContainer = document.getElementById("event");
var homeScoreContainer = document.getElementById("homeScore");
var awayScoreContainer = document.getElementById("awayScore");

request.open('GET', '/json');

request.onload = function() {
    var match = JSON.parse(request.responseText);
    eventContainer.insertAdjacentHTML('beforeennd', match.event);
    homeScoreContainer.insertAdjacentHTML('beforeend', match.homeScore);
    awayScoreContainer.insertAdjacentHTML('beforeend', match.awayScore);
};

request.onerror = function() {

};

request.send();
