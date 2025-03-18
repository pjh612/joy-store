$(document).ready(function () {
    $("#btn-sign-in").on("click", function () {
        signin($("#username").val(), $("#password").val())
    })
})

const signin = (username, password) => {
    $.ajax({
        type: "post",
        url: "/api/sign-in",
        data: {
            "username": username,
            "password": password
        },
        success: function (data) {
            console.log("로그인 성공")
            document.location = "/";
        },
        error: function (e) {
            if (e && e.responseJSON && e.responseJSON.message) {
                alert(e.responseJSON.message);
            } else {
                alert("시스템 오류입니다. 관리자에게 문의하세요.");
            }
        }
    });
}
