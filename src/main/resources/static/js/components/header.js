document.getElementById("header-navbar-link-all-product")?.addEventListener("click",(e)=>{
    const ele_a = document.createElement("a")
    ele_a.setAttribute("href","/products");
    ele_a.click();
    ele_a.remove();
})

document.querySelectorAll(".nav-link-dropdown")?.forEach(ele =>{
    ele.addEventListener("click", e =>{
        const eleDropDownParent = document.querySelector(".dropdown-menu-parent");
        eleDropDownParent.style.display = "none";
    })
})

//droppdown child
document.querySelectorAll(".header-navbar .dropdown-item.dropdown-toggle > a").forEach((ele)=>{
    ele.addEventListener("click", (e) =>{
        const ele_a = document.createElement("a")
        ele_a.setAttribute("href",e.currentTarget.getAttribute("href"));
        ele_a.click();
        ele_a.remove();
    })
})

// document.getElementById("btn-header-search").addEventListener('click',()=>{
//     let value = document.getElementById('header-main-center-search').value;
//     let tagA = document.createElement("a");
//     tagA.href = `search-product?name=${value}`;
//     tagA.click();
// })

//render UI
function renderTotalPriceCartHeader(listData){
    let tagTotalPrice = document.querySelector('#cart-popup-total-price');
    let totalPrice = 0;
    for(let item of listData){
        totalPrice += (item.amount * item.price);
    }
    tagTotalPrice.innerHTML = `${new Intl.NumberFormat('de-DE').format(totalPrice)} đ` ;
}
function renderTotalAmountItemHeader(listDataItem){
    let tagAmounts = document.querySelectorAll('.numbers-item-in-cart');
    if(tagAmounts == null) return;
    let totalAmount = 0;
    for(let item of listDataItem){
        totalAmount += Number(item.amount);
    }
    tagAmounts.forEach(ele => {
        ele.innerHTML = totalAmount;
    })
}
function renderCartPopup(){
    let listData = JSON.parse(localStorage.getItem('cart'))
    let html = [];
    if(document.querySelector('.cart-popup-content') == null) return
    if(listData == null || listData.length == 0){
        document.querySelector('.cart-popup').classList.add('empty');
    }else {
        html = listData.map(item => {
            return `<li class="cart-list-item">
                                        <div>
                                            <a href="${item.url}">
                                                <img src="${item.image}" alt="img"/>
                                            </a>
                                            <div>
                                                <a href="${item.url}">
                                                    ${item.nameItem}
                                                </a>
                                                <span>${new Intl.NumberFormat('de-DE').format(item.price)} đ</span>
                                                <input onkeyup="handleInputNumberCartHeader(event)" onchange="handleInputNumberCartHeader(event)" data-itemId="${item.id}" type="number" value="${item.amount}" name="" min="1"/>
                                            </div>
                                            <button class="cart-item-close" data-itemId="${item.id}" onclick="handleClickBtnRemoveItemHeader(event)">
                                                <i class="fa-solid fa-xmark"></i>
                                            </button>
                            </div>
                     </li>`
        }).join('');
        document.querySelector('.cart-popup')?.classList.remove('empty');

        document.querySelector('.cart-popup-content').innerHTML = html;

        renderTotalPriceCartHeader(listData);
        renderTotalAmountItemHeader(listData)
    }



}

// handle input number header
function handleInputNumberCartHeader(event){
    let listData = JSON.parse(localStorage.getItem('cart'));
    let itemId = event.currentTarget.dataset.itemid;
    let index = listData.findIndex(item => item.id === itemId)
    listData[index].amount = event.currentTarget.value;
    localStorage.setItem("cart", JSON.stringify(listData))

    renderTotalPriceCartHeader(listData)
    renderTotalAmountItemHeader(listData);

    if(document.querySelector(`.table-cart-page-total-price-${itemId}`) != null){
        document.querySelector(`.table-cart-page-input-amount-${itemId}`).value = event.currentTarget.value
        document.querySelector(`.table-cart-page-total-price-${itemId}`).innerHTML = formatCurrency(listData[index].amount * listData[index].price) + 'đ';
        renderTotalPriceCartPage();
    }
}
function handleClickBtnRemoveItemHeader(event){
    let listData = JSON.parse(localStorage.getItem('cart'));
    let itemId = event.currentTarget.dataset.itemid;
    listData = listData.filter(item => item.id !== itemId)
    localStorage.setItem("cart", JSON.stringify(listData))
    renderCartPopup();
    renderTotalAmountItemHeader(listData);

    if(document.querySelector(`.cart-page-item-${itemId}`) != null){
        document.querySelector(`.cart-page-item-${itemId}`).remove();
        renderTotalPriceCartPage();
    }
}

// handleSearch
let inputSearchProduct = document.getElementById('header-main-center-search');
let eventTime = null;
inputSearchProduct?.addEventListener('keyup',(e)=>{
    handleSearch(e,'.header-list-search');
})

let inputSearchProductMobile = document.getElementById('header-research-mobile-search');
inputSearchProductMobile?.addEventListener('keyup',(e)=>{
    handleSearch(e,'.header-list-search-mobile');
})

function handleSearch(e,nameClass){
    if(eventTime){
        clearTimeout(eventTime);
    }
    let value = e.currentTarget.value;
    if(value == '') {
        document.querySelector(nameClass).innerHTML = "";
        return;
    };
    eventTime = setTimeout(()=>{
        getApi(`products?search=${value}`,(res)=>{
            let listData = res.data;
            if(listData.length === 0){
                document.querySelector(nameClass).innerHTML = '';
                return;
            }
            let html = listData.map(data => {
                return `<li>
                                    <a href="${data.slug}">
                                        <img src="${'images/' + data.image}">
                                        <span>${data.name}</span>
                                        <span>${new Intl.NumberFormat('de-DE').format(data.price)} đ</span>
                                    </a>
                                </li>`
            }).join('')
            document.querySelector(nameClass).innerHTML = html;
        })
    },300);
}
//
renderCartPopup();
