package com.example.cuongtran.itapp.utils;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Laptop033 on 2/20/2017.
 */

public class Common {

    //constant
    public static final String API_SERVER_IP = "http://192.168.1.6:8080/ERP-PNV/";
    public static final String SERVER_DEPARTMENT_IMAGE_RESOURCE = "http://192.168.1.6:8080/ERP-PNV/resources/deparment-images/";


    public static String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.NO_WRAP);
        return encodedImage;
    }
}
