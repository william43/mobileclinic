package com.example.mp.clincdatabase;

import java.util.ArrayList;

/**
 * Created by waboy on 3/8/2018.
 */

public class Users {

    private String Fname;
    private String LName;
    private String password;
    private String username;
    private String userID;
    private ArrayList<Records> records;

    public Users(){

    }

    public Users(String fname, String LName, String password, String username, String userID, ArrayList<Records> records) {
        Fname = fname;
        this.LName = LName;
        this.password = password;
        this.username = username;
        this.userID = userID;
        this.records = records;
    }

    public String getFname(){
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() { return userID; }

    public void setUserID(String userID) { this.userID = userID; }

    public ArrayList<Records> getRecords() { return records; }

    public void setRecords(ArrayList<Records> records) { this.records = records; }

}
