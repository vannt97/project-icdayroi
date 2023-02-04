
document.getElementById("breadcrumb").style.display = "none";

function renderData(){
    let dataUser = JSON.parse(localStorage.getItem("user"));

    if(!dataUser){
        window.location.href = "/signin";
    }

    if(dataUser?.name){
        $(".info-user-name").html(`Tên tài khoản: <span>${dataUser.name}</span>`)
    }

    if(dataUser.address){
        $("#info-user-address").html(`Địa chỉ: <span>${dataUser.address}</span>`)
    }

    if(dataUser.city){
        $("#info-user-city").html(`Thành phố: <span>${dataUser.city}</span>`)
    }

    if(dataUser.phoneNumber){
        $("#info-user-phone").html(`Số điện thoại: <span>${dataUser.phoneNumber}</span>`)
    }

}

renderData();

const nameUrl = "history-transaction";
const dataUser = JSON.parse(localStorage.getItem("user"))
let currentPage = 0;
let totalPages = 0;
$( async function (){
    $("#table-info-user-bills").empty();
    $("#info-user-loading").addClass("loading")
    getApi(nameUrl,(res)=>{
        renderUITable(res.data.bills);
        $("#info-user-loading").removeClass("loading");
        currentPage = res.data.currentPage;
        totalPages = res.data.totalPages;
    })
})

function renderUITable(data){
    let html = [];
    html = data.map(item =>{
        let tong = 0;
        item.billProductEntities.forEach(itemChild =>{
            tong += itemChild.amount * itemChild.productEntity.price;
        })
        return `<tr>
                <td>#${item.id.slice(0,5)}</td>
                <td>${item?.createAt}</td>
                <td>${dataUser.name}</td>
                <td>${dataUser.address}</td>
                <td class="info-user-price">
                    ${formatCurrency(tong)} đ
                </td>
                <td>${item.status}</td>
                <td>
                  <a href="/user/bills/${item.id}">Xem chi tiết</a>
                </td>
              </tr>
        `
    }).join("");

    $("#table-info-user-bills").html(html);
}

function handleClickPagenation(e){
    renderPagenation(e.dataset.value)
    $("#table-info-user-bills").empty();
    $("#info-user-loading").addClass("loading")
    getApi(nameUrl+ `?page=${e.dataset.value}`,(res)=>{
        renderUITable(res.data.bills);
        $("#info-user-loading").removeClass("loading");
    })
}

function renderPagenation(currentPage){
   let numberPage = Number(currentPage);
    let html = `<li class="page-item ${numberPage <= 0 ? `disabled`:``}">
                <button onclick="handleClickPagenation(this)" class="page-link" data-value="${numberPage - 1}" href="#" tabindex="-1">Previous</button>
              </li>
              <li class="page-item"><button onclick="handleClickPagenation(this)" class="page-link" data-value="${numberPage}">${numberPage + 1}</button></li>
              <li class="page-item ${numberPage == totalPages - 1 ? `disabled` : `` }">
                <button onclick="handleClickPagenation(this)" class="page-link" data-value="${numberPage + 1}">Next</button>
              </li>`;
    $("#info-user-pagenation").html(html);
}