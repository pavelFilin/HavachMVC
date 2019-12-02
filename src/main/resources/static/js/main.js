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
})


