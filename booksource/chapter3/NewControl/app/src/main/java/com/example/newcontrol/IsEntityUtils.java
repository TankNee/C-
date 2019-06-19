package com.example.newcontrol;

public class IsEntityUtils {
    public static boolean isEntityString(String entity){
        if (entity == null || entity.length() == 0 || entity.equals(""))
            return true;
        return false;
    }
}
