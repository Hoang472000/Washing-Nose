package com.android.washingnose;

import android.content.Context;
import android.content.SharedPreferences;

public class NoseSharedPreferences {
    private final String STORAGE = " com.bkav.nose.shared";
    private android.content.SharedPreferences preferences;
    private Context context;


    public NoseSharedPreferences(Context context) {
        this.context = context;
    }

    public void setChecked(boolean isChecked) {
        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("termsPrivacy", isChecked);
        editor.apply();
    }

    public boolean getchecked() {
        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        boolean isCheck = preferences.getBoolean("termsPrivacy", false);

        return isCheck;
    }

    public void setTime(String time, String address) {
        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(address+"String", time);
        editor.apply();
    }

    public String getTime(String address) {
        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        String time = preferences.getString(address+"String", null);
        return time;
    }

    public void setSchedule(Boolean schedule, String address) {
        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(address+"Boolean", schedule);
        editor.apply();
    }

    public Boolean getSchedule(String address) {
        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        Boolean schedule = preferences.getBoolean(address+"Boolean", false);
        return schedule;
    }
}
