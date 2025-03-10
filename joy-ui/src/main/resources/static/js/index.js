const haruPay = HaruPay.create({
    clientId: '01955c05-cf31-7b68-af5d-c892c0847a70',
    successUrl: window.location.origin + "/pay-success",
    failureUrl: window.location.origin + "/pay-fail"
})

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
                <td><input type="checkbox" class="item-checkbox" value="${item.id}"></td>
                <td><span>${item.id}</span></td>
                <td><span class="item-title">${item.title}</span></td>
                <td><span>${item.description}</span></td>
                <td><span>${item.price.toLocaleString()}</span></td>
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
    $("#submitOrderButton").on("click", (e) => {
        prepareOrder(e)
    });
}

const getOrderItemName = () => {
    const $item = $(".item-checkbox:checked");
    if (!$item) {
        return null;
    }

    const size = $item.length;
    const title = $item.closest("tr").find(".item-title").text();
    if (size >= 2) {
        return title + "외 " + size + "건";
    }
    return title;
}

const createOrderItemParameter = () => {
    const selectedItems = [];

    $(".item-checkbox:checked").each(function () {
        const itemId = $(this).val();
        const quantity = $(this).closest("tr").find(".item-quantity").val();
        const orderItem = {
            itemId: itemId,
            quantity: Number(quantity)
        };
        selectedItems.push(orderItem);
    });

    return selectedItems;
};

const prepareOrder = (e) => {
    const orderItems = createOrderItemParameter();
    if (orderItems == null || orderItems.length === 0) {
        alert("주문할 상품을 선택해주세요.");
        return;
    }

    $.ajax({
        type: "post",
        url: "/api/orders/prepare",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "orderItems": orderItems,
            "payType": "HARU_PAY"
        }),
        success: function (data) {
            const response = data.data;
            const amount = response.amount;
            const orderId = response.id;
            const productName = getOrderItemName();
            pay(orderId, amount, productName);

        },
        error: function (e) {
            alert("주문에 실패했습니다.");
        }
    });
}


const pay = (orderId, amount, productName) => {
    haruPay.open({
        "productName": productName,
        "amount": amount,
        "orderId": orderId
    });
    // $.ajax({
    //     type: "post",
    //     url: `/api/payment/prepare`,
    //     contentType: "application/json; charset=utf-8",
    //     data: JSON.stringify({
    //         "orderId": orderId,
    //         "requestPrice": amount,
    //         "productName": productName
    //     }),
    //     success: function (data) {
    //         haruPay.open({
    //             "productName": productName,
    //             "requestPrice": amount,
    //             "paymentId": data.paymentId
    //         });
    //     },
    //     error: function (e) {
    //         alert("결제에 실패했습니다.");
    //     }
    // });
}
