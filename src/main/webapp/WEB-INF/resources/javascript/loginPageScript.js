jQuery(document).ready(function(){
    $("#showpassword").on('click', function(){
        let pass = $("#password");
        let fieldtype = pass.attr('type');
        if (fieldtype === 'password') {
            pass.attr('type', 'text');
            $(this).text("Hide Password");
        }else{
            pass.attr('type', 'password');
            $(this).text("Show Password");
        }
    });
});