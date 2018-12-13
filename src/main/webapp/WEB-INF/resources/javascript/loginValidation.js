$(document).ready(function () {
    $("#registerForm").validate({
        rules:{
            username:{
                required:true,
                email:true,
                maxlenght:15
            },
            password:{
                required:true,
                minlenght:5,
                maxlenght:15
            }
        }
    })
});