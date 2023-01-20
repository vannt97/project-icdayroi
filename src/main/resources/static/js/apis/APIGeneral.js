function getApi(path,callback){
    $.ajax({
        url: LINK_API + path,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            callback(response);
        },
    }).fail(function (error){
        console.log(error)
    })
}

function postApi(path, data, callback,type = "POST") {
    $.ajax({
        url: LINK_API + path,
        type: type,
        data: data,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            callback(response);
        },
    }).fail(function (error){
        console.log(error)
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Lỗi - vui lòng thử ',
            // footer: '<a href="">Why do I have this issue?</a>'
        })
    })
}