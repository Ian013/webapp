package com.epam.training.application.domain;

public class Archive {

    private int id;
    private int note;
    private User user;
    private Course course;

    public Archive(int id, int note, User user, Course course) {
        this.id =id;
        this.user = user;
        this.course = course;
        this.note = note;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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
