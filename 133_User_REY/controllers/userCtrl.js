const myButton = document.getElementById('adduser');
const myButton2 = document.getElementById('recherche');
myButton.addEventListener('click', function() {
    addUser(document.getElementById("user").value,document.getElementById("password").value, successCallback, errorCallback)

});

myButton2.addEventListener('click', function() {
    chercherUser(document.getElementById("search").value, successCallback2, errorCallback2)

});


function errorCallback2(request, status, error) {
    alert("Erreur lors de la recherche");
}

function successCallback2(data, text, jqXHR) {
    console.log(data)
}

function errorCallback(request, status, error) {
    alert("Erreur lors de l'enregistrement");
}

function successCallback(data, text, jqXHR) {
    if (data.includes("OK"))
    {
        alert("Sign-Up ok");
    }
    else{
        alert("Erreur lors de l'enregistrement");
    }
}


$(document).ready(function () {
    $.getScript("./services/servicesHttp.js", function () {
        console.log("servicesHttp.js chargé !");

    });

});