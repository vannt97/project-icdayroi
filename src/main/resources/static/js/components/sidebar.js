document.querySelectorAll(".sidebar-content .dropdown-edit").forEach((ele)=>{
    ele.addEventListener("click",(e)=>{
        e.currentTarget.classList.toggle("show")
    });
})