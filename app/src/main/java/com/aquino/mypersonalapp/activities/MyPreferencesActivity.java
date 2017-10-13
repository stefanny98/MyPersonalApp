package com.aquino.mypersonalapp.activities;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.aquino.mypersonalapp.R;
import com.ftinc.scoop.Scoop;

import org.polaric.colorful.Colorful;

import java.io.Serializable;
import java.sql.Wrapper;

import im.delight.android.languages.Language;
import im.delight.android.languages.LanguageList;

import static android.graphics.Color.GREEN;

public class MyPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

    }

    public static class MyPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            String value = sharedPreferences.getString(s, null);

            if("username".equals(s)){

                editor.putString("username", value).apply();
                editor.putString("fullname", value).apply();

            }else if("myPreferenceKey".equals(s)){

            }else if("styles".equals(s)){

                if (value.equals("dark")) {
                    editor.putString("theme", "dark").apply();
                } else if (value.equals("light")) {
                    editor.putString("theme", "light").apply();
                }else{
                    editor.putString("theme", "live").apply();
                }

            }
        }

        @Override
        public void onResume() {
            super.onResume();

            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
            super.onPause();
        }

    }


}
