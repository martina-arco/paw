var positions = [   "gk",
                    "lb", "lcb", "cb", "rcb", "rb",
                    "lm", "lcm", "cdm", "cam", "rcm", "rm",
                    "lw", "lf", "st", "rf", "rw"];
var subPos = ["sub1", "sub2", "sub3", "sub4", "sub5", "sub6", "sub7"];
var players = [];
var substitutes = [];

jQuery(document).ready(function(){
    load();
});

function load() {
    var i;
    for(i=0; i<positions.length; i++) {

        players[i] = document.getElementById(positions[i]).value;
    }
    for(i=0; i<subPos.length; i++) {
        substitutes[i] = document.getElementById(subPos[i]).value;
    }

    document.getElementById("pressure").value = document.getElementById("pressure").options[0].value;
    document.getElementById("attitude").value = document.getElementById("attitude").options[0].value;

    showCurrentFormation(document.getElementById("formation").value);


}

function showCurrentFormation(formation){

    if(formation.toString() === "343"){
        document.getElementById("cam").style.display = "none";
        document.getElementById("cam").value = 0;
        document.getElementById("lb").style.display = "none";
        document.getElementById("lb").value = 0;
        document.getElementById("rb").style.display = "none";
        document.getElementById("rb").value = 0;
        document.getElementById("lf").style.display = "none";
        document.getElementById("lf").value = 0;
        document.getElementById("cdm").style.display = "none";
        document.getElementById("cdm").value = 0;
        document.getElementById("rf").style.display = "none";
        document.getElementById("rf").value = 0;
    }
    else if(formation.toString() === "352"){
        document.getElementById("cdm").style.display = "none";
        document.getElementById("cdm").value = 0;
        document.getElementById("lb").style.display = "none";
        document.getElementById("lb").value = 0;
        document.getElementById("rb").style.display = "none";
        document.getElementById("rb").value = 0;
        document.getElementById("lw").style.display = "none";
        document.getElementById("lw").value = 0;
        document.getElementById("st").style.display = "none";
        document.getElementById("st").value = 0;
        document.getElementById("rw").style.display = "none";
        document.getElementById("rw").value = 0;
    }
    else if(formation.toString() === "433"){
        document.getElementById("cam").style.display = "none";
        document.getElementById("cam").value = 0;
        document.getElementById("lm").style.display = "none";
        document.getElementById("lm").value = 0;
        document.getElementById("rm").style.display = "none";
        document.getElementById("rm").value = 0;
        document.getElementById("lf").style.display = "none";
        document.getElementById("lf").value = 0;
        document.getElementById("cb").style.display = "none";
        document.getElementById("cb").value = 0;
        document.getElementById("rf").style.display = "none";
        document.getElementById("rf").value = 0;
    }
    else if(formation.toString() === "442") {
        document.getElementById("cb").style.display = "none";
        document.getElementById("cb").value = 0;
        document.getElementById("cdm").style.display = "none";
        document.getElementById("cdm").value = 0;
        document.getElementById("cam").style.display = "none";
        document.getElementById("cam").value = 0;
        document.getElementById("lw").style.display = "none";
        document.getElementById("lw").value = 0;
        document.getElementById("st").style.display = "none";
        document.getElementById("st").value = 0;
        document.getElementById("rw").style.display = "none";
        document.getElementById("rw").value = 0;
    }
    else if(formation.toString() === "451"){
        document.getElementById("cb").style.display = "none";
        document.getElementById("cb").value = 0;
        document.getElementById("cdm").style.display = "none";
        document.getElementById("cdm").value = 0;
        document.getElementById("lw").style.display = "none";
        document.getElementById("lw").value = 0;
        document.getElementById("lf").style.display = "none";
        document.getElementById("lf").value = 0;
        document.getElementById("rw").style.display = "none";
        document.getElementById("rf").value = 0;
        document.getElementById("rf").style.display = "none";
    }
    else if(formation.toString() === "532"){
        document.getElementById("cam").style.display = "none";
        document.getElementById("cam").value = 0;
        document.getElementById("lm").style.display = "none";
        document.getElementById("lm").value = 0;
        document.getElementById("lm").style.display = "none";
        document.getElementById("lm").value = 0;
        document.getElementById("lw").style.display = "none";
        document.getElementById("lw").value = 0;
        document.getElementById("st").style.display = "none";
        document.getElementById("st").value = 0;
        document.getElementById("rw").style.display = "none";
        document.getElementById("rw").value = 0;
    }
    else if(formation.toString() === "523"){
        document.getElementById("cam").style.display = "none";
        document.getElementById("cam").value = 0;
        document.getElementById("lm").style.display = "none";
        document.getElementById("lm").value = 0;
        document.getElementById("rm").style.display = "none";
        document.getElementById("rm").value = 0;
        document.getElementById("lf").style.display = "none";
        document.getElementById("lf").value = 0;
        document.getElementById("cdm").style.display = "none";
        document.getElementById("cdm").value = 0;
        document.getElementById("rf").style.display = "none";
        document.getElementById("rf").value = 0;
    }
    else if(formation.toString() === "541"){
        document.getElementById("cam").style.display = "none";
        document.getElementById("cam").value = 0;
        document.getElementById("lw").style.display = "none";
        document.getElementById("lw").value = 0;
        document.getElementById("rw").style.display = "none";
        document.getElementById("rw").value = 0;
        document.getElementById("lf").style.display = "none";
        document.getElementById("lf").value = 0;
        document.getElementById("cdm").style.display = "none";
        document.getElementById("cdm").value = 0;
        document.getElementById("rf").style.display = "none";
        document.getElementById("rf").value = 0;
    }
}

function update(position, player) {
    var playerIndex = positions.indexOf(position);
    var i;
    if(playerIndex !== -1) {
        for (i = 0; i < players.length; i++) {
            if (i !== playerIndex && players[i] === player) {//Cambio titular por titular
                document.getElementById(positions[i]).value = players[playerIndex];
                players[i] = players[playerIndex];
                players[playerIndex] = player;
                break;
            }
        }
        if(i >= players.length) {//Cambio titular por suplente
            for(i=0; i<substitutes.length; i++) {
                if(substitutes[i] === player){
                    document.getElementById(subPos[i]).value = players[playerIndex];
                    if(players[playerIndex] === document.getElementById("cap").value) {
                        document.getElementById("cap").value = player;
                    }
                    if(players[playerIndex] === document.getElementById("fk").value) {
                        document.getElementById("fk").value = player;
                    }
                    if(players[playerIndex] === document.getElementById("pen").value) {
                        document.getElementById("pen").value = player;
                    }
                    substitutes[i] = players[playerIndex];
                    players[playerIndex] = player;
                    break;
                }
            }
            if(i >= substitutes.length) {//Cambio titular por reserva
                if(players[playerIndex] === document.getElementById("cap").value) {
                    document.getElementById("cap").value = player;
                }
                if(players[playerIndex] === document.getElementById("fk").value) {
                    document.getElementById("fk").value = player;
                }
                if(players[playerIndex] === document.getElementById("pen").value) {
                    document.getElementById("pen").value = player;
                }
                players[playerIndex] = player;
            }
        }
    }
    else {//Cambio de suplente con suplente
        playerIndex = subPos.indexOf(position);
        for(i=0; i<substitutes.length; i++) {
            if(substitutes[i] === player) {
                document.getElementById(subPos[i]).value = substitutes[playerIndex];
                substitutes[i] = substitutes[playerIndex];
                substitutes[playerIndex] = player;
                break;
            }
        }
        if(i >= substitutes.length) {//Cambio suplente por reserva
            substitutes[playerIndex] = player;
        }
    }
}

function changeFormation(formation) {
    showCurrentFormation(formation);
}
