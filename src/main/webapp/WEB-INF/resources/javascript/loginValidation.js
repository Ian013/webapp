$(document).ready(function () {
    $("#loginForm").validate({
        rules:{
            username:{
                required:true,
                email:true
            },
            password:{
                required:true,
                minlenght:5
            }
        }
    })
});