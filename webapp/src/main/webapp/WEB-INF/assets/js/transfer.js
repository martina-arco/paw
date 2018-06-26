
jQuery(document).ready(function(){
    jQuery("#filterForm").submit(function(e) {
        var myNode = document.getElementById("players");
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild);
        }

        var url = "/transferFilter";
        jQuery.ajax({
            type: "POST",
            url: url,
            data: jQuery("#filterForm").serialize(),
            dataType: "json",
            success: function(result){
                manageData(result)
            }
        });

        e.preventDefault();
    });
});

function manageData(json){
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
            jQuery.ajax({
                type: "POST",
                url: '/transferPlayer',
                data: e.srcElement.id,
                success: function(result){
                    var popup = jQuery('#staticModal');
                    var button = jQuery('#returnButton');
                    var content = jQuery('#modalContent');
                    console.log(content);
                    if(!result){
                        content.innerHTML = "Transfer failed. Insufficient funds.";
                        button.innerHTML = "Retry";
                        button.onclick = function () {
                            popup.modal('show');
                        }
                    } else {
                        content.innerHTML = "Transfer successful!";
                        button.innerHTML = "Return to Home";
                        button.onclick = function () {
                            window.location.href = "../home";
                        }
                    }
                    popup.modal('show');
                }
            });
        };
        buy.innerHTML = "Buy";
        playerDiv.appendChild(buy);

        container.appendChild(playerDiv);
    }
}