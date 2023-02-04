document.getElementById("breadcrumb").style.display = "none";
let _uuid = 0;
let dataUser = JSON.parse(localStorage.getItem("user"))
function getDataFromApi(){
    let dataToken = JSON.parse(localStorage.getItem("dataToken"));
    let url = window.location.href;
    let new_url = url.split("?")[0];
    let uuid  = new_url.split("/").pop();
    _uuid = uuid;
    getApi(`bill/${uuid}`,(res)=>{
        console.log(res.data);
        renderDataBillDetail(res.data);
        $("#bill-detail-loading").removeClass("loading");
    })
}
function renderDataBillDetail(data){
    let tong = 0;
    let htmlItem = data.billProductEntities.map(item =>{
        tong += item.productEntity.price * item.amount;
        return `<tr>
              <th scope="row">${item.productEntity.name}</th>
              <td>${formatCurrency(item.productEntity.price)}đ</td>
              <td>${item.amount}</td>
              <td>${formatCurrency(item.productEntity.price * item.amount)}₫</td>
            </tr>`
    });

    tong+= data.deliveryPrice;

    let html = `
          <div class="col-12">
              <h1>Đơn hàng #${_uuid.slice(0,5)}</h1>
              <p>Ngày tạo — ${data.createAt}</p>
          </div>
        <div class="col-6">
          <h4>Địa chỉ thanh toán</h4>
          <p>Tình trạng thanh toán: ${data.payment}</p>
          <p>${dataUser.name}</p>
          <p>${dataUser.address}</p>
          ${dataUser.phoneNumber == null ? ``:`<p>${dataUser.phoneNumber}</p>`}
        </div>
        <div class="col-6">
          <h4>Địa chỉ giao hàng</h4>
          <p>Tình trạng giao hàng: ${data.status}</p>
          <p>${dataUser.name}</p>
          <p>${dataUser.address}</p>
           ${dataUser.phoneNumber == null ? ``:`<p>${dataUser.phoneNumber}</p>`}
        </div>
        <div class="col-12">
          <table class="table ">
              <thead class="thead-dark">
              <tr>
                <th scope="col">Sản phẩm </th>
                <th scope="col">Giá</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Tổng</th>
              </tr>
              </thead>
            <tbody>
            ${htmlItem}
            </tbody>
            <tfoot>
            <tr class="text-danger">
              <th colspan="3"  scope="row">Chưa thuế</th>
              <td>0₫</td>
            </tr>
            <tr class="text-danger">
              <th colspan="3" >Phí vận chuyển (Nhận hàng tại cửa hàng):</th>
              <td>${formatCurrency(data.deliveryPrice)}₫</td>
            </tr>
            <tr class="text-danger">
              <th colspan="3"  scope="row">Tổng</th>
              <td>${formatCurrency(tong)}₫</td>
            </tr>
            </tfoot>
          </table>
        </div>
        <div class="col-12">
          <a class="btn btn-danger" href="/">Quay lại tài khoản</a>
        </div>
    `
    $("#bill-detail-table").html(html);
}

$(async function (){
    getDataFromApi();
})