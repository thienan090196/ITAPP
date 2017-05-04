package com.example.cuongtran.itapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linh.tran1 on 28/04/2017.
 */

public class AccountBuilder {

    public static Account getAccount(JSONObject object){
        Account account = new Account();
        try {
            JSONObject jsonObject = object.getJSONObject("dataObject");
            if(!jsonObject.isNull("profile")){
                JSONObject profileJson =  jsonObject.getJSONObject("profile");
                Profile profile = getProfile(profileJson);
                account.setProfile(profile);
            }else{
                account.setProfile(new Profile());
            }

            int id_user = jsonObject.getInt("id_user");
            String email = jsonObject.getString("email");
            String password = jsonObject.getString("password");
            int isActive = jsonObject.getInt("isActive");
            String verify_code = jsonObject.getString("verify_code");
            String date_create = jsonObject.getString("date_create");
            String date_update = jsonObject.getString("date_update");

            account.setIdUser(id_user);
            account.setEmail(email);
            account.setPassword(password);
            account.setIsActive(isActive);
            account.setVerifyCodeEmail(verify_code);
            account.setDateCreate(date_create);
            account.setDateUpdate(date_update);



            List<Post> posts = getAllPost(jsonObject);
            account.setPosts(posts);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return account;
    }

    private static Profile getProfile(JSONObject object) {
        Profile profile = new Profile();

        try {
            int id_user = object.getInt("id_user");
            String name = object.getString("name");
            int gender = object.getInt("gender");
            String dob = object.getString("dob");
            String phone = object.getString("phone");
            String job = object.getString("job");
            String city = object.getString("city");
            String avatar = object.getString("avatar");

            profile.setIdUser(id_user);
            profile.setName(name);
            profile.setGender(gender);
            profile.setDob(dob);
            profile.setPhone(phone);
            profile.setJob(job);
            profile.setCity(city);
            profile.setAvatar(avatar);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return profile;
    }

    private static List<Post> getAllPost(JSONObject object){
        List<Post> posts = new ArrayList<Post>();
        Profile profile;

        try {

            JSONArray jsonArray = object.getJSONArray("posts");

            if(!object.isNull("profile")){
                JSONObject profileJson =  object.getJSONObject("profile");
                profile = getProfile(profileJson);
                //account.setProfile(profile);
            }else{
                profile = new Profile();
            }

            for(int i = 0; i < jsonArray.length(); i++){
                Post post = new Post();
                JSONObject postJson = jsonArray.getJSONObject(i);
                int id_post = postJson.getInt("id_post");
                String content = postJson.getString("content");
                String date_create = postJson.getString("date_create");
                String date_update = postJson.getString("date_update");
                //JSONArray comments = postJson.getJSONArray("comments");

                post.setIdPost(id_post);
                post.setContent(content);
                post.setDate_create(date_create);
                post.setDate_update(date_update);
                post.setUserName(profile.getName());

                posts.add(post);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return posts;
    }


}
