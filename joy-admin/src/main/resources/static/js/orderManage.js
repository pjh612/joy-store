let lastId = null;

$(document).ready(function () {
    getOrders(null);
    subscribe();

    // 스크롤 이벤트
    function onScroll() {
        const container = document.getElementById('orderContainer');
        if (container.scrollTop + container.clientHeight >= container.scrollHeight) {
            getOrders(lastId);
        }
    }

    // 스크롤 이벤트 바인딩
    const container = document.getElementById('orderContainer');
    container.addEventListener('scroll', onScroll);
})


const getOrders = (lastId) => {
    let url = `/api/orders?size=10`;
    if (lastId) {
        url += `&lastId=${lastId}`;
    }

    // AJAX 요청
    $.ajax({
        type: "get",
        url: url,
        success: function (data) {
            getOrdersSuccess(data.data);
        },
        error: function (e) {
            alert(e);
        }
    });
};

const getOrdersSuccess = (data) => {
    if (lastId == null && data.length === 0) {
        $("#orderContainer").append("주문 내역이 없습니다.");
        return;
    } else if(data.length === 0) {
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

    lastId = data[data.length - 1].id;
}

const subscribe = () => {
    const alarm = new EventSource("/api/orders/alarm/subscription");
    alarm.addEventListener('connect', e => {
        $(".real-time-status").html("<p>주문 현황 수신 중</p>");
        $(".real-time-status").removeClass("off");
        $(".real-time-status").addClass("on");
    })

    alarm.onerror = (e) => {
        $(".real-time-status").html("<p>주문 현황 수신 연결 중</p>");
        $(".real-time-status").removeClass("on");
        $(".real-time-status").addClass("off");
    }

    alarm.onmessage = (e) => {
        alert(e.data);
    }
}


