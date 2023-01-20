function renderTotalBill(){
    let dataCart = JSON.parse(localStorage.getItem('cart'));

    //tototal amount
    let totalAmountItem = 0;
    if(!dataCart) return;

    dataCart.forEach(item =>{
        totalAmountItem += item.amount;
    });
    $('#checkout-total-amount-item').html(totalAmountItem);

    //render list item
    let html = [];
    html = dataCart.map(item => {
        return `<li>
                    <div>
                     <img src="${item.image}" alt="product"/>
                     <span>${item.amount}</span>
                   </div>
                    <p>${item.nameItem}</p>
                   <span>${formatCurrency(item.price)}đ</span>
                </li>`
    }).join('');
    $('.checkout-list-item').html(html);

    //render total price item
    let totalPrice = 0;
    dataCart.forEach(item => {
        totalPrice += Number(item.price * item.amount);
    });
    $("#checkout-total-price").html(`${formatCurrency(totalPrice)} đ`);

    //render total price item with  transport fee

    $('#checkout-total-price-item-transport-fee').html(`${formatCurrency(totalPrice + 40000)} đ`);

    if(deviceType() === "tablet" || deviceType() === "mobile"){
        $('.checkout-right-wrapper').hide();
    }

}
validCartBeforeLoadPage();
renderTotalBill();


$("#dropmenuBtn").click(function (){
    if($( window ).width() > 991.98) return
    $('.checkout-right-wrapper').toggle();
})

$("input[name=delivery]").click(function (e){
    let price = 0
    if(e.currentTarget.value == "giao_hang_tan_noi"){
     price = 40000;
    }
    $("#checkout-transport-fee").html(price);
    let totalPrice = 0;
    let dataCart = JSON.parse(localStorage.getItem('cart'));
    dataCart.forEach(item => {
        totalPrice += Number(item.price * item.amount);
    });
    $('#checkout-total-price-item-transport-fee').html(`${formatCurrency(totalPrice + Number(price))} đ`);
})

document.getElementById("form-checkout").addEventListener("submit", (e)=>{
    e.preventDefault();
    const data = Object.fromEntries(new FormData(e.target).entries());
    let dataCart = JSON.parse(localStorage.getItem("cart"))
    let listCart = [];

    dataCart.forEach(item =>{
        listCart.push({
            id: Number(item.id),
            amount: item.amount
        })
    })

    data["listProduct"] = listCart;

    postApi("checkout-bills",JSON.stringify(data),(res)=>{
        localStorage.setItem("cart",null)
        Swal.fire(
            'Đã đặt hàng thành công',
            'Cảm ơn bạn đã tin tưởng chúng ',
            'success'
        )
    })

})

function validCartBeforeLoadPage(){
    if(!JSON.parse(localStorage.getItem('cart'))){
       let tagA =  document.createElement("a");
       console.log(window.location.hostname)
       tagA.href = "/";
       tagA.click();
    }
}