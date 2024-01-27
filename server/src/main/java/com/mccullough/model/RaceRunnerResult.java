package com.mccullough.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class RaceRunnerResult {
    private String first_name;
    private String last_name;

    private String city;

    private String state_code;

    private char gender_code;
    private LocalDate birthday;
    private int raceTimeHours;
    private int raceTimeMinutes;

    public int getRaceTimeHours() {
        return raceTimeHours;
    }

    public void setRaceTimeHours(int raceTimeHours) {
        this.raceTimeHours = raceTimeHours;
    }

    public int getRaceTimeMinutes() {
        return raceTimeMinutes;
    }

    public void setRaceTimeMinutes(int raceTimeMinutes) {
        this.raceTimeMinutes = raceTimeMinutes;
    }

    public double getRaceTimeSeconds() {
        return raceTimeSeconds;
    }

    public void setRaceTimeSeconds(double raceTimeSeconds) {
        this.raceTimeSeconds = raceTimeSeconds;
    }

    private double raceTimeSeconds;

    public RaceRunnerResult() {
    }

    public RaceRunnerResult(String first_name, String last_name, String street, String city, String state_code, char gender_code, LocalDate birthday,
                            int raceTimeHours, int raceTimeMinutes, double raceTimeSeconds) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
        this.state_code = state_code;
        this.gender_code = gender_code;
        this.birthday = birthday;
        this.raceTimeHours = raceTimeHours;
        this.raceTimeMinutes = raceTimeMinutes;
        this.raceTimeSeconds = raceTimeSeconds;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
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

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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


}
