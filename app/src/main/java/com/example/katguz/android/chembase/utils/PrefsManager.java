package com.example.katguz.android.chembase.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Oleg on 2017-07-05.
 */

public class PrefsManager {


    private static final String PREFS_KEY = "com.example.sda.retrofitapp.sharedprefs";
    //private static final String TOKEN_KEY = "token_key";
    private static final String CID_NUMBER_KEY = "cid_number_key";

    private SharedPreferences preferences;

    public PrefsManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
    }

    public void saveUserQuery(String cidNumber) {

            preferences.edit()
                    .putString(CID_NUMBER_KEY, cidNumber)
                    .commit();

    }
    public String getUserCidValueQuery() {
        return preferences.getString(CID_NUMBER_KEY, "");
    }

/*  public void saveToken(String token) {
        preferences.edit()
                .putString(TOKE345N_KEY, token)
                .apply();
    }
    public String getToken() {

        return preferences.getString(TOKEN_KEY, null);
    }*/

}

