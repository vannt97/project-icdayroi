let dataUser = JSON.parse(localStorage.getItem("user"));
if(dataUser){
    window.location.href = "/";
}

document.getElementById('form-sign-up').addEventListener('submit', (e) => {
    e.preventDefault();
    const data = Object.fromEntries(new FormData(e.target).entries());
    if(data.password != document.getElementById("form-sign-up-password-confirm").value){
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Lỗi - Mật khẩu không trùng với  ',
            // footer: '<a href="">Why do I have this issue?</a>'
        })
        return;
    }

    if(data.password.length < 6 || document.getElementById("form-sign-up-password-confirm").value.length < 6 ){
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Lỗi - Mật khẩu phải trên 6 ký   ',
            // footer: '<a href="">Why do I have this issue?</a>'
        })
        return;
    }

    postApi('signup', JSON.stringify(data), (res)=>{
        localStorage.setItem("user",JSON.stringify(res.data.user));
        localStorage.setItem("dataToken",JSON.stringify(res.data.dataToken));
        Swal.fire({
            icon: 'success',
            title: 'Success',
            text: 'Đăng ký thành công',
            // footer: '<a href="">Why do I have this issue?</a>'
        })
        setTimeout(()=>{
            window.location.href = "/user";
        },500)
    })
    // Now you can use formData.get('foo'), for example.
    // Don't forget e.preventDefault() if you want to stop normal form .submission
});