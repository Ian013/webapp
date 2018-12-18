function validateCourse() {
    let regex = /^[a-zA-Z0-9]+$/i;
    let title = $('#courseTitle').val();

    let startDate=$('#startDate').val();
    let endDate=$('#endDate').val();

    if(regex.test(title)===false){
        alert("Invalid title!");
        return false;
    }
    if(startDate>=endDate){
        alert("Invalid date!");
        return false
    }
}