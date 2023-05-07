
function addUser(user, mdp, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "text",
        url: "https://schwandern.emf-informatique.ch/javaApiGateway/servletGateway",
        data: {
            type: "Adduser",
            user: user,
            password: mdp
        },
        xhrFields: {
            withCredentials: true
        },
        async: false,
        crossDomain: true,
        success: successCallback,
        error: errorCallback
    });

}


function chercherUser(username ,successCallback, errorCallback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "https://schwandern.emf-informatique.ch/javaApiGateway/servletGateway?type=getUser&user="+username,
        success: function(response) {
            successCallback(response.test);
        },
        xhrFields: {
            withCredentials: true
        },
        async: false,
        crossDomain: true,
        error: errorCallback
    });
}
