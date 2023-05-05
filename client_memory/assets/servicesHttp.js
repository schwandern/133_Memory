function chargerClassement(successCallback, errorCallback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "https://schwandern.emf-informatique.ch/javaApiGateway/servletGateway?type=getclassement",
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



function sendScore(type, score, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "https://schwandern.emf-informatique.ch/javaApiGateway/servletGateway",
        data: {
            type: type,
            score: score
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

function connect(user, mdp, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        dataType: "text",
        url: "https://schwandern.emf-informatique.ch/javaApiGateway/servletGateway",
        data: {
            type: "checkLogin",
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




