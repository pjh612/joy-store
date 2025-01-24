$(document).ready(function () {
    getAllItems();
})

const getAllItems = () => {
    $.ajax({
        type: "get",
        url: "/api/items",
        success: function (data) {
            getAllItemsSuccess(data.data);
        },
        error: function (e) {
            alert(e);
        }
    });
}

const getAllItemsSuccess = (data) => {
    $("#itemContainer").empty();

    if (data.length === 0) {
        $("#itemContainer").append("판매 상품 목록이 없습니다.");
        return;
    }

    let html = `
        <div style="display:inline-block; width:auto;">
            <table border="1" cellspacing="0" cellpadding="5" style="width:100%;">
                <tr>
                    <th>선택</th>
                    <th>상품 번호</th>
                    <th>상품명</th>
                    <th>상품 설명</th>
                    <th>상품 가격</th>
                    <th>상품등록일</th>
                </tr>`;

    data.forEach((item) => {
        html += `
            <tr>
                <td><input type="checkbox" class="item-checkbox" value="${item.sequence}"></td>
                <td><span>${item.sequence}</span></td>
                <td><span>${item.title}</span></td>
                <td><span>${item.description}</span></td>
                <td><span>${item.price.toLocaleString()}</span></td>
                <td><span>${item.createdAt}</span></td>           
            </tr>`;
    });

    html += `</table>`;

    $("#itemContainer").append(html);
}
