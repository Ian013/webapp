package com.epam.training.application.domain;

import java.sql.Date;

public class Course {

    private int id;
    private String name;
    private Date startDate;
    private Date endDate;

    private int teacherId;
    private String teacherLastName;
    private String teacherFirstName;
// private Teacher teacher
    //private ArrayList<Student> students;

    public Course(int id, String name, Date startDate, Date endDate, int teacherId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacherId = teacherId;
    }
    public Course(String name, Date startDate, Date endDate, int teacherId) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacherId = teacherId;
    }


    public Course(int id, String name, Date startDate, Date endDate, int teacherId, String teacherLastName,String teacherFirstName) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacherId = teacherId;
        this.teacherFirstName=teacherFirstName;
        this.teacherLastName = teacherLastName;
    }


    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", teacher - " +teacherFirstName+" "+ teacherLastName+
                '}';
    }
}
