package com.epam.training.application.domain;

import java.util.List;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private List<Course> courses;
    //private String email.
    //private String password;

    public Student() {
    }
    public Student(int id,String firstName,String lastName) {
        this.id=id;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public Student(String firstName,String lastName){
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
