package com.aquino.mypersonalapp.activities;

import android.app.Application;
import android.content.res.Configuration;

import im.delight.android.languages.Language;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Language.setFromPreference(this, "myPreferenceKey");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Language.setFromPreference(this, "myPreferenceKey");
    }

}
