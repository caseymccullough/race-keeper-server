package com.mccullough.model;

public class Race {

    private int id;
    private String name;
    private String city;
    private String state_code;
    private double distance;

    public Race() {
    }

    public Race(int id, String name, String city, String state_code, double distance) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state_code = state_code;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState_code() {
        return state_code;
    }

    public double getDistance() {
        return distance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Race{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state_code='" + state_code + '\'' +
                ", distance=" + distance +
                '}';
    }
}