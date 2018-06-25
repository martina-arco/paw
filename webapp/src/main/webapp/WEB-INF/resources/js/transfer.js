
$(document).ready(function(){
    $("#filterForm").submit(function(e) {
        var myNode = document.getElementById("players");
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild);
        }

        var url = "/transferFilter";
        $.ajax({
            type: "POST",
            url: url,
            data: $("#filterForm").serialize(),
            dataType: "json",
            success: function(result){
                manageData(result)
            }
        });

        e.preventDefault();
    });
});

function manageData(json){
    console.log(json);
    var container = document.getElementById("players")

    for(var index in json){
        var playerDTO = json[index];
        var playerDiv = document.createElement("div");

        for(var att in playerDTO){
            if(att !== 'id') {
                var span = document.createElement("span");
                span.innerHTML = playerDTO[att];
                playerDiv.appendChild(span);
            }
        }

        var buy = document.createElement("button");
        buy.setAttribute("id", playerDTO['id']);
        buy.setAttribute("type", "button");
        buy.onclick = function (e) {
            console.log(e.srcElement.id);
            $.ajax({
                type: "POST",
                url: '/transferPlayer',
                data: e.srcElement.id,
            });
        };
        buy.innerHTML = "Buy";
        playerDiv.appendChild(buy);

        container.appendChild(playerDiv);
    }
}