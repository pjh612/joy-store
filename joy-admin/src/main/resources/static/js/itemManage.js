$(document).ready(function () {
    getAllItems(0);
})

const getAllItems = (page) => {
    $.ajax({
        type: "get",
        url: `/api/items?page=${page}&size=100`,
        success: function (data) {
            getAllItemsSuccess(data);
        },
        error: function (e) {
            alert(e);
        }
    });
}

const getAllItemsSuccess = (data) => {
    $("#itemContainer").empty();

    if (data.content.length === 0) {
        $("#itemContainer").append("판매 상품 목록이 없습니다.");
        return;
    }

    let html = `
        <div style="display:inline-block; width:auto;">
            <table border="1" cellspacing="0" cellpadding="5" style="width:100%;">
                <tr>
                    <th>선택</th>
                    <th>상품 ID</th>
                    <th>상품명</th>
                    <th>상품 설명</th>
                    <th>상품 가격</th>
                    <th>상품등록일</th>
                </tr>`;

    data.content.forEach((item) => {
        html += `
            <tr>
                <td><input type="checkbox" class="item-checkbox" value="${item.id}"></td>
                <td><span>${item.id}</span></td>
                <td><span>${item.title}</span></td>
                <td><span>${item.description}</span></td>
                <td><span>${item.price.toLocaleString()}</span></td>
                <td><span>${item.createdAt}</span></td>           
            </tr>`;
    });

    html += `</table>`;

    $("#itemContainer").append(html);

    let paginationHtml = '<div class="pagination">';
    for (let i = 0; i < data.page.totalPages; i++) {
        paginationHtml += `<button class="page-btn" data-page="${i}">${i + 1}</button>`;
    }
    paginationHtml += '</div>';
    $("#itemContainer").append(paginationHtml);

    $(".page-btn").click(function () {
        const page = $(this).data("page");
        getAllItems(page);
    });
}
