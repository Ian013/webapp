function validate(){
    let email = $("#email").val();
    let firstName=$("#firstName").val();
    let lastName=$("#lastName").val();
    let pass = $("#password").val();
    let confirm=$("#password_confirmation").val();
    let mailRegex = /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/;
    let nameRegex=/^[a-zA-Z]+$/i;

    if(pass !== confirm){
        alert("Passwords doesn't match!");
        return false;
    }else if(mailRegex.test(email)===false){
        alert("Invalid email!");
        return false;
    }else if(nameRegex.test(firstName)===false||nameRegex.test(lastName)===false){
        alert("Invalid characters in name field!")
        return false;
    }

}