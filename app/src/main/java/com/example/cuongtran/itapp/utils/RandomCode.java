package com.example.cuongtran.itapp.utils;

import java.util.Random;

/**
 * Created by an.truong on 12/04/2017.
 */

public class RandomCode {

    public static String ramdomCodeToVerify(){
       Random rd = new Random();
        int MIN = 100000;
        int MAX = 1000000;
        int result = rd.nextInt( MAX - MIN) + MIN;
        String code = Integer.toString(result);
        return code;
    }
}
