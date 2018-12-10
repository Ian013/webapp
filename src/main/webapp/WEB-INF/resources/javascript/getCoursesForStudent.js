$(document).ready(function(){
    $('#myCoursesTable').hide();
    $("#showMyCourses").on('click', function(){
        var myCourses = $('#myCoursesTable').get();
        if(myCourses.hidden){
            myCourses.show();
        }else{
            myCourses.hide()
        }
    });
});