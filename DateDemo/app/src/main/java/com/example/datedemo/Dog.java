package com.example.datedemo;


import java.time.LocalDate;

public class Dog {
    private int id;
    private String dogName;
    private int dogPicDrawable; //this is not the resource name but the drawable int value
    private LocalDate dogDob;

    public int getDogPicDrawable() {
        return dogPicDrawable;
    }

    public void setDogPicDrawable(int dogPicDrawable) {
        this.dogPicDrawable = dogPicDrawable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public LocalDate getDogDob() {
        return dogDob;
    }

    public void setDogDob(LocalDate dogDob) {
        this.dogDob = dogDob;
    }

    public Dog(int id,String dogName,int dogPicDrawable,LocalDate dogDob) {
        this.dogDob = dogDob;
        this.dogPicDrawable = dogPicDrawable;
        this.dogName = dogName;
        this.id = id;
    }
}
