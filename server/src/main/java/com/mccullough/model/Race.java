package com.mccullough.model;

import java.time.LocalDate;

public class Race {

    private int year;

    private LocalDate date;


    public Race() {
    }

    public Race(int year, LocalDate date) {
        this.year = year;
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Race{" +
                "year=" + year +
                ", date=" + date +
                '}';
    }
}