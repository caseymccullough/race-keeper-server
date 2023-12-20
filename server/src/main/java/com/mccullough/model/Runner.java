package com.mccullough.model;

import java.time.LocalDate;
import java.time.Period;

public class Runner {

    private int id;
    private String first_name;
    private String last_name;

    private String street;

    private String city;

    private String state_code;

    private char gender_code;

    private LocalDate birthday;


    public Runner() {
    }

    public Runner(int id, String first_name, String last_name, String street, String city, String state_code, char gender_code, LocalDate birthday) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.street = street;
        this.city = city;
        this.state_code = state_code;
        this.gender_code = gender_code;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState_code() {
        return state_code;
    }

    public char getGender_code() {
        return gender_code;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getAge() {
        LocalDate todaysDate = LocalDate.now();
        return Period.between(birthday, todaysDate).getYears();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public void setGender_code(char gender_code) {
        this.gender_code = gender_code;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Runner{" +
                "runner_id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state_code='" + state_code + '\'' +
                ", gender_code=" + gender_code +
                ", birthday=" + birthday +
                ", age today=" + getAge() +
                '}';
    }
}
