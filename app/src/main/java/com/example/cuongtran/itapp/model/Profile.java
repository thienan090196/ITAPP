package com.example.cuongtran.itapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by an.truong on 12/04/2017.
 */

public class Profile implements Serializable{
    private int idProfile;  // id nay tang tu dong
    private int idUser;
    private String name;
    private String dob;
    private int gender;
    private String phone;
    private String job;
    private String city;
    private String avatar;

    public Profile(){}

    public Profile(int idUser, String name, String dob, int gender, String phone, String job, String city, String avatar){
        this.idUser = idUser;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.job = job;
        this.city = city;
        this.avatar = avatar;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
