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
    const confirmResultSection = document.querySelector('.confirm-result');
    const confirmSuccessSection = document.querySelector('.confirm-success');
    const confirmFailureSection = document.querySelector('.confirm-failure');
    const confirmFailureReasonSection = document.querySelector('.confirm-failure-reason');
    const responseReason = document.querySelector('.response-reason');

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
            confirmResultSection.style.display= 'flex';
            const json = JSON.parse(e.data);

            if(json.orderStatus === "FAILED") {
                confirmFailureSection.style.display = 'flex';
                confirmFailureReasonSection.style.display = 'flex';
                responseReason.textContent = json.errorMsg;
                alert("주문이 실패했습니다.");
            } else {
                confirmSuccessSection.style.display = 'flex';

                alert("주문이 완료되었습니다.");
            }
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

    $("#close").on("click", function(){
        window.close();
    })
});
