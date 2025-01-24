$(document).ready(function () {
    $("#btnRegisterItem").on("click", function () {
        registerItem($("#title").val()
            , $("#description").val()
            , $("#price").val())
    })
})

const registerItem = (title, description, price) => {
    $.ajax({
        type: "post",
        url: "/api/items",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "title": title,
            "description": description,
            "price": price,
        }),
        success: function (data) {
            alert("상품등록이 완료되었습니다.");
        },
        error: function (e) {
            alert("상품등록에 실패했습니다.");
        }
    });
}
