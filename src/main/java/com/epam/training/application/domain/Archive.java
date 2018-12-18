package com.epam.training.application.domain;

import java.sql.Date;

public class Archive {

    private int id;
    private int note;
    private int studentId;
    private int courseId;
    private Date date;

    public Archive(int id,int note,int studentId,int courseId,Date date){
        this.id = id;
        this.note=note;
        this.studentId=studentId;
        this.courseId=courseId;
        this.date = date;
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

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

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

    @Override
    public String toString() {
        return "Archive{" +
                "id=" + id +
                ", note=" + note +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", date=" + date +
                '}';
    }
}
