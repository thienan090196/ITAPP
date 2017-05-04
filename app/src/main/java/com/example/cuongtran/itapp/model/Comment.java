package com.example.cuongtran.itapp.model;

import java.io.Serializable;

/**
 * Created by linh.tran1 on 03/05/2017.
 */

public class Comment implements Serializable{

    private int idComment;
    private int idUser;
    private int imageId;
    private String content;

    public Comment() {
    }

    public Comment(int idUser, int imageId, String content) {
        this.idUser = idUser;
        this.imageId = imageId;
        this.content = content;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }
}
