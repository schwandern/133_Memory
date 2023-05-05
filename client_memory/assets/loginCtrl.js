const myButton = document.getElementById('login');
myButton.addEventListener('click', function() {
    connect(document.getElementById("username").value,document.getElementById("password").value, loginSuccess, loginError);
});

function loginError(request, status, error) {
    alert("Erreur lors du login");
}

function loginSuccess(data, text, jqXHR) {
    if (data.includes("OK")) {
        window.location.replace("index.html");
    } else {
        alert("Erreur lors du login" + data);

    }
}


$(document).ready(function () {
    $.getScript("assets/servicesHttp.js", function () {
        console.log("servicesHttp.js chargé !");

    });


});