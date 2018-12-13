$(document).ready(function(){
    $('#teacherCoursesTable').hide();
    $("#coursesForTeacher").on('click', function(){
        $('#teacherCoursesTable').toggle(1000);
        $('#allCoursesTable').hide();
        $('#actualCoursesTable').hide();

    });
});