package com.shagi.poker.myreminder;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shagi on 27.10.2015.
 */
public class PreferenceHelper {

    public static final String SPLASH_IS_INVISIBLE = "splash_is_invisible";

    private static PreferenceHelper instance;

    private Context context;

    private SharedPreferences preferences;

    private PreferenceHelper() {

    }

    public static PreferenceHelper getInstance() {
        if (instance ==null) {
            instance = new PreferenceHelper();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }
}
