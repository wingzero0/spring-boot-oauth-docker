$(document).ready(function(){
    var checkPasswordMatch = function () {
        var newPassword = $("#newPassword").val();
        var repeatPassword = $("#repeatPassword").val();

        if (newPassword !== repeatPassword) {
            $("#divCheckPasswordMatch").show();
            $("#submitButton").prop("disabled", true);
        }
        else {
            $("#divCheckPasswordMatch").hide();
            $("#submitButton").prop("disabled", false);
        }

    };
    checkPasswordMatch();

    var checkUsername = function (){
        var username = $("#username");
        var targetUrl = username.attr("data-url");
        if (!username.is('[readonly]')){
            $.ajax({
                type: 'GET',
                url: targetUrl,
                data:{
                    username : username.val(),
                },
                success: function (ret) {
                    if (ret.ret === false){
                        $("#duplicateUsernameDiv").show();
                    } else {
                        $("#duplicateUsernameDiv").hide();
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                    console.log(textStatus);
                    console.log(errorThrown);
                    alert(jqXHR.status + ":" + textStatus);
                },
                dataType: 'json'
            });
        }
    };
    $("#newPassword, #repeatPassword").keyup(checkPasswordMatch);
    $("#username").blur(checkUsername);
});
