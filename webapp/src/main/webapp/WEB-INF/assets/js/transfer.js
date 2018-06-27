
jQuery(document).ready(function(){
    jQuery("#filterForm").submit(function(e) {
        if(dt != undefined) {
            dt.destroy();
        }
        var myNode = document.getElementById("players");
        while (myNode.firstChild) {
            myNode.removeChild(myNode.firstChild);
        }

        var url = document.getElementById("filterURL").innerHTML;
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

var dt;

function manageData(json){

    var container = document.getElementById("players")

    for(var index in json){
        var playerDTO = json[index];
        var playerDiv = document.createElement("tr");

        for(var att in playerDTO){
            if(att !== 'id') {
                var span = document.createElement("td");
                span.innerHTML = playerDTO[att];
                playerDiv.appendChild(span);
            }
        }

        var td = document.createElement("td");
        var buy = document.createElement("button");
        buy.setAttribute("id", playerDTO['id']);
        buy.setAttribute("type", "button");
        buy.onclick = function (e) {
            jQuery.ajax({
                type: "POST",
                url: document.getElementById("transferURL").innerHTML,
                data: e.srcElement.id,
                success: function(result){
                    var popup = jQuery('#staticModal');
                    var button = jQuery('#returnButton');
                    var content = jQuery('#modalContent');
                    if(!result){
                        content.html(jQuery('#failMessage').html());
                        button.html(jQuery('#retry').html());
                        button.click(function () {
                            popup.modal('show');
                        });
                    } else {
                        content.html(jQuery('#successMessage').html());
                        button.html(jQuery('#confirm').html());
                        button.click(function () {
                            window.location.href = "../";
                        });
                    }
                    popup.modal('show');
                }
            });
        };
        buy.innerHTML = "Buy";
        td.appendChild(buy);
        playerDiv.appendChild(td);

        container.appendChild(playerDiv);
    }

    var options = {
        "language": {
            "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                "sFirst":    "Primero",
                "sLast":     "Último",
                "sNext":     "Siguiente",
                "sPrevious": "Anterior"
            },
            "oAria": {
                "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }
        }
    };

    switch (container.dataset.lang) {
        case 'en':
            options = {};
            break;
    }

    dt = jQuery('#players-data-table').DataTable(options);
}