$(document).ready(function () {
    $("#btn-sign-up").on("click", function () {
        //validation

        signup($("#username").val()
            , $("#password").val()
            , $("#name").val()
            , $("#gender").val())
    })
})

const signup = (username, password, name, gender) => {
    $.ajax({
        type: "post",
        url: "/api/sign-up",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "username": username,
            "password": password,
            "name": name,
            "gender": gender
        }),
        success: function (data) {
            alert("회원가입이 완료되었습니다 로그인해주세요.");
            document.location = "/";
        },
        error: function (e) {
            alert("회원가입에 실패했습니다.");
        }
    });
}
