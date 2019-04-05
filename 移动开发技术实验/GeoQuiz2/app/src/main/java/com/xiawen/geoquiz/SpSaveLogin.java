package com.xiawen.geoquiz;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SpSaveLogin {
    //save username and password
    public static boolean saveUserInfo(Context context,String name, String password){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("username",name);
        edit.putString("pwd",password);
        edit.commit();
        return true;
    }
    //get username and password
    public static Map<String, String > getUserInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String name = sp.getString("username",null); //key
        String password = sp.getString("pwd",null); //key
        Map<String, String > userMap = new HashMap<>();
        userMap.put("name",name);
        userMap.put("password",password);
        return userMap;
    }
}
