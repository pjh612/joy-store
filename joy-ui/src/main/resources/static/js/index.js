$(document).ready(function () {
    showAllItems();
})

const showAllItems = () => {
    $.ajax({
        type: "get",
        url: "/api/items",
        success: function (data) {
            showAllItemsSuccess(data.data);
        },
        error: function (e) {
            alert(e);
        }
    });
}


const showAllItemsSuccess = (data) => {
    $("#itemContainer").empty();

    if (data.length === 0) {
        $("#itemContainer").append("상품 목록이 없습니다.");
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
                    <th>수량</th>
                </tr>`;

    data.forEach((item) => {
        html += `
            <tr>
                <td><input type="checkbox" class="item-checkbox" value="${item.sequence}"></td>
                <td><span>${item.sequence}</span></td>
                <td><span>${item.title}</span></td>
                <td><span>${item.description}</span></td>
                <td><span>${item.price}</span></td>
                <td><span>${item.createdAt}</span></td>
                <td>
                    <select class="item-quantity">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </td>
            </tr>`;
    });

    html += `</table>`;
    html += `<div style="text-align:right; margin-top:10px;">
                <button id="submitOrderButton" class="btn">주문</button>
            </div>`;

    $("#itemContainer").append(html);
    $("#submitOrderButton").on("click", placeOrder);
}

const placeOrder = () => {
}
