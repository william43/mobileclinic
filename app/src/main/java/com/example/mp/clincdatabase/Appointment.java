package com.example.mp.clincdatabase;

/**
 * Created by waboy on 3/12/2018.
 */

public class Appointment {

    private String physician;
    private String date;
    private String time;

    public Appointment(){

    }

    public Appointment(String physician, String date, String time) {
        this.physician = physician;
        this.date = date;
        this.time = time;
    }

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
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
