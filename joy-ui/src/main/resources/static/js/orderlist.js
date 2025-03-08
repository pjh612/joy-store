$(document).ready(function () {
    getOrders();

    $("#loadMoreBtn").on("click", function () {
        getOrders();
    });
})

let lastId = null;

const getOrders = () => {
    $.ajax({
        type: "get",
        url: "/api/orders",
        data: {
            "lastId": lastId
        },
        success: function (data) {
            getOrdersSuccess(data.data);
        },
        error: function (e) {
            alert(e);
        }
    });
}

const getOrdersSuccess = (data) => {
    if (lastId == null && data.length === 0) {
        $("#orderContainer").append("주문 내역이 없습니다.");
        return;
    }

    const length = data.length;
    if (length === 0) {
        $("#loadMoreContainer").hide(); // 데이터가 없으면 더보기 버튼 숨기기
        return;
    }
    lastId = data[length - 1].id;


    let html = `<div class="order-block">`;

    data.forEach((order) => {
        html += `
            <h4>주문 일시: <span>${order.createdAt}</span></h4>
            <h4>주문 상태: <span>${order.status}</span></h4>
            <table>
                <thead>
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

        html += `</tbody></table>`;
    });

    html += `</div>`;

    $("#orderContainer").append(html);
}