function chargerClassement(successCallback, errorCallback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://localhost:8080/ApiGateway/servletGateway?type=getclassement",
        success: function(response) {
            successCallback(response.test);
        },
        error: errorCallback
    });
}