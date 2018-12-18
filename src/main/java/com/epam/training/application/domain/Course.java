package com.epam.training.application.domain;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Course {

    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private User teacher;
    private List<User> users;


    public Course(String name, Date startDate, Date endDate, User teacher) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
    }
    public Course(int id, String name, Date startDate, Date endDate,User teacher) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
    }
    public Course(){}

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

    public User getTeacher() {
        return teacher;
    }
    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id &&
                Objects.equals(name, course.name) &&
                Objects.equals(startDate, course.startDate) &&
                Objects.equals(endDate, course.endDate) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, teacher, users);
    }

    @Override
    public String toString() {
        return "Course {"+
                " name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
