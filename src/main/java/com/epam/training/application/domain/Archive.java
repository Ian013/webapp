package com.epam.training.application.domain;

import java.util.Objects;

public class Archive {

    private int id;
    private int note;
    private Student student;
    private Course course;

    public Archive(int id, int note, Student student,Course course) {
        this.id =id;
        this.student =student;
        this.course = course;
        this.note = note;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
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

}
