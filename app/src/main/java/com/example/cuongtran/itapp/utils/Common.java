package com.example.cuongtran.itapp.utils;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Common {

    //constant
    public static final String API_SERVER_IP = "http://192.168.103.129:8080/IT-SHARING/";
//    public static final String SERVER_DEPARTMENT_IMAGE_RESOURCE = "http://192.168.103.138:8080/IT-SHARING/resources/deparment-images/";


    public static String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.NO_WRAP);
        return encodedImage;
    }
}
