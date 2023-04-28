function login( user,password,loginSuccess, loginError) {
    console.log("serviceHTTP login");
    $.ajax({
        type: "POST",
        url: "../" +BASE_URL + "loginManager.php",
        data: 'user=' + user + "&password=" + password + "&action=" + "checkLogin",
        success: loginSuccess,
        error: loginError
    });
}