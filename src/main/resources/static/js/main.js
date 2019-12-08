const $productFileInput = $("#product-photo-file-input");
const $productFileLabel = $("#product-photo-file-label");

$productFileInput.change(function () {
    let file = $productFileInput[0].files[0];
    $productFileLabel.text(file.name);
});

function resetFile() {
    $productFileInput.val('');
    $productFileLabel.text('Choose file');
}

$("#product-photo-reset").click(function () {
    resetFile();
});


function changeOrderStatus(input, orderId) {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $.ajax({
        method: "POST",
        url: "/order/change/orderstatus",
        data:
        // data: JSON.stringify({
        //     id: orderId, orderStatus: input.value
        // }),
        {id: orderId, orderStatus: input.value},
        dataType: 'json',
        success: function (data) {

        },
        error: function () {
        }
    });
}


