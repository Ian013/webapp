$(document).ready(function () {
    let actualCourses =$("#actualCoursesTable");
    let teacherCourses =$('#teacherCoursesTable');
    let allCourses=$('#allCoursesTable');
    let studentCourses=$('#myCoursesTable');
    let students = $('#allStudentsTable');

   allCourses.hide();
   students.hide();

    $('#showAllCourses').click(function () {
        allCourses.show(500);
        actualCourses.hide(500);
        teacherCourses.hide(1000);
        studentCourses.hide(1000);
    });
    $('#actualCourses').click(function () {
        actualCourses.toggle(500);
        allCourses.hide(500);
        //teacherCourses.hide(1000);
        //studentCourses.hide(1000);
    });
    $("#showMyCourses").click(function(){
        studentCourses.toggle(500);
        //allCourses.hide(1000);
        //actualCourses.hide(1000);
        //teacherCourses.hide(1000);
    });
    $("#coursesForTeacher").click(function(){
        teacherCourses.toggle(500);
        studentCourses.hide(500);
        //allCourses.hide(1000);
        //actualCourses.hide(1000);
    });
    $('#allCourses').click(function () {
        allCourses.toggle(500);
        //teacherCourses.hide(1000);
        actualCourses.hide(500);
        //studentCourses.hide(1000);
    });
    $('#allStudents').click(function () {
        students.toggle(500);
    });

});
