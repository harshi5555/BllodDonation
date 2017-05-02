package com.example.mapp.blooddonation;

/**
 * Created by harshi on 05/04/17.
 */

public class PlaceInformation {
    private String place;
    private String address;
    private String date;
    private String time;

    public PlaceInformation(){

    }

    public PlaceInformation(String place, String address, String date, String time) {
        this.place = place;
        this.address = address;
        this.date = date;
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
