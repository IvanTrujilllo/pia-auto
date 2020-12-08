package com.example.barbershop.models;

import java.util.Calendar;
import java.util.Date;

public class Appointment {
    private String name, lastname, date;
    private int age;
    private long id;

    public Appointment(String name, String lastname, int age, String date){
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.date = date;
    }

    public Appointment(String name, String lastname, int age, String date, long id) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.date = date;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
