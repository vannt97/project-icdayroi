document.getElementById("header-navbar-link-all-product").addEventListener("click",(e)=>{
    const ele_a = document.createElement("a")
    ele_a.setAttribute("href","/products");
    ele_a.click();
    ele_a.remove();
})

// document.querySelectorAll(".header-navbar .dropdown-menu-parent > li").forEach((ele)=>{
//     ele.addEventListener("click", (e) =>{
//         let eleParent = document.querySelector(".dropdown-menu-parent");
//         if(e.currentTarget.classList.contains("dropdown")){
//             // eleParent.style.display = "block";
//         }
//     })
// })

document.querySelectorAll(".nav-link-dropdown").forEach(ele =>{
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


