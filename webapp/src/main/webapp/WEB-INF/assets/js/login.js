jQuery(document).ready(function(){

    var error = document.getElementById("error");
    error.style.display = "none";

    if(window.location.href.includes("error")){
        error.style.display = "";
    }

});
