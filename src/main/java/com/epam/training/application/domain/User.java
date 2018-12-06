package com.epam.training.application.domain;


import java.util.List;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private List<Course> courses;
    private String email;
    private String password;
    private List<Role> roles;

    public User() {
    }
    public User(int id, String firstName, String lastName) {
        this.id=id;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public User(String firstName, String lastName){
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
