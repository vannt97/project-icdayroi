document.getElementById('form-sign-in').addEventListener('submit', (e) => {
    e.preventDefault();

    const data = Object.fromEntries(new FormData(e.target).entries());

    console.log("data",data);
    postApi('signin', JSON.stringify(data), (res)=>{
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
})