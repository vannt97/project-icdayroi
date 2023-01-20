function handleClickNumberInput(event){
    //handle logic
    let listData = JSON.parse(localStorage.getItem('cart'));
    let itemId = event.currentTarget.dataset.itemid;
    let index = listData.findIndex(item => item.id === itemId)
    listData[index].amount = event.currentTarget.value;

    //handle render
    //handle render total price
    renderTotalPrice(listData)

    //handle render total amount item
    renderTotalAmountItem(listData)

    //store
    localStorage.setItem("cart", JSON.stringify(listData))

}

function handleClickBtnRemoveItem(event){
    let listData = JSON.parse(localStorage.getItem('cart'));
    let itemId = event.currentTarget.dataset.itemid;
    listData = listData.filter(item => item.id !== itemId)
    renderTotalPrice(listData)
    renderTotalAmountItem(listData)
    renderListItem(listData)
    if(listData.length == 0){
        $("#cart-modal").modal("hide");
    }
    localStorage.setItem("cart", JSON.stringify(listData))
}

function renderTotalPrice(listData){
    let tagTotalPrice = document.querySelector('#cart-modal-total-price');
    let totalPrice = 0;
    for(let item of listData){
        totalPrice += (item.amount * item.price);
    }
    tagTotalPrice.innerHTML = `${new Intl.NumberFormat('de-DE').format(totalPrice)} đ` ;
}

function renderTotalAmountItem(listDataItem){
    let tagAmount = document.querySelector('#cart-modal-total-item');
    let totalAmount = 0;
    for(let item of listDataItem){
        totalAmount += Number(item.amount);
    }
    tagAmount.innerHTML = totalAmount;
}

function renderListItem(listDataItem,itemId=null){
    let html = listDataItem.map( item => {
        return `<div class="cart-modal-row">
                        <div class="cart-modal-row-item">
                            <a href="${item.url}"><img src="${item.image}" alt="item"/></a>
                            <div>
                                <a href="${item.url}" class="text-black">${item.nameItem}</a>
                                <button onclick="handleClickBtnRemoveItem(event)" data-itemId="${item.id}">
                                    <span><i class="fa-solid fa-xmark"></i></span>
                                    Bỏ sản phẩm
                                </button>
                                ${itemId === item.id ? `<p><i class="fa-solid fa-check text-success"></i> Sản phẩm vừa thêm vào giỏ hàng</p>`: ''}
                            </div>
                        </div>
                        <div class="cart-modal-row-price">
                            ${new Intl.NumberFormat('de-DE').format(item.price)} đ
                        </div>
                        <div class="cart-modal-amount">
                            <input onchange="handleClickNumberInput(event)" data-itemId="${item.id}" min="1" type="number" value="${item.amount}">
                        </div>
                        <div class="cart-modal-row-total">
                            ${new Intl.NumberFormat('de-DE').format(item.price * item.amount)} đ 
                        </div>
                    </div>`
    }).join('');
    let modalTable = document.querySelector(".cart-modal-table");
    modalTable.innerHTML = html;
}

function getDataFromProduct(event){
    //get data
    let index =  event.relatedTarget.dataset.indexitem;
    let nameItem = document.querySelector(`.home-main-product-${index} > a > p`).textContent;
    let image = document.
    querySelector(`.home-main-product-${index} > a > img`).src;
    let price = document
        .querySelector(`.home-main-product-${index}
        .home-main-product-price > span`).dataset.price;
    let amount = 1;
    let url = document.querySelector(`.home-main-product-${index} > a`).href
    let dataItem = {
        id: index,
        nameItem,
        image,
        price,
        amount,
        url
    }
    return dataItem;
}

function handleAddDataToListDataFromCart(dataItem){
    let listDataItem = JSON.parse(localStorage.getItem("cart"));
    if(listDataItem == null){
        listDataItem = []
        listDataItem.push(dataItem)
    }else {
        let isSame = false;
        for (let item of listDataItem){
            if(item.id === dataItem.id){
                isSame = true;
                break;
            }
        }
        if(isSame){
            let indexItem = listDataItem.findIndex(item => item.id === dataItem.id);
            listDataItem[indexItem].amount = Number(listDataItem[indexItem].amount) + 1;
        }else {
            listDataItem.push(dataItem);
        }
    }
    localStorage.setItem("cart", JSON.stringify(listDataItem));
    return listDataItem;
}

let cartModal = document.getElementById("cart-modal");
cartModal?.addEventListener('show.bs.modal', function (event) {
        //get data
        let dataItem = getDataFromProduct(event);

        //handle store localstore
        let listDataItem = handleAddDataToListDataFromCart(dataItem);

        //render list item
        renderListItem(listDataItem,dataItem.id);

        //render name item and url
        let tagName = document.querySelector("#cart-modal-name-item");
        tagName.innerHTML = dataItem.nameItem;
        tagName.href = dataItem.url;

        //render header
        renderTotalAmountItemHeader(listDataItem);
        renderCartPopup();
        //    render total amount item
        renderTotalAmountItem(listDataItem);

        //render total price
        renderTotalPrice(listDataItem)

    });

let cartSuccessModal = document.getElementById("modal-add-cart-success");
cartSuccessModal?.addEventListener('show.bs.modal', function (event){
    let dataItem = getDataFromProduct(event);
    let listDataItem = handleAddDataToListDataFromCart(dataItem);

    //render header
    renderTotalAmountItemHeader(listDataItem);
    renderCartPopup();

    //render cart
    renderAddProductToCartSuccess(dataItem)
})

function renderAddProductToCartSuccess(dataItem){
    let tagName = document.getElementById('modal-add-cart-success-name');
    tagName.innerHTML = dataItem.nameItem;

    let tagPrice = document.getElementById('modal-add-cart-success-price');
    tagPrice.innerHTML = `${new Intl.NumberFormat('de-DE').format(dataItem.price)} đ` ;

    let tagImage = document.querySelector('#modal-add-cart-success img');
    tagImage.src = dataItem.image;
}