$(document).ready(function () {
    $('#actualCoursesTable').hide();
    $('#teacherCoursesTable').hide();
    $('#actualCourses').click(function () {
        $('#actualCoursesTable').toggle(1000);
        $('#allCoursesTable').hide(1000);
        $('#teacherCoursesTable').hide(1000);
        $('#myCoursesTable').hide(1000);
    });
    $("#showMyCourses").click(function(){
        $('#myCoursesTable').toggle(1000);
        $('#allCoursesTable').hide(1000);
        $('#actualCoursesTable').hide(1000);
        $('#teacherCoursesTable').hide(1000);
    });
    $("#coursesForTeacher").click(function(){
        $('#teacherCoursesTable').toggle(1000);
        $('#allCoursesTable').hide(1000);
        $('#actualCoursesTable').hide(1000);
        $('#myCoursesTable').hide(1000);
    });
    $('#allCourses').click(function () {
        $('#allCoursesTable').toggle(1000);
        $('#teacherCoursesTable').hide(1000);
        $('#actualCoursesTable').hide(1000);
        $('#myCoursesTable').hide(1000);
    })
});
