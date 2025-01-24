$(document).ready(function () {
    getOrders();
})


const getOrders = () => {
    $.ajax({
        type: "get",
        url: "/api/orders",
        success: function (data) {
            getOrdersSuccess(data.data);
        },
        error: function (e) {
            alert(e);
        }
    });
}

const getOrdersSuccess = (data) => {
    $("#orderContainer").empty();

    if (data.length === 0) {
        $("#orderContainer").append("주문 내역이 없습니다.");
        return;
    }

    let html = `<table border="1" cellspacing="0" cellpadding="5">`;
    data.forEach((order) => {
        html += `
            <thead>
                <tr>
                    <th colspan="6">주문 일시: <span id="orderAt">${order.createdAt}</span></th>
                </tr>
                <tr>
                    <th colspan="6">주문 상태: <span id="orderStatus">${order.status}</span></th>
                </tr>
                <tr>
                    <th>상품명</th>
                    <th>수량</th>
                    <th>단가</th>
                    <th>총액</th>
                    <th>할인가</th>
                    <th>최종금액</th>
                </tr>
            </thead>
            <tbody>`;

        order.orderItems.forEach((orderItem) => {
            const totalPrice = orderItem.quantity * orderItem.unitPrice; // 총액
            const finalPrice = totalPrice - orderItem.discountAmount;   // 최종금액

            html += `
                <tr>
                    <td>${orderItem.item.title}</td>
                    <td>${orderItem.quantity.toLocaleString()}</td>
                    <td>${orderItem.unitPrice.toLocaleString()}</td>
                    <td>${totalPrice.toLocaleString()}</td>
                    <td>${orderItem.discountAmount == null ? 0 : orderItem.discountAmount.toLocaleString()}</td>
                    <td>${finalPrice.toLocaleString()}</td>
                </tr>`;
        });

        html += `</tbody>`;
    });

    html += `</table>`;

    $("#orderContainer").append(html);
}


