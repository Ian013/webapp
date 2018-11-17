package com.epam.training.application.dao.model;

import java.util.Objects;

public class Archive {

    private int id;

    private int note;

    private int teacherId;

    private int courseId;

    private int studentId;

    public Archive(int id, int note, int studentId, int courseId) {
        this.id =id;
        this.courseId =courseId;
        this.studentId =studentId;
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Archive archive = (Archive) o;
        return id == archive.id &&
                note == archive.note &&
                teacherId == archive.teacherId &&
                courseId == archive.courseId &&
                studentId == archive.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note, teacherId, courseId, studentId);
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentID) {
        this.studentId = studentID;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
