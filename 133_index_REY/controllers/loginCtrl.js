const myButton = document.getElementById('login');
myButton.addEventListener('click', function() {
    console.log("mybutton");
    login(document.getElementById("user").value,document.getElementById("password").value, loginSuccess, loginError);

});

function loginError(request, status, error) {
    alert("Erreur lors du login");


    
}

function loginSuccess(data, text, jqXHR) {
    if ($(data).find("result").text() == 'true')
    {
        //alert("Login ok");
        //const login = document.getElementById('logina');
        //login.innerHTML = $(data).find("user").text();
        window.location.replace("index.html");

    }
    else{
        alert("Erreur lors du login");
    }
}

$(document).ready(function () {
    $.getScript("./services/servicesHttp.js", function () {
        console.log("servicesHttp.js chargé !");
    });
});