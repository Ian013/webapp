$(document).ready(function () {
 $('#actualCoursesTable').hide();
 $('#actualCourses').click(function () {
     $('#actualCoursesTable').toggle(1000);
     $('#allCoursesTable').hide(100);
     $('#teacherCoursesTable').hide(100);
     $('#myCoursesTable').hide(100);
 });
});