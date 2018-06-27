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
    getInitialValues();

    for(i=0; i<positions.size; i++){
        var select = document.getElementById(positions[i]);
        select.value = select.options[0].value;
    }

    for(i=0; i<substitutes.size; i++){
        var select = document.getElementById(substitutes[i]);
        select.value = select.options[0].value;
    }

    document.getElementById("pressure").value = document.getElementById("pressure").options[0].value;
    document.getElementById("attitude").value = document.getElementById("attitude").options[0].value;
    document.getElementById("formation").value = document.getElementById("formation").options[0].value;

    showCurrentFormation(document.getElementById("formation").value);
}

function getInitialValues() {
    var i;
    for(i=0; i<positions.length; i++) {
        players[i] = document.getElementById(positions[i]).value;
    }
    for(i=0; i<subPos.length; i++) {
        substitutes[i] = document.getElementById(subPos[i]).value;
    }
}

function showCurrentFormation(formation){

    if(formation.toString() === "343"){
        f343();
    }
    else if(formation.toString() === "352"){
        f352();
    }
    else if(formation.toString() === "433"){
        f433();
    }
    else if(formation.toString() === "442") {
        f442();
    }
    else if(formation.toString() === "451"){
        f451();
    }
    else if(formation.toString() === "532"){
        f532();
    }
    else if(formation.toString() === "523"){
        f523();
    }
    else if(formation.toString() === "541"){
        f541();
    }
}

function f343(){
    document.getElementById("cb").style.display = "";
    document.getElementById("lm").style.display = "";
    document.getElementById("rm").style.display = "";
    document.getElementById("lw").style.display = "";
    document.getElementById("st").style.display = "";
    document.getElementById("rw").style.display = "";

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

function f352(){
    document.getElementById("cb").style.display = "";
    document.getElementById("lm").style.display = "";
    document.getElementById("rm").style.display = "";
    document.getElementById("cam").style.display = "";
    document.getElementById("lf").style.display = "";
    document.getElementById("rf").style.display = "";

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

function f433() {
    document.getElementById("lb").style.display = "";
    document.getElementById("rb").style.display = "";
    document.getElementById("cdm").style.display = "";
    document.getElementById("lw").style.display = "";
    document.getElementById("st").style.display = "";
    document.getElementById("rw").style.display = "";

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

function f442() {
    document.getElementById("lb").style.display = "";
    document.getElementById("rb").style.display = "";
    document.getElementById("lm").style.display = "";
    document.getElementById("rm").style.display = "";
    document.getElementById("lf").style.display = "";
    document.getElementById("rf").style.display = "";

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

function f451() {
    document.getElementById("lb").style.display = "";
    document.getElementById("rb").style.display = "";
    document.getElementById("lm").style.display = "";
    document.getElementById("rm").style.display = "";
    document.getElementById("cam").style.display = "";
    document.getElementById("st").style.display = "";

    document.getElementById("cb").style.display = "none";
    document.getElementById("cb").value = 0;
    document.getElementById("cdm").style.display = "none";
    document.getElementById("cdm").value = 0;
    document.getElementById("lw").style.display = "none";
    document.getElementById("lw").value = 0;
    document.getElementById("lf").style.display = "none";
    document.getElementById("lf").value = 0;
    document.getElementById("rw").style.display = "none";
    document.getElementById("rw").value = 0;
    document.getElementById("rf").style.display = "none";
    document.getElementById("rf").value = 0;
}

function f532() {
    document.getElementById("lb").style.display = "";
    document.getElementById("rb").style.display = "";
    document.getElementById("cb").style.display = "";
    document.getElementById("cam").style.display = "";
    document.getElementById("lf").style.display = "";
    document.getElementById("rf").style.display = "";

    document.getElementById("cdm").style.display = "none";
    document.getElementById("cdm").value = 0;
    document.getElementById("lm").style.display = "none";
    document.getElementById("lm").value = 0;
    document.getElementById("rm").style.display = "none";
    document.getElementById("rm").value = 0;
    document.getElementById("lw").style.display = "none";
    document.getElementById("lw").value = 0;
    document.getElementById("st").style.display = "none";
    document.getElementById("st").value = 0;
    document.getElementById("rw").style.display = "none";
    document.getElementById("rw").value = 0;
}

function f523() {
    document.getElementById("lb").style.display = "";
    document.getElementById("rb").style.display = "";
    document.getElementById("cb").style.display = "";
    document.getElementById("lw").style.display = "";
    document.getElementById("st").style.display = "";
    document.getElementById("rw").style.display = "";

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

function f541() {
    document.getElementById("lb").style.display = "";
    document.getElementById("rb").style.display = "";
    document.getElementById("cb").style.display = "";
    document.getElementById("lm").style.display = "";
    document.getElementById("rm").style.display = "";
    document.getElementById("st").style.display = "";

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
            if (i >= players.length) {//Cambio titular por suplente
                for (i = 0; i < substitutes.length; i++) {
                    if (substitutes[i] === player) {
                        document.getElementById(subPos[i]).value = players[playerIndex];
                        if (players[playerIndex] === document.getElementById("cap").value) {
                            document.getElementById("cap").value = player;
                        }
                        if (players[playerIndex] === document.getElementById("fk").value) {
                            document.getElementById("fk").value = player;
                        }
                        if (players[playerIndex] === document.getElementById("pen").value) {
                            document.getElementById("pen").value = player;
                        }
                        substitutes[i] = players[playerIndex];
                        players[playerIndex] = player;
                        break;
                    }
                }
                if (i >= substitutes.length) {//Cambio titular por reserva
                    if (players[playerIndex] === document.getElementById("cap").value) {
                        document.getElementById("cap").value = player;
                    }
                    if (players[playerIndex] === document.getElementById("fk").value) {
                        document.getElementById("fk").value = player;
                    }
                    if (players[playerIndex] === document.getElementById("pen").value) {
                        document.getElementById("pen").value = player;
                    }
                    players[playerIndex] = player;
                }
            }
    }
    else {//Cambio de suplente con suplente
        playerIndex = subPos.indexOf(position);
            for (i = 0; i < substitutes.length; i++) {
                if (substitutes[i] === player) {
                    document.getElementById(subPos[i]).value = substitutes[playerIndex];
                    substitutes[i] = substitutes[playerIndex];
                    substitutes[playerIndex] = player;
                    break;
                }
            }
            if (i >= substitutes.length) {//Cambio suplente por reserva
                substitutes[playerIndex] = player;
            }

    }
}

function changeFormation(formation) {
    showCurrentFormation(formation);
    getInitialValues();
    var captain = document.getElementById("cap");
    var freekickTaker = document.getElementById("fk");
    var penaltyTaker = document.getElementById("pen");
    var capFound, fkFound, penFound, i;

    for(i=0; i<players.length; i++){
        var p = players[i];
        if(p === captain.value)
            capFound = true;
        if(p === freekickTaker.value)
            fkFound = true;
        if(p === penaltyTaker.value)
            fkFound = true;
    }
    if(!capFound)
        captain.value = 0;
    if(!fkFound)
        freekickTaker.value = 0;
    if(!penFound)
        penaltyTaker.value = 0;
}