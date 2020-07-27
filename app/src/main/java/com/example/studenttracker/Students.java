package com.example.studenttracker;

public class Students {
    String name;
    String imageUrl;
    String age;
    String assignment;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public Students(String name, String imageUrl, String age, String assignment) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.age = age;
        this.assignment = assignment;
    }

    public Students() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
