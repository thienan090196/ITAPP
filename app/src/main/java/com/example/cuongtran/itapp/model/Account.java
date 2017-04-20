package com.example.cuongtran.itapp.model;

import java.util.Date;

/**
 * Created by an.truong on 12/04/2017.
 */

public class Account {
    private int idUser ;
    private String email;
    private String password;
    private int isActive;
    private String verifyCodeEmail;
    private Date dateCreate;
    private Date dateUpdate;

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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

}
