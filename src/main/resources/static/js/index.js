// Get the button:
let mybutton = document.getElementById("go-to-top");

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        mybutton.style.display = "flex";
    } else {
        mybutton.style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}

function formatCurrency(price){
    return String(price).replace(/\B(?=(\d{3})+(?!\d))/g, '.');
}

function refreshToken(data){
    postApi("refesh-token",JSON.stringify({token: data}),(res)=>{
        localStorage.setItem("dataToken", JSON.stringify(res.data))
    })
}
function executeToken(){
    let dataUser = JSON.parse(localStorage.getItem("user"));
    if(!dataUser) return;
    let dataToken = JSON.parse(localStorage.getItem("dataToken"));
    let refresh_token = dataToken.refeshToken;
    let jwt = dataToken.token;
    let tokens = jwt.split(".");
    let expToken = JSON.parse(window.atob(tokens[1])).exp;
    let tokenNow = new Date(expToken * 1000); //1000 => minisecond
    var timeNow = new Date();
    var timeRemains = Math.floor(
        Math.abs(tokenNow.getTime() - timeNow.getTime()) / (1000 * 60)
    );
    console.log("tokenNow",tokenNow);
    console.log("timeNow ", timeNow);
    console.log("timeRemains ", timeRemains);

    if (timeRemains <= 10) {
        //refresh Token
        console.log("refresh token ", timeRemains);
        refreshToken({token: refresh_token});
        return;
    }
    if (tokenNow.getTime() - timeNow.getTime() < 0) {
        localStorage.setItem("dataToken",null);
        localStorage.setItem("user",null);
    }
}

executeToken();