package com.example.cuongtran.itapp.model;

import com.example.cuongtran.itapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post implements Serializable{

    public static List<Post> listPost = new ArrayList<Post>();

    public static List<Post> getListPost(){
        listPost.add(new Post(R.drawable.friends, "cuong","chao ban"));
        listPost.add(new Post(R.drawable.info,"an","chao ban hien"));
        return listPost;
    }

    private int idPost;
    private String date_create;
    private String date_update;
    private int imageId;
    private String userName;
    private String content;

    public Post() {
    }

    public Post(int imageId, String userName, String content){
        this.imageId = imageId;
        this.userName = userName;
        this.content = content;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void setListPost(List<Post> listPost) {
        Post.listPost = listPost;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getDate_update() {
        return date_update;
    }

    public void setDate_update(String date_update) {
        this.date_update = date_update;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }
}
