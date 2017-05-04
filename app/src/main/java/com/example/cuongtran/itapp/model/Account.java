package com.example.cuongtran.itapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account implements Serializable{
    private int idUser ;
    private String email;
    private String password;
    private int isActive;
    private String verifyCodeEmail;
    private String dateCreate;
    private String dateUpdate;
    private Profile profile;
    private List<Post> posts;

    public Account(){}
    public Account(String email, String password){
        this.email = email;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setVerifyCodeEmail(String verifyCodeEmail) {
        this.verifyCodeEmail = verifyCodeEmail;
    }

    public String getVerifyCodeEmail() {
        return verifyCodeEmail;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        if(posts == null){
            return new ArrayList<Post>();
        }
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
