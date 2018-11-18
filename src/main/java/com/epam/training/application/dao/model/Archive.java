package com.epam.training.application.dao.model;

import java.util.Objects;

public class Archive {

    private int id;
    private int note;
    private int courseId;
    private int studentId;

    private String teacherLastName;
    private String courseTitle;
    private String studentFirstName;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    private String studentLastName;

    public Archive(int id, int note, String courseTitle,String teacherLastName,String studentFirstName,String studentLastName) {
        this.id =id;
        this.courseTitle =courseTitle;
        this.teacherLastName = teacherLastName;
        this.studentFirstName =studentFirstName;
        this.studentLastName = studentLastName;
        this.note = note;
    }
    public Archive(int id, int note, int studentId,int courseId) {
        this.id =id;
        this.studentId =studentId;
        this.courseId = courseId;
        this.note = note;
    }


    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Archive archive = (Archive) o;
        return id == archive.id &&
                note == archive.note &&
                courseId == archive.courseId &&
                studentId == archive.studentId &&
                Objects.equals(teacherLastName, archive.teacherLastName) &&
                Objects.equals(courseTitle, archive.courseTitle) &&
                Objects.equals(studentFirstName, archive.studentFirstName) &&
                Objects.equals(studentLastName, archive.studentLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note, courseId, studentId, teacherLastName, courseTitle, studentFirstName, studentLastName);
    }
}
