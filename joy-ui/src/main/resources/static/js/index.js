$(document).ready(function () {
    getOrders();
    showAllItems();
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
                <td><span>${item.title}</span></td>
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
    $("#submitOrderButton").on("click", placeOrder);
}

const placeOrder = () => {
    const orderItems = createOrderItemParameter();

    if (orderItems.length === 0) {
        alert('하나 이상의 상품을 선택해주세요.');
        return;
    }
    $.ajax({
        type: "post",
        url: "/api/orders",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "orderItems": orderItems
        }),
        success: function (data) {
            alert("주문이 완료되었습니다.");
            location.reload();
        },
        error: function (e) {
            alert("주문에 실패했습니다.");
        }
    });
}

const createOrderItemParameter = () => {
    const selectedItems = [];

    $(".item-checkbox:checked").each(function () {
        const itemSeq = $(this).val();
        const quantity = $(this).closest("tr").find(".item-quantity").val();
        const orderItem = {
            itemSeq: Number(itemSeq),
            quantity: Number(quantity)
        };
        selectedItems.push(orderItem);
    });

    return selectedItems;
};
