package com.tatla.ggfitness.model;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    public String fname;
    public String lname;
    public String email;
    public String phone;
    public String address;
    public String zone;
    public String date;
    public String gender;



    public User() {
    }


    public User(String fname, String lname, String email, String phone, String address, String zone, String date) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.zone = zone;
        this.date = date;
        this.gender = gender;}

    public void setId(String id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getZone() {
        return zone;
    }

    public String getDate() {
        return date;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id + "/n" + "fname='" + fname + "/n" + "Lname='" + lname + "/n" +
                "email='" + email + "/n" + "phone='" + phone + "/n" + " address='" + address + "/n" +
                "zone='" + zone + "/n" +
                "date='" + date + "/n" +
                "gender='" + gender + "/n" +
                '}';
    }
}

