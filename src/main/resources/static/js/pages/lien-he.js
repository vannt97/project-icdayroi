
// document.getElementById("submbit-form-lien-he").onclick = (e) =>{
//     var formData = new FormData(document.querySelector('form'))
//
//     console.log(formData)
// }

document.getElementById('form-lien-he').addEventListener('submit', (e) => {
    e.preventDefault();
    const data = Object.fromEntries(new FormData(e.target).entries());
    postApi('contact', JSON.stringify(data), (res)=>{
        alert("Đã gửi thành công")
    })
    // Now you can use formData.get('foo'), for example.
    // Don't forget e.preventDefault() if you want to stop normal form .submission
});