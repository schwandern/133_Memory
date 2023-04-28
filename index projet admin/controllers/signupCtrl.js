const myButton = document.getElementById('signup');
myButton.addEventListener('click', function() {
    createAccount(document.getElementById("user").value,document.getElementById("passwd").value, signUpSuccess, signUpError)


});

function signUpError(request, status, error) {
    alert("Erreur lors de l'enregistrement");
}

function signUpSuccess(data, text, jqXHR) {
    if ($(data).find("result").text() == 'true')
    {
        alert("Sign-Up ok");
        window.location.replace("login.html");
    }
    else{
        alert("Erreur lors de l'enregistrement");
    }
}


$(document).ready(function () {
    $.getScript("js/services/servicesHttp.js", function () {
        console.log("servicesHttp.js chargé !");

    });


});