$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const orderId = urlParams.get("orderId");
    const paymentId = urlParams.get("paymentId");
    const requestPrice = urlParams.get("requestPrice");
    const paymentIdElement = document.getElementById("paymentId");
    const requestPriceElement = document.getElementById("amount");

    paymentIdElement.textContent = paymentId;
    requestPriceElement.textContent = `${requestPrice}원`;

    const confirmLoadingSection = document.querySelector('.confirm-loading');
    const confirmingSection = document.querySelector('.confirming');
    const confirmSuccessSection = document.querySelector('.confirm-success');

    const subscribe = () => {
        confirmLoadingSection.style.display = 'none';
        confirmingSection.style.display = 'flex';
        const alarm = new EventSource(`/api/payment/result/subscribe?paymentId=` + paymentId);
        alarm.addEventListener('connect', e => {
        })

        alarm.onerror = (e) => {
        }

        alarm.onmessage = (e) => {
            confirmLoadingSection.style.display = 'none';
            confirmingSection.style.display = 'none';
            confirmSuccessSection.style.display = 'flex';
            confirmOrder(orderId, requestPrice)
            alarm.close();
        }
    }

    $('#confirmPay').on("click", function () {
        $.ajax({
            type: "post",
            url: `/api/payment/confirm`,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                "paymentId": paymentId
            }),
            success: function (data) {
                subscribe();
            },
            error: function (e) {
                alert("결제에 실패했습니다.");
            }
        });


    })

    const confirmOrder = (orderId, amount) => {
        $.ajax({
            type: "post",
            url: "/api/orders",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                orderId: orderId,
                amount: amount
            }),
            success: function (data) {
                alert("주문이 완료되었습니다.");
                window.opener.location.reload();
            },
            error: function (e) {
                alert("주문에 실패했습니다.");
            }
        });
    }

    $("#close").on("click", function(){
        window.close();
    })
});
