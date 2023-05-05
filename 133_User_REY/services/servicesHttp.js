var BASE_URL = "http://localhost:8080/Rest_Admin/";

/*
function login(user, password, loginSuccess, loginError) {
    console.log("serviceHTTP login");
    $.ajax({
        type: "POST",
        dataType: "json",
        data: { type:"checkLogin", user: user, password: password },
        url: BASE_URL,
        xhrFields: {
            withCredentials: true,
        },
        async: false,
        crossDomain: true,
        success: loginSuccess,
        error: loginError
    });
}
*/

function addUser(user, password, loginSuccess, loginError) {
    console.log("serviceHTTP adduser");
    $.ajax({
        type: "POST",
        dataType: "json",
        data: { type:"AddUser", user: user, password: password },
        url: BASE_URL,
        xhrFields: {
            withCredentials: true,
        },
        async: false,
        crossDomain: true,
        success: loginSuccess,
        error: loginError
    });
}


/*
function getUser(user, password, loginSuccess, loginError) {
    console.log("serviceHTTP adduser");
    $.ajax({
        type: "GET",
        dataType: "json",
        data: { type:"AddUser", user: user, password: password },
        url: BASE_URL,
        xhrFields: {
            withCredentials: true,
        },
        async: false,
        crossDomain: true,
        success: loginSuccess,
        error: loginError
    });
}
*/
