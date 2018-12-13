$(document).ready(function(){
    $('#myCoursesTable').hide();
    $("#showMyCourses").on('click', function(){
        $('#myCoursesTable').toggle(1000);
        $('#allCoursesTable').hide();
        $('#actualCoursesTable').hide();
        $('#teacherCoursesTable').hide();
    });
});