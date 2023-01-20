let dataCart = JSON.parse(localStorage.getItem('cart'));

function renderCartContent(){
    let html = [];
    html = dataCart?.map(item =>{
        return `<tr class="cart-page-item-${item.id}">
                    <td>
                            <a href="${item.url}">
                                <img src="${item.image}" alt="product"/>
                            </a>
                        </td>
                        <td class="table-cart-page-name">
                            <a href="${item.url}">
                                ${item.nameItem}
                            </a>
                        </td>
                        <td class="table-cart-page-price">
                            <span>${formatCurrency(item.price)} đ</span>
                        </td>
                        <td>
                            <input data-itemid="${item.id}"  onchange="handleInputNumberCartHeader(event)" onkeyup="handleInputNumberCartHeader(event)" type="number" class="table-cart-page-input-amount-${item.id}" value="${item.amount}"/>
                        </td>
                        <td class="table-cart-page-price table-cart-page-total-price-${item.id}">
                            ${formatCurrency(item.price * item.amount)} đ
                        </td>
                        <td>
                            <button data-itemid="${item.id}" class="btn" onclick="handleClickBtnRemoveItemHeader(event)">
                                <i class="fa-solid fa-trash"></i>
                            </button>
                        </td>
                    </tr>`
    }).join("");
    if(!html){
        document.querySelector("#cart-page").innerHTML = "<div class='container px-0 text-danger fs-3'> Chưa có sản phẩm </div>";
        return;
    }
    document.querySelector(".table-cart-page tbody").innerHTML = html;
    renderTotalPriceCartPage();
}
function renderTotalPriceCartPage(){
    dataCart = JSON.parse(localStorage.getItem('cart'));
    let tagTotalPrice = document.querySelector('.cart-page-total-price');
    let totalPrice = 0;
    for(let item of dataCart){
        totalPrice += (item.amount * item.price);
    }
    tagTotalPrice.innerHTML = `${new Intl.NumberFormat('de-DE').format(totalPrice)} đ` ;
}


renderCartContent();