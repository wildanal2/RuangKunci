package com.keyroom.com.keyroom.Config;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {
    //Share preference
    private SharedPreferences mSharedPreference;
    //Editor for Shared preference
    private SharedPreferences.Editor mEditor;
    //context
    private Context mContext;
    //Shared pref mode
    int PRIVATE_MODE;
    //Shared pref name
     private static final String PREF_NAME = "SharedPreferenceRuangKunci";
     //Shared Preferences Keys
     private static final String IS_LOGIN = "IsLoggedIn";
     public static final String KEY_ID = "id";
     public static final String KEY_LEVEL = "level";
     public static final String KEY_NAMA = "nama";

    public SessionManagement(Context mContext) {
        this.mContext = mContext;
        mSharedPreference = this.mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mSharedPreference.edit();
    }

    public void createLoginSession(String id, String nama,String level){
        // Storing login value as TRUE
         mEditor.putBoolean(IS_LOGIN, true);
         // Storing id
         mEditor.putString(KEY_ID,id );
         // Storing nama
         mEditor.putString(KEY_NAMA,nama );
        // Storing level
        mEditor.putString(KEY_LEVEL,level );
         mEditor.commit();
    }

    public HashMap<String, String> getUserInformation(){

        HashMap<String,String> user = new HashMap<String, String>();
        // user id
        user.put(KEY_ID, mSharedPreference.getString(KEY_ID, null));
        // user nama
        user.put(KEY_NAMA, mSharedPreference.getString(KEY_NAMA, null));
        return user;
    }

    public HashMap<String, String> getLevelInformation(){

        HashMap<String,String> user = new HashMap<String, String>();
        // user id
        user.put(KEY_ID, mSharedPreference.getString(KEY_ID, null));
        // user level
        user.put(KEY_LEVEL, mSharedPreference.getString(KEY_LEVEL, null));
        // return user
        return user;
    }

    public boolean isLoggedIn(){
        return mSharedPreference.getBoolean(IS_LOGIN,false);
    }

    public void logoutUser(){
        mEditor.putBoolean(IS_LOGIN, false);
        mEditor.clear();
        mEditor.commit();
    }
}
